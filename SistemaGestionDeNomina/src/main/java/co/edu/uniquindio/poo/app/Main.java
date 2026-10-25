package co.edu.uniquindio.poo.app;
import co.edu.uniquindio.poo.model.*;

import javax.swing.JOptionPane;

public class Main {
    private static final String MENU = """
        1. Agregar empleado de planta
        2. Agregar empleado de ventas
        3. Agregar empleado temporal
        4. Mostrar todos los empleados
        5. Buscar empleado por documento
        6. Mostrar empleado con mayor salario neto
        7. Mostrar nómina total
        8. Mostrar resumen de pagos
        9. Salir
        """;
    private static final Empresa empresa = new Empresa();

    public static void main(String[] args) {
        int opcion = 0;
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(MENU));
                switch (opcion) {
                    case 1 -> agregarPlanta();
                    case 2 -> agregarVentas();
                    case 3 -> agregarTemporal();
                    case 4 -> JOptionPane.showMessageDialog(null, empresa.mostrarEmpleados());
                    case 5 -> buscarEmpleado();
                    case 6 -> mostrarMayor();
                    case 7 -> mostrarNomina();
                    case 8 -> JOptionPane.showMessageDialog(null, empresa.mostrarResumenesPago());
                    case 9 -> JOptionPane.showMessageDialog(null, "Hasta pronto");
                    default -> JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido");
            } catch (IllegalArgumentException | IllegalStateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (opcion != 9);
    }
    private static String pedirTexto(String mensaje) {
        String respuesta = JOptionPane.showInputDialog(mensaje);
        if (respuesta == null) {
            throw new IllegalStateException("Operación cancelada");
        }
        return respuesta.trim();
    }

    private static int pedirEntero(String mensaje) {
        return Integer.parseInt(pedirTexto(mensaje));
    }

    private static float pedirDecimal(String mensaje) {
        return Float.parseFloat(pedirTexto(mensaje));
    }
    private static CategoriaEmpleado pedirCategoria() {
        Object seleccion = JOptionPane.showInputDialog(null, "Categoría:", "Categoría",
                JOptionPane.QUESTION_MESSAGE, null,
                CategoriaEmpleado.values(), CategoriaEmpleado.JUNIOR);
        if (seleccion == null) {
            throw new IllegalStateException("Operación cancelada");
        }
        return (CategoriaEmpleado) seleccion;
    }
    private record DatosBase(String nombre, String documento, int edad, float salarioBase,
                             CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension) { }
    private static DatosBase pedirDatosBase() {
        String nombre = pedirTexto("Nombre:");
        String documento = pedirTexto("Documento:");
        int edad = pedirEntero("Edad:");
        float salarioBase = pedirDecimal("Salario base:");
        CategoriaEmpleado categoria = pedirCategoria();
        float descuentoSalud = pedirDecimal("Descuento de salud (0-100):");
        float descuentoPension = pedirDecimal("Descuento de pensión (0-100):");

        return new DatosBase(nombre, documento, edad, salarioBase, categoria,
                descuentoSalud, descuentoPension);
    }
    private static void agregarPlanta() {
        DatosBase d= pedirDatosBase();

        String cargo = pedirTexto("Cargo:");
        int horasExtra=pedirEntero("Horas extras:");
        float valorHoraExtra=pedirDecimal("Valor de la hora extra:");
        float auxilioDeTransporte=pedirDecimal("Auxilio de transporte: ");
        Empleado e = new EmpleadoDePlanta(d.nombre(), d.documento(), d.edad(),
        d.salarioBase(), d.categoria(), d.descuentoSalud(),d.descuentoPension()
            ,cargo, horasExtra, valorHoraExtra, auxilioDeTransporte);
        JOptionPane.showMessageDialog(null, empresa.agregarEmpleado(e));
    }
    private static void agregarVentas(){
        DatosBase d= pedirDatosBase();

        float totalVentas= pedirDecimal("Total de ventas: ");
        float porcentajeComision= pedirDecimal("Porcentaje de comision: ");
        Empleado e=new EmpleadoVentas(d.nombre(), d.documento(), d.edad(),
                d.salarioBase(), d.categoria(), d.descuentoSalud(),d.descuentoPension(),
                totalVentas, porcentajeComision);
        JOptionPane.showMessageDialog(null, empresa.agregarEmpleado(e));
    }
    private static void agregarTemporal(){
        DatosBase d=pedirDatosBase();

        int diasTrabajados=pedirEntero("Numero de dias trabajados: ");
        float valorDia=pedirDecimal("Valor del dia: ");

        Empleado e=new EmpleadoTemporal(d.nombre(), d.documento(), d.edad(),
                d.salarioBase(), d.categoria(), d.descuentoSalud(),d.descuentoPension(),diasTrabajados,valorDia);
        JOptionPane.showMessageDialog(null, empresa.agregarEmpleado(e));
    }
    private static void buscarEmpleado(){
        String documento=pedirTexto("Ingrese el documento: ");
        empresa.buscarEmpleado(documento).ifPresentOrElse(e->JOptionPane.showMessageDialog
                (null,e.mostrarInformacion()),()-> JOptionPane.showMessageDialog
                (null,"No existe un empleado con ese numero de documento."));
    }
    private static void mostrarMayor(){
        empresa.hayarEmpleadoMayorSalarioNeto().ifPresentOrElse(e->JOptionPane.showMessageDialog
                (null, e.mostrarInformacion()),()->JOptionPane.showMessageDialog
                        (null,"No hay uno con ayor salario"));
    }
    private static void mostrarNomina(){
        float total= empresa.calcularNominaTotal();
        JOptionPane.showMessageDialog(null,"Nomina total de la empresa: "+ total);
    }
}
