package com.example.safehopper;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.safehopper.ui.contacts.EmergencyContact;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private Context context;
    private List<EmergencyContact> emergencyContactList;

    public RecyclerViewAdapter(Context context, List<EmergencyContact> emergencyContactList) {
        this.emergencyContactList = emergencyContactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        EmergencyContact emergencyContact = emergencyContactList.get(position);

        holder.textContactName.setText(emergencyContact.getName());
        holder.textPhoneNumber.setText(emergencyContact.getPhoneNumber());
        holder.textEmail.setText(emergencyContact.getEmail());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "The position is:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return emergencyContactList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textContactName;
        TextView textPhoneNumber;
        TextView textEmail;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textContactName = itemView.findViewById(R.id.contact_name);
            textPhoneNumber = itemView.findViewById(R.id.phone_number);
            textEmail = itemView.findViewById(R.id.email);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
