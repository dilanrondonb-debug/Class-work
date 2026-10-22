package co.edu.uniquindio.poo.model;

public enum MetodoPago {
EFECTIVO(0),TARJETA_DEBITO(1),TARJETA_CREDITO(2),TRANSFERENCIA(3);
private final int id;
    private MetodoPago(int id){this.id=id;}
    public int getId(){return id;}
}
