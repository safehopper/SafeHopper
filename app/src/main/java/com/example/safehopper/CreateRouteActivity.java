package com.example.testingtwoactivites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.List;

import static com.example.testingtwoactivites.R.id.buttonPanel;
import static com.example.testingtwoactivites.R.id.map;

public class SecondActivity extends AppCompatActivity implements
        OnMapReadyCallback, GoogleMap.OnPolygonClickListener, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnMapClickListener{

    private AppBarConfiguration mAppBarConfiguration;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final PatternItem DOT = new Dot();
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);
    private Route route = new Route();
    private Polyline polyline1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        final Button undo = findViewById(R.id.undo_point);
        undo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                route.removeLastPoint();
                refreshPolyline();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //adds polylines to the map
        polyline1 = googleMap.addPolyline(new PolylineOptions()
        .clickable(true)
        .addAll(route.getRoute()));//Getting the points from the Route Class

        polyline1.setTag("B");
        stylePolyLine(polyline1);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.771365, -118.120892), 15));



        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
        googleMap.setOnMapClickListener(this);
    }

    private void stylePolyLine(Polyline polyline){
        String type = "";

        //Get the data object stored with the polyline.
        if(polyline.getTag() != null){
            type = polyline.getTag().toString();
        }

        switch (type){
            case "A":
                // Use a custom bitmap as the cap at the start o the line
                polyline.setStartCap(new CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone),10));
                break;

            case "B":
                //Use a round cap at the start of the line
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(Color.RED);
        polyline.setJointType(JointType.ROUND);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void oldStuff(){
        //        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
//                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void onPolylineClick(Polyline polyline) {
        //Flips polyline from solid background to dotted pattern
        if((polyline.getPattern() == null)||(!polyline.getPattern().contains(DOT))){
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        }
        else{
            //default pattern
            polyline.setPattern(null);
        }
        Toast.makeText(this,"Route Type" + polyline.getTag().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        route.addPoint(latLng);
        refreshPolyline();
        Log.d("THIS IS THE THING THEY CLICKED", latLng.toString());
    }
    
    public void refreshPolyline(){
        polyline1.setPoints(route.getRoute());
    }
}
