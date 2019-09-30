package com.example.safehopper.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private Spinner unitsSpinner, bufferZoneSpinner;
    private String routeBufferItem, unitItem;
    //R.S:create a new mutableLiveData that holds data of type string.
    private MutableLiveData<String> mMutableLiveData;
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/safeHopper/";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        //R.S: To Modify the text:
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                settingsViewModel.setmText(mMutableLiveData);
            }
        });
        //R.S: to observe changes done to objects of the ViewModel or our livedata objects
//        settingsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                unitsSpinner.notifyAll();
//                bufferZoneSpinner.notifyAll();
//            }
//        });


        // Initialize Spinner
        unitsSpinner = (Spinner) root.findViewById(R.id.units_spinner);
        bufferZoneSpinner = (Spinner) root.findViewById(R.id.buffer_zone_spinner);
        //R.S:create a new mutableLiveData that holds data of type string.
        mMutableLiveData = new MutableLiveData<>();

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
        final ArrayAdapter<String> bufferZoneAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, bufferZoneOptions);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        bufferZoneAdapter.setDropDownViewResource(R.layout.spinner_item);

        unitsSpinner.setAdapter(dataAdapter);
        bufferZoneSpinner.setAdapter(bufferZoneAdapter);

        // Define the OnItemSelectedListener
        unitsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String item = parent.getItemAtPosition(pos).toString();
                unitItem = item;

                String data = "Route Buffer Zone: " + routeBufferItem + "\nUnits: " + unitItem;

                //writing file
                saveToFile(data);

                // Showing selected spinner item
//                Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                // In the future do stuff in the ViewModel
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Define the OnItemSelectedListener
        bufferZoneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //R.S: On selecting a spinner item
                String item = parent.getItemAtPosition(pos).toString();
                routeBufferItem = item;
                // Showing selected spinner item
//                Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                // In the future do stuff in the ViewModel
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return root;
    }


    public static boolean saveToFile(String data) {
        try {
            new File(path).mkdir();
            File file = new File(path + UUID.randomUUID().toString() + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());

            return true;
        } catch (FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return false;


    }

}