package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RecuperarContrasena extends AppCompatActivity {

    Button btnIrAloginRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);

        btnIrAloginRegistrar = findViewById(R.id.btnIrAloginRegistrar);

        cambioDePantalla(btnIrAloginRegistrar, MainActivity.class);
    }

    private void cambioDePantalla(Button nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(RecuperarContrasena.this, clase);
                startActivity(next);
            }
        });
    }
}