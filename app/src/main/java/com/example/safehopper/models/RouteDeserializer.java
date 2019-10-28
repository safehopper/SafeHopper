package com.example.safehopper.models;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RouteDeserializer implements JsonDeserializer<Route> {


    @Override
    public Route deserialize(JsonElement json, Type Route, JsonDeserializationContext context) throws JsonParseException {
        Log.d("JSONELEMENT", json.toString());
        JsonObject jsonObject = json.getAsJsonObject();
        List<LatLng> wayPoints = new ArrayList<>();

        JsonObject jsonElement = jsonObject.get("waypoints").getAsJsonObject();
        JsonArray jsonArrayOfWaypoints = jsonElement.get("values").getAsJsonArray();

        for(int i = 0; i < jsonArrayOfWaypoints.size();i++){
            JsonObject values = jsonArrayOfWaypoints.get(i).getAsJsonObject();
            JsonObject valuePairs = values.get("nameValuePairs").getAsJsonObject();
            double lat = valuePairs.get("latitude").getAsDouble();
            double lon = valuePairs.get("longitude").getAsDouble();
            wayPoints.add(new LatLng(lat, lon));
        }

        Route r = new Route(
                jsonObject.get("route_name").toString(),
                jsonObject.get("user_email").toString(),
                jsonObject.get("distance").toString(),
                jsonObject.get("image_url").toString(),
                wayPoints,
                jsonObject.get("route_id").toString());
        return r;
    }
}

//Prints the jsonObject Received, for testing
       // Log.d("JSON-OBJECT", jsonObject.toString());

// For Testing
//        Log.d("JSON-ROUTE-OBJECT-DESERIALIZER", r.toString());
//                Log.d("JSON-ROUTE-OBJECT-DESERIALIZER - as array", jsonArrayOfWaypoints.toString());