/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AditionalViews;

import Datos.Mesa;
import MeseroView.MesasView;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyectopoo2p.ProyectoPOO2p;
/**
 *
 * @author danny
 */
public class ModificacionMesa {
    private Stage modificacion;
    private GridPane root;
    private VBox mainRoot;
    private Scene scene;
    private Mesa mesaAModificar;
    private MouseEvent eventoCrear;
    private Label numeroMesa,radio;
    private TextField inputNumero,inputRadio;

    public ModificacionMesa(MouseEvent evento) {
        modificacion = new Stage();
        root = new GridPane();
        mainRoot = new VBox();
        eventoCrear = evento;
        scene = new Scene(mainRoot,400,200);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10,10,10,10));
        root.setVgap(10);
        root.setHgap(10);
    }
    
    public ModificacionMesa(MouseEvent evento,Mesa m){
        this(evento);
        mesaAModificar = m;
    }
    
    public void showStage(){
        crearEscena();
        modificacion.setScene(scene);
        modificacion.show();
    }
    
    private void crearEscena(){
        numeroMesa = new Label("Ingrese el numero de la mesa");
        radio = new Label("Ingrese el radio de la mesa");
        inputNumero = new TextField();
        inputRadio = new TextField();
        
        GridPane.setConstraints(numeroMesa, 0, 0);
        GridPane.setConstraints(inputNumero, 1, 0);
        GridPane.setConstraints(radio, 0, 1);
        GridPane.setConstraints(inputRadio, 1, 1);
        
        Button crear = new Button("Crear");
        
        crear.setOnMouseClicked(event->{
            double radio = Double.parseDouble(inputRadio.getText());
            Mesa nuevaMesa = new Mesa(eventoCrear.getSceneX(),eventoCrear.getSceneY(),radio,inputNumero.getText());

            if(datosValidos(nuevaMesa)){
               ProyectoPOO2p.datos.getMesas().add(nuevaMesa);
               MesasView m=new MesasView();
               m.colocarMesas();
                modificacion.close(); 
            }else{
                Alert alerta = new Alert(AlertType.ERROR);
                alerta.setTitle("Datos Invalidos");
                alerta.setContentText("Ya existe una mesa con el mismo numero");
                alerta.showAndWait();
                inputNumero.clear();
                inputRadio.clear();
            }
            
                    
        });
        
        root.getChildren().addAll(numeroMesa,inputNumero,radio,inputRadio);
        mainRoot.getChildren().addAll(root,crear);
        
        
        
    }
    
    public boolean datosValidos(Mesa mesa){
        if(!ProyectoPOO2p.datos.getMesas().contains(mesa)){
            return true;
        }
        return false;
    }
    
    
}
