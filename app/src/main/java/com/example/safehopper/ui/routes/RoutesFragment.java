package com.example.safehopper.ui.routes;

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

public class RoutesFragment extends Fragment {

    private RoutesViewModel routesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        routesViewModel =
                ViewModelProviders.of(this).get(RoutesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_routes, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        routesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}