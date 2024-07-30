package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Consejos extends AppCompatActivity {
    private ImageButton buttonBack;
    private TextView textViewTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        textViewTip = findViewById(R.id.textViewTip);
        buttonBack = findViewById(R.id.buttonBack);

        LoadDailyTip();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Consejos.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }

    private void LoadDailyTip() {
        SharedPreferences preferences = getSharedPreferences("Tips", MODE_PRIVATE);
        String dailyTip = preferences.getString("currentTip", "Consejo no Disponible");
        textViewTip.setText(dailyTip);
    }
}
