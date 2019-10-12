package com.example.safehopper.models;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class RouteDeserializer implements JsonDeserializer<Route> {


    @Override
    public Route deserialize(JsonElement json, Type Route, JsonDeserializationContext context) throws JsonParseException {
        //JsonObject
        JsonObject jsonObject = json.getAsJsonObject();

        Log.d("JSON-OBJECT", jsonObject.toString());

        Gson gson = new Gson();
        Route route = gson.fromJson(jsonObject.get("route"),Route.class);

        Log.d("ROUTE-OBJECT", jsonObject.get("latitude").toString());

        return route;
    }
}
