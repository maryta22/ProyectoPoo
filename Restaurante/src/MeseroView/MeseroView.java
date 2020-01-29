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
        
     
            //Pruebas
        pruebas = new VBox();
        vistaMesa = new HBox();
        Label prueba = new Label("Menu Mesero");
        Button logOut = new Button("Cerrar Sesión");
        pruebas.getChildren().addAll(prueba,logOut);
        logOut.setOnMouseClicked(event ->{
            ProyectoPOO2p.setScene(new LoginView().crearLogin());
        });
        pruebas.setAlignment(Pos.CENTER);
//        cargarMesas();
//        for(Circle c: mesas){
//            root.getChildren().add(c);
//        }
        MesasView mesasView = new MesasView();
        
        root.getChildren().addAll(pruebas,mesasView.build());
        
        
        
        //Pruebas
        
        
        return root;
    }
    

      
        public void crearEscenaPedido(){
             descripcion = new VBox();
             
             pedido = new VBox();
             pedido.setPadding(new Insets(15));
             pedido.setSpacing(25);
             pedido.setStyle("-fx-border-color: green;");
             pedido.getChildren().addAll(new Label("Plato 1"),new Label("Plato 2"),new Label("Plato 3"),new Label("Total A pagar"));
             pedido.setAlignment(Pos.CENTER_LEFT);
             botones = new VBox();
             botones.setPadding(new Insets(15));
             botones.setSpacing(25);
             botones.setAlignment(Pos.CENTER);
             botones.setStyle("-fx-border-color: red;");
             botones.getChildren().addAll(new Button("Finalizar Orden"), new Button("Regresar"));
             descripcion.getChildren().addAll(pedido,botones);
             
            // vistaMesa.getChildren().addAll(descripcion,new PlatillosView().build());
        }
    public void crearPlatillos(){
        platillos = new VBox();
        filtros = new HBox();
        platos = new FlowPane();
        filtros.setSpacing(50);
        platos.setHgap(15);
        platos.setVgap(15);
        platillos.setStyle("-fx-border-color: yellow;");
        for(int i=0;i<4;i++){
            Label filtro = new Label("Filtro"+String.valueOf(i+1));
            //filtro.setStyle("-fx-border-color:white; -fx-background-color: black;");
            filtros.getChildren().add(filtro);
        }
        for(int i=0;i<20;i++){
            Label plato = new Label("Plato"+String.valueOf(i+1));
            //plato.setStyle("-fx-border-color:white; -fx-background-color: black;");
            plato.setMinHeight(50);
            plato.setMinWidth(50); 
            platos.getChildren().add(plato);
        }
        
        
        platillos.getChildren().addAll(filtros,platos);
    }
    
}
