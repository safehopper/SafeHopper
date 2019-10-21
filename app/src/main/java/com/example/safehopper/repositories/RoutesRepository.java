package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.safehopper.models.Route;
import com.google.android.gms.maps.model.LatLng;

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
        List<LatLng> t = new ArrayList<>();
        t.add(new LatLng(334,3434));

        List<Route> routeList = new ArrayList<>();

        routeList.add(new Route("Andrew", "andrewdelgado.email",
                "56miles", "https://cdn-assets.alltrails.com/static-map/production/at-map/20792117/trail-us-california-el-dorado-east-regional-park-perimeter-loop-at-map-20792117-1534368141-414x200-1.png",t,"4747" ));

        setRoutes(routeList);

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