package com.example.chewie_on_a_diet_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivitySettings extends AppCompatActivity {

    //variabelen
    TextView settings,inloggen,registreren,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //variabelen initialiseren
        settings = findViewById(R.id.ActivitySettingsSettingsTextViewContent);
        inloggen = findViewById(R.id.ActivitySettingsinloggenTextViewContent);
        registreren = findViewById(R.id.ActivitySettingsregistrerenTextViewContent);
        back = findViewById(R.id.ActivitySettingsBackTextViewContent);
        //initialisatie van de functies
        settingsfun();
        inloggenfun();
        registrerenfun();
        backfun();
    }

    //hier komt de code
    public void settingsfun(){
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivitySettingsFinal.class);
                startActivity(intent);
            }
        });

    }
    public void inloggenfun(){
        inloggen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityLogin.class);
                startActivity(intent);
            }
        });
    }
    public void registrerenfun(){
        registreren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityRegistreren.class);
                startActivity(intent);
            }
        });
    }
    public void backfun(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityMain.class);
                startActivity(intent);
            }
        });
    }
}