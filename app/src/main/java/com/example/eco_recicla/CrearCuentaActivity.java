package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CrearCuentaActivity extends AppCompatActivity {
    //asignacion de variables
    Button btnIrAloginRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        btnIrAloginRegistrar = findViewById(R.id.btnIrAloginRegistrar);

        //llamado de funciones

        cambioDePantalla(btnIrAloginRegistrar, MainActivity.class); //en la siguiente funcion se ingresa el bton y la clase hacia donde se va

        //faltan botones de registrar y ir a login codigo
    }
    private void cambioDePantalla(Button nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(CrearCuentaActivity.this, clase);
                startActivity(next);
            }
        });
    }

}
