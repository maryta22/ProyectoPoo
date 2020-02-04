/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeseroView;

import Datos.Constantes;
import Datos.Mesa;
import LoginView.LoginView;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import proyectopoo2p.ProyectoPOO2p;

/**
 *
 * @author danny
 */
public class MeseroView {
    private static VBox root;
    private HBox infoMesero;

    
    public MeseroView() {
        root = new VBox();
    }
    
    public Parent build(){
        infoMesero = new HBox();
        Label mesero = new Label(ProyectoPOO2p.usuario.getNombreUsuario());
        mesero.setMinWidth(Constantes.anchoVentana*0.83);
        mesero.setAlignment(Pos.CENTER);
        mesero.getStyleClass().add("label_pestana");
        Button logOut = new Button("Cerrar Sesión");
        logOut.getStyleClass().add("login_button");
        
        logOut.setAlignment(Pos.CENTER_LEFT);
        infoMesero.getChildren().addAll(mesero,logOut);
        logOut.setOnMouseClicked(event ->{
            ProyectoPOO2p.setScene(new LoginView().crearLogin());
        });
        infoMesero.setAlignment(Pos.CENTER);

        MesasView mesasView = new MesasView();
        
        root.getChildren().addAll(infoMesero,mesasView.build());
        return root;
    }
    

}
