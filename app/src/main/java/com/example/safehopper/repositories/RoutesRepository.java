package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;
import com.example.safehopper.models.Route;
import java.util.ArrayList;
import java.util.List;

public class RoutesRepository
{
    private static RoutesRepository instance;
    private ArrayList<Route> dataSet = new ArrayList<>();

    public static RoutesRepository getInstance()
    {
        if(instance == null)
        {
            instance = new RoutesRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Route>> getRoutes()
    {
        MutableLiveData<List<Route>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }
}