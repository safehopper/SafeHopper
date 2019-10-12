package com.example.safehopper;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.safehopper.models.Route;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.util.List;

import static com.example.safehopper.R.id.map;

public class CreateRouteActivity extends AppCompatActivity implements
        OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnMapClickListener {

    // Instance Variables
    private Route route = new Route();
    private Polyline polyline1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_route);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        undoButtonListener();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Default location and zoom level for the map
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.771365, -118.120892), 15));

        //adds polyline to the map
        polyline1 = googleMap.addPolyline(new PolylineOptions()
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

        polyline1.setColor(Color.RED);

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnMapClickListener(this);
    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        Toast.makeText(this, "RouteName: " + polyline.getTag().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        route.addPoint(latLng);
        /////////////////////////////////// for testing
        route.turnToJson();
        route.setName("First Route");
        route.setDistance(findDistace(route.getRouteWaypoints()));
        route.setImageURL("VeryCool.jpeg");
        Log.d("JSON-CreateRouteActivity",route.toString());
        /////////////////////////////////// Can delete when done.
        refreshPolyline();
    }

    private void refreshPolyline() {
        polyline1.setPoints(route.getRouteWaypoints());
    }

    private void undoButtonListener() {
        final Button undo = findViewById(R.id.undo_point);
        undo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                route.removeLastPoint();
                refreshPolyline();
            }
        });
    }

    private void routeModeToggle(){
        final Switch s = findViewById(R.id.switch2);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //make the route snap to roads
                //make the routes not snap to roads
            }
        });
    }

    private String findDistace(List<LatLng> path){
        // gets the length of the path in meters and converts to feet.
       return String.valueOf(SphericalUtil.computeLength(path) * 3.28084);
    }
}
