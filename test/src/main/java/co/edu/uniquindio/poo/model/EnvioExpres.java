package co.edu.uniquindio.poo.model;

public class EnvioExpres extends EnvioNacional {
    private final int horasMaximas;
    public EnvioExpres(String codigo, String destinatario, float peso,
                       String ciudadDestino, boolean entregaADomicilio, int horasMaximas) {
        super(codigo, destinatario, peso, ciudadDestino, entregaADomicilio);
        this.horasMaximas=horasMaximas;
    }
    @Override
    public double obtenerCostoEnvio(){
        double costo=super.obtenerCostoEnvio();
        double costoFinal=costo+costo*0.3;
        if(horasMaximas<=24) costoFinal+=5000;
        return costoFinal;

    }
}
