package com.example.safehopper.models;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Route {

    private String route_name;
    private String email;
    private String distance;    //in feet
    private String image_url;
    private List<LatLng> waypoints = new ArrayList<>();
    private String route_id;

    public Route(){
    }

    public Route(String newName, String newEmail, String newDistace,
                 String newImage, List<LatLng> routeWaypoints, String newRouteID){

        route_name = newName;
        email = newEmail;
        distance = newDistace;
        image_url = newImage;
        waypoints = routeWaypoints;
        route_id = newRouteID;
    }

    public String getName() {
        return route_name;
    }

    public void setName(String name) {
        this.route_name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImageURL() {
        return image_url;
    }

    public void setImageURL(String imageURL) {
        this.image_url = imageURL;
    }

    public List<LatLng> getRouteWaypoints(){
        return waypoints;
    }

    public void addPoint(LatLng point){

        waypoints.add(point);
        turnToJson();
    }

    public void removeLastPoint(){
        if(waypoints.size() != 0)
            waypoints.remove(waypoints.size()-1);
    }

    public String turnToJson(){

        Gson routeObj = new Gson();
        String json = routeObj.toJson(new Route(route_name, email, distance, image_url, waypoints, route_id));
        Log.d("method: turnToJson-->Json of object",json);

        try {
            GsonBuilder gbuilder = new GsonBuilder();
            gbuilder.registerTypeAdapter(Route.class, new RouteDeserializer());

            Gson customGson = gbuilder.create();

            Route route = customGson.fromJson(json,Route.class);

            Log.d("JSON-ROUTE-STUFF-TURN_TO_JSON-PART-2", route.toString());

        }catch(Exception e){
            Log.e("error",e.toString());
        }
        return json;
    }

    @Override
    public String toString(){
        return "name: " + route_name + " distance: " + distance + " imageURL: "
                + image_url + " routeWaypoints: " + waypoints.toString() + " routeID: " + route_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setRouteID(){
        route_id = UUID.randomUUID().toString();
    }

    public String getRouteID(){
        return route_id;
    }

    public void saveRoute(){

    }
}