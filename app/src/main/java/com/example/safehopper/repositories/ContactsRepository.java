package com.example.safehopper.repositories;

import androidx.lifecycle.MutableLiveData;
import com.example.safehopper.models.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactsRepository
{
    private static ContactsRepository instance;
    private List<Contact> dataSet = new ArrayList<>();
    private int size = dataSet.size();

    public static ContactsRepository getInstance()
    {
        if(instance == null)
        {
            instance = new ContactsRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Contact>> getContacts()
    {
        MutableLiveData<List<Contact>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    public void setContacts(List<Contact> contactList) {
       dataSet.add(new Contact("andrew","delgado", "760-555-5555", "dog@yahoo.com", true,true));

        for (Contact contact : contactList) {
            dataSet.add(contact);
        }
        size = dataSet.size();
    }

    public int getRepoSize() {
     return size;
    }
}