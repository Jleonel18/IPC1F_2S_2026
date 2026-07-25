
package com.mycompany.ejemplo1;
import java.util.Scanner;

public class Ejemplo1 {

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("La suma de dos números");
        System.out.print("Escriba el número 1: ");
        int var1 = sc.nextInt();
        System.out.print("Escriba el número 2:");
        int var2 = sc.nextInt();
        System.out.println("La suma es: "+sumar(var1,var2));

    }
    
    public static int sumar(int numero1, int numero2){
        return numero1+ numero2;
    }

}