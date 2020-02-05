/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import AditionalViews.ModificacionMesa;
import AditionalViews.NombreCliente;
import Datos.Constantes;
import Datos.Mesa;
import Usuario.Administrador;
import Usuario.Mesero;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import Restaurante.Restaurante;

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
    private ScrollPane detallePedido;
    
    /**
     * Constructor de la clase
     */
    
    public MesasView(){
       root = new Pane();
       restaurante = new BorderPane();
       cocina = new HBox();
       cocina.setMinWidth(Constantes.anchoVentana-(Constantes.anchoVentana*0.90));
       mesas = new ArrayList<>();
        detallePedido = new ScrollPane();
        
    }
    /**
     * Metodo que construye el root con sus nodos
     * @return Root con los elementos graficos
     */
    
    public Parent build(){
        colocarMesas();
        colocarCocina();
        restaurante.setCenter(root);
        restaurante.setRight(cocina);
        return restaurante;
        
    }
    
    /**
     * Metodo que permite ver la información de la mesa sobre la que se encuentre el mouse
     * @param pane Nodo al cual se le instalara la opcion de ver información
     * @param mesa Mesa de la cual se mostrara la información
     */
    public void mostrarInformaciónMesa(StackPane pane, Mesa mesa){
       Tooltip informacion = new Tooltip(mesa.getDatos());
       Tooltip.install(pane,informacion);
    }
    
    /**
     * Metodo que añade eventos para mover mesas
     * @param pane Nodo al que se le añadira el evento
     * @param mesa Mesa a la que se le actualizara la posicion
     */
    public void crearMovimientoMesas(StackPane pane, Mesa mesa){
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
            mesa.setCoordenadaX(newTranslateX);
            mesa.setCoordenadaY(newTranslateY);
                
            
        }
    }; 
            pane.setOnMouseDragged(circleOnMouseDraggedEventHandler);
            pane.setOnMousePressed(circleOnMousePressedEventHandler);
        
    
    }
    
    /**
     * Metodo que coloca las mesas en la escena
     */
    public void colocarMesas(){
       
        root.getChildren().clear();
        for(Mesa m: Restaurante.datos.getMesas()){
                mesaNumero = new StackPane();
                
                Label numero = new Label(m.getNumeroMesa());
                Circle c = new Circle(m.getRadio());
                darColorMesa(c,m);
                mesaNumero.setTranslateX(m.getCoordenadaX());
                mesaNumero.setTranslateY(m.getCoordenadaY());
                mesas.add(c);
                mesaNumero.getChildren().addAll(c,numero);
                root.getChildren().add(mesaNumero);
                if(Restaurante.usuario instanceof Administrador){
                    if(AdminView.isDiseño()){
                        crearMovimientoMesas(mesaNumero,m);
                        crearModificacion(mesaNumero, m);
                        crearMesa();
                       
                    }else{
                         mostrarInformaciónMesa(mesaNumero,m);
                    }

                }else{
                    if(m.getCliente()==null || "Ninguno".equals(m.getCliente())){
                        mostrarVentanaCliente(mesaNumero,m);
                    }else{
                        
                        crearEscenaPedido(mesaNumero,m);
                    }
                    

                }
            }
        
        
       
    }
    
    /**
     * Metodo que crea el espacio de cocina
     */
    public void colocarCocina(){
        Rectangle barrera = new Rectangle(0,0,15,Constantes.altoVentana);
        Label cocinaLabel = new Label("Cocina");
        cocinaLabel.setStyle("-fx-rotate: -90;"
                            + "-fx-font-family: 'Verdana';"
                            +"-fx-font-size: 20;");
        cocinaLabel.setTranslateY(Constantes.altoVentana*0.5);
        
        
        
        cocina.getChildren().addAll(barrera,cocinaLabel);
    }
    
    /**
     * Metodo que muestra una ventana para añadir un cliente a la mesa 
     * @param pane Nodo al que se le añadira el evento
     * @param mesa Mesa a la cual se le añadira el cliente
     */
    public void mostrarVentanaCliente(StackPane pane, Mesa mesa){
        pane.setOnMouseClicked(event->{
            
                NombreCliente ventanaCliente = new NombreCliente(mesa);
                ventanaCliente.showStage();
                if(!(mesa.getCliente()==null) && !mesa.getCliente().equals("Ninguno")){
                    mesa.setMesero((Mesero)Restaurante.usuario);
                }
                colocarMesas();
            
            
        });
        
    }
    
    
    /**
     * Metodo que añade evento de mostrar la ventana de pedido a una mesa
     * @param pane Nodo al cual se añadira el evento
     * @param mesa Mesa de la que se tomara informacion
     */
    public void crearEscenaPedido(StackPane pane, Mesa mesa){
        pane.setOnMouseClicked(event->{
            crearPedido(mesa);
            
        });
    }
    
    /**
     * Meotodo para crear la escena de tomar el pedido
     * @param mesa Mesa para tomar informacion
     */
    public void crearPedido( Mesa mesa){
    if(mesa.getMesero()==null|| mesa.getMesero().equals((Mesero)Restaurante.usuario)){
             mesa.setMesero((Mesero)Restaurante.usuario); 
             VBox caja = new VBox();
             HBox vistaMesa = new HBox();
             
            VBox descripcion = new VBox();
            descripcion.getChildren().add(detallePedido);
            VBox.setVgrow(detallePedido, Priority.ALWAYS);
            detallePedido.setVmax(Constantes.altoVentana*0.5);
            descripcion.setMinWidth(Constantes.anchoVentana*0.25);
             PlatillosView vistaPlatos = new PlatillosView(mesa);
             pedido = vistaPlatos.seccionDetalle();
             pedido.setPadding(new Insets(10));
             pedido.setSpacing(10);
             pedido.setMaxHeight(Constantes.altoVentana*0.5);
             Thread hiloMesas = new Thread(new HiloActualizarPedido(mesa) );
             hiloMesas.start();
             pedido.setAlignment(Pos.CENTER_LEFT);
             detallePedido.setContent(pedido);
             VBox botones = new VBox();
             botones.setPadding(new Insets(15));
             botones.setSpacing(25);
             botones.setAlignment(Pos.CENTER);
             vistaPlatos.setBotonesEscena(botones);
             descripcion.getChildren().addAll(botones);
             vistaMesa.getChildren().addAll(descripcion,vistaPlatos.build());
             caja.getChildren().addAll(vistaPlatos.getLabel(),vistaMesa);
             Restaurante.setScene(caja);
            }
    }
    
    
    /**
     * Metodo para cambiar el estado de la variable que determina si un evento se ha disparado o no
     */
    public void setDisparo(){
        eventoDisparado=false;
    }
    
    /**
     * Metodo que actualiza el pedido de una mesa
     * @param mesa Mesa para obtener informacion 
     */
    public void colocarPedido(Mesa mesa){
        pedido.getChildren().clear();
        Map<String,ArrayList<Double>> orden = mesa.getComidasPedido();
        Label total = new Label("Total: "+String.valueOf(mesa.totalFactura())); 
        for(String s: orden.keySet()){
            Label plato = new Label(s);
            Double precioUnitario = Restaurante.datos.getPlato(s).getPrecio();
            HBox descripcion = new HBox();
            descripcion.setSpacing(50);
            Label detalle = new Label(String.valueOf(orden.get(s).get(0))+" Unidad(es) a: "+String.valueOf(precioUnitario)+" C/u");
            Label precio = new Label(String.valueOf(orden.get(s).get(1)));
            precio.setTextAlignment(TextAlignment.RIGHT);
            precio.setAlignment(Pos.CENTER_RIGHT);
            Region espacio = new Region();
            HBox.setHgrow(espacio, Priority.ALWAYS);
            descripcion.getChildren().addAll(plato,espacio,precio);
            pedido.getChildren().addAll(descripcion,detalle);
        }
        pedido.getChildren().add(total);
    }
    
    /**
     * Metodo que da color a las mesas respecto a su estado
     * @param circulo Circulo al que se le dara el color
     * @param mesa Mesa para conocer el estado
     */
    public void darColorMesa(Circle circulo, Mesa mesa){
        if(!(Restaurante.usuario instanceof Administrador)){
            if(mesa.getMesero()!=null){
                if(mesa.getMesero().equals(Restaurante.usuario)){
                    circulo.setFill(Color.GREEN);
                }else{
                    circulo.setFill(Color.RED);
                }
            }else{
                circulo.setFill(Color.YELLOW);
            }
        }else{
            if(mesa.isDisponible()){
               
                circulo.setFill(Color.GREEN);
            }else{
                circulo.setFill(Color.RED);
            }
        }
        
    }
    /**
     * Metodo que añade evento a la escena para crear una mesa
     */
    public void crearMesa(){
        root.setOnMouseClicked(event->{
            if(!dragging && !eventoDisparado){
                eventoDisparado = true;
                 ModificacionMesa escenaModificacion = new ModificacionMesa(event,this);
                escenaModificacion.showStage();
            }
           
            
        });
         
    }
    
    /**
     * Metodo que añade evento a mesa para modificarla
     * @param pane Nodo al que se le añadira el evento
     * @param mesa Mesa para obtener informacion
     */
    public void crearModificacion(StackPane pane, Mesa mesa){
        pane.setOnMouseClicked(event->{
            if(!dragging && !eventoDisparado){
                eventoDisparado = true;
                ModificacionMesa escenaModificacion = new ModificacionMesa(event,this,mesa);
                escenaModificacion.showStage();
            }
            
        });
        
    }
    
    /**
     * Hilo que actualiza el pedido del cliente
     */
    public class HiloActualizarPedido implements Runnable{
        private Mesa m; //Al final se colocarán nombre adecuados
        
        public HiloActualizarPedido(Mesa m){
         this.m = m;
        }
        
        @Override
        public void run() {
            while(!Restaurante.cerrar){
            
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
