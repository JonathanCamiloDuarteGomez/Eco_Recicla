package com.example.eco_recicla;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class GestionDeReciclajeAgregarObjeto extends AppCompatActivity {
    private String[] header;
    private ArrayList<String[]> rows;

    TableDynamic tableDynamic;
    private TableLayout tablaFactura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_reciclaje_agregar_objeto);

        //navegacion entre pantallas


        //Configuracion tabla de objetos agregados
        header = new String[]{"Id Producto","Nombre","Kg","Valor Kg ","Coins","Total Coins","Total"};
        tablaFactura=(TableLayout) findViewById(R.id.tablaAgregarObjeto);
        rows = new ArrayList<>();

        ImageButton agregarObjeto = (ImageButton) findViewById(R.id.imageButtonAgregarObjeto);

        tableDynamic = new TableDynamic(tablaFactura,getApplicationContext());
        tableDynamic.addHeader(header);
        //tableDynamic.addData(getProducto());
        tableDynamic.linearColor();

        agregarObjeto.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                saveItem(v);
            }
        });
    }

    //este lo voy a usar cuando se agrega un producto a una factura especifica
    public void saveItem(View view){
        String[] item = new String[]{"3","Aluminio","10","1500","15000","100","1000","15000"};
        tableDynamic.addItems(item);
    }

    private ArrayList<String[]> getProducto() {
        rows.add(new String[]{"1","Cartón","40","500","20000","10","10","20000"});
        rows.add(new String[]{"2","Plástico","55","550","30250","825","835","50250"});
        rows.add(new String[]{" "," "," "," "," ","Total :"," C:835 ","$50250"});
        return rows;
    }


}


