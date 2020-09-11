package com.example.chewie_on_a_diet_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Struct;

public class ActivityRegistreren extends AppCompatActivity {
    //variabelen
    EditText username,password,emailadress;
    Button add;
    DatabaseAccounts databaseAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreren);

        //variabelen initialiseren
        username = findViewById(R.id.registerUserName);
        password = findViewById(R.id.registerPassword);
        emailadress = findViewById(R.id.registerEmailAdress);
        add = findViewById(R.id.registerAdd);
        databaseAccounts = new DatabaseAccounts(this);

        //functies initialiseren
        addfun();
    }

    //de fucnties
    public String getUsername(){
        if (username.toString().trim().isEmpty() || username == null)throw new IllegalArgumentException();
        return username.toString().trim();
    }

    public String getPassword(){
        if (password.toString().trim().isEmpty() || password == null)throw new IllegalArgumentException();
        return password.toString().trim();
    }

    public String getEmail(){
        if (emailadress.toString().trim().isEmpty() || emailadress == null)throw new IllegalArgumentException();
        return emailadress.toString().trim();
    }
    public void addfun(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAccounts.insertData(getUsername(),getPassword(),getEmail());
                Intent intent = new Intent(getApplicationContext(),ActivityMain.class);
                startActivity(intent);
            }
        });
    }
}