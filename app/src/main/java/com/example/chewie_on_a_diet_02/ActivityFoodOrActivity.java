package com.example.chewie_on_a_diet_02;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;

public class ActivityFoodOrActivity extends AppCompatActivity {

    Button foodOrDrink;
    Button activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_food_or_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodOrDrink = findViewById(R.id.kiesfoodToevoegenFoodOrActivityButtonContent);
        activity = findViewById(R.id.kiesActivityToevoegenFoodOrActivityButtonContent);

        button1();
        button2();
    }

    public void button1(){
        foodOrDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFoodOrActivity.this,
                        ActivityAddFoodToVandaag.class);
                startActivity(intent);
            }
        });
    }
    public void button2(){
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFoodOrActivity.this,
                        ActivityAddActivity.class);
                startActivity(intent);
            }
        });
    }
}
