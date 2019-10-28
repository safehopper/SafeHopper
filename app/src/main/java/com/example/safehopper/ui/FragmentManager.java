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

    public void setGoToRoute(boolean b){
        goToRoute = b;
    }

    public boolean getGotToRoute(){
        return goToRoute;
    }
}
