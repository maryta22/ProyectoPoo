/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Usuario.Mesero;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author danny
 */
public class Pedido {
    private ObjectProperty<LocalDate> fecha;
    private SimpleStringProperty mesa;
    private ObjectProperty<Mesero> mesero;
    private SimpleStringProperty cuenta;
    private SimpleStringProperty cliente;
    private SimpleDoubleProperty total;

    /**
     * Constructo de la clase
     * @param fecha Fecha actual cuando se hace el pedido
     * @param mesa Mesa del pedido
     * @param mesero Mesero que tomo el pedido
     * @param cuenta Cuenta asociada al pedido
     * @param cliente Cliente asociado al pedido
     * @param total  Total a pagar por el pedido
     */
    public Pedido(LocalDate fecha, String mesa, Mesero mesero, String cuenta, String cliente, Double total) {
        this.fecha = new SimpleObjectProperty<>(this,"fecha",fecha);
        this.mesa = new SimpleStringProperty(mesa);
        this.mesero = new SimpleObjectProperty<>(this,"mesero",mesero);
        this.cliente = new SimpleStringProperty(cliente);
        this.cuenta =  new SimpleStringProperty(cuenta);
        this.total = new SimpleDoubleProperty(total);
    }

    public LocalDate getFecha() {
        return fecha.get();
    }

    public String getMesa() {
        return mesa.get();
    }

    public Mesero getMesero() {
        return mesero.get();
    }

    public String getCuenta() {
        return cuenta.get();
    }

    public String getCliente() {
        return cliente.get();
    }

    public Double getTotal() {
        return total.get();
    }

    public void setFecha(LocalDate fecha) {
        this.fecha.set(fecha);
    }

    public void setMesa(String mesa) {
        this.mesa.set(mesa);
    }

    public void setMesero(Mesero mesero) {
        this.mesero.set(mesero);
    }

    public void setCuenta(String cuenta) {
        this.cuenta.set(cuenta);
    }

    public void setCliente(String cliente) {
        this.cliente.set(cliente);
    }

    public void setTotal(Double total) {
        this.total.set(total); 
    }
    
    public String toString(){
        return fecha.get().toString()+","+mesa.get().toString()+","+mesero.get() .toString()+","+cuenta.get()+","+cliente.get()+","+String.valueOf(total.get());
    }
    
}
