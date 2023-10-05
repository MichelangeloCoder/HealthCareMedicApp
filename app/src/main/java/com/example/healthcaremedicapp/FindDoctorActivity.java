package com.example.healthcaremedicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView back = findViewById(R.id.cardFamilyBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity.this,HomeActivity.class));
            }
        });

        CardView familyphysicians = findViewById(R.id.cardFamilyphysicians);
        familyphysicians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                in.putExtra("title","Physicians");
                startActivity(in);
            }
        });

        CardView familydieticians = findViewById(R.id.cardFamilyDitecians);
        familydieticians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                in.putExtra("title","Dietician");
                startActivity(in);
            }
        });

        CardView familydentists = findViewById(R.id.cardFamilyDentists);
        familydentists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                in.putExtra("title","Dentists");
                startActivity(in);
            }
        });

        CardView familySurgeon = findViewById(R.id.cardFamilySurgeon);
        familySurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                in.putExtra("title","Surgeon");
                startActivity(in);
            }
        });

        CardView familyCardiologists = findViewById(R.id.cardFamilyCardiologists);
        familyCardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                in.putExtra("title","Cardiologists");
                startActivity(in);
            }
        });

    }
}