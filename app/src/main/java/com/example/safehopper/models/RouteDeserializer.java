package com.example.safehopper.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RouteDeserializer implements JsonDeserializer<Route> {


    @Override
    public Route deserialize(JsonElement json, Type Route, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        List<LatLng> wayPoints = new ArrayList<>();

        JsonElement jsonElement = jsonObject.get("routeWaypoints");
        JsonArray jsonArrayOfWaypoints = jsonElement.getAsJsonArray();

        for(int i = 0; i < jsonArrayOfWaypoints.size();i++){
            double lat = Double.parseDouble(jsonArrayOfWaypoints.get(i).toString().substring(12,21));
            double lng = Double.parseDouble(jsonArrayOfWaypoints.get(i).toString().substring(43,50));
            wayPoints.add(new LatLng(lat,lng));
        }

        Route r = new Route(
                jsonObject.get("name").toString(),
                jsonObject.get("email").toString(),
                jsonObject.get("distance").toString(),
                jsonObject.get("imageURL").toString(),
                wayPoints);

        return r;
    }
}

//Prints the jsonObject Received, for testing
       // Log.d("JSON-OBJECT", jsonObject.toString());

// For Testing
//        Log.d("JSON-ROUTE-OBJECT-DESERIALIZER", r.toString());
//                Log.d("JSON-ROUTE-OBJECT-DESERIALIZER - as array", jsonArrayOfWaypoints.toString());