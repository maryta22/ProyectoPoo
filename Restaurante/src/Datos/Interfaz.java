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
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import static jdk.nashorn.internal.runtime.JSType.isString;

/**
 *
 * @author danny
 */
public class Interfaz implements Serializable {

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

    public Interfaz() {
        crearUsuarios();
        cargarReportes();
        cargarData();
        cargarMesas();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void inicializarDatos() {
        crearPlatos();
        crearUsuarios();
        crearMesas();
        crearArchivoMesas();
        crearArchivos();
        System.out.println(archivoSopas);

    }

    public void crearArchivoMesas() {
        try (ObjectOutputStream op = new ObjectOutputStream(new FileOutputStream("src/Archivos/Mesas.dat"))) {
            op.writeObject(mesas);
            System.out.println("Archivo escrito");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearMesas() {
        mesas.add(new Mesa(100, 100, 15, "01"));
        mesas.add(new Mesa(200, 200, 25, "02"));
        mesas.add(new Mesa(300, 300, 50, "03"));
        mesas.add(new Mesa(200, 500, 25, "04"));
        mesas.add(new Mesa(500, 100, 30, "05"));
    }

    public void crearUsuarios() {
        usuarios.add(new Administrador("Admin", "Admin"));
        usuarios.add(new Mesero("Mesero1", "Mesero1"));
        usuarios.add(new Mesero("Mesero2", "Mesero2"));
        //usuario de acceso rapido para pruebas :v
        usuarios.add(new Administrador("a", "a"));
        usuarios.add(new Mesero("m", "m"));
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
    
    public void actualizarPedidos(){
        for(Mesa m:mesas){
            Map<String,ArrayList<Double>> pedidoMesa = m.getComidasPedido();
                for(String clave: pedidoMesa.keySet()){
                    pedidoMesa.get(clave).set(1, pedidoMesa.get(clave).get(0)*getPlato(clave).getPrecio());
                }
        }
    }

    /**
     * Crea "sopa"
     *
     * @return un ArrayList<Plato> de las sopas.
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
     * @return un ArrayList<Plato> de los segundos.
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
     * @return un ArrayList<Plato> de los postres.
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
     * @return un ArrayList<Plato> de las bebidas.
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
            System.out.println("IOExcepcion");
        }

    }

    /**
     * Escribe los archivos.
     */
    public void agregarElementosArchivos() {
        try {

            for (HashMap.Entry<String, ArrayList<Plato>> entry : platos.entrySet()) {

                if (null == entry.getKey()) {
                    System.out.println("No existe ese tipo");
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
                            System.out.println("No existe ese tipo");
                            break;
                    }
                }

            }

        } catch (IOException ex) {
            System.out.println("IOExcepcion");
        }
    }

    /*
     * Recibe el @param plato y modifica ese plato en el HashMap. 
     */
    public void modificarMenu(Plato p) {
        for (Plato plato : platos.get(p.getTipo())) {
            if (plato.getCodigo() == p.getCodigo()) {
                platos.get(p.getTipo()).remove(plato);
                platos.get(p.getTipo()).add(p);
            }
        }

    }
    
    public boolean platoEnPedido(String plato){
        for(Mesa mesa: mesas){
            if(mesa.getComidasPedido().containsKey(plato)){
                return true;
            }
            
        }
        return false;
    }

    public void cargarData() {
        int contador = 0;
        for (String s : nombresArchivos) {
            try (ObjectInputStream ob = new ObjectInputStream(new FileInputStream("src/Archivos/" + s))) {
                platos.put(clavesMapa.get(contador), (ArrayList<Plato>) ob.readObject());
                System.out.println(s + " leido");

            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                contador += 1;
            }
        }

    }

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

    public void cargarMesas() {
        try (ObjectInputStream op = new ObjectInputStream(new FileInputStream("src/Archivos/Mesas.dat"))) {
            mesas = (ArrayList<Mesa>) op.readObject();

        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarArchivos() {
        crearArchivoMesas();
        crearArchivos();

    }

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

    public void actualizarMenus() {
        File ficherosopa = new File("src/Archivos/Sopas.dat");
    }

    public boolean validarUsuario(String usuario, String contraseña) {
        for (Usuario u : usuarios) {
            if (u.equals(new Usuario(usuario, contraseña))) {
                return true;
            }
        }
        return false;
    }

    public Usuario getUsuario(String nombre) {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombre)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public HashMap<String, ArrayList<Plato>> getPlatos() {
        return platos;
    }

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

    public Mesa getMesa(Mesa mesa) {
        for (Mesa m : mesas) {
            if (m.equals(mesa)) {
                return m;
            }
        }
        return null;
    }

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
