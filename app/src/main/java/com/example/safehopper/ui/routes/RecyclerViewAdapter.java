package com.example.safehopper.ui.routes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.safehopper.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> rImageNames;
    private ArrayList<String> rImages;
    private ArrayList<String> rMiles;
    private Context rContext;

    public RecyclerViewAdapter(ArrayList<String> rImageNames, ArrayList<String> rImages, ArrayList<String> rMiles, Context rContext) {
        this.rImageNames = rImageNames;
        this.rImages = rImages;
        this.rMiles = rMiles;
        this.rContext = rContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.routelist_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(rContext)
                .asBitmap()
                .load(rImages.get(position))
                .into(holder.routeImage);

        holder.routeName.setText(rImageNames.get(position));
        holder.routeMiles.setText(rMiles.get(position));

        holder.routeParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + rImageNames.get(position));

                Toast.makeText(rContext, rImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return rImageNames.size();
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
