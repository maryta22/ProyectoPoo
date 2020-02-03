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
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private StackPane mesaNumero;
    
    public MesasView(){
       root = new Pane();
       mesas = new ArrayList<>();
        
    }
    
    public Parent build(){
        for(Mesa m: ProyectoPOO2p.datos.getMesas()){
            mesaNumero = new StackPane();
            Label numero = new Label(m.getNumeroMesa());
            Circle c = new Circle(m.getRadio());
            darColorMesa(c,m);
            c.setCenterX(m.getCoordenadaX());
            c.setCenterY(m.getCoordenadaY());
            mesaNumero.setLayoutX(m.getCoordenadaX());
            mesaNumero.setLayoutY(m.getCoordenadaY());
            mesas.add(c);
            mesaNumero.getChildren().addAll(c,numero);
            root.getChildren().add(mesaNumero);
            if(ProyectoPOO2p.usuario instanceof Administrador){
                if(AdminView.isDiseño()){
                    crearMovimientoMesas(mesaNumero,m);
                }else{
                     mostrarInformaciónMesa(mesaNumero,m);
                     //root.setCursor(Cursor.NONE);
                }
               
            }else{
                crearEscenaPedido(mesaNumero,m);
               
            }
        }
         
        
        return root;
    }
        public void mostrarInformaciónMesa(StackPane p, Mesa m){
           Tooltip informacion = new Tooltip(m.toString());
           
           Tooltip.install(p,informacion);
        }
    public void crearMovimientoMesas(StackPane c, Mesa m){
 
       
            System.out.println("Mesa movimiento");
                     EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((StackPane)(t.getSource())).getTranslateX();
            orgTranslateY = ((StackPane)(t.getSource())).getTranslateY();
           
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
             
            ((StackPane)(t.getSource())).setTranslateX(newTranslateX);
            ((StackPane)(t.getSource())).setTranslateY(newTranslateY);
            m.setCoordenadaX(t.getSceneX());
            m.setCoordenadaY(t.getSceneY());
                
            
        }
    }; 
            c.setOnMouseDragged(circleOnMouseDraggedEventHandler);
            c.setOnMousePressed(circleOnMousePressedEventHandler);
        
    
    }
    
    public void crearEscenaPedido(StackPane c, Mesa m){
        c.setOnMouseClicked(event->{
            if(m.getMesero()==null|| m.getMesero().equals((Mesero)ProyectoPOO2p.usuario)){
               m.setMesero((Mesero)ProyectoPOO2p.usuario); 
                HBox vistaMesa = new HBox();
            VBox descripcion = new VBox();
             PlatillosView vistaPlatos = new PlatillosView(m);
             pedido = vistaPlatos.seccionDetalle();
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
//             Button regresar = new Button("Regresar");
//             Button finalizar = new Button("Finalizar Orden");
//             //Temporal 
//             regresar.setOnMouseClicked(v->{
//                 enVentana = false;
//                 ProyectoPOO2p.scene.setRoot(new MeseroView().build());
//                
//             });
            
             vistaPlatos.setBotonesEscena(botones);
             descripcion.getChildren().addAll(pedido,botones);
             
             vistaMesa.getChildren().addAll(descripcion,vistaPlatos.build());
             ProyectoPOO2p.setScene(vistaMesa);
            }
            
           
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
    
    public void darColorMesa(Circle c, Mesa m){
        if(m.getMesero()!=null){
            if(m.getMesero().equals(ProyectoPOO2p.usuario)){
                c.setFill(Color.GREEN);
            }else{
                c.setFill(Color.RED);
            }
        }else{
            c.setFill(Color.YELLOW);
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
                    
                    
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MesasView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
             
            }
        }

}
}
