package com.example.safehopper.repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.User;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository
{
    private static UserRepository instance;
    private static MutableLiveData<User> user;

    public static UserRepository getInstance()
    {
        if(instance == null)
        {
            instance = new UserRepository();
            user = new MutableLiveData<>();
        }
        return instance;
    }

    public void setUser(User u) {
        user.setValue(u);
        Log.d("USER SET", u.toString());
    }

    public MutableLiveData<User> getUser() {
        return user;
    }
}