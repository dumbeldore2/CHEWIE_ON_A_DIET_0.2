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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityVandaag extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar = null;

    //variabelen
    DatabaseDagEnCalorien databaseDagEnCalorien;
    DatabaseFull databaseFull;
    DatabaseActivity databaseActivity;
    Button addFoodVandaagButtonJavaClass;
    TextView eigenCalorienVandaagTextViewJavaClass;
    TextView gemCalorienPerDagVandaagTextviewJavaClass;
    ListView listViewVandaagListViewJavaClass;
    ListView listViewTweeVandaagListViewJavaClass;
    ArrayList<String>today;
    ArrayList<String>todayActivity;
    TextView textView1NavHeader;
    View headerView;
    DatabaseAccounts databaseAccounts;
    TextView textView2NavHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vandaag);
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


        //initalisatie van de variabelen
        databaseDagEnCalorien = new DatabaseDagEnCalorien(this);
        databaseFull = new DatabaseFull(this);
        databaseActivity = new DatabaseActivity(this);
        addFoodVandaagButtonJavaClass = findViewById(R.id.addFoodVandaagButtonContent);
        eigenCalorienVandaagTextViewJavaClass = findViewById(R.id.eigenCalorienVandaagTextViewContent);
        gemCalorienPerDagVandaagTextviewJavaClass = findViewById(R.id.gemCalorienPerDagVandaagTextviewContent);
        listViewVandaagListViewJavaClass = findViewById(R.id.liSTviewVandaagListViewContent);
        listViewTweeVandaagListViewJavaClass = findViewById(R.id.liSTviewtweedeVandaagListViewContent);
        today = new ArrayList<>();
        todayActivity = new ArrayList<>();
        headerView = navigationView.getHeaderView(0);
        textView1NavHeader = (TextView) headerView.findViewById(R.id.TextView1NavHeader);
        databaseAccounts = new DatabaseAccounts(this);
        textView2NavHeader = headerView.findViewById(R.id.TextView2NavHeader);


        //initalisatie van de functies
        vulDBin();
        addFun();
        upDateTextviewEigenCalorien();
        upDateTextViewGemAaantalCalorienLaatsteZevenDagen();
        updateListView();
        updateListViewtwee();
        naamFun();
        tweedeNaamFun();


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
                Intent intentHome = new Intent(ActivityVandaag.this, ActivityMain.class);
                startActivity(intentHome);
                break;
            case R.id.nav_vandaag:
                Intent intentVandaag = new Intent(ActivityVandaag.this, ActivityVandaag.class);
                startActivity(intentVandaag);
                break;
            case R.id.nav_food:
                Intent intentFood = new Intent(ActivityVandaag.this, ActivityFood.class);
                startActivity(intentFood);
                break;
            case R.id.nav_drink:
                Intent intentDrink = new Intent(ActivityVandaag.this, ActivityDrink.class);
                startActivity(intentDrink);
                break;
            case R.id.nav_check_weight:
                Intent intentCheckWeight = new Intent(ActivityVandaag.this,
                        ActivityCheckWeight.class);
                startActivity(intentCheckWeight);
                break;
            case R.id.nav_activity:
                Intent intentActivity = new Intent(ActivityVandaag.this, ActivityActivity.class);
                startActivity(intentActivity);
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    // vanaf hier de externe functies
    public void vulDBin() {
        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        if (databaseDagEnCalorien.ErIsNogNiksVanDieDatum(deDatum,databaseAccounts.getLaatsteId())) {
            databaseDagEnCalorien.insertDagObject(databaseAccounts.getLaatsteId());
        }
    }

    public void addFun(){
        addFoodVandaagButtonJavaClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityVandaag.this,ActivityFoodOrActivity.class);
                startActivity(intent);
            }
        });
    }

    public void upDateTextviewEigenCalorien(){
        Date calendar = Calendar.getInstance().getTime();
        String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

        if (databaseFull.erZijnAlDatas(databaseAccounts.getLaatsteId())){

            databaseDagEnCalorien.updateDatabase(databaseFull.berekenDagelijkseCal(deDatum,databaseAccounts.getLaatsteId()),databaseAccounts.getLaatsteId());
            eigenCalorienVandaagTextViewJavaClass.setText("today : " + databaseDagEnCalorien.getCalHuidig(databaseAccounts.getLaatsteId()));

        } else {
            if (!databaseFull.erZijnAlDatas(databaseAccounts.getLaatsteId())){
                eigenCalorienVandaagTextViewJavaClass.setText("today : 0 CAL");
            }
        }
    }

    public void upDateTextViewGemAaantalCalorienLaatsteZevenDagen(){
        if (databaseDagEnCalorien.erZijnAlDatas(databaseAccounts.getLaatsteId())){
            String uitkomst = "";
            uitkomst += databaseDagEnCalorien.berekenGemiddeldefinal(databaseAccounts.getLaatsteId());
            gemCalorienPerDagVandaagTextviewJavaClass.setText("gem last 7 : " +uitkomst + " cal");
        }
    }

    public void updateListView(){
        today = databaseFull.info(databaseAccounts.getLaatsteId());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                today);
        listViewVandaagListViewJavaClass.setAdapter(arrayAdapter);
    }

    public void updateListViewtwee(){
        if (databaseActivity.erZijnAlDatas(databaseAccounts.getLaatsteId())){
            todayActivity = databaseActivity.infotwee(databaseAccounts.getLaatsteId());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,todayActivity);
            listViewTweeVandaagListViewJavaClass.setAdapter(adapter);
        }
    }
    public void naamFun(){
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
