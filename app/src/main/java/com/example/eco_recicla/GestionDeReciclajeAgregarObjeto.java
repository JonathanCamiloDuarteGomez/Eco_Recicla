package com.example.eco_recicla;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eco_recicla.Enums.CategoriasDeReciclaje;

import java.util.ArrayList;
import java.util.List;

public class GestionDeReciclajeAgregarObjeto extends AppCompatActivity {
    private String[] header;
    private ArrayList<String[]> rows;

    TableDynamic tableDynamic;
    private TableLayout tablaObjetosAgregados;
    //private Spinner spinnerProblemas;
    Spinner spinnerSeleccionDeDireccion;
    Spinner spinnerGrupo;
    Spinner spinnerTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_reciclaje_agregar_objeto);

        //navegacion entre pantallas


        //Configuracion tabla de objetos agregados
        header = new String[]{"Id Producto","Nombre","Kg","Valor Kg ","$ Valor","Coins","Total Coins","Total"};
        tablaObjetosAgregados=(TableLayout) findViewById(R.id.tablaAgregarObjeto);
        rows = new ArrayList<>();

        ImageButton agregarObjeto = (ImageButton) findViewById(R.id.imageButtonAgregarObjeto);

        tableDynamic = new TableDynamic(tablaObjetosAgregados,getApplicationContext());
        tableDynamic.addHeader(header);
        //## se necesita encontrar la forma de no tenerque llamar a addData  y que la tabla se cree una sola vez
        tableDynamic.addData(getProducto());
        tableDynamic.linearColor();

        agregarObjeto.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                //agregar objeto

                saveItem();
                //tableDynamic.linearColor();
            }
        });

        //configuracion de Spinners
        spinnerSeleccionDeDireccion = (Spinner) findViewById(R.id.spinnerSeleccionDeDireccion);
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupo1);//categorias de reciclaje
        spinnerTipo = (Spinner) findViewById(R.id.spinnerTipo1);//subcategorias de reciclaje

        //llama a categoria funcion
        ConfiguracionCategoriasDeReciclaje();
    }
    private void ConfiguracionCategoriasDeReciclaje(){
        //categorias de reciclaje
        String [] categorias = new String[CategoriasDeReciclaje.values().length+1];
        categorias[0] = "Seleccione la Categoria";
        for(int i=0; i<CategoriasDeReciclaje.values().length; i++){
            categorias[i + 1] = CategoriasDeReciclaje.values()[i].name().replace("_", " y ");
        }
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrupo.setAdapter(categoryAdapter);

        spinnerGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    CategoriasDeReciclaje selectedCategory = CategoriasDeReciclaje.values()[position - 1];
                    configuracionSppinerTipo(selectedCategory);
                } else {
                    spinnerTipo.setAdapter(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

    }

    private void configuracionSppinerTipo(CategoriasDeReciclaje categoria){
        //subcategorias de reciclaje
        List<String> subcategories = new ArrayList<>();
        subcategories.add("Seleccione la Subcategoría");
        subcategories.addAll(categoria.getSubcategories().keySet());

        ArrayAdapter<String> subcategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subcategories);
        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(subcategoryAdapter);

        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String selectedSubcategory = subcategories.get(position);
                    List<String> examples = categoria.getSubcategories().get(selectedSubcategory);
                    Toast.makeText(GestionDeReciclajeAgregarObjeto.this, "Subcategoría seleccionada: " + selectedSubcategory + ", Ejemplos: " + examples, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

    }

    //este lo voy a usar cuando se agrega un producto a una factura especifica
    public void saveItem(){
        String[] item = new String[]{"3","Aluminio","10","1500","15000","100","1000","15000"};
        tableDynamic.addItems(item);
    }

    private ArrayList<String[]> getProducto() {
        rows.add(new String[]{"1","Cartón","40","500","20000","10","10","20000"});
        rows.add(new String[]{"2","Plástico","55","550","30250","825","835","50250"});
        //rows.add(new String[]{" "," "," "," "," ","Total :"," C:835 ","$50250"});
        return rows;
    }


}
/*
    Spinner spinnerProblemas = findViewById(R.id.spinner);
        //opcionSpinner
        String[] problemas = new String[ProblemaEcoambiental.values().length+1];// pasa el enum
        problemas[0] = "Seleccione la Problematica";
        for (int i=0; i<ProblemaEcoambiental.values().length; i++){//traer los nombres del enum y guardolos en el array
            problemas[i+1] = ProblemaEcoambiental.values()[i].getNombre();
        }
        //configurar el Spiner con los nombres de los problemas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, problemas);//pasarle el array
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//pasarle el layout
        spinnerProblemas.setAdapter(adapter);//pasarle el adapter
        //configurar el listener del spinner
        spinnerProblemas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    textViewInfoProblema.setText("Seleccione la Problematica");
                }else {
                    ProblemaEcoambiental problema = ProblemaEcoambiental.values()[position-1];
                    InfoProblemaEcoambiental infoProblema = new InfoProblemaEcoambiental(problema, "Descripción del problema", "Impacto del problema");
                    textViewInfoProblema.setText(infoProblema.toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textViewInfoProblema.setText("Seleccione la Problematica");
            }
        });
 */

