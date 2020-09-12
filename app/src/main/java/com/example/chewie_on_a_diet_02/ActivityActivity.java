package com.example.chewie_on_a_diet_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar = null;

    TextView activityInfoActivityTextviewJavaClass;
    TextView textView1NavHeader;
    ListView listView;
    Button button;
    DatabaseActivity databaseActivity;
    DatabaseAccounts databaseAccounts;
    ArrayList activitys;
    View headerView;
    TextView textView2NavHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
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

        //initialisatie van de variabelen
        activityInfoActivityTextviewJavaClass = findViewById(R.id.activityInfoActivityTextviewContent);
        listView = findViewById(R.id.listViewActivityListViewContent);
        button = findViewById(R.id.addActivityButtonContent);
        databaseActivity = new DatabaseActivity(this);
        databaseAccounts = new DatabaseAccounts(this);
        activitys = new ArrayList();
        headerView = navigationView.getHeaderView(0);
        textView1NavHeader = headerView.findViewById(R.id.TextView1NavHeader);
        textView2NavHeader = headerView.findViewById(R.id.TextView2NavHeader);


        //initialisatie van de functies
        updateListView();
        naamFun();
        tweedeNaamFun();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //dit is voor het menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        switch (id){
            case R.id.nav_home:
                Intent intentHome = new Intent(ActivityActivity.this, ActivityMain.class);
                startActivity(intentHome);
                break;
            case R.id.nav_vandaag:
                Intent intentVandaag = new Intent(ActivityActivity.this, ActivityVandaag.class);
                startActivity(intentVandaag);
                break;
            case R.id.nav_food:
                Intent intentFood = new Intent(ActivityActivity.this, ActivityFood.class);
                startActivity(intentFood);
                break;
            case R.id.nav_drink:
                Intent intentDrink = new Intent(ActivityActivity.this, ActivityDrink.class);
                startActivity(intentDrink);
                break;
            case R.id.nav_check_weight:
                Intent intentCheckWeight = new Intent(ActivityActivity.this,
                        ActivityCheckWeight.class);
                startActivity(intentCheckWeight);
                break;
            case R.id.nav_activity:
                Intent intentActivity = new Intent(ActivityActivity.this, ActivityActivity.class);
                startActivity(intentActivity);
                break;
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //decode

    public void updateListView(){
        activitys = databaseActivity.info();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,activitys);
        listView.setAdapter(adapter);
    }


    public void naamFun(){
        textView1NavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivitySettings.class);
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
