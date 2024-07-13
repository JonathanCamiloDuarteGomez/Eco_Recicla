package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Version> versionList;
    private Button btnIrAmenu_Historial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnIrAmenu_Historial = findViewById(R.id.btnIrAmenu_Historial);
        initializeData();
        setupRecyclerView();


        btnIrAmenu_Historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, MenuPrincipal.class);
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
        // Add versions to the list
        versionList.add(new Version("Factura #1", "Camilo", "Calle 85 #25-36 sur", "Antonio Peres", "ABC123"));
        versionList.add(new Version("Factura #2", "Camilo", "Calle 85 #25-36 sur", "Antonio Peres", "ABC123"));
        versionList.add(new Version("Factura #3", "Camilo", "Calle 85 #25-36 sur", "Antonio Peres", "ABC123"));
        versionList.add(new Version("Factura #4", "Camilo", "Calle 85 #25-36 sur", "Antonio Peres", "ABC123"));
        versionList.add(new Version("Factura #5", "Camilo", "Calle 85 #25-36 sur", "Antonio Peres", "ABC123"));
    }
}
