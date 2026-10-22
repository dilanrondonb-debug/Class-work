package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.*;

public class Tienda {

    private final String nombre;
    private final String nit;
    private String telefono;

    private final ArrayList<Cliente> listaClientes = new ArrayList<>();
    private final List<Factura> listaFacturas = new LinkedList<>();
    private Map<String, Producto> listaProductos = new HashMap<>();


    public Tienda(String nombre, String nit, String telefono) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
    }


    public String getNit() {

        return nit;
    }

    public String getNombre() {

        return nombre;
    }

    public String getTelefono() {

        return telefono;
    }

    public void setTelefono(String telefono) {

        this.telefono = telefono;
    }

    public String registrarCliente(Cliente cliente) {

        Optional<Cliente> encontrado = buscarCliente(cliente.getDocumento());
        if (encontrado.isEmpty()) {
            listaClientes.add(cliente);
            return "El cliente fue registrado existosamente";
        } else return "Ya existe un cliente con esa información";


    }

    public String eliminarCliente(Cliente cliente) {
        Optional<Cliente> encontrado = buscarCliente(cliente.getDocumento());
        if (encontrado.isPresent()) {
            listaClientes.remove(cliente);
            return "Cliente eliminado con exito";
        } else return "El cliente no existe";

    }

    public Optional<Cliente> buscarCliente(String documento) {
        for (Cliente aux : listaClientes) {
            if (aux.getDocumento().equalsIgnoreCase(documento)) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();

    }

    public String registrarProducto(Producto producto) {

        Optional<Producto> productoEncontrado = buscarProducto(producto.getCodigo());
        if (productoEncontrado.isEmpty()) {

            listaProductos.put(producto.getCodigo(), producto);
            return "Producto registrado existosamente";
        } else return "Ya existe un producto con esos datos";


    }

    public String eliminarProducto(Producto producto) {
        Optional<Producto> productoEncontrado = buscarProducto(producto.getCodigo());
        if (productoEncontrado.isPresent()) {
            listaProductos.remove(producto.getCodigo(), producto);
            return "Producto eliminado con exito";
        } else return "No existe producto con esa información";

    }

    public Optional<Producto> buscarProducto(String codigo) {
        if (listaProductos.containsKey(codigo)) {
            return Optional.of(listaProductos.get(codigo));
        }
        return Optional.empty();
    }

    public String registrarFactura(Factura factura) {
        Optional<Factura> facturaEncontrada = buscarFactura(factura.codigo());
        if (facturaEncontrada.isEmpty()) {
            listaFacturas.add(factura);
            return "Factura registrada con exito";
        } else return "Factura ya existente";

    }

    public String eliminarFactura(Factura factura) {
        Optional<Factura> facturaEncontrada = buscarFactura(factura.codigo());
        if (facturaEncontrada.isPresent()) {
            listaFacturas.remove(factura);
            return "Factura eliminada con exito";
        } else return "Factura no existente";

    }


    public Optional<Factura> buscarFactura(String codigo) {
        for (Factura aux : listaFacturas) {
            if (aux.codigo().equalsIgnoreCase(codigo)) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }
    public List<Producto> obtenerMayorezDiez(){
        List<Producto> productosFiltrado = new ArrayList<>();
        for (Producto p : listaProductos.values()){
            if (p.getCantidadDisponible()>=10){
                productosFiltrado.add(p);
            }
        }
        return  productosFiltrado;
    }
    public List<String> obtenerCodigos(){
        List<String>productosFiltrados = new ArrayList<>();
        for (Producto p : listaProductos.values()){
            if (p.getCantidadDisponible()>=10&&p.getCantidadDisponible()<50);

            productosFiltrados.add(p.getCodigo());
        }
        return  productosFiltrados;
    }
    //Obtener lista de clientes que hayan comprado el 7 octubre 2026

    public List<Cliente> obtenerClientesFecha(LocalDate fechaConsulta){
        fechaConsulta = LocalDate.of(2026,10,7);
        List<Cliente> clientes = new ArrayList<>();
        for (Factura f : listaFacturas){
            if (f.fecha().isEqual(fechaConsulta)){
                clientes.add(f.cliente());


            }
        }
        return clientes;
    }
    public List<Factura> obtenerFacturasClienteConR(){
        ArrayList<Factura> facturas = new ArrayList<>();

        for(Factura f : listaFacturas){
            if(f.tieneClienteConR()){
                facturas.add(f);
            }
        }
        return facturas;
    }
    public List<Factura> obtenerFacturasCompraIphone(){
        ArrayList<Factura>facturas=new ArrayList<>();
        for(Factura f:listaFacturas){
            if(f.compraIphone()){facturas.add(f);}
        }
        return facturas;
    }
    public List<Factura> obtenerCompraNombreJuanProductoIphone(){
        ArrayList<Factura>facturas=new ArrayList<>();
        for(Factura f:listaFacturas){
            if(f.compraIphone()&&f.verificarClienteJuan()){facturas.add(f);}
        }
        return facturas;
    }
    public List<Producto>obtenerProductosDeCategoria(Categoria categoria){
        List<Producto>resultado=new ArrayList<>();
        for(Producto p:listaProductos.values()){
            if(p.getCategoria().equals(categoria)){resultado.add(p);}
        }
        return resultado;
    }
    public List<Producto>obtenerProductosEnRangoDePrecio(double min, double max){
        List<Producto>resultado=new ArrayList<>();
        for(Producto p:listaProductos.values()){
            if(p.getValor()>=min&&p.getValor()<=max){resultado.add(p);}
        }
        return resultado;
    }
    public List<Producto> obtenerProductosOrdenadosPorPrecio() {
        List<Producto> ordenados = new ArrayList<>(listaProductos.values());
        ordenados.sort(Comparator.comparingDouble(Producto::getValor));
        return ordenados;
    }
    public Optional<Producto>obtenerProductoMasCaro(){
        Optional<Producto>masCaro=Optional.empty();
        if(listaProductos.isEmpty()){return Optional.empty();}
        for(Producto p:listaProductos.values()){
            if(masCaro.isEmpty()||p.getValor()>masCaro.get().getValor()){masCaro=Optional.of(p);}
        }
        return masCaro;
    }
    public List<Cliente>clientesDeCiudad(String ciudad){
        List<Cliente>resultado=new ArrayList<>();
        for(Cliente c:listaClientes){
            if(c.getCiudadResidencia().equalsIgnoreCase(ciudad)){resultado.add(c);}
        }
        return resultado;
    }
}