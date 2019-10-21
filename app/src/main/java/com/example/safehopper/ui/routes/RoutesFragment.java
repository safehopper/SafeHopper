package com.example.safehopper.ui.routes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.safehopper.R;
import com.example.safehopper.api_package.Requests;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RoutesFragment extends Fragment {

    private static final String TAG = "RoutesFragment";

    private RoutesViewModel routesViewModel;

    private ArrayList<String> rNames = new ArrayList<>();
    private ArrayList<String> rMiles = new ArrayList<>();
    private ArrayList<String> rImageURLs = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        routesViewModel =
                ViewModelProviders.of(this).get(RoutesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_routes, container, false);

        Log.d(TAG, "onCreateView: started");

        initImageBitmaps();

        RecyclerView recyclerView = root.findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(rNames, rImageURLs, rMiles, this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Requests.getRoutes("andrewdelgado017@gmail.com");

        return root;
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        rImageURLs.add("https://cdn-assets.alltrails.com/static-map/production/at-map/20792117/trail-us-california-el-dorado-east-regional-park-perimeter-loop-at-map-20792117-1534368141-414x200-1.png");
        rNames.add("El Dorado Park");
        rMiles.add("1.1 miles");

        rImageURLs.add("https://cdn-assets.alltrails.com/static-map/production/at-map/17283855/trail-us-california-eaton-canyon-trail-at-map-17283855-1531801042-414x200-1.png");
        rNames.add("Eaton Canyon");
        rMiles.add("3.2 miles");
    }
}