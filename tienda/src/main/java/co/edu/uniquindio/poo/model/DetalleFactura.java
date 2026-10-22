package co.edu.uniquindio.poo.model;

public class DetalleFactura {
    private int cantidadComprada;
    private double subTotal;
    private double precioAplicado;
    private Producto producto;

    public DetalleFactura(Producto producto,int cantidadComprada){
        this.producto=producto;
        this.cantidadComprada=cantidadComprada;
        this.precioAplicado=producto.getPrecio();
        this.subTotal=precioAplicado*cantidadComprada;
    }
    public int getCantidadComprada() {return cantidadComprada;}
    public double getSubTotal() {return subTotal;}
    public double getPrecioAplicado() {return precioAplicado;}
    public Producto getProducto() {return producto;}
}
