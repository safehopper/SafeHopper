package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class AlertRepository
{
    private static AlertRepository instance;
    private ArrayList<Alert> dataSet = new ArrayList<>();

    public static AlertRepository getInstance()
    {
        if(instance == null)
        {
            instance = new AlertRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Alert>> getAlerts()
    {
        MutableLiveData<List<Alert>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }
}
