package com.example.healthcaremedicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;
    private String[][] packages={};

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,btnCheckout, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        //Mapping of Ids with the findViewById method..

        dateButton = findViewById(R.id.BMbuttonCartDate);
        btnCheckout = findViewById(R.id.buttonBMCartCheckout);
        btnBack = findViewById(R.id.buttonBMCartBack);
        tvTotal = findViewById(R.id.textViewBMCartTotalCost);
        lst = findViewById(R.id.listViewBMCart);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        //Its a code to create a Database object and database name is healthcare..
        Database db = new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username,"medicine");
//        Toast.makeText(getApplicationContext(),""+dbData,Toast.LENGTH_SHORT).show();

        packages = new String[dbData.size()][];
        for (int i=0;i<packages.length;i++){
            packages[i] = new String[5];
        }

        // for loop goes to dbdata size split the string with the help of sign first index store in package and
        for (int i=0;i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost : "+strData[1]+"/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }
        //We set the total in the text string..
        tvTotal.setText("Total Cost : "+totalAmount);

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put( "line1", packages[i][0]);
            item.put( "line2", packages[i][1]);
            item.put( "line3", packages[i][2]);
            item.put( "line4", packages[i][3]);
            item.put( "line5", packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        lst.setAdapter(sa);




        //btbBack setonClicklistner will redirect to LabtestActitivty..
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineActivity.class));
            }
        });

        //btnCheckout we have passed some data price date time in labtestbookActivity..

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(CartBuyMedicineActivity.this,BuyMedicineBookActivity.class);
                in.putExtra("price",tvTotal.getText());
                in.putExtra("date",dateButton.getText());
                startActivity(in);
            }
        });

        //datepicker function is called below when wr click date dialog box appear is shown here code..
        initDatePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()+86400000);

    }
}