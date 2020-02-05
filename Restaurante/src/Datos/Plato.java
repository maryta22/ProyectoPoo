/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.io.Serializable;

/**
 *
 * @author danny
 */
public class Plato implements Serializable {
    private int codigo;
    private String nombre;
    private Double precio;
    private String ruta;
    private String tipo;
    /**
     * Constructor de la clase
     * @param codigo codigo del plato
     * @param nombre nombre del plato
     * @param precio precio del plato
     * @param ruta ruta de la imagen
     * @param tipo tipo de plato
     */
    public Plato(int codigo,String nombre, Double precio,String ruta,String tipo){
        this.codigo=codigo;
        this.nombre=nombre;
        this.precio=precio;
        this.ruta=ruta;
        this.tipo=tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getRuta() {
        return ruta;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    

    @Override
    public String toString() {
        return  nombre + "-" +  precio + "-" +  ruta + "-" +  tipo ;
    }
    
    public boolean equals(Object o){
        if(o!=null){
            Plato plato = (Plato) o;
            if(plato.getNombre().equals(this.nombre)){
                return true;
            }
        }
        return false;
    }
    
}
