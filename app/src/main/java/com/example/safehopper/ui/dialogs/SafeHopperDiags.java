package com.example.safehopper.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safehopper.R;
import com.example.safehopper.api_package.Requests;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class SafeHopperDiags {

    private static LayoutInflater inflater;
    private static View dialogView;
    private static AlertDialog dialog;

    public static Dialog getConfirmationDialog(final Context mContext, final String email, final Callback<ResponseBody> callback){

        inflater = LayoutInflater.from(mContext);
        dialogView = inflater.inflate(R.layout.dialog_confirmation, null);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setView(dialogView);

        dialogView.findViewById(R.id.confirmationDialogSubmitButton).setOnClickListener(new View.OnClickListener() {
            EditText mfaCodeTextView = dialogView.findViewById(R.id.confirmationDialogET);
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = Requests.confirmUser(email, mfaCodeTextView.getText().toString());
                call.enqueue(callback);
                dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.confirmationDialogCancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
