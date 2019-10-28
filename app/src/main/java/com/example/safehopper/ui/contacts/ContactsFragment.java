package com.example.safehopper.ui.contacts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safehopper.R;
import com.example.safehopper.models.Contact;
import com.example.safehopper.ui.modifyContact.modifyContact;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsFragment extends Fragment implements RecyclerViewAdapter.OnContactListener{

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
        mAdapter = new RecyclerViewAdapter(getContext(),contactsViewModel.getContacts().getValue(), this);
        RecyclerView.LayoutManager linearLayerManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayerManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onContactClick(int position) {
        Contact c = contactsViewModel.getContacts().getValue().get(position);
//        Toast.makeText(getContext(), "Contact: " + c.getFirstName(), Toast.LENGTH_SHORT).show();
        Fragment modifyFragment = new modifyContact();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contact_list_parent_layout, modifyFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}