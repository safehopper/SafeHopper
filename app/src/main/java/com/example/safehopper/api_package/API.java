package com.example.safehopper.api_package;

import com.example.safehopper.models.Contact;
import com.example.safehopper.models.Route;
import com.example.safehopper.models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    String BASE_URL = "https://safe-hopper-server.herokuapp.com/";

    @POST("/user/")
    Call<ResponseBody> createUser(@Field("password") String passwordValue,
                                  @Field("firstName") String firstnameValue,
                                  @Field("lastName") String lastnameValue,
                                  @Field("phone") String phoneValue,
                                  @Field("email") String emailValue,
                                  @Field("key") String keyValue);

    @POST("/user/")
    Call<ResponseBody> createUser(@Body User user);

    @PUT("/user/{userID}")
    Call<ResponseBody> modifyUser(@Path("userID") String userIDValue,
                                  @Field("email") String emailValue,
                                  @Field("password") String passwordValue,
                                  @Field("firstName") String firstnameValue,
                                  @Field("lastName") String lastnameValue,
                                  @Field("phone") String phoneValue,
                                  @Field("key") String keyValue);

    @DELETE("/user/{userID}")
    Call<ResponseBody> deleteUser(@Path("userID") String UserIDValue);

    @POST("/user/confirm")
    Call<ResponseBody> confirmUser(@Field("email") String emailValue,
                                   @Field("mfaCode") String mfaCodeValue,
                                   @Field("key") String keyValue);

    @POST("/user/authenticate")
    Call<ResponseBody> authenticateUser(@Field("email") String emailValue,
                                        @Field("password") String passwordValue,
                                        @Field("key") String keyValue);

    @GET("/routes/{email}")
    Call<List<Route>>  getRoutes(@Path("email") String emailValue);

    @POST("/routes/")
    Call<ResponseBody> createRoute(@Field("name") String nameValue,
                                   @Field("distance") String distanceValue,
                                   @Field("imageURL") String imageURLValue);

    @PUT("/routes/{routeID}")
    Call<ResponseBody> modifyRoute(@Path("routeID") String routeIDValue,
                                   @Field("name") String nameValue,
                                   @Field("distance") String distanceValue,
                                   @Field("imageURL") String imageURLValue);

    @DELETE("/routes/{routeID}")
    Call<ResponseBody> deleteRoute(@Path("routeID") String routeIDValue);

    @GET("/contacts/{email}")
    Call<List<Contact>>  getContacts(@Path("email") String emailValue);

    @POST("/contacts/")
    Call<ResponseBody> createContact(@Field("firstName") String firstNameValue,
                                     @Field("lastName") String lastNameValue,
                                     @Field("phoneNumber") String phoneNumberValue,
                                     @Field("email") String email,
                                     @Field("textAlert") boolean textAlertValue,
                                     @Field("emailAlert") boolean emailAlertValue);

    @PUT("/contacts/{contactID}")
    Call<ResponseBody> modifyContact(@Path("contactID") String contactID,
                                     @Field("firstName") String firstNameValue,
                                     @Field("lastName") String lastNameValue,
                                     @Field("phoneNumber") String phoneNumberValue,
                                     @Field("email") String email,
                                     @Field("textAlert") boolean textAlertValue,
                                     @Field("emailAlert") boolean emailAlertValue);

    @DELETE("/contacts/{contactID}")
    Call<ResponseBody> deleteContact(@Path("contactID") String contactIDValue);
}
