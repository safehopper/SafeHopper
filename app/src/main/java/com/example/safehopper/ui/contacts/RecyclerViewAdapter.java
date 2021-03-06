package com.example.safehopper.ui.contacts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.safehopper.R;
import com.example.safehopper.models.Contact;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private List<Contact> mContacts = new ArrayList<>();
    private OnContactListener mOnContactListener;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<Contact> contacts, OnContactListener onContactListener) {
        mContacts = contacts;
        mContext = context;
        mOnContactListener = onContactListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view, mOnContactListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Log.d(TAG, mContacts.get(position).getFirstName());

//        String firstName = mContacts.get(position).getFirstName();
//        String newFirstName = firstName.replace("\"", "");
//        mContacts.get(position).setFirstName(newFirstName);

        ((ViewHolder)holder).textContactName.setText(mContacts.get(position).getFirstName()+ " " + mContacts.get(position).getLastName());
        ((ViewHolder)holder).textPhoneNumber.setText(mContacts.get(position).getPhoneNumber());
        ((ViewHolder)holder).textEmail.setText(mContacts.get(position).getEmail());
        ((ViewHolder)holder).emailAlert.setChecked(mContacts.get(position).getSendEmailAlert());
        ((ViewHolder)holder).textAlert.setChecked(mContacts.get(position).getSendTextAlert());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView textContactName;
        TextView textPhoneNumber;
        TextView textEmail;
        CheckBox emailAlert;
        CheckBox textAlert;
        LinearLayout parentLayout;
        OnContactListener onContactListener;

        public ViewHolder(@NonNull View itemView, OnContactListener onContactListener){
            super(itemView);
            textContactName = itemView.findViewById(R.id.contact_name_textview);
            textPhoneNumber = itemView.findViewById(R.id.phone_number_textview);
            textEmail = itemView.findViewById(R.id.email_textview);
            emailAlert = itemView.findViewById(R.id.email_checkbox);
            textAlert = itemView.findViewById(R.id.text_message_checkbox);

            parentLayout = itemView.findViewById(R.id.contact_list_parent_layout);

            this.onContactListener = onContactListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onContactListener.onContactClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onContactListener.onContactLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnContactListener {
        void onContactClick(int position);
        void onContactLongClick(int position);
    }
}
