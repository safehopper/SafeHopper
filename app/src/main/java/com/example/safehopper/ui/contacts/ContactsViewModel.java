package com.example.safehopper.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import com.example.safehopper.models.Contact;

public class ContactsViewModel extends ViewModel
{
    private MutableLiveData<String> mText;
    private MutableLiveData<List<Contact>> currentContacts; // LiveData

    public MutableLiveData<List<Contact>> getCurrentContacts()
    {
        if (currentContacts == null)
        {
            currentContacts = new MutableLiveData<List<Contact>>();
        }
        return currentContacts;
    }

    public ContactsViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is contacts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}