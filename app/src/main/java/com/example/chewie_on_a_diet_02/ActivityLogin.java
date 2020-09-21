package com.example.chewie_on_a_diet_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends AppCompatActivity {

    //variabelen
    EditText loginUserName;
    EditText loginPassword;
    Button loginInLoggenButton;
    DatabaseAccounts databaseAccounts;
    TextView textViewExtraInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialisatie van de variabelen
        loginUserName = findViewById(R.id.loginUserName);
        loginPassword = findViewById(R.id.loginPassword);
        loginInLoggenButton = findViewById(R.id.loginInloggenButton);
        databaseAccounts = new DatabaseAccounts(this);
        textViewExtraInfo = findViewById(R.id.extraInfoInloggenActivity);

        //initialisatie van de functies
        loginInLoggenButton();
    }

    public String getLoginName(){
        return loginUserName.getText().toString().trim();
    }

    public void loginInLoggenButton(){
        loginInLoggenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(databaseAccounts.checkOfErIemandBestaatMetDezeNaam(getLoginName()));
                if(databaseAccounts.checkOfErIemandBestaatMetDezeNaam(getLoginName())){
                    databaseAccounts.updateAllesNaarFals();
                    databaseAccounts.updateTrueTrue(databaseAccounts.nummerCheckOfErIemandBestaatMetDezeNaam(getLoginName()));
                    Intent intent = new Intent(getApplicationContext(),ActivityMain.class);
                    startActivity(intent);
                }
            }
        });
    }
}