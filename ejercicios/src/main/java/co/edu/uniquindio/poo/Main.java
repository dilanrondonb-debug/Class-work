package co.edu.uniquindio.poo;
import javax.swing.JOptionPane;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static void main() {
        int tamanio = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el tamaño del arreglo"));
        int arreglo[] = new int[tamanio];
        for(int i=0; i<arreglo.length; i++){
            arreglo[i]= Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el valor del arreglo en la posicion " + (i+1)));
        }
        //Metodo para sumar
        int suma= sumarArreglo(arreglo);
        //Método que verifique si hay un numero en un arreglo
        int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el numero a comprobar"));
        boolean respuesta = determinarNumero(numero, arreglo);
        //Verificar numero repetido
        int numeroMasRepetido[] =  numeroMasRepetido(arreglo);
        JOptionPane.showMessageDialog(null, "el número "+ numero + (respuesta ? " está":" no está")+" en el arreglo"+"\n"+"el resultado de la suma es:"+ suma);
        if (numeroMasRepetido.length==1) {
            for (int j : arreglo) {
                JOptionPane.showMessageDialog(null, "el numero mas repetido es: " + j);
            }
        }
        else if(numeroMasRepetido.length==0){
            JOptionPane.showMessageDialog(null, "no hay un numero repetido");
        }
        else{
            JOptionPane.showMessageDialog(null, "los numeros repetidos son: ");
            for (int j : arreglo) {
                JOptionPane.showMessageDialog(null, j);
            }
        }
    }

    public static boolean determinarNumero(int numero, int[] arreglo) {
        boolean result = false;
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == numero) {
                result = true;
                break;
            }

        }
        return result;
    }
    public static int sumarArreglo(int[]arreglo){
        int suma=0;
        for(int i=0; i<arreglo.length;i++){
            suma+=arreglo[i];
        }
        return suma;
    }
    public static int[] numeroMasRepetido(int[] array) {
        int maximo = 0;
        int repetidos[] = new int[array.length];
        int repetidosSize = 0;
        for (int i= 0; i < array.length; i++) {
            int contador = 0;
            for (int j=i+1; j<array.length; j++) {
                if (i == j) {
                    contador++;
                }
            }
            if (contador > maximo) {
                maximo = contador;
                repetidosSize = 0;
                repetidos[repetidosSize++] = i;
            } else if (contador == maximo) {
                boolean yaExiste = false;
                for (int k = 0; k < repetidosSize; k++) {
                    if (repetidos[k] == i) {
                        yaExiste = true;
                        break;
                    }
                }
                if (!yaExiste) {
                    repetidos[repetidosSize++] = i;
                }
            }

        }
        int resultado[] = new int[repetidosSize];
        for (int i = 0; i < repetidosSize; i++) {
            resultado[i] = repetidos[i];
        }
        return resultado;
        }
        public static boolean numeroRepetido(int[]arreglo){
        boolean resultado = false;
        for(int i=0; i<arreglo.length && resultado == false; i++){
           int num1=arreglo[i];
           for(int j=i+1; j<arreglo.length; j++){
               int num2= arreglo[j];
               if (num1 == num2) {
                   resultado = true;
                   break;
               }
           }
        }
        return resultado;
        }
    }