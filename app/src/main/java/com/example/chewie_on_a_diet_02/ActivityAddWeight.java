package com.example.chewie_on_a_diet_02;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.Button;
import android.widget.EditText;

public class ActivityAddWeight extends AppCompatActivity {
    //variabelen
    EditText gewichtAddWeightEditTextJavaClass;
    Button addAddWeightButtonJavaClass;
    DatabaseWeight databaseWeight;
    DatabaseAccounts databaseAccounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_weight);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //declarisatie van de variabelen
        gewichtAddWeightEditTextJavaClass = findViewById(R.id.gewichtAddWeightEditTextContent);
        addAddWeightButtonJavaClass = findViewById(R.id.addAddWeightButtonContent);
        databaseWeight = new DatabaseWeight(this);
        databaseAccounts = new DatabaseAccounts(this);

        //declarisatie van de functies
        addGewichtToDBFinal();
    }

    //de code
    public void addGewichtToDB(){
        if (!gewichtAddWeightEditTextJavaClass.getText().toString().trim().isEmpty()){
            databaseWeight.insertData(gewichtAddWeightEditTextJavaClass.getText().toString(),
                    databaseAccounts.getLaatsteId());
        }
    }

    public void addGewichtToDBFinal(){
        addAddWeightButtonJavaClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGewichtToDB();
                Intent intent = new Intent(ActivityAddWeight.this, ActivityCheckWeight.class);
                startActivity(intent);
            }
        });

    }
}
