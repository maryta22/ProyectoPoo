/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeseroView;

import static Datos.Constantes.altoVentana;
import Datos.Mesa;
import Datos.Plato;
import LoginView.LoginView;
import Usuario.Administrador;
import Usuario.Mesero;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import proyectopoo2p.ProyectoPOO2p;

/**
 *
 * @author danny
 */
public class MesasView {
    private static ArrayList<Circle> mesas;
    private double orgSceneX, orgSceneY; //Pruebas
    private double orgTranslateX, orgTranslateY; //Pruebas
    private Pane root;
    private VBox pedido;
    private boolean enVentana=true;
    
    public MesasView(){
       root = new Pane();
       mesas = new ArrayList<>();
        
    }
    
    public Parent build(){
        for(Mesa m: ProyectoPOO2p.datos.getMesas()){
            Circle c = new Circle(m.getRadio());
            c.setCenterX(m.getCoordenadaX());
            c.setCenterY(m.getCoordenadaY());
            mesas.add(c);
            root.getChildren().add(c);
            if(ProyectoPOO2p.usuario instanceof Administrador){
                if(AdminView.isDiseño()){
                    crearMovimientoMesas(c,m);
                }else{
                     root.setCursor(Cursor.NONE);
                }
               
            }else{
                crearEscenaPedido(c,m);
               
            }
        }
         
        
        return root;
    }
    
    public void crearMovimientoMesas(Circle c, Mesa m){
 
       
            System.out.println("Mesa movimiento");
                     EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
            orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
           
        }
    };
        EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            ((Circle)(t.getSource())).setTranslateX(newTranslateX);
            ((Circle)(t.getSource())).setTranslateY(newTranslateY);
            m.setCoordenadaX(t.getSceneX());
            m.setCoordenadaY(t.getSceneY());
                
            
        }
    }; 
            c.setOnMouseDragged(circleOnMouseDraggedEventHandler);
            c.setOnMousePressed(circleOnMousePressedEventHandler);
        
    
    }
    
    public void crearEscenaPedido(Circle c, Mesa m){
        c.setOnMouseClicked(event->{
            HBox vistaMesa = new HBox();
            VBox descripcion = new VBox();
             
             pedido = new VBox();
             pedido.setPadding(new Insets(15));
             pedido.setSpacing(25);
             pedido.setStyle("-fx-border-color: green;");
             Thread hiloMesas = new Thread(new HiloActualizarPedido(m) );
             hiloMesas.start();
             pedido.setAlignment(Pos.CENTER_LEFT);
             VBox botones = new VBox();
             botones.setPadding(new Insets(15));
             botones.setSpacing(25);
             botones.setAlignment(Pos.CENTER);
             botones.setStyle("-fx-border-color: red;");
             Button regresar = new Button("Regresar");
             //Temporal 
             regresar.setOnMouseClicked(v->{
                 enVentana = false;
                 ProyectoPOO2p.scene.setRoot(new MeseroView().build());
                
             });
             botones.getChildren().addAll(new Button("Finalizar Orden"), regresar);
             descripcion.getChildren().addAll(pedido,botones);
             
             vistaMesa.getChildren().addAll(descripcion,new PlatillosView(m).build());
             ProyectoPOO2p.setScene(vistaMesa);
        });
    }
    
    public static void main(String[]args){
        ArrayList<Circle> mesas = new ArrayList<>();
        for(Circle m: mesas){
            mesas.add(m);
            
        }
        try(ObjectOutputStream arch= new ObjectOutputStream(new FileOutputStream("src/Archivos/mesas.dat"))){
            arch.writeObject(mesas);
        }catch(FileNotFoundException ie){
            System.out.println(ie.getMessage());
            
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
    }
    
    public void colocarPedido(Mesa m){
        pedido.getChildren().clear();
        Map<String,Double> orden = m.getComidasPedido();
        for(String s: orden.keySet()){
            Label plato = new Label(s);
            Double precioUnitario = ProyectoPOO2p.datos.getPlato(s).getPrecio();
            Double unidades = orden.get(s)/precioUnitario;
            Label detalle = new Label(String.valueOf(unidades)+" Unidad(es) a: "+String.valueOf(precioUnitario)+" C/u");
            Label precio = new Label(String.valueOf(orden.get(s)));
            pedido.getChildren().addAll(plato,detalle,precio);
        }
        
    }
    
    public class HiloActualizarPedido implements Runnable{
        private Mesa m; //Al final se colocarán nombre adecuados
        
        public HiloActualizarPedido(Mesa m){
         this.m = m;
        }
        
        @Override
        public void run() {
            
            System.out.println("Hilo iniciado");
            while(enVentana){
            
                try {
                    Platform.runLater(()->{
                    colocarPedido(m);
                    });
                    
                    
                    Thread.sleep(2500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MesasView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
             
            }
        }

}
}
