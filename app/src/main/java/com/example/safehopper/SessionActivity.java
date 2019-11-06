package com.example.safehopper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.safehopper.models.Alert;
import com.example.safehopper.models.Route;
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


public class SessionActivity extends AppCompatActivity implements
        OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnMapClickListener, GoogleMap.OnMyLocationButtonClickListener, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnMyLocationClickListener {

    private static final float MIN_DISTANCE = 0;
    private static final long MIN_TIME = 2000;
    private static final int TOLERANCE = 100;


    // Instance Variables
    private Route route = new Route();
    private Alert pathTaken = new Alert(UserRepository.getInstance().getUser().getValue().getEmail());


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

        startSessionButtonListener();

        sessionWithRouteButtonListener();

        locationManagerStuff();

        getCurrentLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Default location and zoom level for the map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.765925, -118.127058), 15));

                route.addPoint(new LatLng(33.7721     , -118.12093));
                route.addPoint(new LatLng(33.77087    , -118.12093));
                route.addPoint(new LatLng(33.77006    , -118.12093));
                route.addPoint(new LatLng(33.77      , -118.12093));
                route.addPoint(new LatLng(33.76988	, -118.12094));
                route.addPoint(new LatLng(33.76985	, -118.12094));
                route.addPoint(new LatLng(33.76988	, -118.12094));
                route.addPoint(new LatLng(33.76987	, -118.12105));
                route.addPoint(new LatLng(33.76987	, -118.12124));
                route.addPoint(new LatLng(33.76987	, -118.12199));
                route.addPoint(new LatLng(33.76987	, -118.12256));
                route.addPoint(new LatLng(33.76987	, -118.12308));
                route.addPoint(new LatLng(33.76987	, -118.12317));
                route.addPoint(new LatLng(33.76987	, -118.12363));
                route.addPoint(new LatLng(33.76987	, -118.12417));
                route.addPoint(new LatLng(33.76981	, -118.12417));
                route.addPoint(new LatLng(33.76945	, -118.12417));

        //adds polyline to the map
        polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .addAll(route.getRouteWaypoints()));//Getting the points from the Route Class
        // I want to add a marker so the user knows where they initially pressed.
        // This code only shows a marker after two points.
        // Fix this in the future.
        // No justin, I will not remove this unused code. WE NEED IT IN THE FUTURE.

        //        polyline1.setStartCap(
        //                new CustomCap(
        //                        BitmapDescriptorFactory.defaultMarker(), 10));

        polyline1.setTag("Set the tag of the polyline to what the user names the polyline in the future?");

        setRouteColor();

        // Set listeners for click events.
        //mMap.setOnPolylineClickListener(this);
        //mMap.setOnMapClickListener(this);

        //for location data
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        // getCurrentLocation();

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
        Toast.makeText(this, "RouteName: " + polyline.getTag().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        route.addPoint(latLng);
        route.setDistance(findDistace(route.getRouteWaypoints()));
        route.setImageURL("VeryCool.jpeg");
        route.setEmail(UserRepository.getInstance().getUser().getValue().getEmail());
        route.setRouteID();
        refreshPolyline();
    }

    private void refreshPolyline() {
        polyline1.setPoints(route.getRouteWaypoints());
    }

    private void startSessionButtonListener() {
        final Button undo = findViewById(R.id.start_session);
        undo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Start Session without route
            }
        });
    }

    private void routeModeToggle() {
        final Switch s = findViewById(R.id.switch2);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //make the route snap to roads
                //make the routes not snap to roads
            }
        });
    }

    private String findDistace(List<LatLng> path) {
        // gets the length of the path in meters and converts to feet.
        return String.valueOf(SphericalUtil.computeLength(path) * 3.28084);
    }


    @Override
    public boolean onMyLocationButtonClick() {
        // Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
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

    // Run once
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
                               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()), 15));
                            }
                        }
                    }
                });
    }

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

                isOnpath = PolyUtil.isLocationOnPath(new LatLng(location.getLatitude(), location.getLongitude()), polyline1.getPoints(), true, TOLERANCE);
                pathTaken.addPoint(new LatLng(location.getLatitude(),location.getLongitude()));


                setRouteColor();

                if(sendAlert) {
                        // TODO: Make send alert request
                    Log.d("ALERT",pathTaken.turnToJson());

                }else{
                    // Checks if it's the users first time outside of the route
                    if (!isOnpath && firstTimeOutOfBoundry) {
                        timeOutSideOfRouteInit = System.currentTimeMillis();
                        firstTimeOutOfBoundry = false;
                        // Checks how long the user has been outside of the route.
                    } else if (!isOnpath && !firstTimeOutOfBoundry) {
                        long difference = System.currentTimeMillis()/1000L - timeOutSideOfRouteInit/1000L;
                        Log.d("ALERT", String.valueOf(difference));
                        if (difference > 10) sendAlert = true;
                    } else {
                        if (isOnpath) firstTimeOutOfBoundry = true;
                    }
                }
                // TODO: figure out logic for deciding if an alert should go off.

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


    private void sessionWithRouteButtonListener() {
        final Button saveFinish = findViewById(R.id.start_session_with_route);
        saveFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CREATE ROUTE", "IS THIS EVEN RUNNING");
                // Start Session With Route
            }
        });
    }

    // Confirmation callback, only used to change screens
    private Callback<ResponseBody> setUpDialogCallback() {
        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("CALLBACK MAIN", "SUCCESSFUL");

                    Intent menuIntent = new Intent(SessionActivity.this, MainActivity.class);
                    FragmentManager.getInstance().setGoToRoute(true);
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




