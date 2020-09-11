package com.example.chewie_on_a_diet_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityFormat extends AppCompatActivity {
    //variabelen
    int huidigCalInt,getal,uit;
    ListView listView;
    ArrayList<String>arrayList;
    DatabaseFull databaseFull;
    String huidigCal,naam,merk,categorie;
    double gegevenGetal , gegevenGetal2;
    EditText editText;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_format);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //initialisatie variabelen
        int getal;
        listView = findViewById(R.id.listViewFormatListViewContent);
        arrayList = new ArrayList<>();
        databaseFull = new DatabaseFull(this);
        editText = findViewById(R.id.FormaatEditTextContent);
        button = findViewById(R.id.FormaatButtonContent);

        Intent get = getIntent();

        huidigCal = get.getStringExtra("calorien");
        huidigCalInt = Integer.parseInt(huidigCal.trim());
        naam = get.getStringExtra("naam");
        merk = get.getStringExtra("merk");
        categorie = get.getStringExtra("categorie");

        //initialisatie functies
        listViewFun();
        listViewClicker();
        buttonFun();
    }

    public void listViewFun(){
        arrayList.add("50 cl (shotglas)");
        arrayList.add("125 cl (koffietas)");
        arrayList.add("150 cl (wijnglas)");
        arrayList.add("175 cl (mok)");
        arrayList.add("200 cl (bierglas)");
        arrayList.add("340 cl (duvelglas)");
        arrayList.add("150 gr (soeplepel)");
        arrayList.add("250 cl (bord)");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                arrayList);
        listView.setAdapter(arrayAdapter);
    }


    public void listViewClicker(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    databaseFull.insert(naam,merk,50,huidigCalInt * 0.5, categorie);
                    Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                    startActivity(intent);
                }
                if (position == 1){
                    databaseFull.insert(naam,merk,125,huidigCalInt * 1.25, categorie);
                    Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                    startActivity(intent);
                }
                if (position == 2){
                    databaseFull.insert(naam,merk,150,huidigCalInt * 1.5, categorie);
                    Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                    startActivity(intent);
                }
                if (position == 3){
                    databaseFull.insert(naam,merk,175,huidigCalInt * 1.75, categorie);
                    Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                    startActivity(intent);
                }
                if (position == 4){
                    databaseFull.insert(naam,merk,200,huidigCalInt * 2, categorie);
                    Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                    startActivity(intent);
                }
                if (position == 5){
                    databaseFull.insert(naam,merk,340,huidigCalInt * 3.4, categorie);
                    Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                    startActivity(intent);
                }
                if (position == 6){
                    databaseFull.insert(naam,merk,150,huidigCalInt * 1.5, categorie);
                    Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                    startActivity(intent);
                }
                if (position == 7){
                    databaseFull.insert(naam,merk,250,huidigCalInt * 2.5, categorie);
                    Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void buttonFun(){
        // maken van het gegeven getal
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (editText.getText().toString().trim().isEmpty()){
                        gegevenGetal = 0;
                    } else
                        if (!editText.getText().toString().trim().isEmpty()){
                        gegevenGetal = Double.parseDouble(editText.getText().toString());
                        gegevenGetal2 = gegevenGetal / 100;

                        databaseFull.insert(naam,merk,(int)gegevenGetal,huidigCalInt * (gegevenGetal2), categorie);
                        Intent intent = new Intent(ActivityFormat.this, ActivityVandaag.class);
                        startActivity(intent);
                         }
                }
            });
    }
}
