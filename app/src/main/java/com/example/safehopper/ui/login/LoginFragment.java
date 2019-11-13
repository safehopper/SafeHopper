package com.example.safehopper.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;
import com.example.safehopper.SessionActivity;
import com.example.safehopper.ui.FragmentManager;
import com.example.safehopper.ui.createAccount.CreateAccountFragment;
import com.example.safehopper.ui.routes.RoutesFragment;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private Button loginButton, createAccountButton;
    private EditText loginEmail, loginPassword;
    private MutableLiveData<Boolean> loggedIn, unsucessfulRequest, errorOnLogin;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        //  Hide Action Bar
        // ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        setLoginSubscriptions();
        setEditTexts(root);
        setCreateAccountOnClick(root);
        setLoginButtonOnClick(root);

        // This saves where we want to go
        Log.d("MANAGER","This should be true: " + FragmentManager.getInstance().getGoToRoute());

        swtichView();

        return root;
    }

    private void swtichView(){
        Log.d("MANAGER-LOGIN","This should be true: " + FragmentManager.getInstance().getGoToRoute());
        if(FragmentManager.getInstance().getGoToRoute()) {

            FragmentManager.getInstance().setGoToRoutes(false);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, new RoutesFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void setEditTexts(View root) {
        loginEmail = root.findViewById(R.id.loginEmailEditText);
        loginPassword = root.findViewById(R.id.loginPasswordEditText);
    }

    private void setLoginButtonOnClick(View root){
        loginButton = root.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.loginUser(loginEmail.getText().toString(), loginPassword.getText().toString());
            }
        });
    }

    private void setCreateAccountOnClick(View root){
        createAccountButton = root.findViewById(R.id.loginCreateAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Add a navigation class to handle navigating between fragments better
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, new CreateAccountFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    private void setLoginSubscriptions() {
        loggedIn = loginViewModel.getLoggedIn();
        unsucessfulRequest = loginViewModel.getUnsuccessfulRequest();
        errorOnLogin = loginViewModel.getErrorOnLogin();

        loggedIn.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean == true){
//                    Toast.makeText(getContext(), "Logged In", Toast.LENGTH_SHORT).show();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.nav_host_fragment, new HomepageFragment());
//                    transaction.addToBackStack(null);
//                    transaction.commit();
                    // SessionsActivity will take the place of homepage fragment
                    Intent menuIntent = new Intent(getActivity(), SessionActivity.class);
                    startActivity(menuIntent);
                }
            }
        });

        unsucessfulRequest.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean == true){
                    Toast.makeText(getContext(), "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        errorOnLogin.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean == true){
                    Toast.makeText(getContext(), "Error on login attempt", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}