package com.example.safehopper.api_package;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.safehopper.R;
import com.example.safehopper.models.Contact;
import com.example.safehopper.models.Route;
import com.example.safehopper.models.RouteDeserializer;
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
    private static Context mContext;
    private static Retrofit retrofit;
    private static API api;
    private static final String BASE_URL = "https://safe-hopper-server.herokuapp.com/";

    public static void getContacts(Context context, String email) {
        setupAPI(context);

        // Build body of request
        Map<String, String> body = new HashMap<String, String>();
        body.put("userEmail", email);
        body.put("key", context.getString(R.string.server_api_key));

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

                    } else {
                        Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void signUpUser(Context context, String email, String password, String firstName, String lastName, String phone){
        setupAPI(context);

        // Build body of request
        Map<String, String> body = new HashMap<String, String>();
        body.put("key", context.getString(R.string.server_api_key));
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("password", password);
        body.put("phone", phone);

        Call<ResponseBody> call = api.signUpUser(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        JsonParser parser = new JsonParser();
                        JsonObject json = parser.parse(response.body().string()).getAsJsonObject();

                        Toast.makeText(mContext, json.get("username") + " was created.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "FAIL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void getRoutes(Context context, String email) {
        setupAPI(context);

        // Build body of request
        Map<String, String> body = new HashMap<String, String>();
        body.put("userEmail", email);
        body.put("key", context.getString(R.string.server_api_key));

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
//
//                        // MAKE CALL TO SETUP REPO HERE

                    } else {
                        Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void setupAPI(Context context) {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(API.class);

        mContext = context;
    }
}