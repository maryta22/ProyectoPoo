/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginView;

import Alertas.Alerta;
import MeseroView.AdminView;
import MeseroView.MeseroView;
import Usuario.Administrador;
import Usuario.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import proyectopoo2p.ProyectoPOO2p;

/**
 *
 * @author danny
 */
public class LoginView {
    private GridPane root;
    private  VBox mainRoot;
    
    public Parent crearLogin(){
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10,10,10,10));
        root.setVgap(10);
        root.setHgap(10);
        
        Label labelNombre = new Label("Usuario");
        labelNombre.getStyleClass().add("label_login");
        GridPane.setConstraints(labelNombre, 0, 0);
        
        TextField inputNombre = new TextField();
        inputNombre.getStyleClass().add("input_login");
        GridPane.setConstraints(inputNombre, 1, 0);
        
        Label labelContraseña = new Label("Contraseña");
        labelContraseña.getStyleClass().add("label_login");
        GridPane.setConstraints(labelContraseña, 0, 1);
        
        TextField inputContraseña = new TextField();
        inputContraseña.getStyleClass().add("input_login");
        GridPane.setConstraints(inputContraseña, 1, 1);
        
        Button login = new Button("Ingresar");
        login.getStyleClass().add("login_button");
        login.setOnMouseClicked(event ->{
           
            if(ProyectoPOO2p.datos.validarUsuario(inputNombre.getText(),inputContraseña.getText())){
                if(ProyectoPOO2p.datos.getUsuario(inputNombre.getText()) instanceof Administrador){
                    ProyectoPOO2p.usuario = ProyectoPOO2p.datos.getUsuario(inputNombre.getText());
                    ProyectoPOO2p.setScene(new AdminView().build());  
                    
                }else{
                    ProyectoPOO2p.usuario = ProyectoPOO2p.datos.getUsuario(inputNombre.getText());
                    ProyectoPOO2p.setScene(new MeseroView().build());
                }
                
            }else{
                new Alerta("Usuario Invalido").mostrarAlerta();
                inputNombre.clear();
                inputContraseña.clear();
            }
            
        
        });
        root.getChildren().addAll(labelNombre,inputNombre, labelContraseña, inputContraseña);
        mainRoot = new VBox();
        mainRoot.getChildren().addAll(root, login);
        mainRoot.setSpacing(20);
        mainRoot.setAlignment(Pos.CENTER);
        
        return mainRoot;
    }
}
