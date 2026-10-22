package co.edu.uniquindio.poo.model;
import java.util.ArrayList;
public abstract class Envio {
    private final String codigo;
    private final String destinatario;
    private final float peso;

    public Envio(String codigo, String destinatario, float peso) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.peso = peso;
    }
    public String getCodigo() {return codigo;}
    public String getDestinatario() {return destinatario;}
    public double getPeso() {return peso;}
    public abstract double obtenerCostoEnvio();

}
