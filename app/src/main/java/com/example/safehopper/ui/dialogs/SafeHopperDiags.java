package com.example.safehopper.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.safehopper.R;

public abstract class SafeHopperDiags {

    public static Dialog getConfirmationDialog(Context mContext){
        LayoutInflater factory = LayoutInflater.from(mContext);
        final View deleteDialogView = factory.inflate(R.layout.dialog_confirmation, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(mContext).create();
        deleteDialog.setView(deleteDialogView);
        deleteDialogView.findViewById(R.id.confirmationDialogSubmitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                deleteDialog.dismiss();
            }
        });
        deleteDialogView.findViewById(R.id.confirmationDialogCancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog.dismiss();
            }
        });

        return deleteDialog;
    }
}
