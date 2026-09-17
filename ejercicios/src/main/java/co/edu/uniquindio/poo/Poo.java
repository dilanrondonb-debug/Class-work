package co.edu.uniquindio.poo;

public class Poo {
    public class Main {
        public static void main(String[] args) {
            // Crear el curso
            Curso curso = new Curso("Programación I", "12309", 30);

            // 1. Crear y registrar estudiante al curso
            Estudiante est1 = new Estudiante(
                    "Robinson", "Arias Muñoz", "1098765432",
                    (byte) 20, "robinson@uniquindio.edu.co", "3001234567"
            );
            curso.registrarEstudiante(est1);

            // 2. Registrar notas al estudiante
            est1.registrarNota("p1", 3.0f);
            est1.registrarNota("p2", 4.5f);

            // 3. Consultar estudiante en el curso
            Estudiante buscado = curso.consultarEstudiante("1098765432");
            if (buscado != null) {
                System.out.println("co.edu.uniquindio.poo.Estudiante encontrado: " + buscado.getNombres() + " " + buscado.getApellidos());
            }

            // 4. Consultar nota del estudiante
            Nota notaBuscada = est1.consultarNota("p1");
            if (notaBuscada != null) {
                System.out.println("co.edu.uniquindio.poo.Nota consultada: " + notaBuscada.getNombre() + " = " + notaBuscada.getValor());
            }
        }
    }
}
