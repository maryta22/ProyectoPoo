/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Usuario.Administrador;
import Usuario.Mesero;
import Usuario.Usuario;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danny
 */
public class Interfaz implements Serializable{

    private HashMap<String,ArrayList<Plato>> platos = new HashMap<>();
    
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Mesa> mesas = new ArrayList<>();

    public Interfaz() {
        inicializarDatos();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void inicializarDatos() {
        crearPlatos();
        crearUsuarios();
        crearMesas();
        crearArchivo();

    }
 
    
    
    public void crearMesas(){
        mesas.add(new Mesa(100, 100, 15, "01"));
        mesas.add(new Mesa(200, 200, 25, "02"));
        mesas.add(new Mesa(300, 300, 50, "03"));
    }

    public void crearUsuarios() {
        usuarios.add(new Administrador("Admin", "Admin"));
        usuarios.add(new Mesero("Mesero1", "Mesero1"));
        usuarios.add(new Mesero("Mesero2", "Mesero2"));
        usuarios.add(new Mesero("m","m"));
    }

    /**
     * Añade los platos al HashMap platos donde la clave es el tipo y el valor
     * toda la información del plato separado por "-".
     */
    public void crearPlatos() {
        platos.put("Sopa", sopas());
        platos.put("Segundo",segundos());
        System.out.println(platos);
    }

    /**
     * Crea "sopa"
     *
     * @return un ArrayList<Plato> de las sopas.
     */
    public ArrayList<Plato>  sopas() {
        ArrayList<Plato> Sopas= new ArrayList<>();
        Plato sopa1 = new Plato("Sopa de queso", 1.50, "src/Archivos/PLATOS/SOPAS/SOPA DE QUESO.jpg", "Sopa");
        Plato sopa2 = new Plato("Caldo de Bola", 2.50, "src/Archivos/PLATOS/SOPAS/CALDO DE BOLA.jpg", "Sopa");
        Plato sopa3 = new Plato("Biche de pescado", 2.00, "src/Archivos/PLATOS/SOPAS/BICHE DE PESCADO.jpg", "Sopa");
        Plato sopa4 = new Plato("Caldo de pata", 3.00, "src/Archivos/PLATOS/SOPAS/CALDO DE PATA.jpg", "Sopa");
        Plato sopa5 = new Plato("Locro de habas", 1.50, "src/Archivos/PLATOS/SOPAS/LOCRO DE HABAS.jpg", "Sopa");
        Sopas.add(sopa1);
        Sopas.add(sopa2);
        return Sopas ;
    }

    /**
     * Crea "segundo"
     *
     * @return un ArrayList<Plato> de los segundos.
     */
    public ArrayList<Plato> segundos() {
        ArrayList<Plato> Segundos = new ArrayList<>();
        Plato segundo1 = new Plato("Guatita", 2.50, "src/Archivos/PLATOS/SEGUNDOS/GUATITA.jpg", "Segundo");
        Plato segundo2 = new Plato("Chaulafán", 4.50, "src/Archivos/PLATOS/SEGUNDOS/CHAULAFÁN.jpg", "Segundo");
        Plato segundo3 = new Plato("Seco de chivo", 2.00, "src/Archivos/PLATOS/SEGUNDOS/SECO DE CHIVO.jpg", "Segundo");
        Plato segundo4 = new Plato("Seco de carne", 2.00, "src/Archivos/PLATOS/SEGUNDOS/SECO DE CARNE.jpg", "Segundo");
        Plato segundo5 = new Plato("Seco de pollo", 2.00, "src/Archivos/PLATOS/SEGUNDOS/SECO DE POLLO.jpg", "Segundo");
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
        Plato postre1 = new Plato("Dulce de higos", 1.00, "src/Archivos/PLATOS/POSTRES/DULCE DE HIGOS.jpg", "Postre");
        Plato postre2 = new Plato("Torta tres leches", 1.50, "src/Archivos/PLATOS/POSTRES/TORTA TRES LECHES.jpg", "Postre");
        Plato postre3 = new Plato("Churros", 1.00, "src/Archivos/PLATOS/POSTRES/CHURROS.jpg", "Postre");
        Plato postre4 = new Plato("Crepas Dulces", 1.00, "src/Archivos/PLATOS/SEGUNDO/CREPAS DULCES.jpg", "Postre");
        Plato postre5 = new Plato("Mousse de Maracuyá", 1.00, "src/Archivos/PLATOS/SEGUNDO/MOUSSE DE MARACUYÁ.jpg", "Postre");
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
        Plato bebida1 = new Plato("Jugo de naranja", 1.00, "src/Archivos/PLATOS/BEBIDAS/JUGO DE NARANJA.jpg", "Bebida");
        Plato bebida2 = new Plato("Jugo de mora", 1.50, "src/Archivos/PLATOS/BEBIDAS/JUGO DE MORA.jpg", "Bebida");
        Plato bebida3 = new Plato("Jugo de maracuyá", 1.00, "src/Archivos/PLATOS/BEBIDAS/JUGO DE MARACUYÁ.jpg", "Bebida");
        Plato bebida4 = new Plato("Limonada", 1.00, "src/Archivos/PLATOS/SEGUNDO/LIMONADA.jpg", "Bebida");
        Plato bebida5 = new Plato("Jugo de coco", 1.00, "src/Archivos/PLATOS/SEGUNDO/JUGO DE COCO.jpg", "Bebida");
        Bebidas.add(bebida1);
        Bebidas.add(bebida2);
        Bebidas.add(bebida3);
        Bebidas.add(bebida4);
        Bebidas.add(bebida5);
        return Bebidas;
    }
    
    public void crearArchivo(){
        try {
            ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream("src/Archivos/Sopas.dat"));
            for (HashMap.Entry<String, ArrayList<Plato>> entry : platos.entrySet()) {
                   
           }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

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

}
