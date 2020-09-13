package com.example.chewie_on_a_diet_02;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAddActivity extends AppCompatActivity {
    //variabelen
    EditText duurActivityEditTextJavaClass;
    EditText aantalCalActivityEditTextJavaClass;
    EditText extraInfoActivityEditTextJavaClass;
    Button addActivityButtonJavaClass;
    DatabaseActivity databaseActivity;
    DatabaseAccounts databaseAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialisatie van variabelen
        duurActivityEditTextJavaClass = findViewById(R.id.duurActivityEditTextContent);
        aantalCalActivityEditTextJavaClass = findViewById(R.id.aantalCalActivityEditTextContent);
        extraInfoActivityEditTextJavaClass = findViewById(R.id.extraInfoActivityEditTextContent);
        addActivityButtonJavaClass = findViewById(R.id.addActivityButtonContent);
        databaseActivity = new DatabaseActivity(this);
        databaseAccounts = new DatabaseAccounts(this);
        //initialisatie van de functies
        addToDB();

    }

    public void addToDB(){
        addActivityButtonJavaClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(duurActivityEditTextJavaClass.getText().toString()) > 0){
                    if (Integer.parseInt(aantalCalActivityEditTextJavaClass.getText().toString())>0){
                        if (!extraInfoActivityEditTextJavaClass.getText().toString().trim().isEmpty()){
                            databaseActivity.insertData(Integer.parseInt(duurActivityEditTextJavaClass.getText().toString()),Integer.parseInt(aantalCalActivityEditTextJavaClass.getText().toString()),extraInfoActivityEditTextJavaClass.getText().toString(),databaseAccounts.getLaatsteId());
                            Intent intent = new Intent(ActivityAddActivity.this,
                                    ActivityActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}
