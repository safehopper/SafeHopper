package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.safehopper.ui.settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class SettingsRepository {

    private static SettingsRepository instance;
    private List<Settings> dataSet = new ArrayList<>();
    private int size = dataSet.size();

    public static SettingsRepository getInstance()
    {
        if(instance == null)
        {
            instance = new SettingsRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Settings>> getSettings()
    {
        MutableLiveData<List<Settings>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    public void setSettings() {

//        Settings s1 = new Settings("Meters","Low");
//        Settings s2 = new Settings("Meters","Medium");
//        Settings s3 = new Settings("Meters","High");
//
//        Settings s4 = new Settings("Feet","Low");
//        Settings s5 = new Settings("Feet","Medium");
//        Settings s6 = new Settings("Feet","High");
//
//        dataSet.add(s1);
//        dataSet.add(s2);
//        dataSet.add(s3);
//        dataSet.add(s4);
//        dataSet.add(s5);
//        dataSet.add(s6);


        //Creating a string Array to select  the spinner values

        String settingsArray[][] = new String[2][];
        settingsArray[0]= new String[] {"Meters", "Low"};
        settingsArray[1]= new String[] {"Meters", "Medium"};
        settingsArray[2]= new String[] {"Meters", "High"};

        settingsArray[3]= new String[] {"Miles", "Low"};
        settingsArray[4]= new String[] {"Miles", "Medium"};
        settingsArray[5]= new String[] {"Miles", "High"};

//        for (Settings settings : settingsList) {
//            dataSet.add(settings);
//        }
//        size = dataSet.size();
    }

    public int getRepoSize() {
        return size;
    }
}
