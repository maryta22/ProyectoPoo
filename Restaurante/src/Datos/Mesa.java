/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Usuario.Mesero;
import javafx.scene.shape.Circle;

/**
 *
 * @author danny
 */
public class Mesa  {
    private double coordenadaX;
    private double coordenadaY;
    private double radio;
    private String numeroMesa;
    private Mesero mesa;
    private boolean disponible;

    public Mesa(double coordenadaX, double coordenadaY,double radio,String numero) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.radio = radio;
        this.numeroMesa = numero;
        disponible=true;
        
    }
    
    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public double getRadio() {
        return radio;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
    
    public String toString(){
        return numeroMesa;
    }

    
}
