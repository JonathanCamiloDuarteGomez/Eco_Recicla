package com.example.eco_recicla;

import static com.example.eco_recicla.VersionAdapter.nFactura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eco_recicla.back.DataProducto;
import com.example.eco_recicla.back.ListadoDeProductos;

import java.util.ArrayList;

public class GestionDeReciclaje_CreacionYConfirmacionDeRecogida extends AppCompatActivity {
    Button btnIrAAgregarSolicitudDeRecogida;
    Button buttonCrearSolicitud;
    EditText editTextConfirmacionDeDireccion;

    private String[] header;
    private ArrayList<String[]> rows;
    private TableDynamic tableDynamic;
    private TableLayout tablaFactura;

    //obtener datos de la anterior pantalla
    // Obtener los datos del Intent
    private String direccion;
    private String tipo ;
    private String date ;
    private String time ;
    private String conductor ;
    private String empresa ;
    private String placa ;
    private ListadoDeProductos listadoDeProductos ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_reciclaje_creacion_yconfirmacion_de_recogida);
        btnIrAAgregarSolicitudDeRecogida = findViewById(R.id.btnIrAAgregarSolicitudDeRecogida);
        buttonCrearSolicitud = findViewById(R.id.buttonCrearSolicitud);
        editTextConfirmacionDeDireccion = findViewById(R.id.editTextConfirmacionDeDireccion);


        btnIrAAgregarSolicitudDeRecogida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(GestionDeReciclaje_CreacionYConfirmacionDeRecogida.this, GestionDeReciclaje_AgregarSolicitudDeRecogida.class);
                startActivity(next);
                finish();
                 }
            });
        buttonCrearSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GestionDeReciclaje_CreacionYConfirmacionDeRecogida.this, "Solicitud creada", Toast.LENGTH_SHORT).show();
                Intent next = new Intent(GestionDeReciclaje_CreacionYConfirmacionDeRecogida.this, MenuPrincipal.class);
                startActivity(next);
                finish();
            }
        });
        //traer datos de las anteriores pantallas
         Intent intent = getIntent();
          direccion = intent.getStringExtra("direccion");
          tipo = intent.getStringExtra("tipo");
          date = intent.getStringExtra("date");
          time = intent.getStringExtra("time");
          conductor = intent.getStringExtra("conductor");
          empresa = intent.getStringExtra("empresa");
          placa = intent.getStringExtra("vehiculo");
          listadoDeProductos = (ListadoDeProductos) intent.getSerializableExtra("listadoDeProductos");

        //configuracion tabla

        header = new String[]{" Id Producto | "," Nombre | "," Kg | "," Valor Kg | "," $ Valor | "," Coins*Kg | "," Total Coins | "," Total  "};
        tablaFactura=(TableLayout) findViewById(R.id.objetosAgregados);
        rows = new ArrayList<>();

        tableDynamic = new TableDynamic(tablaFactura,getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.addData(getProducto());
        tableDynamic.linearColor();

         //Datos a mostrar

            final TextView datosRecoleccion;
            final String datosAMostrar;
            datosAMostrar =
                    " \tNombre de la empresa : " + empresa + "\n" +
                            "\tConductor : " +conductor+"\n" +
                            "\tPlaca : " + placa+"\n" +
                            "\tFecha de Recogida : " + date+"\n" +
                            "\tHora : "+time+"\n" +
                            "\tDirección : " + direccion;

            datosRecoleccion = findViewById(R.id.textViewDatosDeRecoleccion);

            datosRecoleccion.setText(datosAMostrar);


    }



    // Traer el listado de productos agregados a la factura
    private ArrayList<String[]> getProducto() {
        // Limpia las filas antes de agregar los productos
        rows.clear();

        // Itera sobre cada producto en la lista de productos
        for (DataProducto producto : listadoDeProductos.getListaDeProductos()) {
            // Agrega cada producto a las filas en el formato requerido
            rows.add(new String[]{
                    producto.getIdProducto().toString(),
                    producto.getNombre(),
                    String.valueOf(producto.getKg()),
                    String.valueOf(producto.getValorKg()),
                    String.valueOf(producto.calcularTotalValor()),
                    String.valueOf(producto.getCoinsKg()),
                    String.valueOf(producto.calcularTotalCoins()),
                    String.valueOf(producto.calcularTotalValor())
            });
        }

        // Agrega una fila con el total
        rows.add(new String[]{" ", " ", " ", " ", " ", "Total:", " C:" + listadoDeProductos.calcularTotalCoins(), "$" + listadoDeProductos.calcularTotalAPagar()});

        return rows;
    }
}