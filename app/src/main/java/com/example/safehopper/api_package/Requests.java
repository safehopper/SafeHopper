package com.example.safehopper.api_package;

import android.util.Log;
import android.widget.Toast;

import com.example.safehopper.models.Contact;
import com.example.safehopper.models.Route;
import com.example.safehopper.models.RouteDeserializer;
import com.example.safehopper.repositories.ContactsRepository;
import com.example.safehopper.repositories.RoutesRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class Requests {
    private static Retrofit retrofit;
    private static API api;
    private static String serverKey = "pxC3bE5Wzm7dWy2eaF5p";
    private static final String BASE_URL = "https://safe-hopper-server.herokuapp.com/";

    public static void getContacts(String email) {
        setupAPI();

        // Build body of request
        Map<String, String> body = new HashMap<String, String>();
        body.put("userEmail", email);
        body.put("key", serverKey);

        Call<ResponseBody> call = api.getContacts(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        JsonParser parser = new JsonParser();
                        JsonObject json = parser.parse(response.body().string()).getAsJsonObject();
                        JsonObject content = json.getAsJsonObject("content");
                        JsonArray contacts = content.getAsJsonArray("contacts");
                        List<Contact> contactsList = new ArrayList<Contact>();
                        for (int i = 0; i < contacts.size(); i++) {
                            contactsList.add(new Contact(contacts.get(i).getAsJsonObject()));
                        }

                        // MAKE CALL TO SETUP REPO HERE
                        ContactsRepository.getInstance().setContacts(contactsList);

                    } else {
                    }
                } catch (IOException e) {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public static Call<ResponseBody> signUpUser(String email, String password, String firstName, String lastName, String phone){
        setupAPI();

        // Build body of request
        Map<String, String> body = new HashMap<>();
        body.put("key", serverKey);
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("password", password);
        body.put("phone", phone);

        return api.signUpUser(body);
    }

    public static Call<ResponseBody> loginUser(String email, String password) {
        setupAPI();

        Map<String, String> body = new HashMap<>();
        body.put("key", serverKey);
        body.put("email", email);
        body.put("password", password);

        return api.loginUser(body);
    }

    public static Call<ResponseBody> confirmUser(String email, String mfaCode) {
        setupAPI();

        // Build body of request
        Map<String, String> body = new HashMap<String, String>();
        body.put("key", serverKey);
        body.put("email", email);
        body.put("mfaCode", mfaCode);

        Call<ResponseBody> call = api.confirmUser(body);

        return call;
    }

    public static void getRoutes(String email) {
        setupAPI();

        // Build body of request
        Map<String, String> body = new HashMap<String, String>();
        body.put("userEmail", email);
        body.put("key", serverKey);

        Call<ResponseBody> call = api.getRoutes(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        JsonParser parser = new JsonParser();
                        JsonObject json = parser.parse(response.body().string()).getAsJsonObject();
                        JsonObject content = json.getAsJsonObject("content");
                        JsonArray routes = content.getAsJsonArray("routes");

                        GsonBuilder gbuilder = new GsonBuilder();
                        gbuilder.registerTypeAdapter(Route.class, new RouteDeserializer());
                        Gson customGson = gbuilder.create();

                        List<Route> routeList = new ArrayList<Route>();
                        for (int i = 0; i < routes.size(); i++) {
                            routeList.add(customGson.fromJson(routes.get(i),Route.class));
                        }

                        RoutesRepository.getInstance().setRoutes(routeList);

                    } else {
                    }
                } catch (IOException e) {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public Call<ResponseBody> addRoute(Route r) {
        setupAPI();
        String json = new Gson().toJson(r);

        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(json);

            jsonObj.put("key", serverKey);

            Map<String, Object> map = new HashMap<>();

            map.put("key", serverKey);
            map.put("distance", jsonObj.get("distance"));
            map.put("email", jsonObj.get("email"));
            map.put("image_url", jsonObj.get("image_url"));
            map.put("route_id", jsonObj.get("route_id"));
            map.put("waypoints", jsonObj.get("waypoints"));
            map.put("route_name", jsonObj.get("route_name"));

            Log.d("REQUEST", map.toString());


            return api.addRoute(map);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static void setupAPI() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(API.class);
    }
}