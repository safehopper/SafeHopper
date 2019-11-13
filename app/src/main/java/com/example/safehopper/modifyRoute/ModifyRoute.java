package com.example.safehopper.modifyRoute;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.safehopper.R;
import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.Route;
import com.example.safehopper.ui.routes.RoutesViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyRoute extends Fragment {

    private ModifyRouteViewModel mViewModel;

    private Button modifyRoute;
    private Button deleteRoute;
    private EditText mRouteName;

    private String routeName;
    private int position;

    private RelativeLayout mRelativeLayout;
    private PopupWindow mPopupWindow;

    public ModifyRoute(String routeName, int position)
    {
        this.routeName = routeName;
        this.position = position;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(ModifyRouteViewModel.class);
        final View root = inflater.inflate(R.layout.modify_route_fragment, container, false);

        modifyRoute = root.findViewById(R.id.mod_route_button);
        deleteRoute = root.findViewById(R.id.del_route_button);
        mRouteName = root.findViewById(R.id.mod_route_name);

        mRouteName.setText(routeName);

        modifyRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Route r = RoutesViewModel.getRoutes().getValue().get(position);
                r.setName(mRouteName.getText().toString());
                Requests.modifyRoute(r).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                Log.d("MODIFY ROUTE RESPONSE", response.body().string());
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                Toast.makeText(getContext(), "Route name modified.", Toast.LENGTH_SHORT).show();
            }
        });

        deleteRoute.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                final Route r = RoutesViewModel.getRoutes().getValue().get(position);

                mRelativeLayout = (RelativeLayout) getActivity().findViewById(R.id.modify_route_layout);
                LayoutInflater inflater = (LayoutInflater) getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View deleteDialog = inflater.inflate(R.layout.delete_route, null);

                mPopupWindow = new PopupWindow(
                        deleteDialog,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                if(Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }

                // Closes the popup window on click outside of window
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setFocusable(true);

                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);

                Button cancelButton = (Button) deleteDialog.findViewById(R.id.rcancel_delete);
                Button confirmButton = (Button) deleteDialog.findViewById(R.id.rconfirm_delete);

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Requests.deleteRoute(r.getRouteID()).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    try {
                                        Log.d("DELETE ROUTE RESPONSE", response.body().string());
                                    }
                                    catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                        Toast.makeText(getContext(),"Route deleted.", Toast.LENGTH_SHORT).show();
                        mPopupWindow.dismiss();
                    }
                });
            }
        });

        return root;
    }

}
