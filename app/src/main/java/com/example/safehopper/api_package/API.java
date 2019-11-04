package com.example.safehopper.api_package;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

public interface API {
    @POST("contacts/getcontacts")
    Call<ResponseBody> getContacts(@Body Map<String, String> body);

    @POST("contacts")
    Call<ResponseBody> deleteContact(@Body Map<String, String> body);

    @POST("user")
    Call<ResponseBody> signUpUser(@Body Map<String, String> body);

    @POST("routes/getroutes")
    Call<ResponseBody> getRoutes(@Body Map<String, String> body);

    @POST("user/confirm")
    Call<ResponseBody> confirmUser(@Body Map<String, String> body);

    @POST("user/authenticate")
    Call<ResponseBody> loginUser(@Body Map<String, String> body);

    @POST("routes")
    Call<ResponseBody> addRoute(@Body Map<String, Object> body);
}