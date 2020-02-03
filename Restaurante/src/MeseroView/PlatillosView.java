/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeseroView;

import Datos.Mesa;
import Datos.Pedido;
import Datos.Plato;
import Usuario.Mesero;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
    private Mesa mesaActual;
    private Double aPagar = new Double(0);
    private VBox detalle;
    private String[] tipos = {"Sopa", "Segundo", "Postre", "Bebida","Todos"};

    public PlatillosView() {
        root = new VBox(50);
        detalle = new VBox();
        root.setMinHeight(700);
        root.setMinWidth(700);
        
    }
    public VBox seccionDetalle(){
        return detalle;
    }
    public PlatillosView(Mesa mesa){
        this();
        mesaActual = mesa;
    }

    public Parent build() {

        filtros = new HBox();
        platos = new FlowPane();
        filtros.setSpacing(100);
        platos.setHgap(15);
        platos.setVgap(15);
        root.setStyle("-fx-border-color: yellow;");
        for (int i = 0; i < tipos.length; i++) {
            Label filtro = new Label(tipos[i]);
            filtro.setStyle("-fx-border-color:white; -fx-background-color: black;");
            filtro.setMinHeight(50);
            filtro.setMinWidth(filtros.getWidth()/tipos.length);
            makeClickable(filtro);
            //filtro.setStyle("-fx-border-color:white; -fx-background-color: black;");
            filtros.getChildren().add(filtro);
        }
//        for(String s:ProyectoPOO2p.datos.getPlatos().keySet()){
//            for(Plato p:ProyectoPOO2p.datos.getPlatos().get(s)){
//                VBox detalles = new VBox(10);
//                Label precio = new Label("Precio: "+String.valueOf(p.getPrecio()));
//                Label nombre = new Label(p.getNombre());
//                Image plato = new Image(p.getRuta());
//                ImageView img = new ImageView(plato);
//                img.setFitHeight(80);
//                img.setFitWidth(80);  
//                if(ProyectoPOO2p.usuario instanceof Mesero){
//                    crearAccionPedido(img,p.getNombre(),p.getPrecio());
//                    
//                }
//                
//                detalles.getChildren().addAll(precio,img,nombre);
//                platos.getChildren().add(detalles);
//            }
//        }
        colocarTodos();
        root.getChildren().addAll(filtros, platos);
        return root;
    }

    public void crearAccionPedido(ImageView img,String nombre,Double precio){
        img.setOnMouseClicked(event->{
           Map<String,Double> pedidoActual = mesaActual.getComidasPedido();
            if(pedidoActual.containsKey(nombre)){
                pedidoActual.put(nombre, pedidoActual.get(nombre)+precio);
            }else{
                pedidoActual.put(nombre, precio);
            }
            aPagar+=precio;
            System.out.println(mesaActual.getComidasPedido());
        });
        
    }
    
    public void setBotonesEscena(VBox boxBotones){
        Button regresar = new Button("Regresar");
        regresar.setOnMouseClicked(v->{
                 ProyectoPOO2p.scene.setRoot(new MeseroView().build());
                
             });
        Button finalizar = new Button("Finalizar Orden");
        finalizar.setOnMouseClicked(event->{
            Pedido pMesa = new Pedido(LocalDate.now(),mesaActual,(Mesero)ProyectoPOO2p.usuario,"000-123",mesaActual.getCliente(),aPagar);
            ProyectoPOO2p.datos.getPedidos().add(pMesa);
            mesaActual.setVentasTotal(aPagar);
            mesaActual.setMesero(null);
            mesaActual.getComidasPedido().clear();
            detalle.getChildren().clear();
            ProyectoPOO2p.scene.setRoot(new MeseroView().build());
        });
        
        boxBotones.getChildren().addAll(regresar,finalizar);
    }
    
    public void makeClickable(Label filtro){
        String texto = filtro.getText();
        filtro.setOnMouseClicked(event->{
            platos.getChildren().clear();
            if(!texto.equals("Todos")){
                colocarPlatosPorFiltro(texto);
            }else{
                colocarTodos();
            }
            
        });
    }
    
    public void colocarPlatosPorFiltro(String tipoPlato){
        platos.getChildren().clear();
       for(Plato p: ProyectoPOO2p.datos.getPlatos().get(tipoPlato)){
           colocarPlato(p);
       }
    }
    public void colocarTodos(){
        platos.getChildren().clear();
        for(String s:ProyectoPOO2p.datos.getPlatos().keySet()){
                for(Plato p:ProyectoPOO2p.datos.getPlatos().get(s)){
                    colocarPlato(p);
                }
            }
        }
    
    public void colocarPlato(Plato p){
                    VBox detalles = new VBox(10);
                    Label precio = new Label("Precio: "+String.valueOf(p.getPrecio()));
                    Label nombre = new Label(p.getNombre());
                    Image plato = new Image(p.getRuta());
                    ImageView img = new ImageView(plato);
                    img.setFitHeight(80);
                    img.setFitWidth(80);  
                    if(ProyectoPOO2p.usuario instanceof Mesero){
                        crearAccionPedido(img,p.getNombre(),p.getPrecio());

                    }

                    detalles.getChildren().addAll(precio,img,nombre);
                    platos.getChildren().add(detalles);
    }
}
