package com.example.safehopper.models;

public class Route {

    private String name;
    private String distance;    //in miles
    private String imageURL;

    public Route(String name, String distance, String imageURL) {
        this.name = name;
        this.distance = distance;
        this.imageURL = imageURL;
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
}
