package com.example.eco_recicla;

import static com.example.eco_recicla.VersionAdapter.nFactura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eco_recicla.back.DataProducto;
import com.example.eco_recicla.back.Factura;
import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DetalleFactura extends AppCompatActivity {
    private String[] header;
    private ArrayList<String[]> rows;

    TableDynamic tableDynamic;

    private TableLayout tablaFactura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_factura);

        Button irHistorial = findViewById(R.id.btnIrAVentanaHistorial);

        // Obtengo el número de factura
        final TextView numFactura = findViewById(R.id.numFactura_txt);
        numFactura.setText(nFactura);

        header = new String[]{" IdProducto | "," Nombre | "," Kg | "," Valor Kg | "," $ Valor | "," Coins*Kg | "," Total Coins | "," Total  "};
        tablaFactura = findViewById(R.id.tablaFactura);
        rows = new ArrayList<>();

        tableDynamic = new TableDynamic(tablaFactura, getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.addData(getProducto());
        tableDynamic.linearColor();

        irHistorial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent next = new Intent(DetalleFactura.this, HistorialActivity.class);
                startActivity(next);
            }
        });
    }

    // Traer el listado de productos agregados a la factura
    private ArrayList<String[]> getProducto() {
        // Limpia las filas antes de agregar los productos
        rows.clear();

        // Agregar factura al usuario
        UserManager userManager = new UserManager(this);
        // Obtener el usuario
        Usuario usuario = userManager.getUsuario();
        // Traer el email del usuario
        String email = usuario.getEmail();
        // Traer las facturas del usuario
        List<Factura> facturas = userManager.getFacturasForUser(email);
        // Crear una lista de objetos
        List<DataProducto> listaDeObjetos = new ArrayList<>();


        // Buscar la factura específica
        for (Factura factura : facturas) {
            if (factura.getIdFactura() == Integer.parseInt(nFactura.substring(9, nFactura.length()))) {
                listaDeObjetos = factura.getListaDeObjetos();
                break; // Salir del bucle una vez que encontramos la factura
            }
        }

        // Si se encuentra la factura y tiene productos, añadirlos a las filas
        if (listaDeObjetos != null) {
            Float totalPagar = 0.0f;
            Float totalCoins = 0f;

            for (DataProducto producto : listaDeObjetos) {
                rows.add(new String[]{
                        "\t \t" + producto.getIdProducto().toString(),
                        producto.getNombre(),
                        String.valueOf(producto.getKg()),
                        String.valueOf(producto.getValorKg()),
                        String.valueOf(producto.calcularTotalValor()),
                        String.valueOf(producto.getCoinsKg()),
                        String.valueOf(producto.calcularTotalCoins()),
                        String.valueOf(producto.calcularTotalValor())
                });
                totalPagar += producto.calcularTotalValor();
                totalCoins += producto.getTotalCoins();
            }

            // Agrega una fila con el total
            rows.add(new String[]{" ", " ", " ", " ", " ", "Total:", " C:" + totalCoins, "$" + totalPagar});
        }

        return rows;
