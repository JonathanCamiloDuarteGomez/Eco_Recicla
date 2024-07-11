package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ImageButton btnGestionDeRe = findViewById(R.id.buttonGestionDeRe);
        ImageButton btnIncentivosRecompensas = findViewById(R.id.buttonIncentivosRecompensas);
        ImageButton btnEstadisticasDeReciclaje = findViewById(R.id.buttonEstadisticasDeReciclaje);
        ImageButton btnDatosPersonales = findViewById(R.id.buttonDatosPersonales);
        ImageButton btnSalir = findViewById(R.id.floatingActionButton2);

        //en la siguiente funcion se ingresa el bton y la clase hacia donde se va
        cambioDePantalla(btnGestionDeRe, MainActivity.class);
        cambioDePantalla(btnIncentivosRecompensas, MainActivity.class);
        cambioDePantalla(btnEstadisticasDeReciclaje, MainActivity.class);
        cambioDePantalla(btnDatosPersonales, MainActivity.class);
        cambioDePantalla(btnSalir, MainActivity.class);
        //no olvidar reemplazar la clase pa donde se dirije, no son MainActivity
    }

    private void cambioDePantalla(ImageButton nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MenuPrincipal.this, clase);
                startActivity(next);
            }
        });
    }
}