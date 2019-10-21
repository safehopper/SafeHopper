package com.example.safehopper.ui.createAccount;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.safehopper.api_package.Requests;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountViewModel extends ViewModel {
    private MutableLiveData<Boolean> userCreated;

    private MutableLiveData<String> mText;
    private Context mContext;

    public CreateAccountViewModel() {
        userCreated = new MutableLiveData<>();
        userCreated.setValue(false);
        mText = new MutableLiveData<>();
        mText.setValue("This is createAccount fragment");
    }

    public MutableLiveData<Boolean> getUserCreated() {
        return userCreated;
    }

    public void signUpUser(Context context, String email, String password, String firstName, String lastName, String phone) {
        mContext = context;
        Call<ResponseBody> call = Requests.signUpUser(email, password, firstName, lastName, phone);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if (response.isSuccessful()) {
                        JsonParser parser = new JsonParser();
                        JsonObject json = parser.parse(response.body().string()).getAsJsonObject();
                        userCreated.setValue(true);
                    } else {
                        Log.e("ERROR", "ERROR IN CREATE ACCOUNT VM Line 53");
                    }
                } catch (IOException e) {
                    Log.e("ERROR", "ERROR IN CREATE ACCOUNT VM Line 55");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "FAIL", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<String> getText() {
        return mText;
    }
}