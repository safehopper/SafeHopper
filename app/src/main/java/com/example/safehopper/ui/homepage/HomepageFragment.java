package com.example.safehopper.ui.homepage;

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

public class HomepageFragment extends Fragment {

    private HomepageViewModel homepageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homepageViewModel =
                ViewModelProviders.of(this).get(HomepageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_homepage, container, false);
        return root;
    }
}