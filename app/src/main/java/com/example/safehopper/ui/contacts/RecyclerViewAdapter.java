package com.example.safehopper.ui.contacts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safehopper.R;
import com.example.safehopper.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private List<Contact> mContacts = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<Contact> contacts) {
        mContacts = contacts;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
//
//        holder.textContactName.setText(mNames.get(position));
//        holder.textPhoneNumber.setText(mPhoneNumbers.get(position));
//        holder.textEmail.setText(mEmails.get(position));
//
//
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
        ((ViewHolder)holder).textContactName.setText(mContacts.get(position).getFirstName()+ " " + mContacts.get(position).getLastName());
        ((ViewHolder)holder).textPhoneNumber.setText(mContacts.get(position).getPhoneNumber());
        ((ViewHolder)holder).textEmail.setText(mContacts.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
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
