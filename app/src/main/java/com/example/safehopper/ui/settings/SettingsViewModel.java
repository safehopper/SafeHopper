package com.example.safehopper.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is settings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    private int unit;
    private int RouteBuffer;

    public int getUnit(){
        return unit;
    }

    public void setUnit(int unit){
        this.unit=unit;
    }
    public int getRouteBuffer(){
        return RouteBuffer;
    }

    public void setRouteBuffer(int sec){
        this.RouteBuffer = RouteBuffer;
    }
}