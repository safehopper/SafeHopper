package com.example.safehopper.repositories;

public class AlertRepository
{
    private static AlertRepository instance;
    //private ArrayList<Alert> dataSet = new ArrayList<>();

    public static AlertRepository getInstance()
    {
        if(instance == null)
        {
            instance = new AlertRepository();
        }
        return instance;
    }

//    public MutableLiveData<List<Alert>> getAlerts()
//    {
//        MutableLiveData<List<Alert>> data = new MutableLiveData<>();
//        data.setValue(dataSet);
//        return data;
//    }
}
