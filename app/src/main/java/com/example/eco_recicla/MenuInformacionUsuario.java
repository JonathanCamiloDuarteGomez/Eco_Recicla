package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eco_recicla.back.Factura;
import com.example.eco_recicla.back.Usuario;
import com.example.eco_recicla.back.UserManager;

import java.util.List;

public class MenuInformacionUsuario extends AppCompatActivity {

    ImageButton imageButtonActualizacionDatos;
    ImageButton imageButtonHistorial;
    ImageButton imageButtonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserManager userManager = new UserManager(this);
        Usuario usuario = userManager.getUsuario();
        setContentView(R.layout.activity_menu_informacion_usuario);
        imageButtonActualizacionDatos = findViewById(R.id.buttonActualizacionDatos);
        imageButtonHistorial = findViewById(R.id.buttonHistorial);
        imageButtonHome = findViewById(R.id.btnIrAlMenuPrincipal);
        cambioDePantalla(imageButtonActualizacionDatos, Datos_Personales.class);
        List<Factura> facturas = userManager.getFacturasForUser(usuario.getEmail());
        if( facturas == null || facturas.size() == 0){
            Toast.makeText(MenuInformacionUsuario.this, "Historial vacio", Toast.LENGTH_SHORT).show();
        }else{
            cambioDePantalla(imageButtonHistorial, HistorialActivity.class);
        }

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
