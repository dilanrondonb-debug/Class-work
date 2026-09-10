package co.edu.uniquindio.poo;

import javax.swing.JOptionPane;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static void main() {
        int arreglo[] = {1, 2, 3, 4, 5};
        //Método que verifique si hay un numero en un arreglo
        int numero= Integer.parseInt(JOptionPane.showInputDialog(null, "Escriba el numero a comprobar"));
        boolean respuesta= determinarNumero(numero, arreglo);
        JOptionPane.showMessageDialog(null, "el número esta?"+ respuesta);
    }
    }
    public static boolean determinarNumero(int numero, int[] arreglo) {
        boolean result = false;
        for(int i =0; i< arreglo.length; i++) {
            if(arreglo[i]==numero){
                result=true;
                break;
            }

        }
        return result;
    }