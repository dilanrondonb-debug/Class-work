package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Tienda {
  private String nombre;
  private final String nit;
  private String telefono;
  private List<Producto> listaProductos =new ArrayList<>();
  private List<Factura> listaFacturas=new ArrayList<>();
  private List<Cliente> listaClientes=new ArrayList<>();
  public Tienda(String nombre, String nit, String telefono) {
      this.nombre = nombre;
      this.nit = nit;
      this.telefono = telefono;
  }
  public List<Producto>getListaProductos(){return listaProductos;}
    public List<Factura> getListaFacturas(){return listaFacturas;}
    public List<Cliente>getListaCliente(){return listaClientes;}

  public boolean crearCliente(Cliente cliente){
    if (buscarCliente(cliente.getDocumento()).isPresent()){
      return false;
    }
    listaClientes.add(cliente);
    return true;
  }
  public Optional <Cliente> buscarCliente(String documento){
    for(Cliente c:listaClientes){
      if(c.getDocumento().equals(documento)){
        return Optional.of(c);
      }
    }
    return Optional.empty();
  }
  public boolean actualizarCLiente(String documento, String nombreCompleto,String telefono,
                                    String correo, String ciudadResidencia){
      Optional <Cliente> opt=buscarCliente(documento);
      if(opt.isEmpty()) {return false;}
      Cliente c=opt.get();
      c.setNombreCompleto(nombreCompleto);
      c.setTelefono(telefono);
      c.setCorreo(correo);
      c.setCiudadResidencia(ciudadResidencia);
      return true;
  }
  public boolean eliminarCliente(String documento){
    Optional <Cliente> opt=buscarCliente(documento);
    if(opt.isEmpty()){return false;}
    listaClientes.remove(opt.get());
    return true;
  }
  public boolean crearProducto(Producto producto){
      if(buscarProducto(producto.getCodigo()).isPresent())return false;
      listaProductos.add(producto);
      return true;
  }
  public Optional<Producto> buscarProducto(String codigo){
      for(Producto p:listaProductos){
          if(p.getCodigo().equals(codigo))return Optional.of(p);
      }
      return Optional.empty();
  }
  public boolean actualizarProducto(String codigo, String nombre, String descripcion,
                                    double precio, int cantidad, Categoria categoria){
      Optional<Producto> opt= buscarProducto(codigo);
      if (opt.isEmpty())return false;
      Producto p= opt.get();

      p.setNombre(nombre);
      p.setDescripcion(descripcion);
      p.setPrecio(precio);
      p.setCantidadDisponible(cantidad);
      p.setCategoria(categoria);
      return true;
  }
  public boolean eliminarProducto(String codigo){
      Optional<Producto> p=buscarProducto(codigo);
      if(p.isEmpty())return false;
      listaProductos.remove(p);
      return true;
  }
  public boolean registrarFactura(Factura factura){
      if(buscarFactura(factura.getCodigo())!=null)return false;
      listaFacturas.add(factura);
      return true;
  }
  public Optional <Factura> buscarFactura(String codigo){
      for(Factura f:listaFacturas){
          if(f.getCodigo().equals(codigo))return Optional.of(f);
      }
      return Optional.empty();
  }

  public List<Factura> obtenerFacturasPorCliente(String documento){
      List<Factura> resultado=new ArrayList<>();
      for(Factura f:listaFacturas){
          if(f.getCliente().getDocumento().equals(documento))resultado.add(f);
      }
      return resultado;
  }
  private boolean esVenta(Factura f){
      return f.getEstadoFactura()==EstadoFactura.PAGADA
              || f.getEstadoFactura()==EstadoFactura.ENVIADA;
  }
  public double obtenerTotalVendido(){
      double suma=0;
      for(Factura f:listaFacturas){
          if(esVenta(f))suma+=f.getTotal();
      }
      return suma;
  }
  public double obtenerTotalVendidoPorFecha(LocalDate fecha){
      double suma=0;
      for(Factura f:listaFacturas){
          if((esVenta(f)) && f.getFecha().equals(fecha)) suma+=f.getTotal();
      }
      return suma;
  }
  public List<Producto> obtenerProductosBajoInventario(int bajo){
      List<Producto> resultado=new ArrayList<>();
      for(Producto p:listaProductos){
          if(p.getCantidadDisponible()<=bajo)resultado.add(p);
      }
      return resultado;
  }
  public int contarFacturasPorEstado(EstadoFactura estado){
      int contador=0;
      for(Factura f:listaFacturas){
          if(f.getEstadoFactura()==estado)contador++;
      }
      return contador;
  }
  public String generarReporteVentas(){
      Map<String, Integer>unidadesPorProducto=new HashMap<>();
      for(Factura f:listaFacturas){
          if(esVenta(f)){
              for(DetalleFactura d:f.getListaDetalleFactura()){
                  unidadesPorProducto.merge(d.getProducto().getNombre(),d.getCantidadComprada(),Integer::sum);
              }
          }
      }
      String masVendido="";
      int max=0;
      for(Map.Entry<String,Integer>e:unidadesPorProducto.entrySet()){
          if(e.getValue()>max){
              max=e.getValue();
              masVendido=e.getKey();
          }
      }
      StringBuilder sb=new StringBuilder();
      sb.append("===== REPORTE DE VENTAS -").append(nombre).append("=====\n");
      sb.append("Total de facturas: ").append(listaFacturas.size()).append("\n");
      for(EstadoFactura e:EstadoFactura.values()){
          sb.append("  ").append(e).append(": ").append(contarFacturasPorEstado(e)).append("\n");
      }
      sb.append("Total vendido: ").append(obtenerTotalVendido()).append("\n");
      sb.append("Producto más vendido: ").append(masVendido).append("(").append(max).append("unidades)\n");
      return sb.toString();
  }


}
