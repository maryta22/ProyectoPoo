/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Usuario.Mesero;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.shape.Circle;

/**
 *
 * @author danny
 */
public class Mesa implements Serializable  {
    private double coordenadaX;
    private double coordenadaY;
    private double radio;
    private String numeroMesa;
    private Mesero mesero;
    private boolean disponible;
    private Map<String,ArrayList<Double>> comidasPedido;
    private String cliente;
    private Double valorTotalFacturado;

    public Mesa(double coordenadaX, double coordenadaY,double capacidad,String numero) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        radio =asignarRadio(capacidad);
        this.numeroMesa = numero;
        disponible=true;
        comidasPedido = new HashMap<>();
        cliente = null;
        mesero = null;
        valorTotalFacturado = new Double(0);
        
    }
    
    public double asignarRadio(double capacidad){
        double radioAsignado;
         if(capacidad<10){
             radioAsignado = 50;
         }else if(capacidad>=10 && capacidad<30){
             radioAsignado = 60;
         }else{
             radioAsignado = 70;
         }
         return radioAsignado;
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
    public Map<String, ArrayList<Double>> getComidasPedido() {
        return comidasPedido;
    }

    public void setComidasPedido(Map<String, ArrayList<Double>> comidasPedido) {
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
    
    public void setCliente(String cliente){
        this.cliente = cliente;
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
    
    public Double totalFactura(){
        Double valor = new Double(0);
        for(String plato: comidasPedido.keySet()){
            valor += comidasPedido.get(plato).get(1);
        }
        return valor;
    }
    
}
