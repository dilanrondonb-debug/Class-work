package co.edu.uniquindio.poo.model;

public class EmpleadoVentas extends Empleado{
    private float totalVentas;
    private float porcentajeComision;

    public EmpleadoVentas(String nombre, String documento, int edad,
                            float salarioBase, CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension
            ,float totalVentas,float porcentajeComision) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        if (porcentajeComision < 0 || porcentajeComision > 100) { throw new IllegalArgumentException("Porcentaje inválido");}
            if (totalVentas < 0) { throw new IllegalArgumentException("Ingrese un número de horas postivo");}

            this.totalVentas = totalVentas;
            this.porcentajeComision = porcentajeComision;

        }
        @Override
        public float calcularSalarioBruto(){
        return salarioBase+calcularBonificacionCategoria()
                +totalVentas*(porcentajeComision/100);
        }
    }
