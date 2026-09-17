package co.edu.uniquindio.poo;

public class Estudiante {
    private String nombres;
    private String apellidos;
    private String identificacion;
    private byte edad;
    private String correo;
    private String telefono;
    private Nota[] notas;
    private int contadorNotas;

    public Estudiante(String nombres, String apellidos, String identificacion, byte edad, String correo, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
        this.notas = new Nota[5]; // Límite de 5 notas
        this.contadorNotas = 0;
    }

    // Funcionalidad: Registrar co.edu.uniquindio.poo.Nota
    public boolean registrarNota(String nombre, float valor) {
        if (contadorNotas < 5) {
            notas[contadorNotas] = new Nota(nombre, valor); // Relación fuerte (Composición)
            contadorNotas++;
            return true;
        }
        return false;
    }

    // Funcionalidad: Consultar co.edu.uniquindio.poo.Nota
    public Nota consultarNota(String nombreNota) {
        for (int i = 0; i < contadorNotas; i++) {
            if (notas[i].getNombre().equalsIgnoreCase(nombreNota)) {
                return notas[i];
            }
        }
        return null;
    }

    // Getters
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getIdentificacion() { return identificacion; }
    public byte getEdad() { return edad; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public Nota[] getNotas() { return notas; }
}