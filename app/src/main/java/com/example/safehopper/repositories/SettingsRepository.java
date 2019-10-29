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
        //dataSet.add(new Contact("andrew","delgado", "760-555-5555", "dog@yahoo.com", true,true));

        for (Settings settings : settingsList) {
            dataSet.add(settings);
        }
        size = dataSet.size();
    }

    public int getRepoSize() {
        return size;
    }
}
