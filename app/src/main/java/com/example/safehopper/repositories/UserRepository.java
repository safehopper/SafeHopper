package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;
import com.example.safehopper.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository
{
    private static UserRepository instance;
    private ArrayList<User> dataSet = new ArrayList<>();

    public static UserRepository getInstance()
    {
        if(instance == null)
        {
            instance = new UserRepository();
        }
        return instance;
    }

    public MutableLiveData<List<User>> getUser()
    {
        MutableLiveData<List<User>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }
}