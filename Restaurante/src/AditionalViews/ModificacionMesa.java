/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AditionalViews;

import Alertas.Alerta;
import Datos.Mesa;
import Views.MesasView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Restaurante.Restaurante;
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
    private MesasView escenaAnterior;
    private Button boton;

    /**
     * Constructor de la clase
     * @param evento Evento con el que fue llamada la clase
     * @param escena Escena anterior para realizar modificaciones
     */
    
    public ModificacionMesa(MouseEvent evento,MesasView escena) {
        modificacion = new Stage();
        root = new GridPane();
        mainRoot = new VBox();
        mainRoot.setAlignment(Pos.CENTER);
        eventoCrear = evento;
        escenaAnterior = escena;
        scene = new Scene(mainRoot,400,200);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10,10,10,10));
        root.setVgap(10);
        root.setHgap(10);
        modificacion.setOnCloseRequest(event->{
            escenaAnterior.setDisparo();
        });
    }
    /**
     * Constructor sobrecargado para distinguir una creacion de mesa
     * @param evento Evento con el que fue llamada la clase
     * @param escena Escena anterior para realizar modificaciones
     * @param mesa Mesa a ser modificada
     */
    
    public ModificacionMesa(MouseEvent evento,MesasView escena,Mesa mesa){
        this(evento,escena);
        mesaAModificar = mesa;
    }
    /**
     * Metodo que muestra la nueva ventana
     */
    public void showStage(){
        crearEscenaComun();
        if(mesaAModificar==null){
            crearEscena();
        }else{
            if(eventoCrear.getSource() instanceof StackPane){
                crearEscenaCambios();
            }
            
        }
        root.getChildren().addAll(numeroMesa,inputNumero,radio,inputRadio);
        mainRoot.getChildren().addAll(root,boton);
        modificacion.setScene(scene);
        modificacion.show();
    }
    /**
     * Metodo que crea una ventana comun 
     */
    
    public void crearEscenaComun(){
        numeroMesa = new Label("Numero de mesa");
        radio = new Label("Capacidad de la mesa");
        inputNumero = new TextField();
        inputRadio = new TextField();
        
        GridPane.setConstraints(numeroMesa, 0, 0);
        GridPane.setConstraints(inputNumero, 1, 0);
        GridPane.setConstraints(radio, 0, 1);
        GridPane.setConstraints(inputRadio, 1, 1);
        
    }
    
    /**
     * Metodo que crea la escena para crear una mesa
     */
    
    private void crearEscena(){
        boton = new Button("Crear");
        
        boton.setOnMouseClicked(event->{
            
            try{
                double radioGet = Double.parseDouble(inputRadio.getText());
                String numero = inputNumero.getText();
                if(radioGet<0 || Integer.parseInt(numero)<0){
                    throw new NumberFormatException();
                }
                if(validarDatos()){
                     Mesa nuevaMesa = new Mesa(eventoCrear.getX()-Restaurante.datos.asignarRadio(radioGet),eventoCrear.getY()-Restaurante.datos.asignarRadio(radioGet),radioGet,numero);
                    if(datosValidosMesa(nuevaMesa)){
                        Restaurante.datos.getMesas().add(nuevaMesa);
                        escenaAnterior.colocarMesas();
                        escenaAnterior.setDisparo();
                        modificacion.close();  
                    }else{
                        new Alerta("Datos Invalidos").mostrarAlerta();
                    }
                }else{
                    new Alerta("Datos Incosistentes").mostrarAlerta();
                }
                
            }catch(NumberFormatException ex){
                new Alerta("Formato Incorrecto").mostrarAlerta();
                
            }finally{
                inputNumero.clear();
                inputRadio.clear();
            }
                
            
                    
        });
        
        
        
        
        
        
    }
    
    /**
     * Metodo que crea la escena para modificar una mesa
     */
    
    public void crearEscenaCambios(){
        boton = new Button("Guardar Cambios");
        Button eliminar = new Button("Eliminar Mesa");
        inputNumero.setPromptText(mesaAModificar.getNumeroMesa());
        inputRadio.setPromptText(String.valueOf(mesaAModificar.getRadio()));
        boton.setOnMouseClicked(event->{
            try{
                double radioGet = Double.parseDouble(inputRadio.getText());
                
                String numero = inputNumero.getText();
                if(radioGet<0 || Integer.parseInt(numero)<0){
                    throw new NumberFormatException();
                }
                if(validarDatos()){
                    double diffRadio = mesaAModificar.asignarRadio(radioGet)-mesaAModificar.getRadio();
                    Mesa mesa = Restaurante.datos.getMesa(mesaAModificar);
                   Mesa nuevaMesa = new Mesa(mesaAModificar.getCoordenadaX()-diffRadio,mesaAModificar.getCoordenadaY()-diffRadio,radioGet,numero);
                    if(datosValidosMesa(nuevaMesa)){
                        mesa.setRadio(radioGet);
                        mesa.setNumeroMesa(numero);
                        mesa.setCoordenadaX(mesa.getCoordenadaX()-diffRadio);
                        mesa.setCoordenadaY(mesa.getCoordenadaY()-diffRadio);
                        escenaAnterior.colocarMesas();
                        escenaAnterior.setDisparo();
                         modificacion.close();
                        
                    }else{
                         new Alerta("Datos Invalidos").mostrarAlerta();
                    }
                }else{
                     new Alerta("Datos Incosistentes").mostrarAlerta();
                }
               
                
                
            }catch(NumberFormatException ex){
                 new Alerta("Formato Incorrecto").mostrarAlerta();
                
            }finally{
                inputNumero.clear();
                inputRadio.clear();
            }
        });
        
        eliminar.setOnMouseClicked(event->{
            if(mesaAModificar.isDisponible()){
                Restaurante.datos.getMesas().remove(mesaAModificar);
                escenaAnterior.colocarMesas();
                modificacion.close();
            }else{
                 new Alerta("Mesa no Disponible").mostrarAlerta();
            }
            
        });
        mainRoot.getChildren().add(eliminar);
    }
    
   
    /**
     * Metodo que valida que la mesa a crear o modificar no exista
     * @param mesa Mesa a validad
     * @return boolean, si las mesa a crear/modificar tiene datos de otra mesa retorna falso, caso contrario retorna verdadero
     */
    public boolean datosValidosMesa(Mesa mesa){
        if(!Restaurante.datos.getMesas().contains(mesa)){
            return true;
        }
        return false;
    }
    
    /**
     * Metodo que valida que la capacidad de la mesa no sea negativa ni cero
     * @return true si el dato es valido, caso contrario false
     */
    
    public boolean validarDatos(){
        if(Double.parseDouble(inputNumero.getText())>0){
            return true;
        }
        return false;
    }
    
   
    
}
