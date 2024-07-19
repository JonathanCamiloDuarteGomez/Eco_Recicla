package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuInformacionUsuario extends AppCompatActivity {

    ImageButton imageButtonActualizacionDatos;
    ImageButton imageButtonHistorial;
    ImageButton imageButtonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_informacion_usuario);
        imageButtonActualizacionDatos = findViewById(R.id.buttonActualizacionDatos);
        imageButtonHistorial = findViewById(R.id.buttonHistorial);
        imageButtonHome = findViewById(R.id.btnIrAlMenuPrincipal);
        cambioDePantalla(imageButtonActualizacionDatos, Datos_Personales.class);
        cambioDePantalla(imageButtonHistorial, HistorialActivity.class);
        cambioDePantalla(imageButtonHome, MenuPrincipal.class);

    }
    private void cambioDePantalla(ImageButton nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MenuInformacionUsuario.this, clase);
                startActivity(next);
            }
        });
    }

}
