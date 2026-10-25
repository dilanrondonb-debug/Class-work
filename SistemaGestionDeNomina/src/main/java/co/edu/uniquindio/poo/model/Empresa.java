package co.edu.uniquindio.poo.model;
import  java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
public class Empresa {
    private String nombre;
    private List<Empleado> empleados=new ArrayList<>();

    //Funciones requeridas
    public String agregarEmpleado(Empleado e){
        if(e==null) throw new IllegalArgumentException("El empleado no puede estar vacío");
        for(Empleado empleado:empleados){
            if(empleado.getDocumento().equals(e.getDocumento()))return "Empleado ya existe";
        }
        empleados.add(e);
        return "Empleado añadido exitosamente";
    }
    public Optional<Empleado> buscarEmpleado(String documento){
        return empleados.stream().filter(e -> e.getDocumento().equals(documento)).findFirst();
    }
    public String mostrarEmpleados(){
        String resultado="";
        for(Empleado e:empleados){
            resultado+=e.mostrarInformacion()+"\n";
        }
        return resultado;
    }
    public Optional<Empleado> hayarEmpleadoMayorSalarioNeto(){
        return empleados.stream().max(Comparator.comparing(Empleado::calcularSalarioNeto));
    }
    public float calcularNominaTotal(){
        float resultado=0;
        for(Empleado e:empleados){
            resultado+=e.calcularSalarioNeto();
        }
        return resultado;
    }
    public String mostrarResumenesPago(){
        String resultado="";
        for(Empleado e:empleados){
            ResumenPago r=e.generarResumenPago();
            resultado+=r+"\n";
        }
        return resultado;
    }

}
