package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;
import com.example.safehopper.models.Alert;
import java.util.ArrayList;
import java.util.List;

public class AlertRepository
{
    private static AlertRepository instance;
    private List<Alert> dataSet = new ArrayList();
    private int size = dataSet.size();

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

    public void setAlerts(List<Alert> alertList) {
        for (Alert alert : alertList) {
            dataSet.add(alert);
        }
        size = dataSet.size();
    }
}
