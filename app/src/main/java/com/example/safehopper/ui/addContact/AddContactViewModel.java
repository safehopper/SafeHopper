package com.example.safehopper.ui.addContact;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddContactViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddContactViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("How should we contact them");
    }

    public LiveData<String> getText() {
        return mText;
    }
}