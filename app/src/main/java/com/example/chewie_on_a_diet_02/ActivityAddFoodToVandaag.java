package com.example.chewie_on_a_diet_02;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityAddFoodToVandaag extends AppCompatActivity {

    //variabelen
    DatabaseDrink databaseDrink;
    DatabaseFood databaseFood;
    DatabaseFull databaseFull;
    DatabaseDagEnCalorien databaseDagEnCalorien;
    ListView listViewFoodAddToVandaagListViewContent;
    ListView listViewDrinkAddToVandaagListViewContent;
    ArrayList<String>foods;
    ArrayList<String>drinks;
    Button addNewObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_food_to_vandaag);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialiseer variabelen
        databaseFood = new DatabaseFood(this);
        databaseDrink = new DatabaseDrink(this);
        databaseFull = new DatabaseFull(this);
        databaseDagEnCalorien = new DatabaseDagEnCalorien(this);
        listViewFoodAddToVandaagListViewContent =
                findViewById(R.id.listViewFoodAddToVandaagListViewContent);
        listViewDrinkAddToVandaagListViewContent =
                findViewById(R.id.listViewDrinkAddToVandaagListViewContent);
        foods = new ArrayList<>();
        drinks = new ArrayList<>();
        addNewObject = findViewById(R.id.AddNewObject);
        //initialiseer functies
        updateListViews();
        clickOnFood();
        clickOnDrink();
        buttonCreateNewObject();
    }


    //functies
    public void updateListViews(){
        foods = databaseFood.info();
        drinks = databaseDrink.info();

        ArrayAdapter arrayAdapterfood = new ArrayAdapter(this,android.R.layout.simple_list_item_1
                ,foods);
        ArrayAdapter arrayAdapterdrink = new ArrayAdapter(this,android.R.layout.simple_list_item_1
                ,drinks);

        listViewFoodAddToVandaagListViewContent.setAdapter(arrayAdapterfood);
        listViewDrinkAddToVandaagListViewContent.setAdapter(arrayAdapterdrink
        );
    }

    public void clickOnFood(){
        listViewFoodAddToVandaagListViewContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0 ; i < databaseFood.IDMAKER() ; i++){
                    if (position == i){

                        Date calendar = Calendar.getInstance().getTime();
                        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

                        ArrayList<String>objects;
                        objects = new ArrayList<>();
                        objects = databaseFood.get(i);

                        Intent intent = new Intent(getApplicationContext(),
                                ActivityFormat.class);
                        intent.putExtra("naam",""+objects.get(1));
                        intent.putExtra("merk",""+objects.get(2));
                        intent.putExtra("groote",""+objects.get(3));
                        intent.putExtra("calorien",""+objects.get(4));
                        intent.putExtra("deDatum",""+deDatum);
                        intent.putExtra("categorie","food");
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void clickOnDrink(){
        listViewDrinkAddToVandaagListViewContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0 ; i < databaseDrink.IDMAKER() ; i++){
                    if (position == i){


                        Date calendar = Calendar.getInstance().getTime();
                        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

                        ArrayList<String>objects;
                        objects = new ArrayList<>();
                        objects = databaseDrink.get(i);

                        Intent intent = new Intent(getApplicationContext(),
                                ActivityFormat.class);
                        intent.putExtra("naam",""+objects.get(1));
                        intent.putExtra("merk",""+objects.get(2));
                        intent.putExtra("groote",""+objects.get(3));
                        intent.putExtra("calorien",""+objects.get(4));
                        intent.putExtra("deDatum",""+deDatum);
                        intent.putExtra("categorie","drink");
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void buttonCreateNewObject(){
        addNewObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityFoodOrDrink.class);
                intent.putExtra("order",1);
                startActivity(intent);
            }
        });
    }
}
