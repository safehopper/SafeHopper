package com.example.safehopper.api_package;

import com.example.safehopper.models.Contact;
import com.example.safehopper.models.Route;
import com.example.safehopper.models.User;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface API {

    String BASE_URL = "https://safe-hopper-server.herokuapp.com/";

    @FormUrlEncoded
    @POST("/user/")
    Call<ResponseBody> createUser(@Field("password") String password,
                          @Field("firstName") String firstName,
                          @Field("lastName") String lastName,
                          @Field("phone") String phone,
                          @Field("email") String email,
                          @Field("key") String key);


    @FormUrlEncoded
    @PUT("/user/")
    Call<ResponseBody> modifyUser(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("firstName") String firstName,
                                  @Field("lastName") String lastName,
                                  @Field("phone") String phone,
                                  @Field("key") String key);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/user/", hasBody = true)
    Call<ResponseBody> deleteUser(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("key") String key);

    @FormUrlEncoded
    @POST("/user/confirm")
    Call<ResponseBody> confirmUser(@Field("email") String email,
                                   @Field("mfaCode") String mfaCode,
                                   @Field("key") String key);

    @FormUrlEncoded
    @POST("/user/authenticate")
    Call<ResponseBody> authenticateUser(@Field("email") String email,
                                        @Field("password") String password,
                                        @Field("key") String key);

    @FormUrlEncoded
    @POST("/routes/getroutes/")
    Call<Route>  getRoutes(@Field("key") String key,
                                 @Field("userEmail") String userEmail);

    @FormUrlEncoded
    @POST("/routes/")
    Call<ResponseBody> createRoute(@Field("key") String key,
                                   @Field("userEmail") String userEmail,
                                   @Field("name") String name,
                                   @Field("distance") String distance,
                                   @Field("imageURL") String imageURL,
                                   @Field("routeWaypoints") List<LatLng> routeWaypoints,
                                   @Field("routeId") String routeId);


    @FormUrlEncoded
    @PUT("/routes/")
    Call<ResponseBody> modifyRoute(@Field("key") String nameValue,
                                   @Field("userEmail") String userEmail,
                                   @Field("routeId") String routeId);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/routes/", hasBody = true)
    Call<ResponseBody> deleteRoute(@Field("key") String nameValue,
                                   @Field("userEmail") String userEmail,
                                   @Field("routeId") String routeId);

    @FormUrlEncoded
    @POST("/contacts/getcontacts/")
    Call<ResponseBody> getContacts(@Field("key") String key,
                                    @Field("userEmail") String userEmail);


    @FormUrlEncoded
    @POST("/contacts/")
    Call<Contact> createContact(@Field("key") String key,
                                     @Field("firstName") String firstName,
                                     @Field("lastName") String lastName,
                                     @Field("phone") String phone,
                                     @Field("email") String email,
                                     @Field("textAlert") boolean textAlert,
                                     @Field("emailAlert") boolean emailAlert,
                                     @Field("contactOf") String contactOf);

    @FormUrlEncoded
    @PUT("/contacts/")
    Call<ResponseBody> modifyContact(@Field("key") String key,
                                     @Field("firstName") String firstName,
                                     @Field("lastName") String lastName,
                                     @Field("phone") String phoneNumber,
                                     @Field("email") String email,
                                     @Field("textAlert") boolean textAlert,
                                     @Field("emailAlert") boolean emailAlert,
                                     @Field("contactOf") String contactOf);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/contacts/", hasBody = true)
    Call<ResponseBody> deleteContact(@Field("key") String key,
                                     @Field("email") String email,
                                     @Field("contactOf") String contactOf);
}
