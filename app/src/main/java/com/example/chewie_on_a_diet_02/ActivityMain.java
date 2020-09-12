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
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar = null;
    View headerView;
    TextView textView1NavHeader;
    DatabaseAccounts databaseAccounts;
    TextView textView2NavHeader;
    //variabelen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        textView1NavHeader = headerView.findViewById(R.id.TextView1NavHeader);
        databaseAccounts = new DatabaseAccounts(this);
        textView2NavHeader = headerView.findViewById(R.id.TextView2NavHeader);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //declareer variabelen
        //declareer functies
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
                Intent intentHome = new Intent(ActivityMain.this, ActivityMain.class);
                startActivity(intentHome);
                break;
            case R.id.nav_vandaag:
                Intent intentVandaag = new Intent(ActivityMain.this, ActivityVandaag.class);
                startActivity(intentVandaag);
                break;
            case R.id.nav_food:
                Intent intentFood = new Intent(ActivityMain.this, ActivityFood.class);
                startActivity(intentFood);
                break;
            case R.id.nav_drink:
                Intent intentDrink = new Intent(ActivityMain.this, ActivityDrink.class);
                startActivity(intentDrink);
                break;
            case R.id.nav_check_weight:
                Intent intentCheckWeight = new Intent(ActivityMain.this,
                        ActivityCheckWeight.class);
                startActivity(intentCheckWeight);
                break;
            case R.id.nav_activity:
                Intent intentActivity = new Intent(ActivityMain.this, ActivityActivity.class);
                startActivity(intentActivity);
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void naamFun(){
        textView1NavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this,ActivitySettings.class);
                System.out.println("we are gaming now");
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
