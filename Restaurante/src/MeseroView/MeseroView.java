/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeseroView;

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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import proyectopoo2p.ProyectoPOO2p;

/**
 *
 * @author danny
 */
public class MeseroView {
    private static VBox root;
    private VBox pruebas;
    ArrayList<Circle> mesas;
    private HBox vistaMesa,filtros;
    private VBox descripcion, platillos,botones,pedido;
    private FlowPane platos;

    
    public MeseroView() {
        root = new VBox();
    }
    
    public Parent build(){
        pruebas = new VBox();
        vistaMesa = new HBox();
        Label prueba = new Label("Menu Mesero");
        Button logOut = new Button("Cerrar Sesión");
        pruebas.getChildren().addAll(prueba,logOut);
        logOut.setOnMouseClicked(event ->{
            ProyectoPOO2p.setScene(new LoginView().crearLogin());
        });
        pruebas.setAlignment(Pos.CENTER);

        MesasView mesasView = new MesasView();
        
        root.getChildren().addAll(pruebas,mesasView.build());
        return root;
    }
    

}
