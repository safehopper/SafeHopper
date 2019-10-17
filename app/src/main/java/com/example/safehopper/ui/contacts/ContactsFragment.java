package com.example.safehopper.ui.contacts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safehopper.R;
import com.example.safehopper.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {

    public static final String TAG = "ContactsFragment";

    private ContactsViewModel contactsViewModel;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mPhoneNumbers = new ArrayList<>();
    private ArrayList<String> mEmails = new ArrayList<>();
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        mRecyclerView = root.findViewById(R.id.recycler_view);

        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);

        contactsViewModel.init();

        Log.d(TAG, "onCreateView started.");


        contactsViewModel.getContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initContactListItems();

        return root;
    }

    private void initContactListItems(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");


        mAdapter = new RecyclerViewAdapter(getContext(),contactsViewModel.getContacts().getValue());
        RecyclerView.LayoutManager linearLayerManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayerManager);
        mRecyclerView.setAdapter(mAdapter);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mNames.add("Guillermo Barron");
//        mPhoneNumbers.add("(999)999-9999");
//        mEmails.add("gbarron@gmail.com");
//
//        mNames.add("Rojin Shahbazian");
//        mPhoneNumbers.add("(888)888-8888");
//        mEmails.add("rshahbazian@gmail.com");
//
//        mNames.add("Mariel Trajano");
//        mPhoneNumbers.add("(777)777-7777");
//        mEmails.add("mtrajano@gmail.com");
//
//        mNames.add("Andrew Delgado");
//        mPhoneNumbers.add("(666)666-6666");
//        mEmails.add("adelgado@gmail.com");
//
//        mNames.add("Justin Terry");
//        mPhoneNumbers.add("(555)555-5555");
//        mEmails.add("jterry@gmail.com");
    }
}