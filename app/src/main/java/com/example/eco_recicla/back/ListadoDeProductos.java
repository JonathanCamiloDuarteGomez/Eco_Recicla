package com.example.eco_recicla.back;

import android.app.Notification;
import android.util.Log;

import com.example.eco_recicla.Enums.CategoriasDeReciclaje;

import java.io.Serializable;
import java.util.ArrayList;

public class ListadoDeProductos implements Serializable {
    private ArrayList<DataProducto> listaDeProductos;
    public ListadoDeProductos() {
        this.listaDeProductos = new ArrayList<>();
    }
   //constructor para que cada vez que se agregue un producto se agregue a la lista de productos
    public  ListadoDeProductos(DataProducto producto, CategoriasDeReciclaje categoria, String subCategoria, Float kg) {
        //crear un producto
        //se usa en la clase GestionDeReciclajeAgregarObjeto cuando se agrega un producto a una factura
        this.addProducto(producto,categoria,subCategoria,kg);
    }

    public ArrayList<DataProducto> getListaDeProductos() {
        return listaDeProductos;}
    public void setListaDeProductos(ArrayList<DataProducto> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
    }
    //estos metodos se llaman cada vez que se cree un nuevo producto para mantener el valor de la lista actualizado.
    public  Float calcularTotalAPagar() {
        Float totalPagar = 0.0f;
        for (DataProducto producto : listaDeProductos) {
            totalPagar += producto.calcularTotalValor();
        }
        return totalPagar;
    }
    public  Float calcularTotalCoins() {
        Float totalCoins = 0f;
        for (DataProducto producto : listaDeProductos) {
            totalCoins += producto.getTotalCoins();
        }
        return totalCoins;
    }

    @Override
    public String toString() {
        return "ListadoDeProductos{" +
                "listaDeProductos=" + listaDeProductos.size() +
                '}';
    }






    //funcion para adicionar productos a la lista de objetos
    public void addProducto(DataProducto producto, CategoriasDeReciclaje categoria, String subCategoria,Float kg)  {
        //agregar el producto a la lista de objetos

        if(categoria != null && kg != null &&  subCategoria != null){
            switch (categoria){
                case PAPEL_CARTON:{

                    switch (subCategoria){
                        case "Papel":{
                            producto.setIdProducto(1);
                            producto.setNombre("Papel");
                            producto.setKg(kg);
                            producto.setValorKg(1000);
                            producto.setCoinsKg(10);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                            break;
                        }
                        case "Carton":{
                            producto.setIdProducto(2);
                            producto.setNombre("Cartón");
                            producto.setKg(kg);
                            producto.setValorKg(500);
                            producto.setCoinsKg(5);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);

                        }

                        default:
                            System.out.println("Subcategoría no válida para PAPEL_CARTON");
                            break;
                    }
                    break;
                }
                case VIDRIO:{
                    switch (subCategoria){
                        case "Botellas":{
                            producto.setIdProducto(3);
                            producto.setNombre("Botellas");
                            producto.setKg(kg);
                            producto.setValorKg(700);
                            producto.setCoinsKg(8);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        default:
                            System.out.println("Subcategoría no válida para VIDRIO");
                            break;
                    }
                    break;
                }
                case PLASTICOS:{
                    switch (subCategoria){
                        case "PET (Polietileno Tereftalato)":{
                            producto.setIdProducto(4);
                            producto.setNombre("PET");
                            producto.setKg(kg);
                            producto.setValorKg(1500);
                            producto.setCoinsKg(15);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        case "HDPE (Polietileno de Alta Densidad)":{
                            producto.setIdProducto(5);
                            producto.setNombre("HDPE");
                            producto.setKg(kg);
                            producto.setValorKg(1200);
                            producto.setCoinsKg(12);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        case "PVC (Policloruro de Vinilo)":{
                            producto.setIdProducto(6);
                            producto.setNombre("PVC");
                            producto.setKg(kg);
                            producto.setValorKg(900);
                            producto.setCoinsKg(9);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        case "LDPE (Polietileno de Baja Densidad)":{
                            producto.setIdProducto(7);
                            producto.setNombre("LDPE");
                            producto.setKg(kg);
                            producto.setValorKg(800);
                            producto.setCoinsKg(8);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        case "PP (Polipropileno)":{
                            producto.setIdProducto(8);
                            producto.setNombre("PPSe");
                            producto.setKg(kg);
                            producto.setValorKg(600);
                            producto.setCoinsKg(6);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        default:
                            System.out.println("Subcategoría no válida para PLASTICOS");
                            break;
                    }

                    break;
                }
                case METALES:{
                    switch (subCategoria){
                        case "Aluminio":{
                            producto.setIdProducto(9);
                            producto.setNombre("Aluminio");
                            producto.setKg(kg);
                            producto.setValorKg(2000);
                            producto.setCoinsKg(20);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        case "Acero":{
                            producto.setIdProducto(10);
                            producto.setNombre("Acero");
                            producto.setKg(kg);
                            producto.setValorKg(1800);
                            producto.setCoinsKg(18);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        default:
                            System.out.println("Subcategoría no válida para METALES");
                            break;
                    }
                    break;
                }
                case ELECTRONICOS:{
                    switch (subCategoria){
                        case "Electrodomesticos":{
                            producto.setIdProducto(11);
                            producto.setNombre("Electrodomésticos");
                            producto.setKg(kg);
                            producto.setValorKg(3000);
                            producto.setCoinsKg(30);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        case "Electronica de consumo":{
                            producto.setIdProducto(12);
                            producto.setNombre("Electrónica de consumo");
                            producto.setKg(kg);
                            producto.setValorKg(2500);
                            producto.setCoinsKg(25);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeProductos.add(producto);
                        }
                        default:
                            System.out.println("Subcategoría no válida para ELECTRONICOS");
                            break;
                    }
                    break;
                }
                default:
                    System.out.println("Categoría no válida");
            }
        }
    }




}
