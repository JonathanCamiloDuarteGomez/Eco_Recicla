package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ImageButton btnGestionDeRe = findViewById(R.id.buttonGestionDeRe);
        ImageButton btnIncentivosRecompensas = findViewById(R.id.buttonIncentivosRecompensas);
        ImageButton btnEstadisticasDeReciclaje = findViewById(R.id.buttonEstadisticasDeReciclaje);
        ImageButton btnDatosPersonales = findViewById(R.id.buttonInformacionDeUsuario);
        ImageButton btnSalir = findViewById(R.id.floatingActionButton2);

        //en la siguiente funcion se ingresa el bton y la clase hacia donde se va
<<<<<<< HEAD
=======

>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa
        cambioDePantalla(btnGestionDeRe, GestionDeReciclajeAgregarObjeto.class);
        cambioDePantalla(btnIncentivosRecompensas, Premios.class);
        cambioDePantalla(btnEstadisticasDeReciclaje, HistorialActivity.class);
        cambioDePantalla(btnDatosPersonales, MenuInformacionUsuario.class);
        cambioDePantalla(btnSalir, MainActivity.class);//cambiar a login
        //no olvidar reemplazar la clase pa donde se dirije, no son HistorialActivity
    }

    private void cambioDePantalla(ImageButton nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MenuPrincipal.this, clase);
                startActivity(next);
                finish();
            }
        });
    }
}