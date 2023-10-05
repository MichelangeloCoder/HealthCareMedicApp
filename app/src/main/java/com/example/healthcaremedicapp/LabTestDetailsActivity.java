package com.example.healthcaremedicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;

    Button btnAddToCart,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewLDPackageName);
        tvTotalCost = findViewById(R.id.ldtextViewTotalCost);
        edDetails = findViewById(R.id.ldeditTextTextMultiLine);
        btnAddToCart = findViewById(R.id.buttonLDAddToCart);
        btnBack = findViewById(R.id.labdetailbtnback);


        edDetails.setKeyListener(null);

        Intent in=getIntent();
        tvPackageName.setText(in.getStringExtra("text1"));
        edDetails.setText(in.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost :" +in.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(in.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if (db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product already added in cart",Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Medicine inserted into cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }

            }
        });

    }
}