package com.example.safehopper.ui.createAccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;

public class CreateAccountFragment extends Fragment {

    private CreateAccountViewModel createAccountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        createAccountViewModel =
                ViewModelProviders.of(this).get(CreateAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create_account, container, false);
        return root;
    }
}