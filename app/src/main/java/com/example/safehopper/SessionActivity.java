package com.example.safehopper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.Alert;
import com.example.safehopper.models.Route;
import com.example.safehopper.repositories.RoutesRepository;
import com.example.safehopper.repositories.UserRepository;
import com.example.safehopper.ui.FragmentManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.PolyUtil;
import com.google.maps.android.SphericalUtil;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.safehopper.R.id.session_map;
import static com.example.safehopper.R.id.start_alert;
import static com.example.safehopper.R.id.stop_tracking;


public class SessionActivity extends AppCompatActivity implements
        OnMapReadyCallback, GoogleMap.OnPolylineClickListener, GoogleMap.OnMyLocationButtonClickListener, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnMyLocationClickListener {

    private static final float  MIN_DISTANCE =    0;
    private static final long   MIN_TIME     = 2000;
    private static final int    TOLERANCE    =  100;


    // with or without route
    // if this is true change the the buttons on the screen to alert and stop tracking
    private boolean sessionWithRoute = false;

    // Instance Variables
    private Route route = new Route();
    private Alert pathTaken = new Alert(UserRepository.getInstance().getUser().getValue().getEmail());

    private boolean tracking = false;
    // Time outside of route
    private long timeOutSideOfRouteInit;

    // Send Alerts
    private boolean firstTimeOutOfBoundry = true;
    private boolean sendAlert = false;


    private Polyline polyline1;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private Location currentLocation;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Context context = this;
    private boolean isOnpath = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_activity);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(session_map);
        mapFragment.getMapAsync(this);

        makeAPICall();

        sessionAndAlertButtonListener();

        stopTrackingButtonListener();

        locationManagerStuff();

        getCurrentLocation();

        if(getIntent().getStringExtra("RouteID") != null){

            String id = getIntent().getStringExtra("RouteID");
            Log.d("RouteID",id);
            route = RoutesRepository.getInstance().getRoute(id);

            sessionWithRoute = true;
            routeBoilerPlateScaffolding();
        }
    }

    private void routeBoilerPlateScaffolding(){
        final Button left = findViewById(start_alert);
        final Button right = findViewById(stop_tracking);

        Toast toast = Toast.makeText(context, "Session Started", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        
        getSupportActionBar().setTitle(route.getName().replaceAll("\"","") + " " + route.getDistance().replaceAll("\"",""));

        left.setText("SEND ALERT");
        right.setText("STOP TRACKING");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Default location and zoom level for the map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.765925, -118.127058), 15));

        //adds polyline to the map
        polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .addAll(route.getRouteWaypoints()));//Getting the points from the Route Class

        polyline1.setTag("Set the tag of the polyline to what the user names the polyline in the future?");

        setRouteColor();

        // Set listeners for click events.
        mMap.setOnPolylineClickListener(this); //maybe use this to print out the name of the route they are on and what distance
        //mMap.setOnMapClickListener(this);

        //for location data
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        // getCurrentLocation();

    }

    public void makeAPICall(){
        Log.d("EMAIL", UserRepository.getInstance().getUser().getValue().getEmail());
        Requests.getRoutes(UserRepository.getInstance().getUser().getValue().getEmail());
        Requests.getContacts(UserRepository.getInstance().getUser().getValue().getEmail());
    }

    private void setRouteColor(){
        if(isOnpath) polyline1.setColor(Color.GREEN);
        else polyline1.setColor(Color.RED);
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        Toast.makeText(this, "RouteName: " + route.getName()
                + " Distance: " + route.getDistance(), Toast.LENGTH_LONG).show();
    }

    private void sessionAndAlertButtonListener() {
        final Button sessionAndAlert = findViewById(R.id.start_alert);
        sessionAndAlert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String buttonText = sessionAndAlert.getText().toString();

                // Checks user clicked send alert button
                if(buttonText.compareToIgnoreCase("SEND ALERT") == 0) {
                    sendAlert = true;
                }
                //checks if user clicked start session button
                else {
                    if(buttonText.compareToIgnoreCase("START SESSION") == 0) {
                        // Let user know a session has started
                        Toast toast = Toast.makeText(context, "Session Started", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                        sessionWithRoute = false;
                        sessionAndAlert.setText("SEND ALERT");

                        final Button b = findViewById(stop_tracking);
                        b.setText("STOP TRACKING");
                    }
                }
            }
        });
    }

    private void stopTrackingButtonListener() {
        final Button stopTracking = findViewById(R.id.stop_tracking);
        stopTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("stopTrackingButtonListener", "Button was pressed");
                String buttonText = stopTracking.getText().toString();

                if(buttonText.compareToIgnoreCase("START SESSION WITH ROUTE") == 0){

                    Intent i = new Intent(SessionActivity.this, MainActivity.class);
                    FragmentManager.getInstance().setGoToRoutes(true);
                    startActivity(i);

                    // TODO may have to most this to when the route gets loaded
                    final Button b = findViewById(start_alert);
                    b.setText("SEND ALERT");
                }

                // Stop tracking.
                if(buttonText.compareToIgnoreCase("STOP TRACKING") == 0){

                    tracking = false;
                    stopTracking.setBackgroundResource(R.drawable.button_standard_red);
                    stopTracking.setText("RESUME TRACKING");

                }
                //Resumes tracking
                else{
                    tracking = true;

                    stopTracking.setBackgroundResource(R.drawable.button_standard);
                    stopTracking.setText("STOP TRACKING");
                }
            }
        });
    }


    // might not need in this class
    private String findDistace(List<LatLng> path) {
        // gets the length of the path in meters and converts to feet.
        return String.valueOf(SphericalUtil.computeLength(path) * 3.28084);
    }


    @Override
    public boolean onMyLocationButtonClick() {
        // Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        Log.d("onMyLocationButtonClick", "button was clicked");

        getCurrentLocation();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    // Runs once, do you really need it.
    private void getCurrentLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            currentLocation = location;
                            // Logic to handle location object
                            Log.d("CURRENT-LOCATION", location.getLatitude() + " : " + location.getLongitude());
                            isOnpath = PolyUtil.isLocationOnPath(new LatLng(location.getLatitude(), location.getLongitude()), polyline1.getPoints(), true, 10);
                            Log.d("CURRENT-LOCATION", Boolean.toString(isOnpath));

                            // Updates the actual path taken
                            pathTaken.addPoint(new LatLng(location.getLatitude(),location.getLongitude()));
                            pathTaken.setDistance(findDistace(pathTaken.getRouteWaypoints()));


                            if(currentLocation != null){
                               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()), 18));
                            }
                        }
                    }
                });
    }
// You should probably delete this soon 11/9/19
//    private void createLocationRequest() {
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setInterval(1000);
//        locationRequest.setFastestInterval(500);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    }

    // This keeps getting run
    private void locationManagerStuff() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                currentLocation = location;
                isOnpath = PolyUtil.isLocationOnPath(new LatLng(location.getLatitude(), location.getLongitude()), polyline1.getPoints(), true, TOLERANCE);

                if(tracking) pathTaken.addPoint(new LatLng(location.getLatitude(),location.getLongitude()));

                setRouteColor();
                // Logic for sending alerts or not.
                if(sendAlert) {
                        // TODO: Make send alert request
                    Log.d("ALERT",pathTaken.turnToJson());

                    //
                    getWindow().setStatusBarColor(ContextCompat.getColor(context,R.color.actionBarRed));
                    //
                    getSupportActionBar().setTitle("SENDING ALERTS");
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context,R.color.redButton)));

                }else{
                    if(sessionWithRoute) {
                        // Checks if it's the users first time outside of the route
                        if (!isOnpath && firstTimeOutOfBoundry) {
                            timeOutSideOfRouteInit = System.currentTimeMillis();
                            firstTimeOutOfBoundry = false;
                            // Checks how long the user has been outside of the route.
                        } else if (!isOnpath && !firstTimeOutOfBoundry && tracking) {
                            long difference = System.currentTimeMillis() / 1000L - timeOutSideOfRouteInit / 1000L;
                            Log.d("ALERT time outside of boundary", String.valueOf(difference));
                            if (difference > 10) sendAlert = true;
                        } else {
                            if (isOnpath) firstTimeOutOfBoundry = true;
                        }
                    }
                }

                Log.d("CURRENT-LOCATION-LM", location.getLatitude() + " : " + location.getLongitude());
                Log.d("CURRENT-LOCATION-LM", Boolean.toString(isOnpath));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET
            }, 10);
            return;
        }
        locationManager.requestLocationUpdates("gps", MIN_TIME, MIN_DISTANCE, locationListener);
    }




    // Confirmation callback, only used to change screens
    private Callback<ResponseBody> setUpDialogCallback() {
        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("CALLBACK MAIN", "SUCCESSFUL");

                    Intent menuIntent = new Intent(SessionActivity.this, MainActivity.class);
                    FragmentManager.getInstance().setGoToRoutes(true);
                    startActivity(menuIntent);

                } else {
                    Log.d("CALLBACK MAIN", "UN-SUCCESSFUL");
                    Toast.makeText(context, "Confirmation Failed", Toast.LENGTH_SHORT).show();
                    displayConfirmation();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show();
                displayConfirmation();
            }
        };
        return callback;
    }

    public void displayConfirmation() {
        String text = route.getEmail();
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }
}




