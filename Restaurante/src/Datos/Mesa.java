/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Usuario.Mesero;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private Mesero mesero;
    private boolean disponible;
    private Map<String,Double> comidasPedido;
    private String cliente="Prueba";
    private Double valorTotalFacturado;

    public Mesa(double coordenadaX, double coordenadaY,double radio,String numero) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.radio = radio;
        this.numeroMesa = numero;
        disponible=true;
        comidasPedido = new HashMap<>();
        mesero = null;
        valorTotalFacturado = new Double(0);
        
    }
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }
    
    public String getCliente(){
        return cliente;
    }
    public Map<String, Double> getComidasPedido() {
        return comidasPedido;
    }

    public void setComidasPedido(Map<String, Double> comidasPedido) {
        this.comidasPedido = comidasPedido;
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
    
    public void setRadio(double radio){
        this.radio = radio;
    }
    
    public Mesero getMesero(){
        return mesero;
    }
    
    public void setMesero(Mesero mesero){
        this.mesero = mesero;
    }
    
    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
    
    public void setVentasTotal(Double venta){
        valorTotalFacturado += venta;
    }
    
   
    public String getDatos() {
        if(mesero==null){
            return "Numero de Mesa=" + numeroMesa + 
                "\nMesero= Ninguno" + 
                "\nDisponible=" + disponible +
                "\nCliente=" + cliente +
                "\nValor Facturado en el Dia=" + valorTotalFacturado;
        }
        return  "Numero de Mesa=" + numeroMesa + 
                "\n Mesero=" + mesero.getNombreUsuario() + 
                "\n Disponible=" + disponible +
                "\n Cliente=" + cliente +
                "\n Valor Facturado en el Dia=" + valorTotalFacturado;
    }
    
    public String toString(){
        return numeroMesa;
    }
    
    public boolean equals(Object o){
        if(o!=null){
            Mesa mesa = (Mesa) o;
            if(mesa.numeroMesa.equals(this.numeroMesa)){
                return true;
            }
        }
        return false;
    }

    
}
