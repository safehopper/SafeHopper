package com.example.safehopper.ui.confirmation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.safehopper.R;

public class ConfirmationFragment extends Fragment {

    Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirmation, container, false);

        setUpButton(v);

        return v;
    }

    private void setUpButton(View v){
        submitButton = v.findViewById(R.id.confirmationSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "TOASTY", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
