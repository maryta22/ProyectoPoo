/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alertas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author danny
 */
public class Alerta {
    private String tipoAlerta;
    
    /**
     * Contructor de la clase
     * @param tipo String para identificar el tipo de alerta a mostrar
     */
    
    public Alerta(String tipo){
        tipoAlerta = tipo;
    }
    
    /**
     * Metodo para mostrar una alerta de acuerdo al tipo recibido
     */
    
    public void mostrarAlerta(){
        Alert alerta;
        switch(tipoAlerta){
            case "Datos Invalidos":
                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle(tipoAlerta);
                alerta.setContentText("Ya existe una mesa con el mismo numero");
                alerta.showAndWait();
                break;
            case "Formato Incorrecto":
                alerta = new Alert(AlertType.WARNING);
                alerta.setTitle(tipoAlerta);
                alerta.setContentText("Los datos ingresados deben ser numeros");
                alerta.showAndWait();
                break;
            case "Datos Inconsistentes":
                alerta = new Alert(AlertType.WARNING);
                alerta.setTitle(tipoAlerta);
                alerta.setContentText("Los datos ingresados no son validos");
                alerta.showAndWait();
                break;
            case "Mesa no Disponible":
                alerta = new Alert(AlertType.WARNING);
                alerta.setTitle(tipoAlerta);
                alerta.setContentText("La mesa seleccionada no se puede eliminar porque está siendo manejada por un mesero");
                alerta.showAndWait();
                break;
            case "Usuario Invalido":
                alerta = new Alert(AlertType.ERROR);
                alerta.setTitle(tipoAlerta);
                alerta.setContentText("Usuario o Contraseña invalidos");
                alerta.showAndWait();
                break;
        }
    }
}
