package co.edu.uniquindio.poo.empresa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Empresa {
    private String nombre;
    private List<Empleado> empleados=new ArrayList<>();

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    public String registrarEmpleado(Empleado empleado){
        for(Empleado e:empleados){
            if(e.getIdentificacion().equals(empleado.getIdentificacion())) return "El empleado ya existe";
        }
            empleados.add(empleado);
            return "Empleado agregado exitosamente";


    }
    private Optional<Empleado> buscarEmpleado(String identificacion){
        return empleados.stream().filter(c->c.getIdentificacion().equals(identificacion)).findFirst();
    }
    public boolean verificarUsuario(String user, String password){
        return empleados.stream().filter(empleado ->empleado.getUser()&& empleado.getPassword() ).findAny()
    }
}
