package com.example.safehopper.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.safehopper.R;
import com.example.safehopper.api_package.API;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private API api;
    private Button apiTester;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        //final TextView textView = root.findViewById(R.id.text_tools);
//        loginViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        apiTester = root.findViewById(R.id.loginButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        apiTester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //--------USER---------
                // testing create user
                //Requests.createUser(api, getContext(), "Password1!", "Mariel", "Traj", "+15621231111", "marieltraj@gmail.com");

                // testing confirm user
                //Requests.confirmUser(api, getContext(), "marieltraj@gmail.com", "557365");

                // testing modify user
                //Requests.modifyUser(api, getContext(), "Mariel", "T", "+15551231234", "marieltraj@gmail.com", "Password1!");

                // testing authenticate user
                //Requests.authenticateUser(api, getContext(), "marieltraj@gmail.com", "Password1!");

                // testing deleting user
                //Requests.deleteUser(api, getContext(), "marieltraj@gmail.com", "Password1!");

                //-------ROUTES--------
                // testing create route
                //Requests.createRoute(api, getContext(), "marieltraj@gmail.com", "{route id}");

                // testing get routes
                //Requests.getRoutes(api, getContext(), "marieltraj@gmail.com");

                // testing modify route
                //Requests.modifyRoute(api, getContext(), "marieltraj@gmail.com", "{route id}");

                // testing delete route
                //Requests.deleteRoute(api, getContext(), "marieltraj@gmail.com", "{route id}");
            }
        });

        return root;
    }
}