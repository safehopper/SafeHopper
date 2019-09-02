package com.example.safehopper.ui.homepage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomepageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomepageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is homepage fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}