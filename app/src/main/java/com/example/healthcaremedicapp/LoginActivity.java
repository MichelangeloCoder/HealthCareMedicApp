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

/**
 * So basically in Login Activity we have performed here user login credentials with validations if else condition and used of SharedPreferences to store the data
 * we have used SQLitedatabase object name healthcare if login successfully toast message appear and it will redirect to HomeActivity Class.
 */

public class LoginActivity extends AppCompatActivity {

    EditText edUsername,edPassword;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextTextLoginPassword);
        button = findViewById(R.id.LoginButton);
        textView = findViewById(R.id.textViewNewUserRegistration);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                //Login Validation is code is provided here..
               if (username.length()==0 || password.length()==0){
                   Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();
               }else {
                   if (db.login(username,password)==1){
                       Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                       SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs",Context.MODE_PRIVATE);
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("username",username);
                       // to save our data with key and value pairs.
                       editor.apply();
                       startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                   }else {
                       Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                   }
               }

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

}