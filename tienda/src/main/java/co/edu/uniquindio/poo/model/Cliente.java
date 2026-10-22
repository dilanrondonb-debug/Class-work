package co.edu.uniquindio.poo.model;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
private String documento;
private String nombreCompleto;
private String telefono;
private String ciudadResidencia;
private String correo;
private List<Factura> listaFacturas= new ArrayList<>();

//constructor
public Cliente(String correo, String documento, String nombreCompleto, String telefono, String ciudadResidencia) {
    this.documento = documento;
    this.nombreCompleto = nombreCompleto;
    this.telefono = telefono;
    this.ciudadResidencia = ciudadResidencia;
    this.correo = correo;
}
    //setters y getters
public String getDocumento() {return documento;}
public void setDocumento(String documento) {this.documento=documento;}
public String getNombreCompleto() {return nombreCompleto;}
public void setNombreCompleto(String nombreCompleto){this.nombreCompleto = nombreCompleto;}
public String getTelefono() {return telefono;}
public void setTelefono(String telefono){this.telefono = telefono;}
public String getCiudadResidencia() {return ciudadResidencia;}
public void setCiudadResidencia(String ciudadResidencia) {this.ciudadResidencia = ciudadResidencia;}
public List<Factura> getListaFacturas() {return listaFacturas;}
public String getCorreo(){return correo;}
public void setCorreo(String correo){this.correo = correo;}

//CRUD

}
