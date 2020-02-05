/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AditionalViews;

import Datos.Mesa;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author danny
 */
public class NombreCliente {
    private Stage ventanaCliente;
    private Scene scene;
    private HBox datos;
    private Label nombre;
    private TextField inputNombre;
    private Button aceptar;
    private Mesa mesa;
    
    /**
     * Constructor de la clase
     * @param mesa Mesa para obtener datos
     */
    
    public NombreCliente(Mesa mesa){
        this.mesa = mesa;
        ventanaCliente = new Stage();
        datos = new HBox();
        datos.setSpacing(10);
        scene = new Scene(datos,400,50);
         
    }
    
    /**
     * Metodo que muestra la nueva ventana
     */
    
    public void showStage(){
        nombre = new Label("Ingrese el nombre del cliente");
        inputNombre = new TextField();
        aceptar = new Button("Aceptar");
        aceptar.setOnMouseClicked(event->{
            
            
            String cliente = inputNombre.getText();
            if(cliente.equals("")){
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Error en los datos");
                alerta.setContentText("El nombre no puede quedar vacio");
                alerta.showAndWait();
                ventanaCliente.close();
            }else{
                mesa.setCliente(inputNombre.getText());
            }
            
            
            ventanaCliente.close();
        });
        datos.getChildren().addAll(nombre,inputNombre,aceptar);
        ventanaCliente.setScene(scene);
        ventanaCliente.showAndWait();
       
    }
}
