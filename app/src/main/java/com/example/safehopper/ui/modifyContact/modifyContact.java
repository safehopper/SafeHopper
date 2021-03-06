package com.example.safehopper.ui.modifyContact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.safehopper.R;
import com.example.safehopper.api_package.API;
import com.example.safehopper.api_package.Requests;
import com.example.safehopper.models.Contact;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class modifyContact extends Fragment {

    private ModifyContactViewModel modifyContactViewModel;
    private Button modifyContact;
    private EditText mfirstName;
    private EditText mlastName;
    private EditText memail;
    private EditText mphoneNum;
    private CheckBox mtextAlerts;
    private CheckBox memailAlerts;

    private String first;
    private String last;
    private String email;
    private String phone;
    private boolean textal;
    private boolean emailal;

    private API api;

    public modifyContact(String first, String last, String email, String phone, boolean text, boolean emailal)
    {
        this.first = first;
        this.last = last;
        this.email = email;
        this.phone = phone;
        this.textal = text;
        this.emailal = emailal;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        modifyContactViewModel =
                ViewModelProviders.of(this).get(ModifyContactViewModel.class);
        View root = inflater.inflate(R.layout.modify_contact_fragment, container, false);
//        final TextView textView = root.findViewById(R.id.textView2);
//        addContactViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        modifyContact = root.findViewById(R.id.modify_button);
        mfirstName = root.findViewById(R.id.firstname);
        mlastName = root.findViewById(R.id.lastName);
        memail = root.findViewById(R.id.email);
        mphoneNum = root.findViewById(R.id.phoneNumber);
        mtextAlerts = root.findViewById(R.id.checkBox);
        memailAlerts = root.findViewById(R.id.checkBox2);

        mfirstName.setText(first);
        mlastName.setText(last);
        memail.setText(email);
        mphoneNum.setText(phone);
        mtextAlerts.setChecked(textal);
        memailAlerts.setChecked(emailal);

//        api = Requests.getAPI();

        modifyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = mfirstName.getText().toString();
                String lastName = mlastName.getText().toString();
                String email = memail.getText().toString();
                String phoneNum = mphoneNum.getText().toString();
                boolean textAlerts = mtextAlerts.isChecked();
                boolean emailAlerts = memailAlerts.isChecked();

                Contact c = new Contact(firstName, lastName, phoneNum, email, textAlerts, emailAlerts);
                Requests.modifyContact(c).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                Log.d("MODIFY CONTACT RESPONSE", response.body().string());
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
            }
        });

        return root;
    }
}