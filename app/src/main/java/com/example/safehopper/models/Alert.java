package com.example.safehopper.models;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Alert {

    private String email;
    private String distance;    //in feet
    private List<LatLng> waypoints = new ArrayList<>();
    private String alertID;

    public Alert(String newEmail){
        email = newEmail;
        alertID = UUID.randomUUID().toString();
    }

    public Alert(String newEmail, String newDistace,List<LatLng> routeWaypoints, String newAlertID){

        email = newEmail;
        distance = newDistace;
        waypoints = routeWaypoints;
        alertID = newAlertID;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<LatLng> getRouteWaypoints(){
        return waypoints;
    }

    public void addPoint(LatLng point){

        waypoints.add(point);
    }

    public String turnToJson(){

        Gson routeObj = new Gson();
        String json = routeObj.toJson(new Alert(email, distance, waypoints, alertID));
        Log.d("method: turnToJson-->Json of object",json);

        try {
            GsonBuilder gbuilder = new GsonBuilder();
            gbuilder.registerTypeAdapter(Alert.class, new RouteDeserializer());

            Gson customGson = gbuilder.create();

            Alert route = customGson.fromJson(json, Alert.class);

            Log.d("JSON-ROUTE-STUFF-TURN_TO_JSON-PART-2", route.toString());

        }catch(Exception e){
            Log.e("error",e.toString());
        }
        return json;
    }

    @Override
    public String toString(){
        return " distance: " + distance + " routeWaypoints: " + waypoints.toString() + "routeID: " + alertID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public void setUUID(String newAlertID) {

        alertID = newAlertID;
    }

    public String getUUID() {
        return this.alertID;
    }
}