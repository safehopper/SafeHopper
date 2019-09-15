package com.example.safehopper.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Spinner;


import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;

public class SettingsFragment extends Fragment {


    private SettingsViewModel settingsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        String[] values = {"@strings/settings_security_level_low","string/settings_security_level_medium"
                            ,"settings_security_level_high"};
        //Spinner spinner = getView().findViewById(R.id.spinner);
        Spinner spinner = (Spinner) getView().findViewById(R.id.spinner1);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item,values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);

        return root;
    }
}