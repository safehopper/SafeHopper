package com.example.safehopper.ui.createAccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;
import com.example.safehopper.api_package.API;
import com.example.safehopper.api_package.Requests;

public class CreateAccountFragment extends Fragment {

    private CreateAccountViewModel createAccountViewModel;
    private EditText email;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private EditText phone;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        createAccountViewModel =
                ViewModelProviders.of(this).get(CreateAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create_account, container, false);

        email = root.findViewById(R.id.createAccountEmail);
        password = root.findViewById(R.id.createAccountPassword);
        firstName = root.findViewById(R.id.createAccountFirst);
        lastName = root.findViewById(R.id.createAccountLast);
        phone = root.findViewById(R.id.createAccountPhone);

        createAccountButtonListener(root);
        return root;
    }

    public void createAccountButtonListener(View v){
        final Button createAccountButton = v.findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API api = Requests.getAPI();
                Requests.createUser(api, getActivity(), password.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), phone.getText().toString(), email.getText().toString());
            }
        });
    }
}