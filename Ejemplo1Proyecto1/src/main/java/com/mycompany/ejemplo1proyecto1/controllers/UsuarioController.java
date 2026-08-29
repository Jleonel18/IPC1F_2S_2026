package com.mycompany.ejemplo1proyecto1.controllers;


import com.mycompany.ejemplo1proyecto1.models.Usuario;

/**
 *
 * @author leonel
 */
public class UsuarioController {
    
    public static Usuario[] usuarios = new Usuario[100];
    
    public Usuario login(String usuario, String password){
        
        if(usuarios == null || usuario == null || password == null){
            return null;
        }
        
        if(usuario.isEmpty() || password.isEmpty()){
            return null;
        }
        
        for(Usuario u: usuarios){
            if(u == null){
                continue;
            }
            
            String usuarioGuardado = u.getUsuario();
            String passwordGuardado = u.getPassword();
            
            if(usuarioGuardado == null){
                continue;
            }
            
            if(usuarioGuardado.equals(usuario) && passwordGuardado.equals(password)){
                return u;
            }
        }
        
        return null;
        
    }
    
    public boolean registrarUsuario(Usuario usuario){
        if(usuarios == null || usuario == null ){
            return false;
        }
        
        if(usuario.getPassword() == null){
            return false;
        }
        
        if(buscarPorUsuario(usuario.getUsuario())!= null){
            return false;
        }
        
        for(int i= 0; i<usuarios.length; i++){
            if(usuarios[i] == null){
                usuarios[i] = usuario;
                return true;
            }
        }
        
        
        return false;
    }
    
    public Usuario buscarPorUsuario(String usuario){
        if(usuarios == null || usuario == null){
            return null;
        }
        
        for(Usuario u: usuarios){
            if(u==null || u.getUsuario() == null){
                continue;
            }
            
            if(u.getUsuario().equals(usuario)){
                return u;
            }
        }
        
        return null;
    }
    
}
