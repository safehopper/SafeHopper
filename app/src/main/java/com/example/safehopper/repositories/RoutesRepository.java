package com.example.safehopper.repositories;

import android.util.Log;

import com.example.safehopper.models.Route;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

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
        dataSet = new ArrayList<>();
        for (Route route : routeList) {
            route.setName(route.getName().replace("\"", ""));
            route.setDistance(route.getDistance().replace("\"", ""));
            dataSet.add(route);
        }

        size = dataSet.size();
        Log.d("ROUTES", dataSet.toString());
    }

    public int getRepoSize()
    {
        return size;
    }
}