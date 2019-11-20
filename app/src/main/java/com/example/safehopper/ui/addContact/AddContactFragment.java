package com.example.safehopper.ui.addContact;

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
import com.example.safehopper.ui.contacts.ContactsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContactFragment extends Fragment {

    private AddContactViewModel addContactViewModel;
    private Button createContact;
    private EditText mfirstName;
    private EditText mlastName;
    private EditText memail;
    private EditText mphoneNum;
    private CheckBox mtextAlerts;
    private CheckBox memailAlerts;

    private API api;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addContactViewModel =
                ViewModelProviders.of(this).get(AddContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_contact, container, false);
//        final TextView textView = root.findViewById(R.id.textView2);
//        addContactViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        createContact = root.findViewById(R.id.button);
        mfirstName = root.findViewById(R.id.firstname);
        mlastName = root.findViewById(R.id.lastName);
        memail = root.findViewById(R.id.email);
        mphoneNum = root.findViewById(R.id.phoneNumber);
        mtextAlerts = root.findViewById(R.id.checkBox);
        memailAlerts = root.findViewById(R.id.checkBox2);

//        api = Requests.getAPI();

        createContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = mfirstName.getText().toString();
                String lastName = mlastName.getText().toString();
                String email = memail.getText().toString();
                String phoneNum = mphoneNum.getText().toString();
                boolean textAlerts = mtextAlerts.isChecked();
                boolean emailAlerts = memailAlerts.isChecked();

                Contact c = new Contact(firstName, lastName, phoneNum, email, textAlerts, emailAlerts);
                Requests.addContact(c).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                Log.d("ADD CONTACT RESPONSE", response.body().string());
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.nav_host_fragment, new ContactsFragment());
                                transaction.addToBackStack(null);
                                transaction.commit();
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