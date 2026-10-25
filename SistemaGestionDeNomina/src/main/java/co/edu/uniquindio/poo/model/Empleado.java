package co.edu.uniquindio.poo.model;

public abstract class Empleado {
    protected String nombre;
    protected String documento;
    protected int edad;
    protected float salarioBase;
    protected CategoriaEmpleado categoria;
    protected float descuentoSalud;
    protected float descuentoPension;

    public Empleado(String nombre, String documento, int edad,
                    float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension ) {
        if(salarioBase<0){throw new IllegalArgumentException("el salario no puede ser negativo"); }
        if(descuentoSalud <0||descuentoSalud>100||descuentoPension<0||descuentoPension>100){throw new IllegalArgumentException("Descuento inválido");}
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.salarioBase = salarioBase;
        this.categoria = categoria;
        this.descuentoSalud = descuentoSalud;
        this.descuentoPension = descuentoPension;

    }
    public abstract float calcularSalarioBruto();
    public float calcularBonificacionCategoria(){
    return salarioBase*(categoria.getPorcentajeBonificacion()/100);
    }
    public float calcularDescuentos(){
        float resultado=calcularSalarioBruto()*(descuentoPension/100)+
                calcularSalarioBruto()*(descuentoSalud/100);
        return resultado;
    }
    public float calcularSalarioNeto() {
        float resultado = calcularSalarioBruto()-calcularDescuentos();
        return resultado;
    }
    public String mostrarInformacion(){
        return "nombre: "+ nombre + "\ndocumento: "+documento+
                "\nedad: "+edad+"\nsalario base: "+ salarioBase+"\ncategoria: "+categoria+"\ndescuentos: "
                + calcularDescuentos()+ "\nbonificaciones: "+calcularBonificacionCategoria()+"\nsalario bruto: "+calcularSalarioBruto()+
                "\nsalario neto: "+ calcularSalarioNeto();
    }
    public ResumenPago generarResumenPago(){
        return new ResumenPago(documento,nombre,  this.getClass().getSimpleName(), calcularSalarioBruto(), calcularDescuentos(), calcularSalarioNeto());
    }
    //getters
    public String getDocumento(){return documento;}
    public String getNombre(){return nombre;}

}
