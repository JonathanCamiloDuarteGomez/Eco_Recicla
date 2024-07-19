package com.example.eco_recicla;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //variables
    Button btnInicioSesion;
    Button btnRegistrar;
    Button btnOlvContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        //asignacion de variables
        btnInicioSesion = findViewById(R.id.btnInicia_sesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnOlvContra = findViewById(R.id.btnOlvContra);

        //llamado de funciones

        cambioDePantalla(btnInicioSesion, MenuPrincipal.class); //en la siguiente funcion se ingresa el bton y la clase hacia donde se va
        cambioDePantalla(btnRegistrar, CrearCuentaActivity.class);
        cambioDePantalla(btnOlvContra, RecuperarContrasena.class);


    }
    private void cambioDePantalla(Button nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, clase);
                startActivity(next);
            }
        });
    }
}
