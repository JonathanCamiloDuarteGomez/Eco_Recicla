package com.example.eco_recicla.Enums;

public enum TiposDeDocumentos {
    DNI("DNI"),
    PASAPORTE("PASAPORTE"),
    CEDULA("CEDULA"),
    TARJETA_IDENTIDAD("TARJETA DE IDENTIDAD");

    private String nombre;

    TiposDeDocumentos(String nombres) {
        this.nombre = nombres;
    }


    public String getNombre() {
        return nombre;
    }
}

