package com.example.healthcaremedicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * So basically in Register Activity we have map ids with findViewBy Ids we have code login validation with if else conditions
 * if both password and confirm password match record will be saved in database and we have used password validation
 * String code below maximum password length will be 8 digit.
 */

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername,edEmail,edPassword,edConfirm;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edEmail = findViewById(R.id.editTextRegEmail);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        button = findViewById(R.id.buttonRegister);
        textView = findViewById(R.id.textViewExistingUser);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);//Database object is created here..
                //Login Validation is code is provided here..
                if (username.length()==0||password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please enter all details",Toast.LENGTH_SHORT).show();
                }else {
                    //check password or Confirm password are same !!
                    if (password.compareTo(confirm)==0){
                        if (isvalid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Password must contain atleast 8 characters, in all forms",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"Please confirm password, password didn't match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    //password validation code..

    public static boolean isvalid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if (passwordhere.length() < 8){
            return false;

        }else {
            for (int p = 0; p < passwordhere.length(); p++){
                if (Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++){
                if (Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++){
                char c = passwordhere.charAt(s);
                if (c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}