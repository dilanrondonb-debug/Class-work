package co.edu.uniquindio.poo.empresajfx.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Empresa {
    private String nombre;
    private Collection<Cliente> clientes= new LinkedList<>();
    private List<Producto> productos= new ArrayList<>();
    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public boolean agregarCliente(Cliente cliente) {
        boolean centinela = false;
        if (!verificarCliente(cliente.getCedula())) {
            clientes.add(cliente);
            centinela = true;
        }
        return centinela;
    }
    public boolean eliminarCliente(String cedula) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                clientes.remove(cliente);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean actualizarCliente(String cedula, Cliente actualizado) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                cliente.setCedula(actualizado.getCedula());
                cliente.setNombre(actualizado.getNombre());
                cliente.setApellido(actualizado.getApellido());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarCliente(String cedula) {
        boolean centinela = false;
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }
    public Optional<Producto> buscarProducto(String codigo){
        return productos.stream().filter(p->p.getCodigo().equals(codigo)).findFirst();
    }
    public String agregarProducto(Producto p){
        if(buscarProducto(p.getCodigo()).isPresent()) return "Ya existe un producto con ese código";
        productos.add(p);
        return "Producto añadido con éxito";
    }
    public String eliminarProducto(String codigo){
        Optional<Producto>productoEncontrado=buscarProducto(codigo);
        if(productoEncontrado.isPresent()){
            productos.remove(productoEncontrado.get());
            return "Producto removido con éxito";
        }
        return "No existe un producto con ese código";
    }
    public String actualizarProducto(String codigo, Producto actualizado ){
        Optional<Producto>productoEncontrado=buscarProducto(codigo);
        if(productoEncontrado.isPresent()){
            boolean codigoCambiado= !codigo.equals(actualizado.getCodigo());
            if(codigoCambiado && buscarProducto(actualizado.getCodigo()).isPresent()) return "Ya existe otro producto con el código nuevo";
        Producto p=productoEncontrado.get();
        p.setCodigo(actualizado.getCodigo());
        p.setNombre(actualizado.getNombre());
        p.setPrecio(actualizado.getPrecio());
        return "Producto actualizado correctamente";
        }
    return "No existe un producto con ese código";
    }
}
