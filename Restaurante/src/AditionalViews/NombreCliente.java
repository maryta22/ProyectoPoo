/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AditionalViews;

import Datos.Mesa;
import javafx.scene.Scene;
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
    
    public NombreCliente(Mesa m){
        mesa = m;
        ventanaCliente = new Stage();
        datos = new HBox();
        scene = new Scene(datos,500,400);
         
    }
    
    public void showStage(){
        nombre = new Label("Ingrese el nombre del cliente");
        inputNombre = new TextField();
        aceptar = new Button("Aceptar");
        aceptar.setOnMouseClicked(event->{
            mesa.setCliente(inputNombre.getText());//Falta hacer validaciones
        });
        datos.getChildren().addAll(nombre,inputNombre,aceptar);
        ventanaCliente.setScene(scene);
        ventanaCliente.showAndWait();
       
    }
}
