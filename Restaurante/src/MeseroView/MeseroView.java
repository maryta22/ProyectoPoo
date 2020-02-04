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
import proyectopoo2p.ProyectoPOO2p;

/**
 *
 * @author danny
 */
public class MeseroView {
    private static VBox root;
    private StackPane infoMesero;

    
    public MeseroView() {
        root = new VBox();
    }
    
    public Parent build(){
        infoMesero = new StackPane();
        Label mesero = new Label(ProyectoPOO2p.usuario.toString());
        mesero.setMinWidth(Constantes.anchoVentana);
        
        
        Label prueba = new Label("Menu Mesero");
        Button logOut = new Button("Cerrar Sesión");
        logOut.setAlignment(Pos.CENTER_LEFT);
        infoMesero.getChildren().addAll(prueba,logOut);
        logOut.setOnMouseClicked(event ->{
            ProyectoPOO2p.setScene(new LoginView().crearLogin());
        });
        infoMesero.setAlignment(Pos.CENTER);

        MesasView mesasView = new MesasView();
        
        root.getChildren().addAll(infoMesero,mesasView.build());
        return root;
    }
    

}
