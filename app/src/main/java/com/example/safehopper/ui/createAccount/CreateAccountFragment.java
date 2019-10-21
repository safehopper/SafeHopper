package com.example.safehopper.ui.createAccount;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;
import com.example.safehopper.ui.dialogs.SafeHopperDiags;
import com.example.safehopper.ui.login.LoginFragment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        observeCreateUser();
        createAccountButtonListener(root);
        return root;
    }

    public void createAccountButtonListener(View v){
        final Button createAccountButton = v.findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccountViewModel.signUpUser(getContext(), email.getText().toString(), password.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), phone.getText().toString());
                Dialog confirmDialog = SafeHopperDiags.getConfirmationDialog(getContext(), email.getText().toString(), setUpDialogCallback());
                confirmDialog.show();
            }
        });
    }

    private void observeCreateUser() {
        createAccountViewModel.getUserCreated().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean created) {
                if(created == true) {
                    Toast.makeText(getContext(), "Check your email for the confimation code.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void displayConfirmation() {
        String text = email.getText().toString();
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

    }

    // Confirmation callback, only used to change screens
    private Callback<ResponseBody> setUpDialogCallback() {
        Callback<ResponseBody> callback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // Still need to check for actual success here, right now just runs based on receiving a response
                    Toast.makeText(getContext(), "Successfully Confirmed", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, new LoginFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    Toast.makeText(getContext(), "Confirmation Failed", Toast.LENGTH_SHORT).show();
                    displayConfirmation();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                displayConfirmation();
            }
        };
        return callback;
    }

}