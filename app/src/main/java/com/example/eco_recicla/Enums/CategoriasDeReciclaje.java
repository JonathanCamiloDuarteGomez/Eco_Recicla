package com.example.eco_recicla.Enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum CategoriasDeReciclaje {
    PAPEL_CARTON(Map.of(
            "Papel", Arrays.asList("Periódicos", "revistas", "papel "),
            "Cartón", Arrays.asList("Cajas de cartón")
    )),
    VIDRIO(Map.of(
            "Botellas", Arrays.asList("Botellas de vidrio de bebidas")
    )),
    PLASTICOS(Map.of(
            "PET (Polietileno Tereftalato)", Arrays.asList( "envases de alimentos"),
            "HDPE (Polietileno de Alta Densidad)", Arrays.asList("Botellas de detergente", "envases de leche"),
            "PVC (Policloruro de Vinilo)", Arrays.asList("Tubos", "botellas de aceite"),
            "LDPE (Polietileno de Baja Densidad)", Arrays.asList("Bolsas de plástico", "envoltorios"),
            "PP (Polipropileno)", Arrays.asList("Tapas de botellas")
    )),
    METALES(Map.of(
            "Aluminio", Arrays.asList("Latas de refrescos y cervezas", "papel de aluminio"),
            "Acero", Arrays.asList("Latas de alimentos", "tapas de frascos", "objetos metálicos diversos")
    )),
    ELECTRONICOS(Map.of(
            "Electrodomésticos", Arrays.asList("Refrigeradores", "microondas", "lavadoras"),
            "Electrónica de consumo", Arrays.asList("Teléfonos móviles", "ordenadores", "televisores")
    ));

    private final Map<String, List<String>> subcategories;


    CategoriasDeReciclaje(Map<String, List<String>> subcategories) {
        this.subcategories = subcategories;
    }

    public Map<String, List<String>> getSubcategories() {
        return subcategories;
    }
}
