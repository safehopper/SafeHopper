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

    public void setSettings(List<Settings> settingsList) {

        Settings s1 = new Settings("Meters","Low");
        Settings s2 = new Settings("Meters","Medium");
        Settings s3 = new Settings("Meters","High");

        Settings s4 = new Settings("Feet","Low");
        Settings s5 = new Settings("Feet","Medium");

        dataSet.add(s1);
        dataSet.add(s2);
        dataSet.add(s3);
//        for (Settings settings : settingsList) {
//            dataSet.add(settings);
//        }
//        size = dataSet.size();
    }

    public int getRepoSize() {
        return size;
    }
}
