package com.example.safehopper.ui.homepage;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.safehopper.R;
import com.example.safehopper.api_package.Requests;
import com.example.safehopper.repositories.UserRepository;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import static com.example.safehopper.R.id.homepage_fragment;



public class HomepageFragment extends Fragment implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback
{

   private HomepageViewModel homepageViewModel;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationRequest lr;
    private GoogleApiClient lc;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homepageViewModel = ViewModelProviders.of(this).get(HomepageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_homepage, container, false);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(homepage_fragment);
        mapFragment.getMapAsync(this);


        makeAPICall();
        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.765925, -118.127058), 15));
    }

    public void makeAPICall(){
        Log.d("EMAIL", UserRepository.getInstance().getUser().getValue().getEmail());
        Requests.getRoutes(UserRepository.getInstance().getUser().getValue().getEmail());
        Requests.getContacts(UserRepository.getInstance().getUser().getValue().getEmail());
    }

}