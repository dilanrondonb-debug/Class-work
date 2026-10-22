package co.edu.uniquindio.poo.model;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnvioTest{
        /**
         * Instancia para el manejo de logs
         */
        private static final Logger LOG = Logger.getLogger(EnvioTest.class.getName());
        @Test
        public void Envio() {
            LOG.info("Inicio de prueba calcularCostoFijo...");
            // codigo de la prueba
            Envio envio= new EnvioNacional("001","juan", 25, "Armenia",true);

            double costoTotal=envio.obtenerCostoEnvio();
            assertEquals(25,costoTotal);
            LOG.info("Fin de prueba calcularCostoFijo...");
        }

}


