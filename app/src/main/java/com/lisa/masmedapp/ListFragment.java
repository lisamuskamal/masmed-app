package com.lisa.masmedapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private View v;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    private DatabaseReference GroupRef;

//    private String addParcels;
    List<AddParcel> addParcels;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_list, container, false);

        GroupRef = FirebaseDatabase.getInstance().getReference();
        InitializeFields();

        RetriveAndDisplayGroup();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
//              addParcels = arrayList.get(i);
////              ArrayList addParcel = arrayList.get(i);
//                Toast.makeText(getActivity(), "Parcel Updated" + addParcels, Toast.LENGTH_LONG).show();
////                showUpdateDeleteDialog(arrayList.get(i).getReceipientId(), addParcels.getReceipient(), addParcels.getTrackingNumber(), addParcels.getPhoneNumber(), addParcels.getParcelDate(), addParcels.getCourier());
                AddParcel addParcel = addParcels.get(i);
                showUpdateDeleteDialog(addParcel.getReceipientId(), addParcel.getReceipient(), addParcel.getTrackingNumber(), addParcel.getPhoneNumber(), addParcel.getParcelDate(), addParcel.getCourier());
                return true;
            }
        });

        return v;
    }


    private void RetriveAndDisplayGroup() {
        GroupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList list = new ArrayList();
                addParcels = new ArrayList<>();
                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String rface = (String) dataSnapshot.child("receipient").getValue();
                    String rface2 = (String) dataSnapshot.child("trackingNumber").getValue();
                    String rface3 = (String) dataSnapshot.child("courier").getValue();
//                    arrayList.add(rface, rface2);
//                    System.err.println(dataSnapshot.getKey());
                    AddParcel data = dataSnapshot.getValue(AddParcel.class);
                    if(data != null){
                        arrayList.add(rface2 + "\n" +rface3 +" | " + rface);
                    }
                    addParcels.add(data);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void InitializeFields() {
        listView = (ListView) v.findViewById(R.id.parcel_list);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }

    private boolean updateParcel(String receipientId, String receipient, String trackingNumber, String phoneNumber, String parcelDate, String courier) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference().child(receipientId);

        //updating artist
        AddParcel addParcel = new AddParcel(receipientId, receipient, trackingNumber, phoneNumber, parcelDate, courier);
        dR.setValue(addParcel);
        Toast.makeText(getActivity(), "Parcel Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteParcel(String receipientId, String receipient, String trackingNumber, String phoneNumber, String parcelDate, String courier) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference().child(receipientId);
        //removing artist
        dR.removeValue();
        Toast.makeText(getActivity(), "Parcel Deleted", Toast.LENGTH_LONG).show();

        return true;
    }


    private void showUpdateDeleteDialog(final String receipientId, String receipient, String trackingNumber, String phoneNumber, String parcelDate, String courier) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_update_parcel, null);
        dialogBuilder.setView(dialogView);

        final EditText updatePerson = (EditText) dialogView.findViewById(R.id.update_parcel_name_person);
        final EditText updatePhone = (EditText) dialogView.findViewById(R.id.update_parcel_phone);
        final EditText updateTrackingNumber = (EditText) dialogView.findViewById(R.id.update_parcel_tracking_number);
        final EditText updateDate = (EditText) dialogView.findViewById(R.id.update_parcel_date);
        final EditText updateCourier = (EditText) dialogView.findViewById(R.id.update_spinnerCourier);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.delete_parcel);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.update_parcel);

        updatePerson.setText(receipient);
        updateTrackingNumber.setText(trackingNumber);
        updatePhone.setText(phoneNumber);
        updateDate.setText(parcelDate);
        updateCourier.setText(courier);


        dialogBuilder.setTitle(receipient);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updatePerson.getText().toString().trim();
                String trackingNum = updateTrackingNumber.getText().toString().trim();
                String phone = updatePhone.getText().toString().trim();
                String date = updateDate.getText().toString().trim();
                String courier = updateCourier.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    updateParcel(receipientId, name, trackingNum, phone, date, courier);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteParcel(receipientId, receipient, trackingNumber, phoneNumber, parcelDate, courier);
                b.dismiss();

            }
        });
    }
}