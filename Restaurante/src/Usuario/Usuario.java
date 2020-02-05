/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author danny
 */
public class Usuario implements Serializable{
    private String nombreUsuario;
    private String contraseña;
    
    
    /**
     * Constructor de la clase
     * @param usuario Nombre de usuario
     * @param contraseña Contraseña del usuario
     */
    public Usuario(String usuario, String contraseña){
        this.nombreUsuario = usuario;
        this.contraseña = contraseña;
        
    }
    /**
     * Metodo para obtener el nombre de usuario
     * @return String con el nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    /**
     * Meotdo para obtener la contraseña
     * @return String con la contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    @Override
    /**
     * Metodo para imprimir el nombre
     */
    public String toString() {
        return nombreUsuario;    }
    
    /**
     * Metodo para comparar dos usuarios
     * @param obj Objeto a comparar
     * @return true si los usuarios son iguales, caso contrario false
     */
    public boolean equals(Object obj){
        if(obj!=null){
            Usuario u = (Usuario) obj;
            if(this.contraseña.equals(u.getContraseña()) && this.nombreUsuario.equals(u.getNombreUsuario())){
                return true;
            }else{
                return false;
            }
            
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.nombreUsuario);
        hash = 41 * hash + Objects.hashCode(this.contraseña);
        return hash;
    }
    
    
}
