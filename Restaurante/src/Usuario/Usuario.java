/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

/**
 *
 * @author danny
 */
public class Usuario {
    private String nombreUsuario;
    private String contraseña;
    
    public Usuario(String usuario, String contraseña){
        this.nombreUsuario = usuario;
        this.contraseña = contraseña;
        
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public String toString() {
        return nombreUsuario;    }
    
    public boolean equals(Object o){
        if(o!=null){
            Usuario u = (Usuario) o;
            if(this.contraseña.equals(u.getContraseña()) && this.nombreUsuario.equals(u.getNombreUsuario())){
                return true;
            }else{
                return false;
            }
            
        }
        return false;
    }
    
    
}
