package com.example.safehopper.api_package;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface API {
    @POST("contacts/getcontacts")
    Call<ResponseBody> getContacts(@Body Map<String, String> body);

    @HTTP(method = "DELETE", path = "contacts", hasBody = true)
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

    @PUT("routes")
    Call<ResponseBody> modifyRoute(@Body Map<String, Object> body);

    @PUT("user")
    Call<ResponseBody> modifyUser(@Body Map<String, String> body);

    @POST("contacts")
    Call<ResponseBody> addContact(@Body Map<String, String> body);

    @PUT("contacts")
    Call<ResponseBody> modifyContact(@Body Map<String, String> body);
}