package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Datos_Personales extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);
        ImageButton imageButtonHome = findViewById(R.id.imageButtonHome);
        Button btnIrAmenuInformacionUsuario = findViewById(R.id.btnIrAmenuInformacionUsuario);

        btnIrAmenuInformacionUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Datos_Personales.this, MenuInformacionUsuario.class);
                startActivity(next);
            }
        });

        imageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Datos_Personales.this, MenuPrincipal.class);
                startActivity(next);
            }
        });
    }


}
