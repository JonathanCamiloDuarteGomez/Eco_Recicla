package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Estadisticas extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button buttonClear;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        tableLayout = findViewById(R.id.myTableLayout);
        buttonClear = findViewById(R.id.buttonClear);
        buttonBack = findViewById(R.id.buttonBack);

        loadData();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y vuelve a la anterior
            }
        });
    }

    private void loadData() {
        SharedPreferences waterPrefs = getSharedPreferences("DataP", MODE_PRIVATE);
        //SharedPreferences electricityPrefs = getSharedPreferences("ElectricityData", MODE_PRIVATE);

        int waterIndex = waterPrefs.getInt("index", 0);
       // int electricityIndex = electricityPrefs.getInt("index", 0);

        for (int i = 0; i < waterIndex; i++) {
            String email = waterPrefs.getString("email" + i, "");
            String Telefono = waterPrefs.getString("telefono" + i,"");
            String Direccion = waterPrefs.getString("adress" + i, "");


            TableRow tableRow = new TableRow(this);

            TextView textViewMonth = new TextView(this);
            textViewMonth.setText(email);
            textViewMonth.setBackgroundResource(R.color.white);
            tableRow.addView(textViewMonth);

            TextView textViewService = new TextView(this);
            textViewService.setText(Telefono);
            textViewService.setBackgroundResource(R.color.white);
            tableRow.addView(textViewService);

            TextView textViewConsumption = new TextView(this);
            textViewConsumption.setText(Direccion);
            textViewConsumption.setBackgroundResource(R.color.white);
            tableRow.addView(textViewConsumption);

            tableLayout.addView(tableRow);
        }

    }

    private void clearData() {
        SharedPreferences waterPrefs = getSharedPreferences("DataP", MODE_PRIVATE);

        SharedPreferences.Editor waterEditor = waterPrefs.edit();
        waterEditor.clear();
        waterEditor.apply();

        tableLayout.removeAllViews();

        Toast.makeText(this, "Datos borrados", Toast.LENGTH_SHORT).show();
    }
}