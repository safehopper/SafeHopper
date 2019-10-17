package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;
import com.example.safehopper.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsRepository
{
    private static ContactsRepository instance;
    private ArrayList<Contact> dataSet = new ArrayList<>();

    public static ContactsRepository getInstance()
    {
        if(instance == null)
        {
            instance = new ContactsRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Contact>> getContacts()
    {   setContacts();
        MutableLiveData<List<Contact>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setContacts(){
        dataSet.add(new Contact("Andrew", "Delgado","760", "andrewdelgado017@gmail.com", true, true));


    }
}