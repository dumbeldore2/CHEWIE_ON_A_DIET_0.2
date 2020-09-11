package com.example.chewie_on_a_diet_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    //variabelen
    EditText loginUserName, loginPassword;
    CheckBox loginCheckBoxForRemembering;
    Button loginInLoggenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialisatie van de variabelen
        loginUserName = findViewById(R.id.loginUserName);
        loginPassword = findViewById(R.id.loginPassword);
        loginCheckBoxForRemembering = findViewById(R.id.loginCheckBoxForRemembering);
        loginInLoggenButton = findViewById(R.id.loginInloggenButton);

        //initialisatie van de functies

    }

    public void loginInLoggenButton(){
        loginInLoggenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}