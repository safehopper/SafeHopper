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

        JsonElement jsonElement = jsonObject.get("waypoints");
        JsonArray jsonArrayOfWaypoints = jsonElement.getAsJsonArray();

        for(int i = 0; i < jsonArrayOfWaypoints.size();i++){
            JsonElement jsonEle = jsonArrayOfWaypoints.get(i);
            String str = jsonEle.getAsString();
            int indexOfPara = str.indexOf('(');
            int indexOfComma = str.indexOf(',');
            int indexOfClose = str.indexOf(')');
            wayPoints.add(new LatLng(Double.parseDouble(str.substring(indexOfPara+1, indexOfComma)), Double.parseDouble(str.substring(indexOfComma+1, indexOfClose))));
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