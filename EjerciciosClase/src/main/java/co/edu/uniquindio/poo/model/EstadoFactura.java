package co.edu.uniquindio.poo.model;

public enum EstadoFactura {
    GENERADA(0),PAGADA(1),CANCELADA(2),ENVIADA(3);
    private final int id;
    private EstadoFactura(int id){this.id=id;}
    public int getId(){return id;}
}
