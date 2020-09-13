package com.example.chewie_on_a_diet_02;

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

public class
ActivityDrink extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar = null;

    //variabelen
    ListView listViewDrinkListViewJavaClass;
    DatabaseDrink databaseDrink;
    ArrayList<String>drinks;
    Button addDrinkDrinkButtonJavaClass;
    TextView textView1NavHeader;
    View headerView;
    DatabaseAccounts databaseAccounts;
    TextView textView2NavHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
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

        //Declaratie van de variabelen
        listViewDrinkListViewJavaClass = findViewById(R.id.listViewDrinkListViewContent);
        databaseDrink = new DatabaseDrink(this);
        drinks = new ArrayList<>();
        addDrinkDrinkButtonJavaClass = findViewById(R.id.addDrinkDrinkButtonContent);
        headerView = navigationView.getHeaderView(0);
        textView1NavHeader = (TextView) headerView.findViewById(R.id.TextView1NavHeader);
        databaseAccounts = new DatabaseAccounts(this);
        textView2NavHeader = headerView.findViewById(R.id.TextView2NavHeader);

        //Declaratie van de functies

        updateListView();
        addButtonFun();
        naamFun();
        tweedeNaamFun();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // dit is het menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        switch (id) {

            case R.id.nav_home:
                Intent intentHome = new Intent(ActivityDrink.this, ActivityMain.class);
                startActivity(intentHome);
                break;
            case R.id.nav_vandaag:
                Intent intentVandaag = new Intent(ActivityDrink.this, ActivityVandaag.class);
                startActivity(intentVandaag);
                break;
            case R.id.nav_food:
                Intent intentFood = new Intent(ActivityDrink.this, ActivityFood.class);
                startActivity(intentFood);
                break;
            case R.id.nav_drink:
                Intent intentDrink = new Intent(ActivityDrink.this, ActivityDrink.class);
                startActivity(intentDrink);
                break;
            case R.id.nav_check_weight:
                Intent intentCheckWeight = new Intent(ActivityDrink.this,
                        ActivityCheckWeight.class);
                startActivity(intentCheckWeight);
                break;
            case R.id.nav_activity:
                Intent intentActivity = new Intent(ActivityDrink.this, ActivityActivity.class);
                startActivity(intentActivity);
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateListView(){
        drinks = databaseDrink.info(databaseAccounts.getLaatsteId());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                drinks);
        listViewDrinkListViewJavaClass.setAdapter(arrayAdapter);
    }

    public void addButtonFun(){
        addDrinkDrinkButtonJavaClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDrink.this,ActivityAddDrink.class);
                intent.putExtra("order",0);
                startActivity(intent);
            }
        });
    }
    public void naamFun(){
        View headerView = navigationView.getHeaderView(0);
        TextView textView1NavHeader = (TextView) headerView.findViewById(R.id.TextView1NavHeader);
        textView1NavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivitySettings.class);
                startActivity(intent);
            }
        });
    }

    public void tweedeNaamFun(){
        if (databaseAccounts.erIsAlDatas()){
            textView1NavHeader.setText(databaseAccounts.getLaatsteUsername());
            textView2NavHeader.setText(databaseAccounts.getLaatstemail());
        }
    }
}
