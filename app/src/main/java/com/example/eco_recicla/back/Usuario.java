package com.example.eco_recicla.back;

import android.provider.ContactsContract;

import com.example.eco_recicla.Enums.CategoriasDeReciclaje;
import com.example.eco_recicla.Enums.TiposDeDocumentos;

import java.util.ArrayList;
import java.util.HashMap;

public class Usuario {
    private TiposDeDocumentos tipoDeDocuemnto;
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String email;
    private String telefono;
    private String direccion;
    private String direccionAlternativa;
    private String password;
    private ArrayList<Factura> ListadoDeFacturas;
    private Integer coins;
    private HashMap<CategoriasDeReciclaje,Integer> mapEstadisticas;


    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Usuario(TiposDeDocumentos tipoDeDocuemnto, Integer idUsuario, String nombre, String apellido, Integer edad, String email, String telefono, String direccion, String direccionAlternativa, String password) {
        this.tipoDeDocuemnto = tipoDeDocuemnto;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.direccionAlternativa = direccionAlternativa;
        this.password = password;
        this.ListadoDeFacturas = new ArrayList<>();
    }
    //getters y setters
    public TiposDeDocumentos getTipoDeDocuemnto() {
        return tipoDeDocuemnto;
    }

    public void setTipoDeDocuemnto(TiposDeDocumentos tipoDeDocuemnto) {
        this.tipoDeDocuemnto = tipoDeDocuemnto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }



    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccionAlternativa() {
        return direccionAlternativa;
    }

    public void setDireccionAlternativa(String direccionAlternativa) {
        this.direccionAlternativa = direccionAlternativa;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Factura> getListadoDeFacturas() {
        return ListadoDeFacturas;
    }

    public void setListadoDeFacturas(ArrayList<Factura> listadoDeFacturas) {
        ListadoDeFacturas = listadoDeFacturas;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public HashMap<CategoriasDeReciclaje, Integer> getMapEstadisticas() {
        return mapEstadisticas;
    }

    public void setMapEstadisticas(HashMap<CategoriasDeReciclaje, Integer> mapEstadisticas) {
        this.mapEstadisticas = mapEstadisticas;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //toString
    @Override
    public String toString() {
        return "Usuario{" +
                "tipoDeDocuemnto=" + tipoDeDocuemnto +
                ", idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", email=" + email +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", direccionAlternativa='" + direccionAlternativa + '\'' +
                ", password='" + password + '\'' +
                ", ListadoDeFacturas=" + ListadoDeFacturas +
                ", coins=" + coins +
                ", mapEstadisticas=" + mapEstadisticas +
                '}';
    }



}
