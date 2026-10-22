package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

import javax.swing.*;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final String TITULO = "TechStore";
    private static Tienda tienda;

    /** Se lanza cuando el usuario cierra o cancela un cuadro de diálogo. */
    private static class Cancelado extends RuntimeException { }

    public static void main(String[] args) {
        // Tienda(telefono, nombre, nit)
        tienda = new Tienda("3001234567", "TechStore", "900123456-1");
        cargarDatosDePrueba();
        menuPrincipal();
    }

    // =====================================================================
    // DATOS INICIALES
    // =====================================================================

    private static void cargarDatosDePrueba() {
        // Cliente(correo, documento, nombreCompleto, telefono, ciudadResidencia)
        tienda.crearCliente(new Cliente("ana@mail.com", "1094000000", "Ana Gómez",
                "3100000000", "Armenia"));

        // Producto(categoria, cantidadDisponible, codigo, nombre, descripcion, precio)
        tienda.crearProducto(new Producto(Categoria.COMPUTADORES, 10, "P001",
                "Laptop", "Laptop 16GB RAM", 2500000));
        tienda.crearProducto(new Producto(Categoria.ACCESORIOS, 30, "P002",
                "Mouse", "Mouse inalámbrico", 50000));
        tienda.crearProducto(new Producto(Categoria.CELULARES, 3, "P003",
                "Celular", "Celular 128GB", 1200000));
    }

    // =====================================================================
    // MENÚS
    // =====================================================================

    private static void menuPrincipal() {
        String[] opciones = {"Clientes", "Productos", "Facturas", "Consultas y reportes", "Salir"};
        while (true) {
            int op = elegir("Menú principal", opciones);
            switch (op) {
                case 0: menuClientes(); break;
                case 1: menuProductos(); break;
                case 2: menuFacturas(); break;
                case 3: menuConsultas(); break;
                default:
                    JOptionPane.showMessageDialog(null, "¡Hasta pronto!", TITULO,
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
            }
        }
    }

    private static void menuClientes() {
        String[] opciones = {"Crear cliente", "Buscar cliente", "Actualizar cliente",
                "Eliminar cliente", "Listar clientes", "Volver"};
        while (true) {
            int op = elegir("Clientes", opciones);
            switch (op) {
                case 0: ejecutar(Main::crearCliente); break;
                case 1: ejecutar(Main::buscarCliente); break;
                case 2: ejecutar(Main::actualizarCliente); break;
                case 3: ejecutar(Main::eliminarCliente); break;
                case 4: listarClientes(); break;
                default: return;
            }
        }
    }

    private static void menuProductos() {
        String[] opciones = {"Crear producto", "Buscar producto", "Actualizar producto",
                "Eliminar producto", "Listar productos", "Volver"};
        while (true) {
            int op = elegir("Productos", opciones);
            switch (op) {
                case 0: ejecutar(Main::crearProducto); break;
                case 1: ejecutar(Main::buscarProducto); break;
                case 2: ejecutar(Main::actualizarProducto); break;
                case 3: ejecutar(Main::eliminarProducto); break;
                case 4: listarProductos(); break;
                default: return;
            }
        }
    }

    private static void menuFacturas() {
        String[] opciones = {"Crear factura", "Ver detalle de una factura",
                "Cambiar estado de una factura", "Listar facturas", "Volver"};
        while (true) {
            int op = elegir("Facturas", opciones);
            switch (op) {
                case 0: ejecutar(Main::crearFactura); break;
                case 1: ejecutar(Main::verFactura); break;
                case 2: ejecutar(Main::cambiarEstadoFactura); break;
                case 3: listarFacturas(); break;
                default: return;
            }
        }
    }

    private static void menuConsultas() {
        String[] opciones = {"Facturas de un cliente", "Total vendido en una fecha",
                "Total vendido (general)", "Productos con bajo inventario",
                "Reporte de ventas", "Volver"};
        while (true) {
            int op = elegir("Consultas y reportes", opciones);
            switch (op) {
                case 0: ejecutar(Main::consultarFacturasCliente); break;
                case 1: ejecutar(Main::consultarTotalPorFecha); break;
                case 2:
                    aviso("Total vendido: " + dinero(tienda.obtenerTotalVendido()));
                    break;
                case 3: ejecutar(Main::consultarBajoInventario); break;
                case 4: mostrarTexto("Reporte de ventas", tienda.generarReporteVentas()); break;
                default: return;
            }
        }
    }

    // =====================================================================
    // CLIENTES
    // =====================================================================

    private static void crearCliente() {
        String documento = leerTexto("Documento de identidad:");
        if (tienda.buscarCliente(documento).isPresent()) {
            aviso("Ya existe un cliente con ese documento.");
            return;
        }
        String nombre = leerTexto("Nombre completo:");
        String telefono = leerTexto("Teléfono:");
        String correo = leerTexto("Correo electrónico:");
        String ciudad = leerTexto("Ciudad de residencia:");

        Cliente c = new Cliente(correo, documento, nombre, telefono, ciudad);
        tienda.crearCliente(c);
        aviso("Cliente creado correctamente.");
    }

    private static void buscarCliente() {
        Optional<Cliente> c = tienda.buscarCliente(leerTexto("Documento del cliente:"));
        if (c.isEmpty()) {
            aviso("Cliente no encontrado.");
            return;
        }
        mostrarTexto("Cliente", descripcionCliente(c));
    }

    private static void actualizarCliente() {
        Optional<Cliente> c = tienda.buscarCliente(leerTexto("Documento del cliente a actualizar:"));
        if (c.isEmpty()) {
            aviso("Cliente no encontrado.");
            return;
        }
        String nombre = leerTexto("Nombre completo:", c.getNombreCompleto());
        String telefono = leerTexto("Teléfono:", c.getTelefono());
        String correo = leerTexto("Correo electrónico:", c.getCorreo());
        String ciudad = leerTexto("Ciudad de residencia:", c.getCiudadResidencia());

        tienda.actualizarCliente(c.getDocumento(), nombre, telefono, correo, ciudad);
        aviso("Cliente actualizado correctamente.");
    }

    private static void eliminarCliente() {
        Optional<Cliente> opt = tienda.buscarCliente(leerTexto("Documento del cliente a eliminar:"));
        if (opt.isEmpty()) {
            aviso("Cliente no encontrado.");
            return;
        }
        Cliente c= opt.get();
        if (!c.getListaFacturas().isEmpty()) {
            aviso("No se puede eliminar: el cliente tiene facturas registradas.");
            return;
        }
        if (confirmar("¿Eliminar a " + c.getNombreCompleto() + "?")) {
            tienda.eliminarCliente(c.getDocumento());
            aviso("Cliente eliminado.");
        }
    }

    private static void listarClientes() {
        if (tienda.getListaCliente().isEmpty()) {
            aviso("No hay clientes registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Cliente c : tienda.getListaCliente()) {
            sb.append(c.getDocumento()).append("  |  ")
                    .append(c.getNombreCompleto()).append("  |  ")
                    .append(c.getCiudadResidencia()).append("\n");
        }
        mostrarTexto("Clientes registrados", sb.toString());
    }

    private static String descripcionCliente(@org.jetbrains.annotations.UnknownNullability Optional<Cliente> c) {
        return "Documento: " + c.getDocumento() + "\n"
                + "Nombre: " + c.getNombreCompleto() + "\n"
                + "Teléfono: " + c.getTelefono() + "\n"
                + "Correo: " + c.getCorreo() + "\n"
                + "Ciudad: " + c.getCiudadResidencia() + "\n"
                + "Facturas: " + c.getListaFacturas().size();
    }

    // =====================================================================
    // PRODUCTOS
    // =====================================================================

    private static void crearProducto() {
        String codigo = leerTexto("Código del producto:");
        if (tienda.buscarProducto(codigo) != null) {
            aviso("Ya existe un producto con ese código.");
            return;
        }
        String nombre = leerTexto("Nombre:");
        String descripcion = leerTexto("Descripción:");
        double precio = leerDouble("Precio:", "");
        int cantidad = leerEntero("Cantidad disponible:", 0, "");
        Categoria categoria = elegirOpcion("Categoría:", Categoria.values(), null);

        Producto p = new Producto(categoria, cantidad, codigo, nombre, descripcion, precio);
        tienda.crearProducto(p);
        aviso("Producto creado correctamente.");
    }

    private static void buscarProducto() {
        Producto p = tienda.buscarProducto(leerTexto("Código del producto:"));
        if (p == null) {
            aviso("Producto no encontrado.");
            return;
        }
        mostrarTexto("Producto", descripcionProducto(p));
    }

    private static void actualizarProducto() {
        Producto p = tienda.buscarProducto(leerTexto("Código del producto a actualizar:"));
        if (p == null) {
            aviso("Producto no encontrado.");
            return;
        }
        String nombre = leerTexto("Nombre:", p.getNombre());
        String descripcion = leerTexto("Descripción:", p.getDescripcion());
        double precio = leerDouble("Precio:", String.valueOf(p.getPrecio()));
        int cantidad = leerEntero("Cantidad disponible:", 0, String.valueOf(p.getCantidadDisponible()));
        Categoria categoria = elegirOpcion("Categoría:", Categoria.values(), p.getCategoria());

        tienda.actualizarProducto(p.getCodigo(), nombre, descripcion, precio, cantidad, categoria);
        aviso("Producto actualizado correctamente.");
    }

    private static void eliminarProducto() {
        Producto p = tienda.buscarProducto(leerTexto("Código del producto a eliminar:"));
        if (p == null) {
            aviso("Producto no encontrado.");
            return;
        }
        if (confirmar("¿Eliminar el producto " + p.getNombre() + "?")) {
            tienda.eliminarProducto(p.getCodigo());
            aviso("Producto eliminado.");
        }
    }

    private static void listarProductos() {
        if (tienda.getListaProductos().isEmpty()) {
            aviso("No hay productos registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Producto p : tienda.getListaProductos()) {
            sb.append(p.getCodigo()).append("  |  ")
                    .append(p.getNombre()).append("  |  ")
                    .append(p.getCategoria()).append("  |  ")
                    .append(dinero(p.getPrecio())).append("  |  stock: ")
                    .append(p.getCantidadDisponible()).append("\n");
        }
        mostrarTexto("Productos", sb.toString());
    }

    private static String descripcionProducto(Producto p) {
        return "Código: " + p.getCodigo() + "\n"
                + "Nombre: " + p.getNombre() + "\n"
                + "Descripción: " + p.getDescripcion() + "\n"
                + "Precio: " + dinero(p.getPrecio()) + "\n"
                + "Disponible: " + p.getCantidadDisponible() + "\n"
                + "Categoría: " + p.getCategoria();
    }

    // =====================================================================
    // FACTURAS
    // =====================================================================

    private static void crearFactura() {
        Optional <Cliente> cliente = tienda.buscarCliente(leerTexto("Documento del cliente:"));
        if (cliente.isEmpty()) {
            aviso("Cliente no encontrado. Créalo primero en el menú Clientes.");
            return;
        }
        if (tienda.getListaProductos().isEmpty()) {
            aviso("No hay productos registrados.");
            return;
        }
        String codigo = leerTexto("Código de la factura:");
        if (tienda.buscarFactura(codigo) != null) {
            aviso("Ya existe una factura con ese código.");
            return;
        }
        MetodoPago metodo = elegirOpcion("Método de pago:", MetodoPago.values(), null);

        // Se arman los detalles primero; la factura solo se crea si el usuario no cancela
        List<DetalleFactura> detalles = new ArrayList<>();
        do {
            Optional<Producto> opt = tienda.buscarProducto(leerTexto("Código del producto a comprar:"));
            if (opt.isEmpty()) {
                aviso("Producto no encontrado.");
                continue;
            }
            Producto p=opt.get();
            int yaEnFactura = cantidadEnFactura(detalles, p);
            int disponible = p.getCantidadDisponible() - yaEnFactura;
            if (disponible <= 0) {
                aviso("No hay unidades disponibles de " + p.getNombre() + ".");
                continue;
            }
            int cantidad = leerEntero("Cantidad de " + p.getNombre()
                    + " (disponibles: " + disponible + "):", 1, "");
            if (cantidad > disponible) {
                aviso("Stock insuficiente. Solo hay " + disponible + " unidades.");
                continue;
            }
            detalles.add(new DetalleFactura(p, cantidad));
        } while (detalles.isEmpty() || confirmar("¿Agregar otro producto?"));

        Factura f = new Factura(codigo, cliente, metodo);
        for (DetalleFactura d : detalles) {
            f.agregarDetalle(d);
        }
        f.calcularTotal();
        tienda.registrarFactura(f);

        if (confirmar("Factura " + codigo + " creada.\nTotal: " + dinero(f.getTotal())
                + "\n\n¿Confirmar la venta ahora?")) {
            if (f.confirmarVenta()) {
                aviso("Venta confirmada. Inventario actualizado.");
            } else {
                aviso("No se pudo confirmar la venta (revisa el inventario).");
            }
        } else {
            aviso("La factura quedó en estado GENERADA.");
        }
    }

    private static int cantidadEnFactura(List<DetalleFactura> detalles, Producto p) {
        int total = 0;
        for (DetalleFactura d : detalles) {
            if (d.getProducto() == p) {
                total += d.getCantidadComprada();
            }
        }
        return total;
    }

    private static void verFactura() {
        Optional<Factura> opt = tienda.buscarFactura(leerTexto("Código de la factura:"));
        if (opt.isEmpty()) {
            aviso("Factura no encontrada.");
            return;
        }
        Factura f= opt.get();
        StringBuilder sb = new StringBuilder();
        sb.append("Factura: ").append(f.getCodigo()).append("\n");
        sb.append("Fecha: ").append(f.getFecha()).append("\n");
        sb.append("Cliente: ").append(f.getCliente().getNombreCompleto()).append("\n");
        sb.append("Método de pago: ").append(f.getMetodoPago()).append("\n");
        sb.append("Estado: ").append(f.getEstadoFactura()).append("\n");
        sb.append("\nProductos:\n");
        for (DetalleFactura d : f.getListaDetalleFactura()) {
            sb.append("  - ").append(d.getProducto().getNombre())
                    .append("  x").append(d.getCantidadComprada())
                    .append("  =  ").append(dinero(d.getSubTotal())).append("\n");
        }
        sb.append("\nTOTAL: ").append(dinero(f.calcularTotal()));
        mostrarTexto("Detalle de factura", sb.toString());
    }

    private static void cambiarEstadoFactura() {
        Optional<Factura> opt = tienda.buscarFactura(leerTexto("Código de la factura:"));
        if (opt.isEmpty()) {
            aviso("Factura no encontrada.");
            return;
        }
        Factura f=opt.get();
        String[] opciones = {"Confirmar venta (PAGADA)", "Marcar como ENVIADA", "Cancelar factura"};
        int op = elegir("Estado actual: " + f.getEstadoFactura(), opciones);
        if (op == -1) {
            return;
        }
        switch (op) {
            case 0:
                if (f.confirmarVenta()) {
                    aviso("Venta confirmada. Inventario actualizado.");
                } else {
                    aviso("No se pudo confirmar: la factura debe estar GENERADA,\n"
                            + "tener productos y stock suficiente.");
                }
                break;
            case 1:
                if (f.getEstadoFactura() == EstadoFactura.PAGADA) {
                    f.setEstadoFactura(EstadoFactura.ENVIADA);
                    aviso("Factura marcada como ENVIADA.");
                } else {
                    aviso("Solo se puede enviar una factura PAGADA.");
                }
                break;
            case 2:
                if (f.getEstadoFactura() == EstadoFactura.GENERADA) {
                    f.setEstadoFactura(EstadoFactura.CANCELADA);
                    aviso("Factura cancelada.");
                } else {
                    aviso("Solo se puede cancelar una factura GENERADA.");
                }
                break;
            default:
                break;
        }
    }

    private static void listarFacturas() {
        if (tienda.getListaFacturas().isEmpty()) {
            aviso("No hay facturas registradas.");
            return;
        }
        mostrarTexto("Facturas", resumenFacturas(tienda.getListaFacturas()));
    }

    private static String resumenFacturas(List<Factura> facturas) {
        StringBuilder sb = new StringBuilder();
        for (Factura f : facturas) {
            sb.append(f.getCodigo()).append("  |  ")
                    .append(f.getFecha()).append("  |  ")
                    .append(f.getCliente().getNombreCompleto()).append("  |  ")
                    .append(f.getEstadoFactura()).append("  |  ")
                    .append(dinero(f.getTotal())).append("\n");
        }
        return sb.toString();
    }

    // =====================================================================
    // CONSULTAS
    // =====================================================================

    private static void consultarFacturasCliente() {
        String documento = leerTexto("Documento del cliente:");
        Optional<Cliente> opt = tienda.buscarCliente(documento);
        if (opt.isEmpty()) {
            aviso("Cliente no encontrado.");
            return;
        }
        List<Factura> facturas = tienda.obtenerFacturasPorCliente(documento);
        Cliente c= opt.get();
        if (facturas.isEmpty()) {
            aviso(c.getNombreCompleto() + " no tiene facturas.");
            return;
        }
        mostrarTexto("Facturas de " + c.getNombreCompleto(), resumenFacturas(facturas));
    }

    private static void consultarTotalPorFecha() {
        LocalDate fecha = leerFecha("Fecha a consultar (aaaa-mm-dd):");
        aviso("Total vendido el " + fecha + ": "
                + dinero(tienda.obtenerTotalVendidoPorFecha(fecha)));
    }

    private static void consultarBajoInventario() {
        int umbral = leerEntero("Mostrar productos con cantidad menor o igual a:", 0, "5");
        List<Producto> bajos = tienda.obtenerProductosBajoInventario(umbral);
        if (bajos.isEmpty()) {
            aviso("No hay productos con inventario <= " + umbral + ".");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Producto p : bajos) {
            sb.append(p.getCodigo()).append("  |  ")
                    .append(p.getNombre()).append("  |  stock: ")
                    .append(p.getCantidadDisponible()).append("\n");
        }
        mostrarTexto("Productos con bajo inventario", sb.toString());
    }

    // =====================================================================
    // UTILIDADES DE ENTRADA / SALIDA
    // =====================================================================

    /** Ejecuta una acción y, si el usuario cancela un diálogo, simplemente vuelve al menú. */
    private static void ejecutar(Runnable accion) {
        try {
            accion.run();
        } catch (Cancelado e) {
            // el usuario canceló: no se hace nada
        }
    }

    /** Muestra un menú desplegable. Devuelve el índice elegido o -1 si se cancela. */
    private static int elegir(String titulo, String[] opciones) {
        Object r = JOptionPane.showInputDialog(null, "Seleccione una opción:", titulo,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (r == null) {
            return -1;
        }
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(r)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private static <T> T elegirOpcion(String mensaje, T[] opciones, T inicial) {
        Object r = JOptionPane.showInputDialog(null, mensaje, TITULO,
                JOptionPane.QUESTION_MESSAGE, null, opciones,
                inicial != null ? inicial : opciones[0]);
        if (r == null) {
            throw new Cancelado();
        }
        return (T) r;
    }

    private static String leerTexto(String mensaje) {
        return leerTexto(mensaje, "");
    }

    private static String leerTexto(String mensaje, String inicial) {
        while (true) {
            Object r = JOptionPane.showInputDialog(null, mensaje, TITULO,
                    JOptionPane.QUESTION_MESSAGE, null, null, inicial);
            if (r == null) {
                throw new Cancelado();
            }
            String texto = r.toString().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            aviso("El campo no puede estar vacío.");
        }
    }

    private static int leerEntero(String mensaje, int minimo, String inicial) {
        while (true) {
            String texto = leerTexto(mensaje, inicial);
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo) {
                    return valor;
                }
                aviso("El valor debe ser mayor o igual a " + minimo + ".");
            } catch (NumberFormatException e) {
                aviso("Ingresa un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje, String inicial) {
        while (true) {
            String texto = leerTexto(mensaje, inicial);
            try {
                double valor = Double.parseDouble(texto);
                if (valor > 0) {
                    return valor;
                }
                aviso("El valor debe ser mayor que cero.");
            } catch (NumberFormatException e) {
                aviso("Ingresa un número válido (sin puntos de miles ni comas).");
            }
        }
    }

    private static LocalDate leerFecha(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje, LocalDate.now().toString());
            try {
                return LocalDate.parse(texto);
            } catch (DateTimeParseException e) {
                aviso("Formato inválido. Usa aaaa-mm-dd, por ejemplo 2026-10-06.");
            }
        }
    }

    private static boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, TITULO,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private static void aviso(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, TITULO, JOptionPane.INFORMATION_MESSAGE);
    }

    /** Muestra texto largo en un área con scroll. */
    private static void mostrarTexto(String titulo, String texto) {
        JTextArea area = new JTextArea(texto, 15, 50);
        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JOptionPane.showMessageDialog(null, new JScrollPane(area), titulo,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static String dinero(double valor) {
        return String.format("$%,.0f", valor);
    }
}