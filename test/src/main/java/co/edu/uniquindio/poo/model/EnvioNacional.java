package co.edu.uniquindio.poo.model;

public class EnvioNacional extends Envio{
    private final short costoFijo=6000;
    private String ciudadDestino;
    private boolean entregaADomicilio;
    public EnvioNacional(String codigo, String destinatario, float peso,
                         String ciudadDestino, boolean entregaADomicilio) {
        super(codigo, destinatario, peso);
        this.ciudadDestino = ciudadDestino;
        this.entregaADomicilio = entregaADomicilio;
    }
    @Override
    public double obtenerCostoEnvio(){
        double costo=costoFijo+2000*getPeso();
        if (entregaADomicilio) costo=costo+3000;
        return costo;
    }
}
