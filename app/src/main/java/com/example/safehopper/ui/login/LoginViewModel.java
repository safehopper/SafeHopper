package com.example.safehopper.ui.login;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.User;
import com.example.safehopper.models.UserDeserializer;
import com.example.safehopper.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> loggedIn, errorOnLogin, unsuccessfulRequest;
    private UserRepository userRepo;

    public LoginViewModel() {
        userRepo = UserRepository.getInstance();
        loggedIn = new MutableLiveData<>();
        errorOnLogin = new MutableLiveData<>();
        unsuccessfulRequest = new MutableLiveData<>();
        loggedIn.setValue(false);
        errorOnLogin.setValue(false);
        unsuccessfulRequest.setValue(false);
    }

    public MutableLiveData<Boolean> getLoggedIn() {
        return loggedIn;
    }

    public MutableLiveData<Boolean> getErrorOnLogin() {
        return errorOnLogin;
    }

    public MutableLiveData<Boolean> getUnsuccessfulRequest() {
        return unsuccessfulRequest;
    }

    public void loginUser(String email, String password) {
        Requests.loginUser(email, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {

                    GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapter(User.class, new UserDeserializer());
                    Gson customGson = gsonBuilder.create();
                    try {
                        UserRepository.getInstance().setUser(customGson.fromJson(response.body().string(), User.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    loggedIn.setValue(true);
                }else{
                    unsuccessfulRequest.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    errorOnLogin.setValue(true);
            }
        });
    }
}