package com.example.safehopper.ui.contacts;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import com.example.safehopper.repositories.ContactsRepository;

public class ContactsViewModel extends ViewModel
{
    private ContactsRepository contactsRepository;


    public ContactsViewModel(@Nullable ContactsRepository contactsRepository)
    {
        if(this.contactsRepository != null)
        {
            // Viewmodel is created per Activity, so instantiate once
            // we know the userId won't change
            return;
        }

        if(contactsRepository != null)
        {
            this.contactsRepository = contactsRepository;
        }
    }

    public void onPullRequested()
    {
        contactsRepository.loadListNewData();
    }
}