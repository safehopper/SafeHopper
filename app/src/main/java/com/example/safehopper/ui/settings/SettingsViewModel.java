package com.example.safehopper.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.safehopper.repositories.SettingsRepository;

import java.util.List;

public class SettingsViewModel extends ViewModel {

    private SettingsRepository settingsRepository;
    private MutableLiveData<List<Settings>> mSettings;

    public void init(){
        if(mSettings != null){
            return;
        }
        settingsRepository = SettingsRepository.getInstance();
        mSettings = settingsRepository.getSettings();
    }

    public LiveData<List<Settings>> getSettings(){
        return mSettings;
    }
}