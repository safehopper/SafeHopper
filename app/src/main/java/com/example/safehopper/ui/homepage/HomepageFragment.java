package com.example.safehopper.ui.homepage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safehopper.R;
import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.Contact;
import com.example.safehopper.repositories.ContactsRepository;
import com.example.safehopper.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomepageFragment extends Fragment {

    private HomepageViewModel homepageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homepageViewModel = ViewModelProviders.of(this).get(HomepageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_homepage, container, false);
        makeAPICall();
        return root;
    }

    public void makeAPICall(){
        Log.d("EMAIL", UserRepository.getInstance().getUser().getValue().getEmail());
        Requests.getRoutes(UserRepository.getInstance().getUser().getValue().getEmail());
        Requests.getContacts(UserRepository.getInstance().getUser().getValue().getEmail());
        List<Contact> c = new ArrayList<Contact>();

        c = ContactsRepository.getInstance().getDataSet();

    }
}