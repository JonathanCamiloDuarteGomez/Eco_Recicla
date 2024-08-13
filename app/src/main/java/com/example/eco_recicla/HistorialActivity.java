package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eco_recicla.back.Factura;
import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;

import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Version> versionList;
    private Button btnIrAmenu_Historial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        recyclerView = findViewById(R.id.recyclerView);
        btnIrAmenu_Historial = findViewById(R.id.btnIrAmenu_Historial);
        initializeData();
        setupRecyclerView();


        btnIrAmenu_Historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(HistorialActivity.this, MenuInformacionUsuario.class);
                startActivity(next);
            }
        });

    }

    private void setupRecyclerView() {
        VersionAdapter versionAdapter = new VersionAdapter(versionList);
        recyclerView.setAdapter(versionAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initializeData() {
        versionList = new ArrayList<>();

        //Agregar factura al usuario
        UserManager userManager = new UserManager(this);
        //obtener el usuario
        Usuario usuario = userManager.getUsuario();
        //traer el email del usuario
        String email = usuario.getEmail();
        //traer las facturas del usuario
        List<Factura> facturas = userManager.getFacturasForUser(email);
        //recorrer las facturas
        for (Factura factura : facturas){
            //agregar las facturas del usuario a la lista
            versionList.add(new Version("Factura #"+factura.getIdFactura(), factura.getNombreEmpresa(), factura.getDireccionDeRecogida(),  factura.getNombreConductor(), factura.getPlacaVehiculo()));
        }


    }
}
