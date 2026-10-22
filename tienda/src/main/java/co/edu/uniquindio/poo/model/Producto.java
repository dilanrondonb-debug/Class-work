package co.edu.uniquindio.poo.model;

public class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadDisponible;
    private Categoria categoria;

    public Producto(Categoria categoria, int cantidadDisponible, String codigo, String nombre, String descripcion, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.categoria = categoria;
    }
    public double getPrecio() {return precio;}
    public  void setPrecio(double precio) {this.precio = precio;}
    public int getCantidadDisponible() {return cantidadDisponible;}
    public void setCantidadDisponible(int cantidadDisponible){this.cantidadDisponible=cantidadDisponible;}
    public String getCodigo() {return codigo;}
    public String getNombre() {return nombre;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public String getDescripcion(){return descripcion;}

}
