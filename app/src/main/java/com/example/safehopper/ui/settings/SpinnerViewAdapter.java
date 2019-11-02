package com.example.safehopper.ui.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.safehopper.R;
import com.example.safehopper.ui.settings.Settings;

import java.util.List;

public class SpinnerViewAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    private final List<Settings> settings;
    private final Context mContext;
    private final int mResource;

    public SpinnerViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects)
    {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        settings = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent){
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent)
    {
        final View view = mInflater.inflate(mResource, parent, false);

        Spinner unitSpinner = (Spinner) view.findViewById(R.id.units_spinner);
        Spinner bufferSpinner = (Spinner) view.findViewById(R.id.buffer_zone_spinner);

        Settings settingsData = settings.get(position);


        unitSpinner.setText(settingsData.getUnit());
        bufferSpinner.setText(settingsData.getSecurityLevel());

        unitSpinner.setPromptId();



        //unitSpinner.getResources().getS
        return view;
    }

}
