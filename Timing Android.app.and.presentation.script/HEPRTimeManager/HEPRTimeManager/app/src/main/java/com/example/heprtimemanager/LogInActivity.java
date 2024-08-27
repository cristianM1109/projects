package com.example.heprtimemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    private EditText user;
    private EditText pass;
    private Button login;

    private static final String Username = "hepr";
    private static final String Password = "Hepr2017";


    static boolean isLogged = false;
    private boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        user = findViewById(R.id.inputUser);
        pass = findViewById(R.id.inputPassword);
        login = findViewById(R.id.logInBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUser = user.getText().toString();
                String inputPass = pass.getText().toString();

                if(inputUser.isEmpty() || inputPass.isEmpty()){
                    Toast.makeText(LogInActivity.this, "Please insert information!", Toast.LENGTH_SHORT).show();
                }else{
                    isValid = validate(inputUser,inputPass);

                    if(!isValid){
                        Toast.makeText(LogInActivity.this, "Incorrect username or password!", Toast.LENGTH_SHORT).show();
                    }else{
                     Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                        Toast.makeText(LogInActivity.this, "User Logged In", Toast.LENGTH_SHORT).show();
                     startActivity(intent);
                    }
                }

            }
        });
    }

    private boolean validate(String username,String password){

        if(username.equals(Username) && password.equals(Password)){
            isLogged=true;
            return true;
        }
     return false;
    }

}