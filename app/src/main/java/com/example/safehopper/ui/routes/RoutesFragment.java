package com.example.safehopper.ui.routes;

import android.content.Intent;
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
import com.example.safehopper.SessionActivity;
import com.example.safehopper.models.Route;
import com.example.safehopper.repositories.RoutesRepository;

import java.util.List;

public class RoutesFragment extends Fragment implements RecyclerViewAdapter.OnRouteListener{

    private static final String TAG = "RoutesFragment";

    private RoutesViewModel routesViewModel;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_routes, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerv_view);


        routesViewModel = ViewModelProviders.of(this).get(RoutesViewModel.class);

        routesViewModel.init();

        routesViewModel.getRoutes().observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                mAdapter.notifyDataSetChanged();
                Log.d("ON CHANGED", RoutesRepository.getInstance().getRoutes().toString());
            }
        });

        initRouteListItems();
        return root;
    }

    private void initRouteListItems() {
        mAdapter = new RecyclerViewAdapter(getContext(), routesViewModel.getRoutes().getValue(),this);
        RecyclerView.LayoutManager linearLayerManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayerManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRouteClick(int position) {
        // Get RouteID
        Route r = routesViewModel.getRoutes().getValue().get(position);
        Log.d("ROUTE_INFO", r.toString());

        Intent i = new Intent(getActivity(), SessionActivity.class);
        i.putExtra("RouteID", r.getRouteID());
        startActivity(i);
    }
}