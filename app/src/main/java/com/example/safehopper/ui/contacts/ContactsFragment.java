package com.example.safehopper.ui.contacts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safehopper.R;
import com.example.safehopper.models.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ContactsFragment extends Fragment {

    public static final String TAG = "ContactsFragment";

    private ContactsViewModel contactsViewModel;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mPhoneNumbers = new ArrayList<>();
    private ArrayList<String> mEmails = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactsViewModel =
                ViewModelProviders.of(this).get(ContactsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);

        Log.d(TAG, "onCreateView started.");

        initContactListItems();

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mPhoneNumbers, mEmails, this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return root;
    }

    private void initContactListItems(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mNames.add("Guillermo Barron");
        mPhoneNumbers.add("(999)999-9999");
        mEmails.add("gbarron@gmail.com");

        mNames.add("Rojin Shahbazian");
        mPhoneNumbers.add("(888)888-8888");
        mEmails.add("rshahbazian@gmail.com");

        mNames.add("Mariel Trajano");
        mPhoneNumbers.add("(777)777-7777");
        mEmails.add("mtrajano@gmail.com");

        mNames.add("Andrew Delgado");
        mPhoneNumbers.add("(666)666-6666");
        mEmails.add("adelgado@gmail.com");

        mNames.add("Justin Terry");
        mPhoneNumbers.add("(555)555-5555");
        mEmails.add("jterry@gmail.com");
    }
}