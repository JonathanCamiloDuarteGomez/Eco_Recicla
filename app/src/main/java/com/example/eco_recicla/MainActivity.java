package com.example.eco_recicla;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Version> versionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        initializeData();
        setupRecyclerView();

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
