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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView tv;

    private DatePickerDialog datePickerDialog;

    private TimePickerDialog timePickerDialog;

    private Button dateButton, timeButton, btnBook, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textViewbookAppointment);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFess);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        btnBook = findViewById(R.id.buttonBookAppointment);
        btnBack = findViewById(R.id.buttonAppBack);

        //Edit text will not changeable or Editable..
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        //Here with the help on intent we are fetching the data..

        Intent in = getIntent();
        String title = in.getStringExtra("text1");
        String fullname = in.getStringExtra("text2");
        String address = in.getStringExtra("text3");
        String contact = in.getStringExtra("text4");
        String fee = in.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Fees:"+fee+"/-");

        //datepicker function is called below when wr click date dialog box appear is shown here code..
        initDatePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        //timepicker function is called below when we click time dialog box appear is shown here code..
        initTimePicker();

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
               if (db.checkAppointmentExists(username,title+" => "+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                   Toast.makeText(getApplicationContext(),"Appointment already booked",Toast.LENGTH_SHORT).show();
               }else {
                   db.addOrder(username,title+" => "+fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fee),"appointment");
                   Toast.makeText(getApplicationContext(),"Your appointment is booked successfully",Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
               }


            }
        });
    }

    //Here we have code the select date and time with the help of the date picker library

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

    private void initTimePicker(){

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);

            }
        };

        Calendar calendar = Calendar.getInstance();
        int hrs = calendar.get(Calendar.HOUR);
        int mins = calendar.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}