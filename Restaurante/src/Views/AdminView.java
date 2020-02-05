/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Datos.Pedido;
import Usuario.Mesero;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextAlignment;
import Restaurante.Restaurante;


/**
 *
 * @author danny
 */
public class AdminView {
    private BorderPane root;
    private List<String> pestañas = Arrays.asList("Monitoreo","Diseño Plano","Gestión Menú","Reporte Ventas","Salir");
    private List<String> columnasReporte = Arrays.asList("fecha","mesa","mesero","cuenta","cliente","total");
    private TableView tablaReportes;
    private ArrayList<TableColumn> columnas = new ArrayList<>();
    private DatePicker fechaInicio,fechaFin;
    private static boolean diseño ;
    
    /**
     * Constructor de la clase
     */
    
    public AdminView() {
        root = new BorderPane();  
        
    }
    
    /**
     * Metodo estatico para determinar si el usuario se encuentra en la pestaña diseño
     * @return 
     */
    public static boolean isDiseño() {
        return diseño;
    }
    
    /**
     * Metodo estatico para cambiar el valor de la variable diseño
     * @param diseño 
     */

    public static void setDiseño(boolean diseño) {
        AdminView.diseño = diseño;
    }
    
    /**
     * Metodo que construye el root 
     * @return root con los elementos graficos
     */
    
    public Parent build(){
        crearSeccionTop();
        return root;
    }
    
    /**
     * Metodo que construye la parte de los filtros para el admin
     */
    
    public void crearSeccionTop(){
        HBox seccionTop = new HBox();
        
        
        for(int i=0;i<pestañas.size();i++){
            Label lpestaña = new Label(pestañas.get(i));
            lpestaña.setStyle("-fx-border-color:white; -fx-background-color: black;");
            lpestaña.setMinHeight(50);
            lpestaña.setMinWidth(Restaurante.scene.getWidth()/5);
            lpestaña.setTextFill(Paint.valueOf("white"));
            lpestaña.setTextAlignment(TextAlignment.RIGHT);
            makeClikable(lpestaña);
            seccionTop.getChildren().add(lpestaña);
        }
        root.setTop(seccionTop); 
    }
    
    /**
     * Metodo que añade evento de cambiar de ventana a los filtros
     * @param label filtro al que se le añadira el evento
     */
    
    public void makeClikable(Label label){
        String texto = label.getText();
        EventHandler<MouseEvent> event = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                switch(texto){
                    case "Monitoreo":
                        System.out.println("Monitor");
                        diseño = false;
                        root.setCenter(new MesasView().build());
                        //Aqui se colocará el codigo para generar las mesas
                        break;
                    case "Diseño Plano":
                        System.out.println("Diseñar");
                        diseño = true;
                        root.setCenter(new MesasView().build());
                        //Aqui se colocará el código para generar las mesas
                        break;
                    case "Gestión Menú":
                        System.out.println("Menu");
                        root.setCenter(new PlatillosView().build());
                        
                        break;
                    case "Reporte Ventas":
                        System.out.println("Reporte");
                        generarReportes();
                        break;
                    case "Salir":
                        Restaurante.setScene(new LoginView().crearLogin());
                        break;
                    default:
                        break;
                }
            }
        };
        label.setOnMouseClicked(event);
        
    }
    
    /**
     * Metodo que crea la Tabla con los reportes de pedidos
     */
    
    public void generarReportes(){
        VBox reporte = new VBox();
        HBox filtrado = new HBox();
        filtrado.setSpacing(15);
        filtrado.setAlignment(Pos.CENTER);
        filtrado.setPadding(new Insets(10));
        Label inicio = new Label("Fecha Inicio");
        Label fin = new Label("Fecha Fin");
        fechaInicio = new DatePicker();
        fechaInicio.setEditable(false);
        fechaFin = new DatePicker();
        fechaFin.setEditable(false);
        Button buscar = new Button("Buscar");
        aplicarFiltro(buscar);
        filtrado.getChildren().addAll(inicio,fechaInicio,fin,fechaFin,buscar);
        tablaReportes = new TableView();
        tablaReportes.setEditable(true);
        crearColumnas();
        ObservableList<Pedido> items = cargarPedidos();
                //FXCollections.observableArrayList(new Pedido(LocalDate.now(),new Mesa(10,10,5,"25"),new Mesero("A","A"),"001-00","Juan",15.25));//Datos de prueba
        tablaReportes.setItems(items);
        reporte.setStyle("-fx-border-color: green;");
        reporte.getChildren().addAll(filtrado,tablaReportes);
        root.setCenter(reporte);
        
        
    }
    
    /**
     * Metodo que carga los reportes 
     * @return Lista de reportes 
     */
    public ObservableList<Pedido> cargarPedidos(){
        ArrayList<Pedido> pedidos = Restaurante.datos.getPedidos();
        return (FXCollections.observableArrayList(pedidos));
    }
    
    
    /**
     * Metodo que crea las columnas de la tabla reporte
     */
    public void crearColumnas(){
        columnas.clear();
        TableColumn<Pedido,LocalDate> fecha = new TableColumn("Fecha");
        TableColumn <Pedido,String> mesa = new TableColumn("Mesa");
        TableColumn <Pedido,Mesero> mesero = new TableColumn("Mesero");
        TableColumn <Pedido,String>cuenta = new TableColumn("#Cuenta");
        TableColumn <Pedido,String>cliente = new TableColumn("Cliente");
        TableColumn <Pedido,Double>total = new TableColumn("Total");
        columnas.add(fecha);
        columnas.add(mesa);
        columnas.add(mesero);
        columnas.add(cuenta);
        columnas.add(cliente);
        columnas.add(total);
        modelarColumnas(columnas);
        
        
    }
    
    /**
     * Metodo que da las caracteristicas a las columnas para que puedan almacenar datos
     * @param columnas 
     */
    
    public void modelarColumnas(ArrayList<TableColumn> columnas){
        int contador = 0;
        for(TableColumn t:columnas){
            t.setMinWidth(Restaurante.scene.getWidth()/columnas.size()); 
            t.setCellValueFactory(new PropertyValueFactory<>(columnasReporte.get(contador))); 
            contador +=1;
        }
        tablaReportes.getColumns().addAll(columnas);
    }
    
     
    /**
     * Metodo que añade un evento para filtras pedidos por fecha
     * @param filtro Boton al que se le añadira el evento
     */
    
    public void aplicarFiltro(Button filtro){
        filtro.setOnMouseClicked(event->{
            LocalDate inicioFiltro = fechaInicio.getValue();
            LocalDate finFiltro = fechaFin.getValue();
            ObservableList<Pedido> items = cargarPedidos();
            List<Pedido> itemsFiltrados = new ArrayList<>();
            
            for(Pedido p: items){
                if(p.getFecha().isAfter(inicioFiltro) && p.getFecha().isBefore(finFiltro)){
                    itemsFiltrados.add(p);
                }
            }
            tablaReportes.setItems(FXCollections.observableArrayList(itemsFiltrados));
            
        });
    }
    
}
