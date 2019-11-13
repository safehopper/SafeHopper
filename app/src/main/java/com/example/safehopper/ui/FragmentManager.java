package com.example.safehopper.ui;

public abstract class FragmentManager {

    private boolean goToRoute = false;
    private static FragmentManager instance;

    public static FragmentManager getInstance()
    {
        if(instance == null)
        {
            instance = new FragmentManager() {
            };
        }
        return instance;
    }

    public void setGoToRoutes(boolean b){
        goToRoute = b;
    }

    public boolean getGoToRoute(){
        return goToRoute;
    }
}
