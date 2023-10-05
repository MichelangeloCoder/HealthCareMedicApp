package com.example.healthcaremedicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1=
            {
                    {"Doctor Name : Mohan Yadav", "Hospital Address : Mumbai", "Exp : 5yrs", "Mobile : 123456",  "700"},
                    {"Doctor Name : Aarvik Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "600"},
                    {"Doctor Name : Aarya Yadav", "Hospital Address : Mumbai", "Exp : 2yrs", "Mobile : 123456", "500"},
                    {"Doctor Name : Parth Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "400"},
                    {"Doctor Name : Chirag Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "300"},
            };

    private String[][] doctor_details2=
            {
                    {"Doctor Name : Rekha Yadav", "Hospital Address : Mumbai", "Exp : 5yrs", "Mobile : 123456", "700"},
                    {"Doctor Name : Mamta Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "600"},
                    {"Doctor Name : Suman Yadav", "Hospital Address : Mumbai", "Exp : 2yrs", "Mobile : 123456", "500"},
                    {"Doctor Name : Saroj Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "400"},
                    {"Doctor Name : Lalita Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "300"},
            };

    private String[][] doctor_details3=
            {
                    {"Doctor Name : Satyadev Yadav", "Hospital Address : Mumbai", "Exp : 5yrs", "Mobile : 123456", "700"},
                    {"Doctor Name : Satyavijay Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "600"},
                    {"Doctor Name : Birendar Yadav", "Hospital Address : Mumbai", "Exp : 2yrs", "Mobile : 123456", "500"},
                    {"Doctor Name : Ravikant Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "400"},
                    {"Doctor Name : Gamaprasad Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "300"},
            };

    private String[][] doctor_details4=
            {
                    {"Doctor Name : Mohan Yadav", "Hospital Address : Mumbai", "Exp : 5yrs", "Mobile : 123456", "700"},
                    {"Doctor Name : Aarvik Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "600"},
                    {"Doctor Name : Aarya Yadav", "Hospital Address : Mumbai", "Exp : 2yrs", "Mobile : 123456", "500"},
                    {"Doctor Name : Parth Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "400"},
                    {"Doctor Name : Chirag Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "300"},
            };

    private String[][] doctor_details5=
            {
                    {"Doctor Name : Mohan Yadav", "Hospital Address : Mumbai", "Exp : 5yrs", "Mobile : 123456", "700"},
                    {"Doctor Name : Dev Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "600"},
                    {"Doctor Name : Aarya Yadav", "Hospital Address : Mumbai", "Exp : 2yrs", "Mobile : 123456", "500"},
                    {"Doctor Name : Parth Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "400"},
                    {"Doctor Name : Chirag Yadav", "Hospital Address : Mumbai", "Exp : 3yrs", "Mobile : 123456", "300"},
            };

    TextView tv;
    Button backbtn;

    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.docdetailtitle);
        backbtn = findViewById(R.id.detailsbackbtn);
        Intent in = getIntent();
        String title = in.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Physicians")==0)
            doctor_details = doctor_details2;
        else
        if (title.compareTo("Dietician")==0)
            doctor_details = doctor_details1;
        else
        if (title.compareTo("Dentists")==0)
            doctor_details = doctor_details2;
        else
        if (title.compareTo("Surgeon")==0)
            doctor_details = doctor_details3;
        else
            doctor_details = doctor_details5;

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<doctor_details.length;i++){
            //So basically hashmap is used to map the doctor details array
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5",doctor_details[i][4]+"/-");
            list.add(item);
        }
        //Below code is the maping of multi_line layout xml file..

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView list = findViewById(R.id.listviewdoctordetails);
        list.setAdapter(sa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent in = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                in.putExtra("text1",title);
                in.putExtra("text2",doctor_details[i][0]);
                in.putExtra("text3",doctor_details[i][1]);
                in.putExtra("text4",doctor_details[i][3]);
                in.putExtra("text5",doctor_details[i][4]);
                startActivity(in);
            }
        });

    }
}