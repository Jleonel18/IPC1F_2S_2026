
package com.mycompany.ejemplo1proyecto1.models;

/**
 *
 * @author leonel
 */
public class Usuario {
    
    private int codigo;
    private String usuario;
    private String password;
    private String rol;

    public Usuario() {
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
