package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Envio> envios = new ArrayList<>();
        envios.add(new EnvioNacional("N1", "Ana", 2f, "Pereira", true));
        envios.add(new EnvioInternacional("I1", "Luis", 2f, "España", true));
        envios.add(new EnvioExpres("E1", "Sara", 2f, "Cali", true, 24));

        for (Envio e : envios) {
            System.out.println(e.getCodigo() + ": " + e.obtenerCostoEnvio());
        }
    }
}
