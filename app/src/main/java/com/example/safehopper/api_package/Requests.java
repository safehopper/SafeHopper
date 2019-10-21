package com.example.safehopper.api_package;

import com.example.safehopper.models.Contact;
import com.example.safehopper.models.Route;
import com.example.safehopper.models.RouteDeserializer;
import com.example.safehopper.repositories.ContactsRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, String> body = new HashMap<String, String>();
        body.put("key", serverKey);
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("password", password);
        body.put("phone", phone);

        Call<ResponseBody> call = api.signUpUser(body);

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

//                      MAKE CALL TO SETUP REPO HERE

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

    private static void setupAPI() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(API.class);
    }
}