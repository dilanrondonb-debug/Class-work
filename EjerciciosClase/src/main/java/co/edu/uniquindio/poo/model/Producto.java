package co.edu.uniquindio.poo.model;

public class Producto {
    private final String codigo;
    private final String nombre;
    private final String descripcion;
    private int cantidadDisponible;
    private final double valor;
    private final Categoria categoria;

    private final Tienda ownedByTienda;


    public Producto(String codigo, String nombre, String descripcion, int cantidadDisponible, double valor, Categoria categoria, Tienda ownedByTienda) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadDisponible = cantidadDisponible;
        this.valor = valor;
        this.categoria = categoria;
        this.ownedByTienda = ownedByTienda;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public double getValor() {
        return valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Tienda getOwnedByTienda() {
        return ownedByTienda;
    }
    public boolean verificarIphone(){return nombre.equalsIgnoreCase("iphone 16 pro max");}
}
