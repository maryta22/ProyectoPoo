/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeseroView;

import Datos.Constantes;
import Datos.Mesa;
import Datos.Pedido;
import Datos.Plato;
import Usuario.Administrador;
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
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private String[] tipos = {"Sopa", "Segundo", "Postre", "Bebida", "Todos"};
    private Stage ventanaParaModificar;
    private Stage ventanaParaIngresarDatos;
    ComboBox combo;

    public PlatillosView() {
        root = new VBox(50);
        detalle = new VBox();
        root.setMinHeight(700);
        root.setMinWidth(700);

    }

    public VBox seccionDetalle() {
        return detalle;
    }

    public PlatillosView(Mesa mesa) {
        this();
        mesaActual = mesa;
        mesaActual.setDisponible(false);
    }

    public Parent build() {
        Button boton = new Button("Crear nuevo plato");
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
            filtro.setMinWidth(filtros.getWidth() / tipos.length);
            makeClickable(filtro);
            //filtro.setStyle("-fx-border-color:white; -fx-background-color: black;");
            filtros.getChildren().add(filtro);
        }

//            Label cliente = new Label(mesaActual.getCliente());//TEMPORAL
//            filtros.getChildren().add(cliente);
        //Label cliente = new Label(mesaActual.getCliente());//TEMPORAL
        //filtros.getChildren().add(cliente);
        colocarTodos();
        
        if (ProyectoPOO2p.usuario instanceof Administrador) {
            crearNuevoPlato(boton);
        }
        
        
        root.getChildren().addAll(filtros, platos);
        root.getChildren().add(boton);
        return root;
    }

    public void crearAccionPedido(ImageView img, String nombre, Double precio) {
        img.setOnMouseClicked(event -> {
            Map<String, Double> pedidoActual = mesaActual.getComidasPedido();
            if (pedidoActual.containsKey(nombre)) {
                pedidoActual.put(nombre, pedidoActual.get(nombre) + precio);
            } else {
                pedidoActual.put(nombre, precio);
            }
            aPagar += precio;
            System.out.println(mesaActual.getComidasPedido());
        });

    }

    public void setBotonesEscena(VBox boxBotones) {
        Button regresar = new Button("Regresar");
        regresar.setOnMouseClicked(v -> {
            ProyectoPOO2p.scene.setRoot(new MeseroView().build());

        });
        Button finalizar = new Button("Finalizar Orden");
        finalizar.setOnMouseClicked(event -> {
            mesaActual.setDisponible(true);
            mesaActual.setCliente(null);
            Pedido pMesa = new Pedido(LocalDate.now(), mesaActual.getNumeroMesa(), (Mesero) ProyectoPOO2p.usuario, "000-123", mesaActual.getCliente(), aPagar);
            ProyectoPOO2p.datos.guardarPedido(pMesa);
            ProyectoPOO2p.datos.getPedidos().add(pMesa);
            mesaActual.setVentasTotal(aPagar);
            mesaActual.setMesero(null);
            mesaActual.getComidasPedido().clear();
            detalle.getChildren().clear();
            ProyectoPOO2p.scene.setRoot(new MeseroView().build());
        });

        boxBotones.getChildren().addAll(regresar, finalizar);
    }

    public void makeClickable(Label filtro) {
        String texto = filtro.getText();
        filtro.setOnMouseClicked(event -> {
            platos.getChildren().clear();
            if (!texto.equals("Todos")) {
                colocarPlatosPorFiltro(texto);
            } else {
                colocarTodos();
            }

        });
    }

    public void colocarPlatosPorFiltro(String tipoPlato) {
        platos.getChildren().clear();
        for (Plato p : ProyectoPOO2p.datos.getPlatos().get(tipoPlato)) {
            colocarPlato(p);
        }
    }

    public void colocarTodos() {
        platos.getChildren().clear();
        for (String s : ProyectoPOO2p.datos.getPlatos().keySet()) {
            for (Plato p : ProyectoPOO2p.datos.getPlatos().get(s)) {
                colocarPlato(p);
            }
        }
    }

    public void colocarPlato(Plato p) {
        VBox detalles = new VBox(10);
        Label precio = new Label("Precio: " + String.valueOf(p.getPrecio()));
        Label nombre = new Label(p.getNombre());
        Image plato = new Image(p.getRuta());
        ImageView img = new ImageView(plato);
        img.setFitHeight(80);
        img.setFitWidth(80);
        if (ProyectoPOO2p.usuario instanceof Mesero) {
            crearAccionPedido(img, p.getNombre(), p.getPrecio());

        }
        if (ProyectoPOO2p.usuario instanceof Administrador) {
            cambiarInformacion(img, p);
        }
        detalles.getChildren().addAll(precio, img, nombre);
        platos.getChildren().add(detalles);
    }

    /**
     * Método que crea la nueva pestaña en la que se muestran las opciones para
     * modificar o eliminar.
     *
     * @param p el plato que se va a modificar o eliminar.
     */
    public void modificarPlato(Plato plato) {

        Label elegir = new Label("          Elija una opción: ");
        
        TextField ingresoNombre = new TextField();
        TextField ingresoPrecio = new TextField();

        Button nombre = new Button("Modificar nombre");
        Button precio = new Button("Modificar Precio");
        Button eliminar = new Button("Eiminar el plato");
        

        ventanaParaModificar = new Stage();

        VBox root = new VBox();
        root.getChildren().addAll(ingresoNombre, nombre, ingresoPrecio, precio, eliminar);

        HBox caja = new HBox();
        caja.setSpacing(50);
        caja.getChildren().addAll(elegir, root);

        Scene scene = new Scene(caja, Constantes.anchoVentana / 2, Constantes.altoVentana / 2);

        ventanaParaModificar.setResizable(false);
        ventanaParaModificar.setScene(scene);
        ventanaParaModificar.show();

        eliminarPlato(eliminar, plato);

        cambiarPlatos(nombre, plato, ingresoNombre);
        cambiarPlatos(precio, plato,ingresoPrecio);
    }

    /**
     * Método que permite modificar la información de ese plato.
     *
     * @param img la imagen a la que le da click.
     * @param p el plato que se va a modificar o elimiar.
     */
    public void cambiarInformacion(ImageView img, Plato p) {
        img.setOnMouseClicked(event -> {
            modificarPlato(p);
        });
    }

    /**
     * Recibe el evento y llama al método que modifica el plato
     *
     * @param boton el boton de eliminar
     * @param plato el plato a eliminar
     */
    public void eliminarPlato(Button boton, Plato plato) {
        boton.setOnMouseClicked(event -> {
            ProyectoPOO2p.datos.eliminarPlato(plato);
            colocarTodos();
            colocarPlatosPorFiltro(plato.getTipo());
            ventanaParaModificar.close();
        });
    }

    public void cambiarPlatos(Button boton, Plato p, TextField mensaje) {
        boton.setOnMouseClicked(event -> {    
            if (boton.getText().equals("Modificar nombre")) {
                p.setNombre(mensaje.getText());

            } else if (boton.getText().equals("Modificar Precio")) {
                p.setPrecio(Double.parseDouble(mensaje.getText()));
            }
            
            ProyectoPOO2p.datos.modificarMenu(p);
            colocarTodos();
            colocarPlatosPorFiltro(p.getTipo());
            
        });

    }
    
    public void crearNuevoPlato(Button boton) {
        boton.setOnMouseClicked((MouseEvent event) -> {

            Stage ventana = new Stage();
            combo = new ComboBox();
            VBox root = new VBox(10);
            Button crear = new Button(" Crear ");
            
            HBox paraTipo = new HBox(10);
            HBox paraNombre = new HBox(10);
            HBox paraPrecio= new HBox(10);
            
            TextField escribirNombre = new TextField();
            TextField escribirPrecio = new TextField();
            
            Label escoja = new Label(" Tipo de plato:  ");
            Label nombre = new Label(" Nombre del plato: ");
            Label precio = new Label(" Precio del plato: ");

            ObservableList<String> items = FXCollections.observableArrayList();
            for(int i= 0 ; i<4 ; i++){
                items.add(tipos[i]);
            }
            combo.itemsProperty().set(items);
            
            paraTipo.getChildren().addAll(escoja,combo);
            paraNombre.getChildren().addAll(nombre,escribirNombre);
            paraPrecio.getChildren().addAll(precio, escribirPrecio);
            
            root.getChildren().addAll(paraTipo,paraNombre,paraPrecio,crear);
            
            Scene scene = new Scene(root, Constantes.anchoVentana / 2, Constantes.altoVentana / 2);
            
            ventana.setScene(scene);
            ventana.setResizable(true);
            ventana.show();
            
            agregarNuevoPlato(crear,escribirNombre,escribirPrecio);
        });
    }
    
    public void agregarNuevoPlato(Button boton, TextField nombre, TextField precio){
        
        
        boton.setOnMouseClicked(event -> {    
            ProyectoPOO2p.datos.getPlatos().get(combo.getValue().toString()).add(
                    new Plato(0003,nombre.getText(),Double.parseDouble(precio.getText()),"/Archivos/PLATOS/nuevo.gif", combo.getValue().toString()));
            colocarTodos();
        });

    }

   
}
