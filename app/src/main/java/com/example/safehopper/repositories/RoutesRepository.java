package com.example.safehopper.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.safehopper.api_package.Requests;
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
//        List<LatLng> newRoute = new ArrayList<>();
//        newRoute.add(new LatLng(34,34));
//
//        dataSet.add(new Route("string","antoherString",
//                "thirdString", "dog", newRoute,"df"));
        Requests.getRoutes(UserRepository.getInstance().getUser().getValue().getEmail());

        MutableLiveData<List<Route>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    public void setRoutes(List<Route> routeList) {

        dataSet = new ArrayList<>();
        for (Route route : routeList) {
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