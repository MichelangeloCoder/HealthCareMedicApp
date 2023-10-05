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

public class BuyMedcineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;

    Button btnBack, btnAddtoCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medcine_details);

        tvPackageName = findViewById(R.id.textViewBMDPackageName);
        tvTotalCost = findViewById(R.id.textViewBMDTotalCost);
        edDetails = findViewById(R.id.editTextTextBMDMultiLine);
        edDetails.setKeyListener(null);
        btnBack = findViewById(R.id.BMDbtnback);
        btnAddtoCart = findViewById(R.id.buttonBMDAddToCart);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedcineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });

        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if (db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product already added in cart",Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Medicine inserted into cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedcineDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });


    }
}