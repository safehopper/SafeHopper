package com.example.safehopper.ui.contacts;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.safehopper.R;
import com.example.safehopper.models.Contact;
import com.example.safehopper.ui.modifyContact.modifyContact;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

    private RelativeLayout mRelativeLayout;
    private PopupWindow mPopupWindow;

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
        Fragment modifyFragment = new modifyContact(c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhoneNumber(),c.getSendTextAlert(), c.getSendEmailAlert());
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, modifyFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onContactLongClick(int position) {
        Contact c = contactsViewModel.getContacts().getValue().get(position);

        mRelativeLayout = (RelativeLayout) getActivity().findViewById(R.id.contacts_layout);
//        Toast.makeText(getContext(), "Contact: " + c.getFirstName(), Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = (LayoutInflater) getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View deleteDialog = inflater.inflate(R.layout.delete_contact, null);

        mPopupWindow = new PopupWindow(
                deleteDialog,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        if(Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }

        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);

        Button cancelButton = (Button) deleteDialog.findViewById(R.id.cancel_delete);
        Button confirmButton = (Button) deleteDialog.findViewById(R.id.confirm_delete);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Confirm Delete",Toast.LENGTH_SHORT).show();
            }
        });
    }
}