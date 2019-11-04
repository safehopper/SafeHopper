package com.example.safehopper.ui.routes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.safehopper.models.Route;
import com.example.safehopper.repositories.RoutesRepository;

import java.util.List;

public class RoutesViewModel extends ViewModel
{
    private RoutesRepository routesRepository;
    private MutableLiveData<List<Route>> mRoutes;

    public void init()
    {
        if(mRoutes != null){
            return;
        }
        routesRepository = RoutesRepository.getInstance();
        mRoutes = routesRepository.getRoutes();
    }

    public LiveData<List<Route>> getRoutes()
    {
        return mRoutes;
    }

    public void addNewValue(final Route newRoute){
        List<Route> currentRoutes = mRoutes.getValue();
        currentRoutes.add(newRoute);
        mRoutes.postValue(currentRoutes);
    }
}