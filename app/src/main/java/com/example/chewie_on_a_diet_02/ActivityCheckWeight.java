package com.example.chewie_on_a_diet_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityCheckWeight extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar = null;

    //variabelen
    ListView listView;

    DatabaseWeight databaseWeight;

    ArrayList<String> weights;

    Button addweightCheckWeightButtonJavaClass;

    TextView infoLaatsteGewicht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_weight);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //declaratie van de variabelen
        databaseWeight = new DatabaseWeight(this);

        listView = findViewById(R.id.listViewCheckWeightListViewContent);

        weights = new ArrayList<>();

        addweightCheckWeightButtonJavaClass = findViewById(R.id.addweightCheckWeightButtonContent);

        infoLaatsteGewicht = findViewById(R.id.infoInfoCheckWeightTextviewContent);
        //declarisatie van de functies
        updateListView();
        buttonClick();
        updateTextView();
        naamFun();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){
            case R.id.nav_home:
                Intent intentHome = new Intent(ActivityCheckWeight.this, ActivityMain.class);
                startActivity(intentHome);
                break;
            case R.id.nav_vandaag:
                Intent intentVandaag = new Intent(ActivityCheckWeight.this, ActivityVandaag.class);
                startActivity(intentVandaag);
                break;
            case R.id.nav_food:
                Intent intentFood = new Intent(ActivityCheckWeight.this, ActivityFood.class);
                startActivity(intentFood);
                break;
            case R.id.nav_drink:
                Intent intentDrink = new Intent(ActivityCheckWeight.this, ActivityDrink.class);
                startActivity(intentDrink);
                break;
            case R.id.nav_check_weight:
                Intent intentCheckWeight = new Intent(ActivityCheckWeight.this,
                        ActivityCheckWeight.class);
                startActivity(intentCheckWeight);
                break;
            case R.id.nav_activity:
                Intent intentActivity = new Intent(ActivityCheckWeight.this, ActivityActivity.class);
                startActivity(intentActivity);
                break;

        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // de code hier
    public void updateListView(){
        weights = databaseWeight.info();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,weights);
        listView.setAdapter(adapter);
    }

    public void buttonClick(){
        addweightCheckWeightButtonJavaClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCheckWeight.this, ActivityAddWeight.class);
                startActivity(intent);
            }
        });
    }
    public void updateTextView(){
        if(databaseWeight.erZijnAlDatas()){
            infoLaatsteGewicht.setText(databaseWeight.getLaatsteGewicht());
        }
    }
    public void naamFun(){
        View headerView = navigationView.getHeaderView(0);
        TextView textView1NavHeader = (TextView) headerView.findViewById(R.id.TextView1NavHeader);
        textView1NavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivitySettings.class);
                startActivity(intent);
            }
        });
    }
}
