
package com.mycompany.ejemplo2proyecto1.persistencia;

import com.mycompany.ejemplo2proyecto1.models.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author leonel
 */
public class Persistencia {
    
    private static final String ARCHIVO = "usuarios.dat";
    private static final int CAPACIDAD = 100;
    
    public static boolean guardar(Usuario[] usuarios){
        if(usuarios == null){
            return false;
        }
        
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO))){
            salida.writeObject(usuarios);
            return true;
        }catch(IOException e){
            System.out.println("Error al guardar usuarios:"+e.getMessage());
            return false;
        }
    }
    
    public static Usuario[] cargar(){
        File archivo = new File(ARCHIVO);
        if(!archivo.exists()){
            return new Usuario[CAPACIDAD];
        }
        
        try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO))){
            return (Usuario[]) entrada.readObject();
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Error al cargar usuarios:"+e.getMessage());
            return new Usuario[CAPACIDAD];
        }
    }
    
}
