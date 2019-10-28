package com.example.safehopper.ui.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.safehopper.R;
import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.Alert;

public class HomepageFragment extends Fragment {

    private HomepageViewModel homepageViewModel;
    private Alert alert;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homepageViewModel = ViewModelProviders.of(this).get(HomepageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_homepage, container, false);
        makeAPICall();

        return root;
    }

    public void makeAPICall(){
        Requests.getContacts("z400jt618@gmail.com");
    }
}