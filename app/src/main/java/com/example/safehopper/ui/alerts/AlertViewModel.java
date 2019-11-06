package com.example.safehopper.ui.alerts;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.safehopper.models.Alert;
import com.example.safehopper.repositories.AlertRepository;

import java.util.List;

public class AlertViewModel extends ViewModel
{
    private AlertRepository alertRepository;
    private MutableLiveData<List<Alert>> mAlerts;

    public void init()
    {
        if(mAlerts != null){
            return;
        }
        alertRepository = AlertRepository.getInstance();
        mAlerts = alertRepository.getAlerts();
    }
}
