package com.example.safehopper.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private String name;
    private String distance;    //in miles
    private String imageURL;
    private List<LatLng> route = new ArrayList<>();

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
    }

    public void removeLastPoint(){
        if(route.size() != 0)
            route.remove(route.size()-1);}
}
