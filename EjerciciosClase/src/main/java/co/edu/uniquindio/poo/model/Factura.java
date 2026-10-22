package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Optional;

public record Factura(String codigo, LocalDate fecha, double Total, EstadoFactura estado, MetodoPago metodoPago,
                      Cliente cliente, ArrayList<DetalleFactura>listaDetallesFactura, Tienda ownedByTienda) {



    //Los records son clases inmutables osea que los atributos después de asignados no se pueden modificar
    public String registrarDetalleFactura(DetalleFactura detalleFactura){
        Optional <DetalleFactura> detalleEncontrado = buscarDetalleFactura(String.valueOf(detalleFactura.getProducto()));
        if (detalleEncontrado.isEmpty()){
            listaDetallesFactura.add(detalleFactura);
            return "Detalle registrado con exito";
        }else return "Ya existe un detalle con esta info";

    }
    public String eliminarDetalleFactura(DetalleFactura detalleFactura){
        Optional <DetalleFactura> detalleEncontrado = buscarDetalleFactura(String.valueOf(detalleFactura.getProducto()));
        if (detalleEncontrado!=null){
            listaDetallesFactura.remove(detalleFactura);
            return "Detalle eliminado con exito";
        }else return  "No existe ningún detalle con esta info";


    }
    public Optional<DetalleFactura> buscarDetalleFactura(String producto){
        for (DetalleFactura aux : listaDetallesFactura){
            if (aux.getProducto().equals(producto)){
                return Optional.of(aux);
            }
        }
        return Optional.empty();

    }
    public boolean tieneClienteConR() {
        boolean resultado = false;
        resultado = cliente.verificarNombreConR();
        return resultado ;
    }
    public boolean compraIphone(){
        boolean resultado=false;
        for(DetalleFactura dF: listaDetallesFactura){
            if(dF.compraIphone()){return true;}
        }
        return false;
    }
    public boolean verificarClienteJuan(){
        return cliente.verificarJuan();
    }
}