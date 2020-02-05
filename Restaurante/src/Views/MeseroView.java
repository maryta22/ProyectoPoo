/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Datos.Constantes;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Restaurante.Restaurante;

/**
 *
 * @author danny
 */
public class MeseroView {
    private static VBox root;
    private HBox infoMesero;

    /**
     * Constructor de la clase
     */
    public MeseroView() {
        root = new VBox();
    }
    /**
     * Metodo que crea la raiz de la escena
     * @return root con los elementos graficos de la escena
     */
    public Parent build(){
        infoMesero = new HBox();
        Label mesero = new Label(Restaurante.usuario.getNombreUsuario());
        mesero.setMinWidth(Constantes.anchoVentana*0.83);
        mesero.setAlignment(Pos.CENTER);
        mesero.getStyleClass().add("label_pestana");
        Button logOut = new Button("Cerrar Sesión");
        logOut.getStyleClass().add("login_button");
        
        logOut.setAlignment(Pos.CENTER_LEFT);
        infoMesero.getChildren().addAll(mesero,logOut);
        logOut.setOnMouseClicked(event ->{
            Restaurante.setScene(new LoginView().crearLogin());
        });
        infoMesero.setAlignment(Pos.CENTER);

        MesasView mesasView = new MesasView();
        
        root.getChildren().addAll(infoMesero,mesasView.build());
        return root;
    }
    

}
