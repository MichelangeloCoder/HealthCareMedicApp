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

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Package 1 : Full Body Checkup","","","","1000"},
                    {"Package 2 : Blood Glucose Fasting", "","","","500"},
                    {"Package 3 : COVID-19 Antibody", "", "","","1000"},
                    {"Package 4 : Thyroid Checkup", "", "","","700"},
                    {"Package 5 : Immunity Checkup", "", "","","800"},
            };

    private String[] package_details = {
            "Blood Glucose Fasting\n"+
                    "Complete Hemogram\n"+
                    "HbA1c\n"+
                    "Kidney Function Test\n"+
                    "LDH Lactate Serum\n"+
                    "Lipid Profile\n"+
                    "Liver Functioning Test",
                    "Blood Glucose Fasting",
                    "COVID-19 AntiBody - IgG",
                    "Thyroid Profile-total (T3,T4 & TSH Ultra-Sensitive)",
                    "Lipid Profile"

    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button GoToCartbtn,btnback;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        GoToCartbtn = findViewById(R.id.addcartbtn);
        btnback = findViewById(R.id.labbackbtn);
        listView = findViewById(R.id.lablistView);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });

        GoToCartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });

        //We have used Hashmap code to map the list and packages array details

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        //We have map the multi line and set the Simple Adapter on below Code..

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e,});
                listView.setAdapter(sa);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in = new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                        in.putExtra("text1",packages[i][0]);
                        in.putExtra("text2",package_details[i]);
                        in.putExtra("text3",packages[i][4]);
                        startActivity(in);

                    }
                });

    }
}