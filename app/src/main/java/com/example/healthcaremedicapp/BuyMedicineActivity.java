package com.example.healthcaremedicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    //Its a String Raw Data of Medicine and Medcine Details..

    private String[][] packages =
            {
                    {"Acetaminophen","","","","1000"},
                    {"Adderall", "","","","500"},
                    {"Amitriptyline", "", "","","1000"},
                    {"Dolo", "","","","500"},
                    {"Amoxicillin", "", "","","800"},
                    {"Ativan", "", "","","800"},
                    {"Atorvastatin", "", "","","800"},
                    {"Azithromycin", "", "","","800"},
            };

    private String[] package_details = {
            "Building and keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/stress and muscular pain\n" +
                    "Boosting immunity and increasing resistance aganist infection",
            "Chromium picolinate is a supplement that some people take as a complementary and alternative therapy to help a chromium deficiency, control blood sugar levels, lower cholesterol, or lose weight",
            "Building and keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/stress and muscular pain\n" +
                    "Maintain Healthy nervous system",
            "Building and keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/stress and muscular pain\n" +
                    "Chromium picolinate is a supplement that some people take as a complementary and alternative therapy to help a chromium deficiency, control blood sugar levels, lower cholesterol, or lose weight",
            "Dolo 650 is a brand name, and it has 650 MG of paracetamol is also called Acetaminophen",
            "Building and keeping the bones & teeth strong\n" +
                    "Maintain Healthy nervous system",
            "Building and keeping the bones & teeth strong\n" +
                    "Maintain Healthy nervous system",
            "Building and keeping the bones & teeth strong\n" +
                    "Maintain Healthy nervous system",
            "Maintain Healthy nervous system",

    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button ButtonBMBack, buttonBMGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBM);
        buttonBMGoToCart = findViewById(R.id.buttonBMGoToCart);
        ButtonBMBack = findViewById(R.id.ButtonBMBack);

        buttonBMGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));

            }
        });

        ButtonBMBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put( "line1", packages[i][0]);
            item.put( "line2", packages[i][1]);
            item.put( "line3", packages[i][2]);
            item.put( "line4", packages[i][3]);
            item.put( "line5", "Total Cost: "+packages[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(BuyMedicineActivity.this,BuyMedcineDetailsActivity.class);
                in.putExtra("text1",packages[i][0]);
                in.putExtra("text2",package_details[i]);
                in.putExtra("text3",packages[i][4]);
                startActivity(in);
            }
        });
    }
}