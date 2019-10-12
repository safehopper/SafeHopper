package com.example.safehopper.models;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;

public class Route {

    private String name;
    private String distance;    //in miles
    private String imageURL;
    private List<LatLng> route = new ArrayList<>();

    public Route(){

    }

    public Route(String newName, String newDistace,
                 String newImage, List<LatLng> waypoints){

        name = newName;
        distance = newDistace;
        imageURL = newImage;
        route = waypoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<LatLng> getRoute(){
        return route;
    }

    public void addPoint(LatLng point){

        route.add(point);
        turnToJson();
    }

    public void removeLastPoint(){
        if(route.size() != 0)
            route.remove(route.size()-1);
    }

    public JsonObject turnToJson(){

        List<LatLng> waypoints = new ArrayList<>();

        waypoints.add(new LatLng(343,343));
        waypoints.add(new LatLng(243,3434));
        waypoints.add(new LatLng(3434,34343));

//      Route test = new Route(name, distance, imageURL, route);

        Gson routeObj = new Gson();
        String json = routeObj.toJson(new Route(name, distance, imageURL, route));
        Log.d("JSON",json);

        try {
            GsonBuilder gbuilder = new GsonBuilder();
            gbuilder.registerTypeAdapter(Route.class, new RouteDeserializer());
            Gson customGson = gbuilder.create();

            Route route = customGson.fromJson(json,Route.class);

            Log.d("ROUTE-STUFF", route.toString());

        }catch(Exception e){
            Log.d("error",e.toString());
        }
        return null;
    }

    @Override
    public String toString(){
        String s = "name: " + name + " distance: " + distance + " imageURL: "
                + imageURL + " route: " + route.toString();

        return s;
    }
}
