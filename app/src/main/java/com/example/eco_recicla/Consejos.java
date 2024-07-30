package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Consejos extends AppCompatActivity {
private ImageButton buttonBack;
private TextView textViewTip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consejos);

        textViewTip = findViewById(R.id.textViewTip);

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Consejos.this,MenuPrincipal.class);
                startActivity(intent);

            }

        });
    }
    private void LoadDailyTip(){
        SharedPreferences preferences = getSharedPreferences("Tips",MODE_PRIVATE);
        String dailyTip = preferences.getString("CurrenTip","Consejo no Disponible");
        textViewTip.setText(dailyTip);
    }
}