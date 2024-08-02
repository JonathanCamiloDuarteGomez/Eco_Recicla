package com.example.eco_recicla.back;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.eco_recicla.Enums.CategoriasDeReciclaje;
import com.example.eco_recicla.GestionDeReciclajeAgregarObjeto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataProducto {
    private Integer idProducto;
    private String nombre;
    private Float kg;
    private Integer valorKg;
    private Integer coinsKg;
    private Float totalCoins;
    private Float totalValor;
    private CategoriasDeReciclaje categoria;
    private String subCategoria;

   public Float calcularTotalCoins() {
        return kg * coinsKg;
   }
   public Float calcularTotalValor() {
        return kg * valorKg;
   }
}
