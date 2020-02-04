/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeseroView;

import AditionalViews.ModificacionMesa;
import AditionalViews.NombreCliente;
import Datos.Constantes;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import proyectopoo2p.ProyectoPOO2p;

/**
 *
 * @author danny
 */
public class MesasView {
    private static ArrayList<Circle> mesas;
    private double orgSceneX, orgSceneY; //Pruebas
    private double orgTranslateX, orgTranslateY; //Pruebas
    private HBox cocina;
    private Pane root;
    private BorderPane restaurante;
    private VBox pedido;
    private StackPane mesaNumero;
    private boolean dragging = false;
    private boolean eventoDisparado = false;
    
    public MesasView(){
       root = new Pane();
       restaurante = new BorderPane();
       cocina = new HBox();
       cocina.setMinWidth(Constantes.anchoVentana-(Constantes.anchoVentana*0.90));
       mesas = new ArrayList<>();
        
        
    }
    
    public Parent build(){
        colocarMesas();
        colocarCocina();
        restaurante.setCenter(root);
        restaurante.setRight(cocina);
        return restaurante;
        
    }
    public void mostrarInformaciónMesa(StackPane p, Mesa m){
       Tooltip informacion = new Tooltip(m.getDatos());
       Tooltip.install(p,informacion);
    }
    public void crearMovimientoMesas(StackPane c, Mesa m){
        EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            dragging = false;
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
            dragging = true;
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            ((StackPane)(t.getSource())).setTranslateX(newTranslateX);
            ((StackPane)(t.getSource())).setTranslateY(newTranslateY);
            m.setCoordenadaX(newTranslateX);
            m.setCoordenadaY(newTranslateY);
                
            
        }
    }; 
            c.setOnMouseDragged(circleOnMouseDraggedEventHandler);
            c.setOnMousePressed(circleOnMousePressedEventHandler);
        
    
    }
    
    public void colocarMesas(){
       
        root.getChildren().clear();
        for(Mesa m: ProyectoPOO2p.datos.getMesas()){
                mesaNumero = new StackPane();
                
                Label numero = new Label(m.getNumeroMesa());
                Circle c = new Circle(m.getRadio());
                darColorMesa(c,m);
                System.out.println(m.isDisponible());
                mesaNumero.setTranslateX(m.getCoordenadaX());
                mesaNumero.setTranslateY(m.getCoordenadaY());
                mesas.add(c);
                mesaNumero.getChildren().addAll(c,numero);
                root.getChildren().add(mesaNumero);
                if(ProyectoPOO2p.usuario instanceof Administrador){
                    if(AdminView.isDiseño()){
                        crearMovimientoMesas(mesaNumero,m);
                        crearModificacion(mesaNumero, m);
                        crearMesa();
                       
                    }else{
                         mostrarInformaciónMesa(mesaNumero,m);
                    }

                }else{
                    if(m.getCliente()==null){
                        mostrarVentanaCliente(mesaNumero,m);
                    }else{
                        
                        crearEscenaPedido(mesaNumero,m);
                    }
                    

                }
            }
        
        
       
    }
    
    public void colocarCocina(){
        Rectangle barrera = new Rectangle(0,0,15,Constantes.altoVentana);
        Label cocinaLabel = new Label("Cocina");
        cocinaLabel.setStyle("-fx-rotate: -90;"
                            + "-fx-font-family: 'Verdana';"
                            +"-fx-font-size: 20;");
        cocinaLabel.setTranslateY(Constantes.altoVentana*0.5);
        
        
        
        cocina.getChildren().addAll(barrera,cocinaLabel);
    }
    
    public void mostrarVentanaCliente(StackPane sp, Mesa m){
        sp.setOnMouseClicked(event->{
            NombreCliente ventanaCliente = new NombreCliente(m);
            ventanaCliente.showStage(); 
            crearPedido(sp,m);
        });
        
    }
    
    public void crearEscenaPedido(StackPane c, Mesa m){
        c.setOnMouseClicked(event->{
            crearPedido(c,m);
        });
    }
    
    public void crearPedido(StackPane sp, Mesa m){
    if(m.getMesero()==null|| m.getMesero().equals((Mesero)ProyectoPOO2p.usuario)){
             m.setMesero((Mesero)ProyectoPOO2p.usuario); 
             HBox vistaMesa = new HBox();
            VBox descripcion = new VBox();
            descripcion.setMinWidth(Constantes.anchoVentana*0.25);
             PlatillosView vistaPlatos = new PlatillosView(m);
             pedido = vistaPlatos.seccionDetalle();
             pedido.setPadding(new Insets(10));
             pedido.setSpacing(10);
             pedido.setStyle("-fx-border-color: green;");
             Thread hiloMesas = new Thread(new HiloActualizarPedido(m) );
             hiloMesas.start();
             pedido.setAlignment(Pos.CENTER_LEFT);
             VBox botones = new VBox();
             botones.setPadding(new Insets(15));
             botones.setSpacing(25);
             botones.setAlignment(Pos.CENTER);
             botones.setStyle("-fx-border-color: red;");
             vistaPlatos.setBotonesEscena(botones);
             descripcion.getChildren().addAll(pedido,botones);
             vistaMesa.getChildren().addAll(descripcion,vistaPlatos.build());
             ProyectoPOO2p.setScene(vistaMesa);
            }
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
    
    public void setDisparo(){//Se puede cambiar
        eventoDisparado=false;
    }
    
    public void colocarPedido(Mesa m){
        pedido.getChildren().clear();
        Map<String,ArrayList<Double>> orden = m.getComidasPedido();
        Label total = new Label("Total: "+String.valueOf(m.totalFactura())); 
        for(String s: orden.keySet()){
            Label plato = new Label(s);
            Double precioUnitario = ProyectoPOO2p.datos.getPlato(s).getPrecio();
             
           
            Label detalle = new Label(String.valueOf(orden.get(s).get(0))+" Unidad(es) a: "+String.valueOf(precioUnitario)+" C/u");
            Label precio = new Label(String.valueOf(orden.get(s).get(1)));
            pedido.getChildren().addAll(plato,detalle,precio);
        }
        pedido.getChildren().add(total);
    }
    
    public void darColorMesa(Circle c, Mesa m){
        if(!(ProyectoPOO2p.usuario instanceof Administrador)){
            if(m.getMesero()!=null){
                if(m.getMesero().equals(ProyectoPOO2p.usuario)){
                    c.setFill(Color.GREEN);
                }else{
                    c.setFill(Color.RED);
                }
            }else{
                c.setFill(Color.YELLOW);
            }
        }else{
            System.out.println(m.isDisponible());
            if(m.isDisponible()){
               
                c.setFill(Color.GREEN);
            }else{
                c.setFill(Color.RED);
            }
        }
        
    }
    
    public void crearMesa(){
        root.setOnMouseClicked(event->{
            if(!dragging && !eventoDisparado){
                eventoDisparado = true;
                 ModificacionMesa escenaModificacion = new ModificacionMesa(event,this);
                escenaModificacion.showStage();
            }
           
            
        });
         
    }
    
    public void crearModificacion(StackPane sp, Mesa m){
        sp.setOnMouseClicked(event->{
            if(!dragging && !eventoDisparado){
                eventoDisparado = true;
                ModificacionMesa escenaModificacion = new ModificacionMesa(event,this,m);
                escenaModificacion.showStage();
            }
            
        });
        
    }
    
    
    public class HiloActualizarPedido implements Runnable{
        private Mesa m; //Al final se colocarán nombre adecuados
        
        public HiloActualizarPedido(Mesa m){
         this.m = m;
        }
        
        @Override
        public void run() {
            while(!ProyectoPOO2p.cerrar){
            
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
