package com.example.eco_recicla;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.eco_recicla.Enums.CategoriasDeReciclaje;
import com.example.eco_recicla.back.DataProducto;
import com.example.eco_recicla.back.Factura;
import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Estadisticas extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button buttonClear;
    private ImageButton buttonBack;
    private TextView resultadosDelFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        resultadosDelFiltro = findViewById(R.id.txtResultados);

        UserManager userManager = new UserManager(this);
        Usuario usuario = userManager.getUsuario();

        List<Factura> facturas = userManager.getFacturasForUser(usuario.getEmail());
        if (facturas == null) {
            facturas = new ArrayList<>();
        }

        // Crear filtro por categorías de reciclaje y recolectar la cantidad de kg acumulados
        HashMap<CategoriasDeReciclaje, Float> mapEstadisticas = new HashMap<>();
        for (Factura factura : facturas) {
            for (DataProducto producto : factura.getListaDeObjetos()) {
                CategoriasDeReciclaje categoria = producto.getCategoria();

                // Obtener la categoría del producto
                if (categoria != null) {
                    // Obtener el valor actual de la categoría desde el HashMap
                    Float valorActual = mapEstadisticas.getOrDefault(categoria, 0f);
                    // Sumar el valor actual con el nuevo valor encontrado en producto.getKg()
                    Float nuevoValor = valorActual + producto.getKg();
                    // Actualizar el HashMap con la suma de los valores mapEstadisticas.put(categoria, nuevoValor);
                }
            }
            //las categorias que no se han recolectado se ponen en 0
            for (CategoriasDeReciclaje categoria : CategoriasDeReciclaje.values()) {
                if ( mapEstadisticas.containsKey(categoria)) { mapEstadisticas.put(categoria, 0f);
                }
            }
        }

        // Mostrar los datos de mapEstadisticas en el TextView
        for (CategoriasDeReciclaje categoria : mapEstadisticas.keySet()) {
            Float kg = mapEstadisticas.get(categoria);
            resultadosDelFiltro.append("Categoria: " + categoria.name() + ", kg: " + kg + "\n");
            // Si deseas agregar la fila a una tabla, puedes descomentar la siguiente línea
            // agregarFilaATabla(categoria.name(), kg);
        }
    }

    private void agregarFilaATabla(String name, Float kg) {
        TableRow tableRow = new TableRow(this);
        CategoriasDeReciclaje categoria ;
        categoria = CategoriasDeReciclaje.valueOf(name);

        TextView textViewCategoria = new TextView(this);
        textViewCategoria.setText(categoria.name());
        textViewCategoria.setPadding(16, 16, 16, 16);

        TextView textViewKg = new TextView(this);
        textViewKg.setText(kg + " kg");
        textViewKg.setPadding(16, 16, 16, 16);

        tableRow.addView(textViewCategoria);
        tableRow.addView(textViewKg);

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
 */