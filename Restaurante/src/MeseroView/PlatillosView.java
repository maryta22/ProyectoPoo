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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
        for (int i = 0; i < 20; i++) {
            Label plato = new Label("Plato" + String.valueOf(i + 1));
            //plato.setStyle("-fx-border-color:white; -fx-background-color: black;");
            plato.setMinHeight(50);
            plato.setMinWidth(50);
            platos.getChildren().add(plato);
        }
        root.getChildren().addAll(filtros, platos);
        return root;
    }

    

}
