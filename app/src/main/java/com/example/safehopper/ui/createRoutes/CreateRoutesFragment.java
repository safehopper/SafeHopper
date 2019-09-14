package com.example.safehopper.ui.createRoutes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;

public class CreateRoutesFragment extends Fragment {

    private CreateRoutesViewModel createRoutesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        createRoutesViewModel =
                ViewModelProviders.of(this).get(CreateRoutesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create_route, container, false);
        final TextView textView = root.findViewById(R.id.text_create_route);
        createRoutesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}