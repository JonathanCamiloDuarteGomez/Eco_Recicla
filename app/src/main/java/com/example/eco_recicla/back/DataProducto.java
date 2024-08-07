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
    private Integer idProducto;//independiente
    private String nombre;//independiente
    private Float kg;//dependiente
    private Integer valorKg;//independiente
    private Integer coinsKg;//independiente
    private CategoriasDeReciclaje categoria;//independiente
    private String subCategoria;//independiente
    private Float totalCoins;//dependiente
    private Float totalValor;//dependiente

    //constructor para inicializar un producto
    public DataProducto() {}


    //constructor para modificar valor de un producto por parte de la empresa.
    public DataProducto(Integer idProducto, String nombre, Float kg, Integer valorKg, Integer coinsKg) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.kg = kg;
        this.valorKg = valorKg;
        this.coinsKg = coinsKg;
    }
    //constructor para agregar un producto a una factura
    public DataProducto(Integer idProducto, String nombre, Float kg, Integer valorKg, Integer coinsKg, CategoriasDeReciclaje categoria, String subCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.kg = kg;
        this.valorKg = valorKg;
        this.coinsKg = coinsKg;
        this.categoria = categoria;
        this.subCategoria = subCategoria;
    }
    public Float calcularTotalCoins() {
        return kg * coinsKg;
   }
   public Float calcularTotalValor() {
        return kg * valorKg;
   }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getKg() {
        return kg;
    }

    public void setKg(Float kg) {
        this.kg = kg;
    }

    public Integer getValorKg() {
        return valorKg;
    }

    public void setValorKg(Integer valorKg) {
        this.valorKg = valorKg;
    }

    public Integer getCoinsKg() {
        return coinsKg;
    }

    public void setCoinsKg(Integer coinsKg) {
        this.coinsKg = coinsKg;
    }

    public CategoriasDeReciclaje getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasDeReciclaje categoria) {
        this.categoria = categoria;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

}
