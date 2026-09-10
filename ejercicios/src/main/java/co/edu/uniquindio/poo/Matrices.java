package co.edu.uniquindio.poo;

import javax.swing.*;

public class Matrices {
    public static void main(String[]args){
        int filas=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de filas que trendrá su matriz"));
        int columnas=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de columnas que trendrá su matriz"));

        int [][] matriz= new int[filas][columnas];
        for (int fila=0; fila<filas; fila++){
            for(int columna=0; columna<columnas; columna++){
                matriz[fila][columna]=Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el valor de la fila "+(fila+1)+" columna "+(columna+1)));
            }
        }
        imprimirMatriz(matriz);
        int sumaMatriz= sumarNumerosDeMatriz(matriz);
        int sumaDiagonal = sumarDiagonalPrincipal(matriz);
        int sumaDiagonalSecundaria = sumarDiagonalSecundaria(matriz);
        JOptionPane.showMessageDialog(null,"La suma de la diagonal principal de la matriz es: "+ sumaDiagonal);
        JOptionPane.showMessageDialog(null,"La suma de la diagonal secundaria de la matriz es: "+ sumaDiagonalSecundaria);
        JOptionPane.showMessageDialog(null,"La suma de los elementos de la matriz es: "+ sumaMatriz);
        int tamanioEspiral=Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el tamaño de la matriz en espiral"));
        int[][] matrizEspiral= crearEspiral(tamanioEspiral);
        imprimirMatriz(matrizEspiral);

    }
    public static void imprimirMatriz(int[][] matriz) {
        String texto = "";
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz[fila].length; columna++) {
                texto += " "+ matriz[fila][columna] + "\t";
            }

            texto += "\n";
        }

        JOptionPane.showMessageDialog(null, texto);
    }
    public static int sumarDiagonalPrincipal(int[][]matriz){
        int resultado =0;
        for(int i=0; i<matriz.length;i++){
            resultado+=matriz[i][i];
        }
        return resultado;
    }
    public static int sumarDiagonalSecundaria(int[][]matriz){
        int resultado=0;
        int n= matriz.length;
        for(int i=0; i< matriz.length;i++){
            resultado+=matriz[i][n-1-i];
        }
        return resultado;
    }
    public static int sumarNumerosDeMatriz(int[][]matriz){
        int resultado=0;
        for(int i=0; i< matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                resultado+=matriz[i][j];
            }
        }
        return resultado;
    }
    public static int[][] crearEspiral(int n) {
        int[][] matriz = new int[n][n];

        int numero = 1;

        int arriba = 0;
        int abajo = n - 1;
        int izquierda = 0;
        int derecha = n - 1;

        while (arriba <= abajo && izquierda <= derecha) {

            // 1. Ir de izquierda a derecha
            for (int columna = izquierda; columna <= derecha; columna++) {
                matriz[arriba][columna] = numero;
                numero++;
            }
            arriba++;

            // 2. Ir de arriba hacia abajo
            for (int fila = arriba; fila <= abajo; fila++) {
                matriz[fila][derecha] = numero;
                numero++;
            }
            derecha--;

            // 3. Ir de derecha a izquierda
            for (int columna = derecha; columna >= izquierda; columna--) {
                matriz[abajo][columna] = numero;
                numero++;
            }
            abajo--;

            // 4. Ir de abajo hacia arriba
            for (int fila = abajo; fila >= arriba; fila--) {
                matriz[fila][izquierda] = numero;
                numero++;
            }
            izquierda++;
        }

        return matriz;
    }
}
