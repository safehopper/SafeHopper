package com.example.safehopper.ui.modifyContact;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModifyContactViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ModifyContactViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("How should we contact them");
    }

    public LiveData<String> getText() {
        return mText;
    }
}