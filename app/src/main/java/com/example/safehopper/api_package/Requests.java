package com.example.safehopper.api_package;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.safehopper.R;
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

    public static API api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

    }

    public static void createUser(API api, final Context context, String password, String firstname, String lastname, String phone, String email)
    {
        Call<User> call = api.createUser(password, firstname, lastname, phone, email, context.getString(R.string.server_api_key));


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(context, "Create user: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Error: Could not create user.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void modifyUser(API api, final Context context, String firstname, String lastname, String phone, String email, String password)
    {
        Call<ResponseBody> call = api.modifyUser(email, password, firstname, lastname, phone, context.getString(R.string.server_api_key));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Modify user: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not modify user.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void deleteUser(API api, final Context context, String email, String password)
    {
        Call<ResponseBody> call = api.deleteUser(email, password, context.getString(R.string.server_api_key));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Delete user: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not delete user.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void confirmUser(API api, final Context context, String email, String mfa)
    {
        Call<ResponseBody> call = api.confirmUser(email, mfa, context.getString(R.string.server_api_key));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Confirm user: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not confirm user.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void authenticateUser(API api, final Context context, String email, String password)
    {
        Call<ResponseBody> call = api.authenticateUser(email, password, context.getString(R.string.server_api_key));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Authenticate user: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not authenticate user.", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public static void getRoutes(API api, final Context context, String email)
    {
        Call<List<Route>> call = api.getRoutes(context.getString(R.string.server_api_key), email);

        call.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                List<Route> routes = response.body();
                Toast.makeText(context, "Get Routes: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                Toast.makeText(context, "Error: Could not retrieve routes.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void createRoute(API api, final Context context, String email, String routeId)
    {
        Call<ResponseBody> call = api.createRoute(context.getString(R.string.server_api_key), email, routeId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Create Route: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not create route.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void modifyRoute(API api, final Context context, String email, String rId)
    {
        Call<ResponseBody> call = api.modifyRoute(context.getString(R.string.server_api_key), email, rId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Modify Route: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not modify route.", Toast.LENGTH_SHORT).show();
        }
        });
    }

    public static void deleteRoute(API api, final Context context, String email, String rId)
    {
        Call<ResponseBody> call = api.deleteRoute(context.getString(R.string.server_api_key), email, rId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Delete Route: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not delete route.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    public static void getContacts(API api, final Context context, String email)
    {
        Call<List<Contact>> call = api.getContacts(email, context.getString(R.string.server-api-key));

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
                Toast.makeText(context, "Get Contacts: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(context, "Error: Could not get contacts.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void createContact(API api, final Context context, String first, String last, String phone, String email, boolean text, boolean emailalert)
    {
        Call<ResponseBody> call = api.createContact(context.getString(R.string.server_api_key), first, last, phone, email, text, emailalert);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Create Contact: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not create contact.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void modifyContact(API api, final Context context, String id, String first, String last, String phone, String email, boolean text, boolean emailalert)
    {
        Call<ResponseBody> call = api.modifyContact(context.getString(R.string.server_api_key), first, last, phone, email, text, emailalert);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Modify Contact: " + response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not modify contact.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void deleteContact(API api, final Context context, String id)
    {
        Call<ResponseBody> call = api.deleteContact(id, context.getString(R.string.server_api_key));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(context, "Delete Contact: " + response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error: Could not delete contact.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    */
}
