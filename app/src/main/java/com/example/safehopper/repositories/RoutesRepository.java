package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;
import com.example.safehopper.models.Route;
import java.util.ArrayList;
import java.util.List;

public class RoutesRepository
{
    private static RoutesRepository instance;
    private List<Route> dataSet = new ArrayList<>();
    private int size = dataSet.size();

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

    public void setRoutes(List<Route> routeList) {
        for (Route route : routeList) {
            dataSet.add(route);
        }
        size = dataSet.size();
    }

    public int getRepoSize()
    {
        return size;
    }
}