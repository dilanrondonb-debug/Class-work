package co.edu.uniquindio.poo.empresajfx.controller;

import co.edu.uniquindio.poo.empresajfx.model.Empresa;
import co.edu.uniquindio.poo.empresajfx.model.Producto;

import java.util.List;

public class ProductoController {

    Empresa empresa;

    public ProductoController(Empresa empresa) {
        this.empresa = empresa;
    }

    public String crearProducto(Producto producto) {
        return empresa.agregarProducto(producto);
    }

    public List<Producto> obtenerListaProductos() {
        return empresa.getProductos();
    }

    public String eliminarProducto(String codigo) {
        return empresa.eliminarProducto(codigo);
    }

    public String actualizarProducto(String codigo, Producto producto) {
        return empresa.actualizarProducto(codigo, producto);
    }
}
