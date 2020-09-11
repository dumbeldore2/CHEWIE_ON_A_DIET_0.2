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
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityAddFood extends AppCompatActivity{
    //variabelen
    EditText naamAddFoodEditTextJavaClass;
    EditText merkAddFoodEditTextJavaClass;
    EditText calorienAddFoodEditTextJavaClass;
    Button addAddFoodButtonJavaClass;
    DatabaseFood databaseFood;
    int order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_food);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // declarisatie van de variabelen en de plek waar functies worden geplaatst
        naamAddFoodEditTextJavaClass  = findViewById(R.id.naamAddFoodEditTextContent);
        merkAddFoodEditTextJavaClass  = findViewById(R.id.merkAddFoodEditTextContent);
        calorienAddFoodEditTextJavaClass  = findViewById(R.id.calorienAddFoodEditTextContent);

        addAddFoodButtonJavaClass  = findViewById(R.id.addAddFoodButtonContent);

        databaseFood = new DatabaseFood(this);

        Intent intent = getIntent();
        order = intent.getIntExtra("order",-1);
        System.out.println(order);
        //functies
        addFoodToDataBaseFinal();
    }

    // de code
    public void addFoodToDataBase(){
        if (!naamAddFoodEditTextJavaClass.getText().toString().trim().isEmpty()){
            if (!merkAddFoodEditTextJavaClass.getText().toString().trim().isEmpty()){
                    if (Integer.parseInt(calorienAddFoodEditTextJavaClass.getText().toString()) > 0){
                        ObjectFood foodObject = new ObjectFood(naamAddFoodEditTextJavaClass.getText().toString(),
                                merkAddFoodEditTextJavaClass.getText().toString(),100,
                                Integer.parseInt(calorienAddFoodEditTextJavaClass.getText().toString()));
                        if (foodObject != null){
                            databaseFood.insertFoodObject(foodObject);
                        }
                    }
            }
        }
    }

    public void addFoodToDataBaseFinal(){
        addAddFoodButtonJavaClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodToDataBase();
                if (order == 1){
                    Intent intent = new Intent(getApplicationContext(),ActivityFormat.class);

                    Date calendar = Calendar.getInstance().getTime();
                    String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

                    intent.putExtra("naam",""+naamAddFoodEditTextJavaClass.getText().toString().trim());
                    intent.putExtra("merk",""+merkAddFoodEditTextJavaClass.getText().toString().trim());
                    intent.putExtra("groote",""+100);
                    intent.putExtra("calorien",""+Integer.parseInt(calorienAddFoodEditTextJavaClass.getText().toString()));
                    intent.putExtra("deDatum",""+deDatum);
                    intent.putExtra("categorie","drink");
                    startActivity(intent);
                } else if (order == 0){
                    Intent intent = new Intent(getApplicationContext(), ActivityFood.class);
                    startActivity(intent);
                }
            }
        });
    }
}

