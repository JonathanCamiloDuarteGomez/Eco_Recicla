package com.example.eco_recicla.back;

import static com.example.eco_recicla.Enums.CategoriasDeReciclaje.PAPEL_CARTON;

import com.example.eco_recicla.Enums.CategoriasDeReciclaje;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private Integer idFactura;
    private Integer idUsuario;
    private String nombreUsuario;
    private String  nombreEmpresa; //Empresa, la empresa no se va a implementar aun.
    private String nombreConductor;
    private String placaVehiculo;
    private String fechaDeRecogida;
    private String horaDeRecogida;
    private String direccionDeRecogida;
    private List<DataProducto> listaDeObjetos;
    private Float totalCoins;
    private Float totalValor;

    public Factura(Integer idFactura, Integer idUsuario, String nombreUsuario, String nombreEmpresa, String nombreConductor, String placaVehiculo, String fechaDeRecogida, String horaDeRecogida, String direccionDeRecogida, List<DataProducto> listaDeObjetos) {
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
    //cuando se le de generar el pedido,se agrega la lista de objetos a la factura y se calcula el total de la factura
    public void agregarListaDeObjetos() {
        listaDeObjetos = new ArrayList<>();
        ListadoDeProductos listadoDeProductos = new ListadoDeProductos();
        if(listadoDeProductos != null){
            //agregar los productos a la lista de objetos
            for (DataProducto producto : listadoDeProductos.getListaDeProductos()) {
                listaDeObjetos.add(producto);
            }
            //calcular el total de la factura
            totalCoins = listadoDeProductos.calcularTotalCoins();
            totalValor = listadoDeProductos.calcularTotalAPagar();
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

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
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
