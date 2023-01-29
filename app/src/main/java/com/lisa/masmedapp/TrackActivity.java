package com.lisa.masmedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TrackActivity extends AppCompatActivity {

    EditText trackingNumber;
    Button traceButton;
    Button loginButton;
    Button list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        trackingNumber = findViewById(R.id.trackingNumber);
        traceButton = findViewById(R.id.traceButton);
        loginButton = findViewById(R.id.loginButton);
//        list = findViewById(R.id.list);

        traceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trackingNumber.getText().toString().equals("ER1234677MY")) {
                    Toast.makeText(TrackActivity.this, "Found", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(getApplicationContext(), TrackingList.class);
                    startActivity(it);
                } else {
                    Toast.makeText(TrackActivity.this, "Sorry Tracking Number Not Found!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(TrackActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

//        list.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(getApplicationContext(), TrackingList.class);
//                startActivity(it);
//            }
//        });


    }
}