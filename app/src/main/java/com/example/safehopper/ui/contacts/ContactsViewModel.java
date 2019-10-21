package com.example.safehopper.ui.contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.safehopper.models.Contact;
import com.example.safehopper.repositories.ContactsRepository;

import java.util.List;

public class ContactsViewModel extends ViewModel
{
    private ContactsRepository contactsRepository;
    private MutableLiveData<List<Contact>> mContacts;


    public void init(){
        if(mContacts != null){
            return;
        }
        contactsRepository = ContactsRepository.getInstance();
        mContacts = contactsRepository.getContacts();
    }

    public LiveData<List<Contact>> getContacts(){
        return mContacts;
    }
}