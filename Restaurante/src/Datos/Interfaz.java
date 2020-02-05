/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Usuario.Administrador;
import Usuario.Mesero;
import Usuario.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danny
 */
public class Interfaz {

    ObjectOutputStream archivoSopas;
    ObjectOutputStream archivoSegundos;
    ObjectOutputStream archivoPostres;
    ObjectOutputStream archivoBebidas;

    private HashMap<String, ArrayList<Plato>> platos = new HashMap<>();
    private List<String> nombresArchivos = Arrays.asList("Sopas.dat", "Segundos.dat", "Postres.dat", "Bebidas.dat");
    private List<String> clavesMapa = Arrays.asList("Sopa", "Segundo", "Postre", "Bebida");

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Mesa> mesas = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    /**
     * Constructor de la clase encargado de cargar los datos
     */
    public Interfaz() {
        crearUsuarios();
        cargarReportes();
        cargarData();
        cargarMesas();
    }

    /**
     * Metodo para obtener un ArrayList de usuarios
     * @return ArrayList con los usuarios registrados
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    /**
     * Metodo para obtener un ArrayList de los pedidos
     * @return ArrayList con los pedidos registrados
     */
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    /**
     * Metodo para cargar los datos iniciales
     */
    public void inicializarDatos() {
        crearPlatos();
        crearUsuarios();
        crearMesas();
        crearArchivoMesas();
        crearArchivos();

    }
    /**
     * Metodo que asigna un radio a una mesa a partir de su capacidad
     * @param capacidad Capacidad de la mesa
     * @return  Radio de la mesa
     */
     public double asignarRadio(double capacidad){
        double radioAsignado;
         if(capacidad<10){
             radioAsignado = 50;
         }else if(capacidad>=10 && capacidad<30){
             radioAsignado = 60;
         }else{
             radioAsignado = 70;
         }
         return radioAsignado;
    }
    
     /**
      * Metod que crea el archivo de mesas
      */
    public void crearArchivoMesas() {
        try (ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("src/Archivos/Mesas.dat"))) {
            op.writeObject(mesas);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     /**
      * Metodo que agrega mesas al ArrayList
      */
    public void crearMesas() {
        mesas.add(new Mesa(100, 100, 10, "1"));
        mesas.add(new Mesa(200, 200, 25, "2"));
        mesas.add(new Mesa(300, 300, 15, "3"));
        mesas.add(new Mesa(200, 500, 20, "4"));
        mesas.add(new Mesa(500, 100, 2, "5"));
    }
    /**
     * Metodo que añade usuarios al ArrayList
     */
    public void crearUsuarios() {
        usuarios.add(new Administrador("Admin", "Admin"));
        usuarios.add(new Mesero("Mesero1", "Mesero1"));
        usuarios.add(new Mesero("Mesero2", "Mesero2"));
        
    }

    /**
     * Añade los platos al HashMap platos donde la clave es el tipo y el valor
     * toda la información del plato separado por "-".
     */
    public void crearPlatos() {
        platos.put("Sopa", sopas());
        platos.put("Segundo", segundos());
        platos.put("Postre", postres());
        platos.put("Bebida", bebidas());

    }
    /**
     * Metodo para comprobar si existe un plato con el codigo enviado
     * @param codigo codigo a comprobar
     * @return true si el codigo existe, caso contrario false
     */
    public boolean existeCodigo(int codigo){
        for(String clave: platos.keySet()){
            for(Plato plato:platos.get(clave)){
                if(plato.getCodigo()==codigo){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Metodo que actualiza los pedidos de los usuarios en caso que un admin cambie el precio de los platos
     */
    public void actualizarPedidos(){
        for(Mesa m:mesas){
            Map<String,ArrayList<Double>> pedidoMesa = m.getComidasPedido();
                for(String clave: pedidoMesa.keySet()){
                    pedidoMesa.get(clave).set(1, pedidoMesa.get(clave).get(0)*getPlato(clave).getPrecio());
                }
        }
    }

    /**
     * Crea "sopa" en el ArrayList
     *
     * @return un ArrayList de las sopas.
     */
    public ArrayList<Plato> sopas() {
        ArrayList<Plato> Sopas = new ArrayList<>();
        Plato sopa1 = new Plato(0001, "Sopa de queso", 1.50, "/Archivos/PLATOS/SOPAS/SOPA DE QUESO.jpg", "Sopa");
        Plato sopa2 = new Plato(0002, "Caldo de Bola", 2.50, "/Archivos/PLATOS/SOPAS/CALDO DE BOLA.jpg", "Sopa");
        Plato sopa3 = new Plato(0003, "Biche de pescado", 2.00, "/Archivos/PLATOS/SOPAS/BICHE DE PESCADO.jpg", "Sopa");
        Plato sopa4 = new Plato(0004, "Caldo de pata", 3.00, "/Archivos/PLATOS/SOPAS/CALDO DE PATA.jpg", "Sopa");
        Plato sopa5 = new Plato(0005, "Locro de habas", 1.50, "/Archivos/PLATOS/SOPAS/LOCRO DE HABAS.jpg", "Sopa");
        Sopas.add(sopa1);
        Sopas.add(sopa2);
        Sopas.add(sopa3);
        Sopas.add(sopa4);
        Sopas.add(sopa5);
        return Sopas;
    }

    /**
     * Crea "segundo"
     *
     * @return un ArrayList de los segundos.
     */
    public ArrayList<Plato> segundos() {
        ArrayList<Plato> Segundos = new ArrayList<>();
        Plato segundo1 = new Plato(0010, "Guatita", 2.50, "/Archivos/PLATOS/SEGUNDOS/GUATITA.jpg", "Segundo");
        Plato segundo2 = new Plato(0011, "Chaulafán", 4.50, "/Archivos/PLATOS/SEGUNDOS/CHAULAFÁN.jpg", "Segundo");
        Plato segundo3 = new Plato(0012, "Seco de chivo", 2.00, "/Archivos/PLATOS/SEGUNDOS/SECO DE CHIVO.jpg", "Segundo");
        Plato segundo4 = new Plato(0013, "Seco de carne", 2.00, "/Archivos/PLATOS/SEGUNDOS/SECO DE CARNE.jpg", "Segundo");
        Plato segundo5 = new Plato(0014, "Seco de pollo", 2.00, "/Archivos/PLATOS/SEGUNDOS/SECO DE POLLO.jpg", "Segundo");
        Segundos.add(segundo1);
        Segundos.add(segundo2);
        Segundos.add(segundo3);
        Segundos.add(segundo4);
        Segundos.add(segundo5);
        return Segundos;
    }

    /**
     * Crea "postre"
     *
     * @return un ArrayList de los postres.
     */
    public ArrayList<Plato> postres() {
        ArrayList<Plato> Postres = new ArrayList<>();
        Plato postre1 = new Plato(0020, "Dulce de higos", 1.00, "/Archivos/PLATOS/POSTRES/DULCE DE HIGOS.jpg", "Postre");
        Plato postre2 = new Plato(0021, "Torta tres leches", 1.50, "/Archivos/PLATOS/POSTRES/TORTA TRES LECHES.jpg", "Postre");
        Plato postre3 = new Plato(0022, "Churros", 1.00, "/Archivos/PLATOS/POSTRES/CHURROS.jpg", "Postre");
        Plato postre4 = new Plato(0023, "Crepas Dulces", 1.00, "/Archivos/PLATOS/POSTRES/CREPAS DULCES.jpg", "Postre");
        Plato postre5 = new Plato(0024, "Mousse de Maracuyá", 1.00, "/Archivos/PLATOS/POSTRES/MOUSSE DE MARACUYÁ.jpg", "Postre");
        Postres.add(postre1);
        Postres.add(postre2);
        Postres.add(postre3);
        Postres.add(postre4);
        Postres.add(postre5);
        return Postres;
    }

    /**
     * Crea "bebida"
     *
     * @return un ArrayList de las bebidas.
     */
    public ArrayList<Plato> bebidas() {
        ArrayList<Plato> Bebidas = new ArrayList<>();
        Plato bebida1 = new Plato(0031, "Jugo de naranja", 1.00, "/Archivos/PLATOS/BEBIDAS/JUGO DE NARANJA.jpg", "Bebida");
        Plato bebida2 = new Plato(0032, "Jugo de mora", 1.50, "/Archivos/PLATOS/BEBIDAS/JUGO DE MORA.jpg", "Bebida");
        Plato bebida3 = new Plato(0033, "Jugo de maracuyá", 1.00, "/Archivos/PLATOS/BEBIDAS/JUGO DE MARACUYÁ.jpg", "Bebida");
        Plato bebida4 = new Plato(0034, "Limonada", 1.00, "/Archivos/PLATOS/BEBIDAS/LIMONADA.jpg", "Bebida");
        Plato bebida5 = new Plato(0035, "Jugo de coco", 1.00, "/Archivos/PLATOS/BEBIDAS/JUGO DE COCO.jpg", "Bebida");
        Bebidas.add(bebida1);
        Bebidas.add(bebida2);
        Bebidas.add(bebida3);
        Bebidas.add(bebida4);
        Bebidas.add(bebida5);
        return Bebidas;
    }

    /**
     * Crea los archivos binarios recorriendo el HashMap.
     */
    public void crearArchivos() {
        try {
            archivoSopas = new ObjectOutputStream(new FileOutputStream("src/Archivos/Sopas.dat"));
            archivoSegundos = new ObjectOutputStream(new FileOutputStream("src/Archivos/Segundos.dat"));
            archivoPostres = new ObjectOutputStream(new FileOutputStream("src/Archivos/Postres.dat"));
            archivoBebidas = new ObjectOutputStream(new FileOutputStream("src/Archivos/Bebidas.dat"));

            agregarElementosArchivos();

        } catch (IOException ex) {
            
        }

    }

    /**
     * Escribe los archivos.
     */
    public void agregarElementosArchivos() {
        try {

            for (HashMap.Entry<String, ArrayList<Plato>> entry : platos.entrySet()) {

                if (null == entry.getKey()) {
                    System.err.println("No existe ese tipo");
                } else {
                    switch (entry.getKey()) {
                        case "Sopa":
                            archivoSopas.writeObject(entry.getValue());
                            break;
                        case "Segundo":
                            archivoSegundos.writeObject(entry.getValue());
                            break;
                        case "Postre":
                            archivoPostres.writeObject(entry.getValue());
                            break;
                        case "Bebida":
                            archivoBebidas.writeObject(entry.getValue());
                            break;
                        default:
                            System.err.println("No existe ese tipo");
                            break;
                    }
                }

            }

        } catch (IOException ex) {
        }
    }

    /*
     * Recibe el @param plato y modifica ese plato en el HashMap. 
     */
    public void modificarMenu(Plato p) {
        Iterator iterador = platos.get(p.getTipo()).iterator();
        while(iterador.hasNext()){
            Plato plato = (Plato)iterador.next();
             if (plato.getCodigo() == p.getCodigo()) {
                 iterador.remove();
                 platos.get(p).add(plato);
             }
        }
     

    }
    
    /**
     * Metodo que verifica si un plato se encuentra en el pedido
     * @param plato Plato a verificar
     * @return true si el plato está en el pedido, caso contrario, false
     */
    public boolean platoEnPedido(String plato){
        for(Mesa mesa: mesas){
            if(mesa.getComidasPedido().containsKey(plato)){
                return true;
            }
            
        }
        return false;
    }
    /**
     * Metodo que carga la data de los archivos 
     */
    public void cargarData() {
        int contador = 0;
        for (String s : nombresArchivos) {
            try (ObjectInputStream ob = new ObjectInputStream(new FileInputStream("src/Archivos/" + s))) {
                platos.put(clavesMapa.get(contador), (ArrayList<Plato>) ob.readObject());
              
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                contador += 1;
            }
        }

    }
    /**
     * Metodo que carga los reportes del archivo
     */
    public void cargarReportes() {
        try (BufferedReader bf = new BufferedReader(new FileReader("src/Archivos/pedidos.txt"))) {
            String linea = null;
            while ((linea = bf.readLine()) != null) {
                String[] datosPedido = linea.split(",");
                Pedido pedido = new Pedido(LocalDate.parse(datosPedido[0]), datosPedido[1], (Mesero) getUsuario(datosPedido[2]), datosPedido[3], datosPedido[4], Double.parseDouble(datosPedido[5]));
                pedidos.add(pedido);
            }

        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Metodo que carga las mesas del archivo
     */
    public void cargarMesas() {
        try (ObjectInputStream op = new ObjectInputStream(new FileInputStream("src/Archivos/Mesas.dat"))) {
            mesas = (ArrayList<Mesa>) op.readObject();

        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * metodo que actualiza los archivos cuando se cierra la app
     */
    public void actualizarArchivos() {
        crearArchivoMesas();
        crearArchivos();

    }
    /**
     * Metodo que guarda un pedido en el archivo
     * @param pedido Pedido a guardar en el archivo
     */
    public void guardarPedido(Pedido pedido) {
        File file = new File("src/Archivos/pedidos.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try (BufferedWriter bf = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));) {
                bf.write(pedido.toString() + "\n");
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    

    /**
     * Metodo que valida los datos de un usuario
     * @param usuario Nombre de usuario a comprobar
     * @param contraseña Contraseña a comprobar
     * @return True si los datos coinciden con un usuario registrado, caso contrario, false
     */
    public boolean validarUsuario(String usuario, String contraseña) {
        for (Usuario u : usuarios) {
            if (u.equals(new Usuario(usuario, contraseña))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para obtener un usuario a partir de su nombre
     * @param nombre Nombre de usuario a obtener
     * @return Usuario que coincida con el nombre pasado como parametro
     */
    public Usuario getUsuario(String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombre)) {
                return u;
            }
        }
        return null;
    }
    /**
     * Metodo para obtener las mesas
     * @return ArrayList de mesas
     */
    public ArrayList<Mesa> getMesas() {
        return mesas;
    }
    /**
     * Metodo para obtener los platos
     * @return  Mapa con los platos
     */
    public HashMap<String, ArrayList<Plato>> getPlatos() {
        return platos;
    }

    /**
     * Metodo para obtener un plato a partir de su nombre
     * @param nombre Nombre del plato a obtener
     * @return Plato con el nombre pasado como parametro
     */
    public Plato getPlato(String nombre) {
        List<Plato> platosCompletos = new ArrayList<>();
        for (ArrayList<Plato> listaPlatos : platos.values()) {
            platosCompletos.addAll(listaPlatos);
        }
        for (Plato p : platosCompletos) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo para la mesa pasada como parametro
     * @param mesa Mesa a comprobar
     * @return Mesa pasada como parametro
     */
    public Mesa getMesa(Mesa mesa) {
        for (Mesa m : mesas) {
            if (m.equals(mesa)) {
                return m;
            }
        }
        return null;
    }
    /**
     * Metodo para obtener una mesa a partir de su numero
     * @param numero Numero de mesa a obtener
     * @return Mesa con el numero pasado como parametro
     */
    public Mesa getMesa(String numero) {
        for (Mesa m : mesas) {
            if (m.getNumeroMesa().equals(numero)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Elimina de platos el plato pasado por el parámetro.
     *
     * @param p plato a ser eliminado.
     */
    public void eliminarPlato(Plato p) {
        platos.get(p.getTipo()).remove(p);
    }


}
