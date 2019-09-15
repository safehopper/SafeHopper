package com.example.safehopper.ui.contacts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safehopper.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mNames;
    private ArrayList<String> mPhoneNumbers;
    private ArrayList<String> mEmails;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mNames, ArrayList<String> mPhoneNumbers, ArrayList<String> mEmails, Context mContext) {
        this.mNames = mNames;
        this.mPhoneNumbers = mPhoneNumbers;
        this.mEmails = mEmails;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.textContactName.setText(mNames.get(position));
        holder.textPhoneNumber.setText(mPhoneNumbers.get(position));
        holder.textEmail.setText(mEmails.get(position));


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textContactName;
        TextView textPhoneNumber;
        TextView textEmail;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textContactName = itemView.findViewById(R.id.contact_name_textview);
            textPhoneNumber = itemView.findViewById(R.id.phone_number_textview);
            textEmail = itemView.findViewById(R.id.email_textview);
            parentLayout = itemView.findViewById(R.id.contact_list_parent_layout);
        }
    }
}
