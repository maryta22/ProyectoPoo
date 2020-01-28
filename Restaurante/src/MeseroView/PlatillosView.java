/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeseroView;

import Datos.Plato;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import proyectopoo2p.ProyectoPOO2p;

/**
 *
 * @author danny
 */
public class PlatillosView {
    private VBox root;
    private HBox filtros;
    private FlowPane platos;
    DataOutputStream sopas;
    private String[] tipos = {"Sopa", "Segundo", "Postre", "Bebida"};

    public PlatillosView() {
        root = new VBox();
        root.setMinHeight(700);
        root.setMinWidth(700);
        
    }

    public Parent build() {

        filtros = new HBox();
        platos = new FlowPane();
        filtros.setSpacing(50);
        platos.setHgap(15);
        platos.setVgap(15);
        root.setStyle("-fx-border-color: yellow;");
        for (int i = 0; i < tipos.length; i++) {
            Label filtro = new Label(tipos[i]);
            //filtro.setStyle("-fx-border-color:white; -fx-background-color: black;");
            filtros.getChildren().add(filtro);
        }
//        for (int i = 0; i < 20; i++) {
//            Label plato = new Label("Plato" + String.valueOf(i + 1));
//            //plato.setStyle("-fx-border-color:white; -fx-background-color: black;");
//            plato.setMinHeight(50);
//            plato.setMinWidth(50);
//            platos.getChildren().add(plato);
//        }
        for(String s:ProyectoPOO2p.datos.getPlatos().keySet()){
            for(Plato p:ProyectoPOO2p.datos.getPlatos().get(s)){
                System.out.println(p.getRuta());
                VBox detalles = new VBox();
                Label precio = new Label("Precio: "+String.valueOf(p.getPrecio()));
                Label nombre = new Label(p.getNombre());
                Image plato = new Image(p.getRuta());
                ImageView img = new ImageView(plato);
                img.setFitHeight(80);
                img.setFitWidth(80);
                detalles.getChildren().addAll(precio,img,nombre);
                platos.getChildren().add(detalles);
            }
        }
        root.getChildren().addAll(filtros, platos);
        return root;
    }

    

}
