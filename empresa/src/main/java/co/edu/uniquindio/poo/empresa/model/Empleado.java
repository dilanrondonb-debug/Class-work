package co.edu.uniquindio.poo.empresa.model;

public class Empleado {
    private String nombre;
    private String apellido;
    private String identificacion;
    private String user;
    private String password;

    public Empleado(String nombre, String apellido, String identificacion, String user, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.user = user;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String usser) {
        this.user = usser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
