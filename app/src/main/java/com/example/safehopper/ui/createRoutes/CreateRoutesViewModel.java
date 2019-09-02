package com.example.safehopper.ui.createRoutes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateRoutesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CreateRoutesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is create route fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}