package com.lisa.masmedapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddParcelFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    String receipient;
    String receipientId;
    String trackingNumber;
    String phoneNumber;
    Button add_parcel_button;

    EditText add_parcel_name_person;
    EditText add_parcel_tracking_number;
    EditText add_parcel_phone;
    EditText add_parcel_date;
    EditText spinnerCourier;

    DatabaseReference databaseReceipient;


    String[] courierNames={"Pos Laju","J&T","ShopeeExpress","Ninja Van","Flash Express"};
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View v = inflater.inflate(R.layout.fragment_add_parcel, container, false);
        FirebaseApp.initializeApp(getActivity());
        //Create database reference
        databaseReceipient = FirebaseDatabase.getInstance().getReference();
        //get values from XML
        add_parcel_name_person = (EditText) v.findViewById(R.id.add_parcel_name_person);
        add_parcel_tracking_number = (EditText) v.findViewById(R.id.add_parcel_tracking_number);
        add_parcel_phone = (EditText) v.findViewById(R.id.add_parcel_phone);
        add_parcel_date = (EditText) v.findViewById(R.id.add_parcel_date);
        spinnerCourier = (EditText) v.findViewById(R.id.spinnerCourier);
        add_parcel_button = (Button) v.findViewById(R.id.add_parcel_button);

//        //Getting the instance of Spinner and applying OnItemSelectedListener on it
//        Spinner spin = (Spinner) v.findViewById(R.id.spinnerCourier);
//        spin.setOnItemSelectedListener(this);
//
//        //Creating the ArrayAdapter instance having the courier name list
//        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,courierNames);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        spin.setAdapter(aa);

        //attach clicklistener to the button
        add_parcel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReceipient();
                add_parcel_name_person.setText("");
                add_parcel_tracking_number.setText("");
                add_parcel_phone.setText("");
                add_parcel_date.setText("");

            } });
        // Inflate the layout for this fragment
        return v;
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getActivity().getApplicationContext(), courierNames[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }


    private void addReceipient() {
        //get artistname and convert -to string from editextname
        String name = add_parcel_name_person.getText().toString().trim();
        String tracking = add_parcel_tracking_number.getText().toString().trim();
        String phone = add_parcel_phone.getText().toString().trim();
        String date = add_parcel_date.getText().toString().trim();
        String courier = spinnerCourier.getText().toString().trim();

        //check if the name is not empty
        if (!TextUtils.isEmpty(name)) {
            //if exist push data to firebase database
            //store inside id in database
            //every time data stored the id will be unique
            String receipientId = databaseReceipient.push().getKey();
            //store
            AddParcel addParcel = new AddParcel(receipientId, name, tracking, phone, date, courier);
            //store artist inside unique id
            databaseReceipient.child(receipientId).setValue(addParcel);
            Toast.makeText(getActivity(), "Parcel added", Toast.LENGTH_LONG).show();

        } else {
            //if the name is empty
            //if the value is not given displaying a toast
            Toast.makeText(getActivity(), "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}