
package com.mycompany.ejemplo_proyecto1;

import java.util.Random;
import java.util.Scanner;

public class Ejemplo_Proyecto1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        menu(sc);
        
    }
    
    public static void menu(Scanner sc){
        int opcion;
        char [][] tablero = crearTablero();
        
        do{
            System.out.println("Sistema de estacionamiento");
            System.out.println("1. Ingresar vehículo");
            System.out.println("2. Retirar vehículo");
            System.out.println("3. Mostrar estacionamiento");
            System.out.println("7. Salir");
            
            opcion = sc.nextInt();
            
            switch(opcion){
                
                case 1:
                    System.out.println("Está ingresando un vehículo");
                    ingresarVehiculo(sc);
                    break;
                case 2:
                    break;
                case 3:
                    mostarTablero(tablero);
                    break;
                    
                case 7:
                    System.out.println("Gracias por entrar");
                    break;
                    
                default:
                    System.out.println("Opción inválida");
                
            }
        }while(opcion !=7);
    }
    
    public static char[][] crearTablero(){
    
        char[][] tablero = new char[5][5];
        
        //Llenar todo con '='
        for(int i=0; i<tablero.length; i++){
            for(int j=0; j<tablero[i].length; j++){
                tablero[i][j]='=';
            }
        }
        
        for(int i=1; i<=3; i++){
            for(int j=1; j<=3;j++){
                tablero[i][j] = 'L';
            }
        }
        
        int[][] posiciones = crearEntradaYSalida();
        int [] entrada = posiciones[0];
        int [] salida = posiciones[1];
        
        tablero[entrada[0]][entrada[1]] = 'E';
        tablero[salida[0]][salida[1]] = 'S';
        
         
        return tablero;
        
    }
    
    public static void ingresarVehiculo(Scanner sc){
        System.out.println("Ingresa la placa:");
        String placa;
        placa = sc.nextLine();
        
        // Ingresar lógica para validar placas
    }
    
    public static void mostarTablero(char[][] tablero){
        System.out.println("Estacionamiento:");
        System.out.println("            ");
        for(int i=1; i<=3; i++){
            System.out.print(i+"  ");
            
        }
        System.out.println();
        
        for(int i =0; i< tablero.length;i++){
            if(i>=1 && i<=3){
                System.out.print(i+" ");
                
            }else{
                System.out.print("  ");
                
            }
            
            for(int j=0; j<tablero[i].length;j++){
                System.out.print(tablero[i][j]+" ");
            }
            System.out.println();
        }
        
        
    }
    
    public static int[] generarPosicionBorde(){
        int borde = (int) (Math.random()*4);
        
        int fila;
        int columna;
        
        switch(borde){
            case 0: //Arriba
                fila = 0;
                columna = (int) (Math.random()*5);
                break;
                
            case 1: // Abajo
                fila = 4;
                columna = (int) (Math.random()*5);
                break;
            case 2: //Izquierda
                fila = (int) (Math.random()*5);
                columna = 0;
                break;
            default: // Derecha
                fila = (int) (Math.random()*5);
                columna = 4;
                break;
                        
        }
        
        return new int[]{fila,columna};
    }
    
    public static int[][] crearEntradaYSalida(){
        int[] entrada = generarPosicionBorde();
        int[] salida = generarPosicionBorde();
        
        //Si son iguales, generar otra posición
        while(entrada[0]==salida[0] && entrada[1]==salida[1]){
            salida = generarPosicionBorde();
        }
        
        return new int[][]{entrada,salida};
    }
    
    
}
