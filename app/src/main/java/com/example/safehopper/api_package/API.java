package com.example.safehopper.api_package;

import com.example.safehopper.models.Contact;
import com.example.safehopper.models.Route;
import com.example.safehopper.models.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    String BASE_URL = "https://safe-hopper-server.herokuapp.com/";

    @FormUrlEncoded
    @POST("/user/")
    Call<User> createUser(@Field("password") String password,
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
    @GET("/routes/")
    Call<List<Route>>  getRoutes(@Field("key") String key,
                                 @Field("userEmail") String userEmail);

    @FormUrlEncoded
    @POST("/routes/")
    Call<ResponseBody> createRoute(@Field("key") String key,
                                   @Field("userEmail") String userEmail,
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
    @GET("/contacts/{email}")
    Call<List<Contact>> getContacts(@Path("email") String emailValue,
                                    @Field("key") String key);

    @FormUrlEncoded
    @POST("/contacts/")
    Call<ResponseBody> createContact(@Field("key") String key,
                                     @Field("firstName") String firstNameValue,
                                     @Field("lastName") String lastNameValue,
                                     @Field("phoneNumber") String phoneNumberValue,
                                     @Field("email") String email,
                                     @Field("textAlert") boolean textAlertValue,
                                     @Field("emailAlert") boolean emailAlertValue);

    @FormUrlEncoded
    @PUT("/contacts/")
    Call<ResponseBody> modifyContact(@Field("key") String key,
                                     @Field("firstName") String firstNameValue,
                                     @Field("lastName") String lastNameValue,
                                     @Field("phoneNumber") String phoneNumberValue,
                                     @Field("email") String email,
                                     @Field("textAlert") boolean textAlertValue,
                                     @Field("emailAlert") boolean emailAlertValue);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/contacts/{contactID}", hasBody = true)
    Call<ResponseBody> deleteContact(@Path("contactID") String contactIDValue,
                                     @Field("key") String key);
}
