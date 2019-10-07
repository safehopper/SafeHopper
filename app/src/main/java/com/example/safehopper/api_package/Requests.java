package com.example.safehopper.api_package;

import android.os.Bundle;
import android.widget.Toast;

import com.example.safehopper.R;
import com.example.safehopper.models.Contact;
import com.example.safehopper.models.Route;
import com.example.safehopper.models.User;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

    }

    protected void createUser(API api, User user)
    {
        Call<ResponseBody> call = api.createUser(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Create User Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not create user.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void createUser(API api, String password, String firstname, String lastname, String phone, String email, String key)
    {
        Call<ResponseBody> call = api.createUser(password, firstname, lastname, phone, email, key);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Create User Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not create user.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void modifyUser(API api, String id, String password, String firstname, String lastname, String phone, String email, String key)
    {
        Call<ResponseBody> call = api.modifyUser(id, password, firstname, lastname, phone, email, key);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Modify User Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not modify user.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void deleteUser(API api, String userID)
    {
        Call<ResponseBody> call = api.deleteUser(userID);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Delete User Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not delete user.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void confirmUser(API api, String email, String mfa, String key)
    {
        Call<ResponseBody> call = api.confirmUser(email, mfa, key);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Confirm User Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not confirm user.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void authenticateUser(API api, String email, String password, String key)
    {
        Call<ResponseBody> call = api.authenticateUser(email, password, key);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Authenticate User Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not authenticate user.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void getRoutes(API api, String email)
    {
        Call<List<Route>> call = api.getRoutes(email);

        call.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                List<Route> routes = response.body();
                Toast.makeText(getApplicationContext(), "Get Routes Successful.", Toast.LENGTH_SHORT).show();
                // what to do with routes list
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not retrieve routes.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void createRoute(API api, String name, String dist, String url)
    {
        Call<ResponseBody> call = api.createRoute(name, dist, url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Create Route Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not create route.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void modifyRoute(API api, String id, String name, String dist, String url)
    {
        Call<ResponseBody> call = api.modifyRoute(id, name, dist, url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Modify Route Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not modify route.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void deleteRoute(API api, String routeID)
    {
        Call<ResponseBody> call = api.deleteRoute(routeID);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Delete Route Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not delete route.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void getContacts(API api, String email)
    {
        Call<List<Contact>> call = api.getContacts(email);

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
                Toast.makeText(getApplicationContext(), "Get Contacts Successful.", Toast.LENGTH_SHORT).show();
                // what to do with list of contacts
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not get contacts.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void createContact(API api, String first, String last, String phone, String email, boolean text, boolean emailalert)
    {
        Call<ResponseBody> call = api.createContact(first, last, phone, email, text, emailalert);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Create Contact Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not create contact.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void modifyContact(API api, String id, String first, String last, String phone, String email, boolean text, boolean emailalert)
    {
        Call<ResponseBody> call = api.modifyContact(id, first, last, phone, email, text, emailalert);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Modify Contact Successful.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not modify contact.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void deleteContact(API api, String id)
    {
        Call<ResponseBody> call = api.deleteContact(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Delete Contact Successful.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: Could not delete contact.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
