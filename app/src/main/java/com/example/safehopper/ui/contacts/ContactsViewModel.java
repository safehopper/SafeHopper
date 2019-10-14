package com.example.safehopper.ui.contacts;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import com.example.safehopper.models.Contact;
import com.example.safehopper.repositories.ContactsRepository;

public class ContactsViewModel extends ViewModel
{

    private MutableLiveData<List<Contact>> mContacts;
    private ContactsRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private MutableLiveData<String> mText;

    public void init()
    {
        if(mContacts != null)
        {
            return;
        }
        mRepo = ContactsRepository.getInstance();
        mContacts = mRepo.getContacts();
    }

    public void addNewValue(final Contact contact)
    {
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected void onPostExecute(Void aVoid)
            {
                super.onPostExecute(aVoid);
                List<Contact> currentContacts = mContacts.getValue();
                currentContacts.add(contact);
                mContacts.postValue(currentContacts);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids)
            {
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<Contact>> getContacts()
    {
        return mContacts;
    }

    public LiveData<Boolean> getIsUpdating()
    {
        return mIsUpdating;
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