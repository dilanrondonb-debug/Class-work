package co.edu.uniquindio.poo;

public class Curso {
    private String nombre;
    private String codigo;
    private Estudiante[] estudiantes;
    private int contadorEstudiantes;

    public Curso(String nombre, String codigo, int capacidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.estudiantes = new Estudiante[capacidad];
        this.contadorEstudiantes = 0;
    }

    // Funcionalidad: Registrar co.edu.uniquindio.poo.Estudiante (Agregación)
    public boolean registrarEstudiante(Estudiante estudiante) {
        if (contadorEstudiantes < estudiantes.length) {
            estudiantes[contadorEstudiantes] = estudiante;
            contadorEstudiantes++;
            return true;
        }
        return false;
    }

    // Funcionalidad: Consultar co.edu.uniquindio.poo.Estudiante
    public Estudiante consultarEstudiante(String identificacion) {
        for (int i = 0; i < contadorEstudiantes; i++) {
            if (estudiantes[i].getIdentificacion().equals(identificacion)) {
                return estudiantes[i];
            }
        }
        return null;
    }

    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
}