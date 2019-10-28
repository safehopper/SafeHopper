package com.example.safehopper.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.safehopper.R;
import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.Route;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public abstract class SaveRouteDialog {

    private static LayoutInflater inflater;
    private static View dialogView;
    private static AlertDialog dialog;

    public static Dialog getSaveRouteDialog(final Route routeToSave, final Context mContext, final String email, final Callback<ResponseBody> callback){

        inflater = LayoutInflater.from(mContext);
        dialogView = inflater.inflate(R.layout.route_name_confirmation, null);
        dialog = new AlertDialog.Builder(mContext).create();
        dialog.setView(dialogView);

        dialogView.findViewById(R.id.confirmationDialogSaveButton).setOnClickListener(new View.OnClickListener() {
            EditText newRouteName = dialogView.findViewById(R.id.confirmationDialogET);
            @Override
            public void onClick(View v) {
                routeToSave.setName(newRouteName.getText().toString());

                Log.d("DIALOG", routeToSave.turnToJson());

                Call<ResponseBody> call = Requests.loginUser(email, routeToSave.turnToJson());
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
