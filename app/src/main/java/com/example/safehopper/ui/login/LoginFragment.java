package com.example.safehopper.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;
import com.example.safehopper.api_package.API;
import com.example.safehopper.api_package.Requests;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

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

        api = Requests.getAPI();

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

                List<LatLng> test = new ArrayList<>();
                test.add(new LatLng(33.7719616191341,-118.12443405389787));
                test.add(new LatLng(33.77717622536079,-118.12429357320069));

                Requests.createRoute(api, getContext(), "andrewdelgado017@gmail.com", "Andrews Route 2", "100", "andrew.com",test,"a83468c0-2630-488f-8db0-c2e3b6358340");

                // testing get routes
                //Requests.getRoutes(api, getContext(), "z400jt618@gmail.com");

                // testing modify route
                //Requests.modifyRoute(api, getContext(), "z400jt618@gmail.com", "1f82279e-b163-4998-bc39-c90f867ad9c4");

                // testing delete route
                //Requests.deleteRoute(api, getContext(), "z400jt618@gmail.com", "1f82279e-b163-4998-bc39-c90f867ad9c4");

                //-------CONTACTS--------
                // testing create contact
                //Requests.createContact(api, getContext(), "John", "Smith", "+12341231234", "john@gmail.com", true, true, "z400jt618@gmail.com");

                // testing get contacts
                //Requests.getContacts(api, getContext(), "z400jt618@gmail.com");

                // testing modify contacts
                //Requests.modifyContact(api, getContext(), "John", "Smith", "+12341231234", "john@gmail.com", true, false, "z400jt618@gmail.com");

                // testing delete contacts
                //Requests.deleteContact(api, getContext(), "john@gmail.com", "z400jt618@gmail.com");
            }
        });

        return root;
    }
}