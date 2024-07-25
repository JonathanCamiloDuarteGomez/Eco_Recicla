package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.eco_recicla.Enums.TiposDeDocumentos;

import java.util.ArrayList;
import java.util.List;

public class GestionDeReciclajeAgregarObjeto extends AppCompatActivity {
    //bandera de la tabla
    private boolean flag = false;
    private String[] header;
    private ArrayList<String[]> rows;
    private Button btnSiguiente;
    private Button btnIrAMenuPrincipal;
    TableDynamic tableDynamic;
    private TableLayout tablaObjetosAgregados;
    //private Spinner spinnedirecciones;
    private Spinner spinnerSeleccionDeDireccion;
    private Spinner spinnerGrupo;
    private Spinner spinnerTipo;
    private String direccion;
    private String grupo;
    private String tipo;
    private float kg;

    //opciones del spinner
    String[]direcciones = new String[3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_reciclaje_agregar_objeto);

        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnIrAMenuPrincipal = (Button) findViewById(R.id.btnIrAMenuPrincipal);direcciones[0] = "Seleccione Direccion";direcciones[1] = "Direccion 1";direcciones[2] = "Direccion 2";

        direccion = "";
        grupo = "";
        tipo = "";
        kg = 0.0F;

        //navegacion entre pantallas
        btnIrAMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(GestionDeReciclajeAgregarObjeto.this, MenuPrincipal.class);
                startActivity(next);
                finish();
            }});
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (tableDynamic != null && tableDynamic.sizeData() != 0) {
                        Intent next = new Intent(GestionDeReciclajeAgregarObjeto.this, GestionDeReciclaje_AgregarSolicitudDeRecogida.class);

                        //enviar datos capturados a la siguiente pantalla
                        // Añadir datos al Intent
                        next.putExtra("direccion", direccion.toString());
                        next.putExtra("grupo", grupo.toString());
                        next.putExtra("tipo", tipo.toString());
                        next.putExtra("kg", Float.toString(kg));

                        startActivity(next);
                        finish();
                    }
                } catch (Exception e) {
                    Log.e("Error", "Error al procesar el botón Siguiente", e);
                    Toast.makeText(GestionDeReciclajeAgregarObjeto.this, "Ingrese almenos un Objeto", Toast.LENGTH_SHORT).show();
                }
            }
        });




        //Configuracion tabla de objetos agregados
        header = new String[]{"Id Producto","Nombre","Kg","Valor Kg ","$ Valor","Coins","Total Coins","Total"};
        tablaObjetosAgregados=(TableLayout) findViewById(R.id.tablaAgregarObjeto);
        rows = new ArrayList<>();

        ImageButton agregarObjeto = (ImageButton) findViewById(R.id.imageButtonAgregarObjeto);

        tableDynamic = new TableDynamic(tablaObjetosAgregados,getApplicationContext());
        tableDynamic.addHeader(header);
        //## se necesita encontrar la forma de no tenerque llamar a addData  y que la tabla se cree una sola vez
        //tableDynamic.addData(getProducto());




        agregarObjeto.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                if(!flag){//si la tabla no esta creada

                    if(obtenerInformacionSegunSpinner()==true){//si la informacion es correcta
                        tableDynamic.addData(getProducto());
                        tableDynamic.linearColor();
                        saveItem();
                        flag = true;
                        Toast.makeText(GestionDeReciclajeAgregarObjeto.this, "Datos ingresados correctamente", Toast.LENGTH_SHORT).show();
                    }else if(obtenerInformacionSegunSpinner()==false) {//si la informacion es incorrecta
                        Toast.makeText(GestionDeReciclajeAgregarObjeto.this, "Datos ingresados incorrectamente", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }
                }else if(flag==true){//si la tabla esta creada
                    saveItem();
                }
            }
        });

        //configuracion de Spinners
        spinnerSeleccionDeDireccion = (Spinner) findViewById(R.id.spinnerSeleccionDeDireccion);
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupo1);//categorias de reciclaje
        spinnerTipo = (Spinner) findViewById(R.id.spinnerTipo1);//subcategorias de reciclaje

        //llama a categoria funcion
        ConfiguracionCategoriasDeReciclaje();




        //configurar el Spiner con Direcciones del usuario

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,direcciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeleccionDeDireccion.setAdapter(adapter);
        //configurar el listener del spinner
        spinnerSeleccionDeDireccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                    Log.i("Direccion: ",direcciones[position].toString());
                }else {
                    Log.i("Direccion: ",direcciones[position].toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Tipo de documento", "Seleccione Tipo de documento");
            }
        });
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
        subcategories.add("Seleccione la Subcategoria");
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
        String[] item = new String[]{"3",tipo,Float.toString(kg),"1500","15000","100","1000","15000"};
        tableDynamic.addItems(item);
    }

    private ArrayList<String[]> getProducto() {
        //rows.add(new String[]{"","","","","","","",""});
        //rows.add(new String[]{"1","Cartón","40","500","20000","10","10","20000"});
        //rows.add(new String[]{"2","Plástico","55","550","30250","825","835","50250"});
        //rows.add(new String[]{" "," "," "," "," ","Total :"," C:835 ","$50250"});
        return rows;
    }

    private boolean obtenerInformacionSegunSpinner() {
        try {
            if (spinnerSeleccionDeDireccion == null || spinnerGrupo == null || spinnerTipo == null) {
                Log.e("Error", "Uno o más spinners son nulos");
                return false;
            }

            String direccionSeleccionada = spinnerSeleccionDeDireccion.getSelectedItem().toString();
            String grupoSeleccionado = spinnerGrupo.getSelectedItem().toString();
            String tipoSeleccionado = spinnerTipo.getSelectedItem().toString();
            EditText editText = findViewById(R.id.EditTextKG);

            if (!direccionSeleccionada.equals("Seleccione Direccion") &&
                    !grupoSeleccionado.equals("Seleccione la Categoria")&& !editText.getText().toString().equals("")
                    && !tipoSeleccionado.equals("Seleccione la Subcategoria")
                ) {

                // Verificar cuál spinner tiene la información seleccionada
                direccion = spinnerSeleccionDeDireccion.getSelectedItem().toString();
                grupo = spinnerGrupo.getSelectedItem().toString();
                tipo = spinnerTipo.getSelectedItem().toString();
                kg = Float.parseFloat(editText.getText().toString());
                Log.i("Info Direccion", direccionSeleccionada);
                Log.i("Info Grupo", grupoSeleccionado);
                Log.i("Info Tipo", tipoSeleccionado);
                Log.i("Info KG", Float.toString(kg));

                Log.i("Info Direccion", direccionSeleccionada);
                return true;
            } else {
                Log.i("Info Direccion", "No se ha seleccionado ninguna opción válida");
                return false;
            }
        } catch (Exception e) {
            Log.e("Error", "Error al obtener información del spinner", e);
            return false;
        }
    }



}


