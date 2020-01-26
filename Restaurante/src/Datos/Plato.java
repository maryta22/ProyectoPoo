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
    private String nombre;
    private Double precio;
    private String ruta;
    private String tipo;
    
    public Plato(String nombre, Double precio,String ruta,String tipo){
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

    @Override
    public String toString() {
        return  nombre + "-" +  precio + "-" +  ruta + "-" +  tipo ;
    }
    
    
}
