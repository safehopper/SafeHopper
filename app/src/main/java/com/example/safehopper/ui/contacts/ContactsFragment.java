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

import java.util.List;

public class ContactsFragment extends Fragment {

    public static final String TAG = "ContactsFragment";

    private ContactsViewModel contactsViewModel;
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
        mAdapter = new RecyclerViewAdapter(getContext(),contactsViewModel.getContacts().getValue());
        RecyclerView.LayoutManager linearLayerManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayerManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}