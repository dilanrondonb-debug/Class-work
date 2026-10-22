package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {

    private final String documento;
    private final String nombreCompleto;
    private final String telefono;
    private final String ciudadResidencia;
    private final String correo;

    private List<Factura>listaFacturas;
    private final Tienda ownedByTienda;

    public Cliente(String documento, String nombreCompleto, String telefono, String ciudadResidencia, String correo, Tienda tienda) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.ciudadResidencia = ciudadResidencia;
        this.correo = correo;
        this.ownedByTienda = tienda;
        this.listaFacturas = new ArrayList<>();

    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public String getCorreo() {
        return correo;
    }

    public List<Factura> getListaFacturas() {
        return Collections.unmodifiableList(listaFacturas); //Para que nada pueda modificar la factura del cliente desde afuera
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }
    public boolean verificarNombreConR() {
        return nombreCompleto.startsWith("R");
    }
    public boolean verificarJuan(){return nombreCompleto.toLowerCase().startsWith("juan");}
}

