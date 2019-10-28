package com.example.safehopper.models;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject idToken = jsonObject.get("idToken").getAsJsonObject();
        JsonObject payload = idToken.get("payload").getAsJsonObject();

        String firstName = payload.get("given_name").toString().replace("\"", "");
        String lastName = payload.get("family_name").toString().replace("\"", "");
        String email = payload.get("email").toString().replace("\"", "");
        String phone = payload.get("phone_number").toString().replace("\"", "");

        return new User(firstName, lastName, phone, email, "NULL");

    }
}
