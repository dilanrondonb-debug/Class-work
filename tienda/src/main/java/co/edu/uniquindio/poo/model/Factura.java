package co.edu.uniquindio.poo.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Factura {
    private final String codigo;
    private final LocalDate fecha;
    private double total;
    private MetodoPago metodoPago;
    private EstadoFactura estadoFactura;
    private Cliente cliente;
    private final List<DetalleFactura> listaDetalleFactura =new ArrayList<>();
    public Factura(String codigo, Cliente cliente, MetodoPago metodoPago) {
        this(codigo, cliente, metodoPago, LocalDate.now());
    }
    public Factura(String codigo,  Cliente cliente, MetodoPago metodoPago, LocalDate fecha) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.estadoFactura = EstadoFactura.GENERADA;
        cliente.getListaFacturas().add(this);
    }

    public boolean agregarDetalle(DetalleFactura detalle){
        if(estadoFactura != EstadoFactura.GENERADA) return false;
        listaDetalleFactura.add(detalle);
        return true;
    }
    public double calcularTotal(){
        total=0;
        for(DetalleFactura d: listaDetalleFactura)
            total+=d.getSubTotal();
        return total;
    }
    public boolean hayStockSuficiente() {
        for (DetalleFactura d : listaDetalleFactura) {
            if (d.getCantidadComprada() > d.getProducto().getCantidadDisponible()) {
                return false;
            }
        }
        return true;
    }
    public void actualizarInventario(){
        for(DetalleFactura detalleFactura: listaDetalleFactura){
            Producto p=detalleFactura.getProducto();
            p.setCantidadDisponible(p.getCantidadDisponible()-detalleFactura.getCantidadComprada());
        }
    }
    public boolean confirmarVenta(){
        if(estadoFactura != EstadoFactura.GENERADA || listaDetalleFactura.isEmpty()
           || listaDetalleFactura.isEmpty() || !hayStockSuficiente()){
            return false;
        }
        calcularTotal();
        actualizarInventario();
        estadoFactura= EstadoFactura.PAGADA;
        return true;
    }
    public void setEstadoFactura(EstadoFactura e){this.estadoFactura = e;}
    public EstadoFactura getEstadoFactura() {return estadoFactura;}
    public String getCodigo() {return codigo;}
    public double getTotal(){return total;}
    public LocalDate getFecha() {return fecha;}
    public Cliente getCliente(){return cliente;}
    public MetodoPago getMetodoPago(){return metodoPago;}
    public List<DetalleFactura> getListaDetalleFactura() {return listaDetalleFactura;}
    }


