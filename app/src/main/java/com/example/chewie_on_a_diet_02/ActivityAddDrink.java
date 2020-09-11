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
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityAddDrink extends AppCompatActivity {
    //variabelen
    EditText naamAddDrinkEditTextJavaClass;
    EditText merkAddDrinkEditTextJavaClass;
    EditText calorienAddDrinkEditTextJavaClass;
    Button addAddDrinkButtonJavaClass;
    DatabaseDrink databaseDrink;
    int order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_drink);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // declarisatie van de variabelen en de plek waar functies worden geplaatst
        naamAddDrinkEditTextJavaClass  = findViewById(R.id.naamAddDrinkEditTextContent);
        merkAddDrinkEditTextJavaClass  = findViewById(R.id.merkAddDrinkEditTextContent);
        calorienAddDrinkEditTextJavaClass  = findViewById(R.id.calorienAddDrinkEditTextContent);

        addAddDrinkButtonJavaClass  = findViewById(R.id.addAddDrinkButtonContent);

        databaseDrink = new DatabaseDrink(this);

        Intent intent = getIntent();
        order = intent.getIntExtra("order",-1);
        System.out.println(order);

        //functies
        addDrinkToDataBaseFinal();
    }


    // vanaf hier de code
    public void addDrinkToDataBase(){
        if (!naamAddDrinkEditTextJavaClass.getText().toString().trim().isEmpty()){
            if (!merkAddDrinkEditTextJavaClass.getText().toString().trim().isEmpty()){
                    if (Integer.parseInt(calorienAddDrinkEditTextJavaClass.getText().toString())
                            > -1){
                        ObjectDrink drinkObject = new ObjectDrink(naamAddDrinkEditTextJavaClass.getText().toString(),
                                merkAddDrinkEditTextJavaClass.getText().toString(), 100 ,
                                Integer.parseInt(calorienAddDrinkEditTextJavaClass.getText().toString()));
                        if (drinkObject != null){
                            databaseDrink.insertDrinkObject(drinkObject);
                        }
                    }
            }
        }
    }

    public void addDrinkToDataBaseFinal(){
        addAddDrinkButtonJavaClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrinkToDataBase();
                if (order == 1){
                Intent intent = new Intent(getApplicationContext(),ActivityFormat.class);

                Date calendar = Calendar.getInstance().getTime();
                String deDatum = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar);

                intent.putExtra("naam",""+naamAddDrinkEditTextJavaClass.getText().toString().trim());
                intent.putExtra("merk",""+merkAddDrinkEditTextJavaClass.getText().toString().trim());
                intent.putExtra("groote",""+100);
                intent.putExtra("calorien",""+Integer.parseInt(calorienAddDrinkEditTextJavaClass.getText().toString()));
                intent.putExtra("deDatum",""+deDatum);
                intent.putExtra("categorie","drink");
                startActivity(intent);
            } else if (order == 0){
                Intent intent = new Intent(getApplicationContext(), ActivityDrink.class);
                startActivity(intent);
            }
            }
        });
    }
}
