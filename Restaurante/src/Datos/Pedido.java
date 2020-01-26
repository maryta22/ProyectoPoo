/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Usuario.Mesero;
import java.time.LocalDate;
import java.util.Date;
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
    private ObjectProperty<Mesa> mesa;
    private ObjectProperty<Mesero> mesero;
    private SimpleStringProperty cuenta;
    private SimpleStringProperty cliente;
    private SimpleDoubleProperty total;

    public Pedido(LocalDate fecha, Mesa mesa, Mesero mesero, String cuenta, String cliente, Double total) {
        this.fecha = new SimpleObjectProperty<>(this,"fecha",fecha);
        this.mesa = new SimpleObjectProperty<>(this,"mesa",mesa);
        this.mesero = new SimpleObjectProperty<>(this,"mesero",mesero);
        this.cliente = new SimpleStringProperty(cliente);
        this.cuenta =  new SimpleStringProperty(cuenta);
        this.total = new SimpleDoubleProperty(total);
    }

    public LocalDate getFecha() {
        return fecha.get();
    }

    public Mesa getMesa() {
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

    public void setMesa(Mesa mesa) {
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
    
    
}
