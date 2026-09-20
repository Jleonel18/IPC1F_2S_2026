
package com.mycompany.ejemplo2proyecto1.models;

import java.io.Serializable;

/**
 *
 * @author leonel
 */
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int codigo;
    private String usuario;
    private String password;
    private String rol;

    public Usuario(){
    }
    
    

    public Usuario(int codigo, String usuario, String password, String rol) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
    
}
