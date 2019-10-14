package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class SessionsRepository
{
    private static SessionsRepository instance;
    private ArrayList<Session> dataSet = new ArrayList<>();

    public static SessionRepository getInstance()
    {
        if(instance == null)
        {
            instance = new SessionRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Session>> getSessions()
    {
        MutableLiveData<List<Session>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }
}
