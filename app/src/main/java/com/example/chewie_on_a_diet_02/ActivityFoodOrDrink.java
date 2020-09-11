package com.example.chewie_on_a_diet_02;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class ActivityFoodOrDrink extends AppCompatActivity {


    Button food;
    Button drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_food_or_drink);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        food = findViewById(R.id.kiesfoodToevoegenFoodOrDrinkButtonContent);
        drink = findViewById(R.id.kiesDrinkToevoegenFoodOrDrinkButtonContent);

        foodFun();
        drinkFun();
    }

    public void foodFun(){
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityAddFood.class);
                intent.putExtra("order" , 1);
                startActivity(intent);
            }
        });
    }

    public void drinkFun(){
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityAddDrink.class);
                intent.putExtra("order",1);
                startActivity(intent);
            }
        });
    }
}