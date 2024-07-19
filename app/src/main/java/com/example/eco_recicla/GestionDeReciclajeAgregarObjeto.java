package com.example.eco_recicla;

<<<<<<< HEAD
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
=======
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;
>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

<<<<<<< HEAD
import java.util.ArrayList;
=======
import com.example.eco_recicla.Enums.CategoriasDeReciclaje;

import java.util.ArrayList;
import java.util.List;
>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa

public class GestionDeReciclajeAgregarObjeto extends AppCompatActivity {
    private String[] header;
    private ArrayList<String[]> rows;
<<<<<<< HEAD

    TableDynamic tableDynamic;
    private TableLayout tablaFactura;
=======
    private Button btnSiguiente;

    TableDynamic tableDynamic;
    private TableLayout tablaObjetosAgregados;
    //private Spinner spinnerProblemas;
    Spinner spinnerSeleccionDeDireccion;
    Spinner spinnerGrupo;
    Spinner spinnerTipo;


>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_reciclaje_agregar_objeto);

<<<<<<< HEAD
        //navegacion entre pantallas


        //Configuracion tabla de objetos agregados
        header = new String[]{"Id Producto","Nombre","Kg","Valor Kg ","Coins","Total Coins","Total"};
        tablaFactura=(TableLayout) findViewById(R.id.tablaAgregarObjeto);
=======
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        //navegacion entre pantallas
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(GestionDeReciclajeAgregarObjeto.this, GestionDeReciclaje_AgregarSolicitudDeRecogida.class);
                startActivity(next);
                finish();
            }
        });


        //Configuracion tabla de objetos agregados
        header = new String[]{"Id Producto","Nombre","Kg","Valor Kg ","$ Valor","Coins","Total Coins","Total"};
        tablaObjetosAgregados=(TableLayout) findViewById(R.id.tablaAgregarObjeto);
>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa
        rows = new ArrayList<>();

        ImageButton agregarObjeto = (ImageButton) findViewById(R.id.imageButtonAgregarObjeto);

<<<<<<< HEAD
        tableDynamic = new TableDynamic(tablaFactura,getApplicationContext());
        tableDynamic.addHeader(header);
        //tableDynamic.addData(getProducto());
=======
        tableDynamic = new TableDynamic(tablaObjetosAgregados,getApplicationContext());
        tableDynamic.addHeader(header);
        //## se necesita encontrar la forma de no tenerque llamar a addData  y que la tabla se cree una sola vez
        tableDynamic.addData(getProducto());
>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa
        tableDynamic.linearColor();

        agregarObjeto.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
<<<<<<< HEAD
                saveItem(v);
            }
        });
    }

    //este lo voy a usar cuando se agrega un producto a una factura especifica
    public void saveItem(View view){
=======
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
>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa
        String[] item = new String[]{"3","Aluminio","10","1500","15000","100","1000","15000"};
        tableDynamic.addItems(item);
    }

    private ArrayList<String[]> getProducto() {
        rows.add(new String[]{"1","Cartón","40","500","20000","10","10","20000"});
        rows.add(new String[]{"2","Plástico","55","550","30250","825","835","50250"});
<<<<<<< HEAD
        rows.add(new String[]{" "," "," "," "," ","Total :"," C:835 ","$50250"});
=======
        //rows.add(new String[]{" "," "," "," "," ","Total :"," C:835 ","$50250"});
>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa
        return rows;
    }


}


