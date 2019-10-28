package com.example.safehopper.ui.routes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.safehopper.R;
import com.example.safehopper.models.Route;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private LiveData<List<Route>> mRoutes;
    private Context mContext;
    private LifecycleOwner owner;

    public RecyclerViewAdapter(Context context, LifecycleOwner frag, LiveData<List<Route>> routes)
    {
        mRoutes = routes;
        owner = frag;

        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routelist_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mRoutes.getValue().get(position).getImageURL())
                .into(((ViewHolder)holder).routeImage);

        ((ViewHolder)holder).routeName.setText(mRoutes.getValue().get(position).getName());
        ((ViewHolder)holder).routeMiles.setText(mRoutes.getValue().get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return mRoutes.getValue().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView routeImage;
        TextView routeName;
        TextView routeMiles;
        RelativeLayout routeParentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            routeImage = itemView.findViewById(R.id.route_image);
            routeName = itemView.findViewById(R.id.route_name);
            routeMiles = itemView.findViewById(R.id.route_miles);
            routeParentLayout = itemView.findViewById(R.id.routeparent_layout);
        }
    }
}
