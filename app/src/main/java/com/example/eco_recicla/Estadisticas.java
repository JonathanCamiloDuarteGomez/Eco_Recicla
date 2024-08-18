package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.eco_recicla.Enums.CategoriasDeReciclaje;
import com.example.eco_recicla.back.DataProducto;
import com.example.eco_recicla.back.Factura;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Estadisticas extends AppCompatActivity {

    private ImageButton buttonBack;
    private TextView resultadosDelFiltro;

    private String[] header;
    private ArrayList<String[]> rows;

    TableDynamic tableDynamic;

    private TableLayout tablaFactura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        resultadosDelFiltro = findViewById(R.id.txtResultados);
        buttonBack = findViewById(R.id.buttonBack1);


        UserManager userManager = new UserManager(this);
        Usuario usuario = userManager.getUsuario();

        List<Factura> facturas = userManager.getFacturasForUser(usuario.getEmail());
        if (facturas == null) {
            facturas = new ArrayList<>();
        }

        // Crear filtro por categorías de reciclaje y recolectar la cantidad de kg acumulados
        HashMap<CategoriasDeReciclaje, Float> mapEstadisticas = new HashMap<>();

        // Procesar las facturas para acumular kg por categoría
        for (Factura factura : facturas) {
            for (DataProducto producto : factura.getListaDeObjetos()) {
                CategoriasDeReciclaje categoria = producto.getCategoria();
                if (categoria != null) {
                    // Obtener el valor actual de la categoría desde el HashMap
                    Float valorActual = mapEstadisticas.getOrDefault(categoria, 0f);
                    // Sumar el valor actual con el nuevo valor encontrado en producto.getKg()
                    Float nuevoValor = valorActual + producto.getKg();
                    // Actualizar el HashMap con la suma de los valores
                    mapEstadisticas.put(categoria, nuevoValor);
                }
            }
        }

        // Asegurarse de que todas las categorías estén representadas en el mapa
        for (CategoriasDeReciclaje categoria : CategoriasDeReciclaje.values()) {
            if (!mapEstadisticas.containsKey(categoria)) {
                // Si la categoría no está en el HashMap, agregarla con un valor de 0
                mapEstadisticas.put(categoria, 0f);
            }
        }


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Estadisticas.this, MenuPrincipal.class);
                startActivity(next);
            }
        });
        //tabla
        header = new String[]{" Categoria "," Kg "};
        tablaFactura = findViewById(R.id.tablaFactura1);
        rows = new ArrayList<>();

        tableDynamic = new TableDynamic(tablaFactura, getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.addData(getProducto(mapEstadisticas ));
        //tableDynamic.linearColor();

    }

    private ArrayList<String[]> getProducto(HashMap<CategoriasDeReciclaje, Float> mapEstadisticas ) {
        // Limpia las filas antes de agregar los productos
        rows.clear();
        //agregar
        for (CategoriasDeReciclaje categoria : mapEstadisticas.keySet()) {
            Float kg = mapEstadisticas.get(categoria);
            rows.add(new String[]{
                    categoria.name(),
                    String.valueOf(kg)
            });
        }
        return rows;
    }
}

/*
tableLayout = findViewById(R.id.myTableLayout);
        buttonClear = findViewById(R.id.buttonClear);
        buttonBack = findViewById(R.id.buttonBack);

        loadData();

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta actividad y vuelve a la anterior
            }
        });
    }

    private void loadData() {
        SharedPreferences waterPrefs = getSharedPreferences("DataP", MODE_PRIVATE);
        //SharedPreferences electricityPrefs = getSharedPreferences("ElectricityData", MODE_PRIVATE);

        int waterIndex = waterPrefs.getInt("index", 0);
       // int electricityIndex = electricityPrefs.getInt("index", 0);

        for (int i = 0; i < waterIndex; i++) {
            String email = waterPrefs.getString("email" + i, "");
            String Telefono = waterPrefs.getString("telefono" + i,"");
            String Direccion = waterPrefs.getString("adress" + i, "");


            TableRow tableRow = new TableRow(this);

            TextView textViewMonth = new TextView(this);
            textViewMonth.setText(email);
            textViewMonth.setBackgroundResource(R.color.white);
            tableRow.addView(textViewMonth);

            TextView textViewService = new TextView(this);
            textViewService.setText(Telefono);
            textViewService.setBackgroundResource(R.color.white);
            tableRow.addView(textViewService);

            TextView textViewConsumption = new TextView(this);
            textViewConsumption.setText(Direccion);
            textViewConsumption.setBackgroundResource(R.color.white);
            tableRow.addView(textViewConsumption);

            tableLayout.addView(tableRow);
        }

        private void clearData() {
        SharedPreferences waterPrefs = getSharedPreferences("DataP", MODE_PRIVATE);

        SharedPreferences.Editor waterEditor = waterPrefs.edit();
        waterEditor.clear();
        waterEditor.apply();

        tableLayout.removeAllViews();

        Toast.makeText(this, "Datos borrados", Toast.LENGTH_SHORT).show();
    }
 */