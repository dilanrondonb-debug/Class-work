package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniquindio.poo.model.CategoriaEmpleado;
import co.edu.uniquindio.poo.model.EmpleadoVentas;
import co.edu.uniquindio.poo.model.Empresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;

/**
 * Pruebas de la clase EmpleadoVentas
 */
public class EmpleadoVentasTest {
    @BeforeEach
    public void setUp(){
        Empresa empresa=new Empresa();
        EmpleadoVentas e=new EmpleadoVentas("Juan","123",12,1200000,CategoriaEmpleado.JUNIOR,2, 12,
                1500000,12);
    }

    private static final Logger LOG = Logger.getLogger(EmpleadoVentasTest.class.getName());

    @Test
    public void calcularSalarioBruto() {
        LOG.info("Inicio test calcularSalarioBruto");

        // 1. Preparar
        EmpleadoVentas empleado = new EmpleadoVentas("Juan", "1093", 30, 1000000f,
                CategoriaEmpleado.JUNIOR, 4f, 4f, 100000f, 10f);

        // 2. Ejecutar y 3. Verificar
        // 1.000.000 + bonificación 5% (50.000) + comisión 10% de 100.000 (10.000)
        assertEquals(1060000f, empleado.calcularSalarioBruto(), 0.01f);

        LOG.info("Fin test calcularSalarioBruto");
    }

    @Test
    public void salarioBaseNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmpleadoVentas("Juan", "1093", 30, -1000f,
                        CategoriaEmpleado.JUNIOR, 4f, 4f, 100000f, 10f));
    }
}