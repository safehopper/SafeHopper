package com.example.safehopper.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;
import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.Route;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private Spinner unitsSpinner, bufferZoneSpinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Requests.getRoutes("TEST@EMAIL.COM");


        // Initialize Spinner
        unitsSpinner = (Spinner) root.findViewById(R.id.units_spinner);
        bufferZoneSpinner = (Spinner) root.findViewById(R.id.buffer_zone_spinner);

        // Create the options
        List<String> unitOptions = new ArrayList<String>();
        List<String> bufferZoneOptions = new ArrayList<String>();
        unitOptions.add("Kilometers/Meters");
        unitOptions.add("Miles/Feet");
        bufferZoneOptions.add("Low Security");
        bufferZoneOptions.add("Medium Security");
        bufferZoneOptions.add("High Security");

        // Define what the spinner items are and what they look like
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, unitOptions);
        ArrayAdapter<String> bufferZoneAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, bufferZoneOptions);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        bufferZoneAdapter.setDropDownViewResource(R.layout.spinner_item);

        unitsSpinner.setAdapter(dataAdapter);
        bufferZoneSpinner.setAdapter(bufferZoneAdapter);

        // Define the OnItemSelectedListener
        unitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                // In the future do stuff in the ViewModel
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Define the OnItemSelectedListener
        bufferZoneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                // In the future do stuff in the ViewModel
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return root;
    }
}