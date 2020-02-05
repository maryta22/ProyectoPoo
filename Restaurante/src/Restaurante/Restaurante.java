/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante;

import Datos.Constantes;
import Datos.Interfaz;
import Views.LoginView;
import Usuario.Usuario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author danny
 */
public class Restaurante extends Application {

    public static Scene scene;
    public static Interfaz datos;
    public static Usuario usuario;
    public static boolean cerrar = false;

    @Override
    /**
     * Metodo que inicia la app
     */
    public void start(Stage primaryStage) {

        scene = new Scene(new LoginView().crearLogin(),Constantes.anchoVentana,Constantes.altoVentana,Color.AQUA);
        scene.getStylesheets().add(Constantes.rutaEstilo);
        primaryStage.setTitle("Proyecto Poo");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    @Override
    /**
     * Metodo para detener hilos y actualizar los archivos
     */
    public void stop(){
        cerrar = true;
        datos.actualizarArchivos();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        datos = new Interfaz();
        launch(args);
    }

    /**
     * Metodo para cambiar el root de la escena
     * @param newRoot Root a modificar
     */
    public static void setScene(Parent newRoot) {
        scene.setRoot(newRoot);
    }

    
    public Interfaz getInterfaz() {
        return datos;
    }

}
