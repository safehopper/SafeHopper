package com.example.safehopper.models;

import android.graphics.Point;
import java.util.ArrayList;
import java.util.List;

public class Alert
{
    private String userEmail;
    private List<Point> waypoints = new ArrayList<>();

    public Alert(String userEmail, List<Point> waypoints) {
        this.userEmail = userEmail;
        this.waypoints = waypoints;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Point> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Point> waypoints) {
        this.waypoints = waypoints;
    }
}
