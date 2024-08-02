package com.example.eco_recicla.back;

import static com.example.eco_recicla.Enums.CategoriasDeReciclaje.PAPEL_CARTON;

import com.example.eco_recicla.Enums.CategoriasDeReciclaje;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private Integer idFactura;
    private Integer idUsuario;
    private String nombreUsuario;
    private Empresa nombreEmpresa;
    private Empresa nombreConductor;
    private String placaVehiculo;
    private String fechaDeRecogida;
    private String horaDeRecogida;
    private String direccionDeRecogida;
    private List<DataProducto> listaDeObjetos;

    public Factura(Integer idFactura, Integer idUsuario, String nombreUsuario, Empresa nombreEmpresa, Empresa nombreConductor, String placaVehiculo, String fechaDeRecogida, String horaDeRecogida, String direccionDeRecogida, List<DataProducto> listaDeObjetos) {
        this.idFactura = idFactura;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreConductor = nombreConductor;
        this.placaVehiculo = placaVehiculo;
        this.fechaDeRecogida = fechaDeRecogida;
        this.horaDeRecogida = horaDeRecogida;
        this.direccionDeRecogida = direccionDeRecogida;
        this.listaDeObjetos = listaDeObjetos;
    }

    public  Factura() {


    }



    //funcion para adicionar productos a la lista de objetos
    public void addProducto(DataProducto producto, CategoriasDeReciclaje categoria, String subCategoria,Float kg)  {
        //agregar el producto a la lista de objetos
        if(listaDeObjetos == null){
            this.listaDeObjetos = new ArrayList<>();
        }

        if(categoria != null && categoria != null && kg != null &&  subCategoria != null){
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
                            listaDeObjetos.add(producto);
                            break;
                        }
                        case "Cartón":{
                            producto.setIdProducto(2);
                            producto.setNombre("Cartón");
                            producto.setKg(kg);
                            producto.setValorKg(500);
                            producto.setCoinsKg(5);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            producto.setCategoria(categoria);
                            producto.setSubCategoria(subCategoria);
                            listaDeObjetos.add(producto);

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
                            listaDeObjetos.add(producto);
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
                            listaDeObjetos.add(producto);
                        }
                        case "HDPE (Polietileno de Alta Densidad)":{
                            producto.setIdProducto(5);
                            producto.setNombre("HDPE");
                            producto.setKg(kg);
                            producto.setValorKg(1200);
                            producto.setCoinsKg(12);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            listaDeObjetos.add(producto);
                        }
                        case "PVC (Policloruro de Vinilo)":{
                            producto.setIdProducto(6);
                            producto.setNombre("PVC");
                            producto.setKg(kg);
                            producto.setValorKg(900);
                            producto.setCoinsKg(9);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            listaDeObjetos.add(producto);
                        }
                        case "LDPE (Polietileno de Baja Densidad)":{
                            producto.setIdProducto(7);
                            producto.setNombre("LDPE");
                            producto.setKg(kg);
                            producto.setValorKg(800);
                            producto.setCoinsKg(8);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            listaDeObjetos.add(producto);
                        }
                        case "PP (Polipropileno)":{
                            producto.setIdProducto(8);
                            producto.setNombre("PP");
                            producto.setKg(kg);
                            producto.setValorKg(600);
                            producto.setCoinsKg(6);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            listaDeObjetos.add(producto);
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
                            listaDeObjetos.add(producto);
                        }
                        case "Acero":{
                            producto.setIdProducto(10);
                            producto.setNombre("Acero");
                            producto.setKg(kg);
                            producto.setValorKg(1800);
                            producto.setCoinsKg(18);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            listaDeObjetos.add(producto);
                        }
                        default:
                            System.out.println("Subcategoría no válida para METALES");
                            break;
                    }
                    break;
                }
                case ELECTRONICOS:{
                    switch (subCategoria){
                        case "Electrodomésticos":{
                            producto.setIdProducto(11);
                            producto.setNombre("Electrodomésticos");
                            producto.setKg(kg);
                            producto.setValorKg(3000);
                            producto.setCoinsKg(30);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            listaDeObjetos.add(producto);
                        }
                        case "Electrónica de consumo":{
                            producto.setIdProducto(12);
                            producto.setNombre("Electrónica de consumo");
                            producto.setKg(kg);
                            producto.setValorKg(2500);
                            producto.setCoinsKg(25);
                            producto.setTotalCoins(producto.calcularTotalCoins());
                            producto.setTotalValor(producto.calcularTotalValor());
                            listaDeObjetos.add(producto);
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

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Empresa getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(Empresa nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Empresa getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(Empresa nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getFechaDeRecogida() {
        return fechaDeRecogida;
    }

    public void setFechaDeRecogida(String fechaDeRecogida) {
        this.fechaDeRecogida = fechaDeRecogida;
    }

    public String getHoraDeRecogida() {
        return horaDeRecogida;
    }

    public void setHoraDeRecogida(String horaDeRecogida) {
        this.horaDeRecogida = horaDeRecogida;
    }

    public String getDireccionDeRecogida() {
        return direccionDeRecogida;
    }

    public void setDireccionDeRecogida(String direccionDeRecogida) {
        this.direccionDeRecogida = direccionDeRecogida;
    }

    public List<DataProducto> getListaDeObjetos() {
        return listaDeObjetos;
    }

    public void setListaDeObjetos(List<DataProducto> listaDeObjetos) {
        this.listaDeObjetos = listaDeObjetos;
    }
}
