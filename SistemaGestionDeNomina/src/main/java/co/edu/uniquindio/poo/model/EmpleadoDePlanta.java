package co.edu.uniquindio.poo.model;

public class EmpleadoDePlanta extends  Empleado{
    private String cargo;
    private int horasExtras;
    private float valorHoraExtra;
    private float auxilioTransporte;
    public EmpleadoDePlanta(String nombre, String documento, int edad,
                            float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension
            ,String cargo,int horasExtras, float valorHoraExtra,float auxilioTransporte){
        super(nombre,documento,edad, salarioBase, categoria,descuentoSalud,  descuentoPension );
        if(horasExtras<0){throw new IllegalArgumentException("No puede tener horas extras negativas");}
        if(valorHoraExtra<0){throw new IllegalArgumentException("No puede tener valor de horas extras negativas");}
        this.cargo=cargo;
        this.horasExtras=horasExtras;
        this.valorHoraExtra=valorHoraExtra;
        this.auxilioTransporte=auxilioTransporte;
    }
    @Override
    public float calcularSalarioBruto(){
        return  salarioBase+calcularBonificacionCategoria()
                +valorHoraExtra*horasExtras+auxilioTransporte;
    }
}
