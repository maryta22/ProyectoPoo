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
public class Interfaz implements Serializable {

    ObjectOutputStream archivoSopas;
    ObjectOutputStream archivoSegundos;
    ObjectOutputStream archivoPostres;
    ObjectOutputStream archivoBebidas;

    private HashMap<String, ArrayList<Plato>> platos = new HashMap<>();

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
        crearArchivos();

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

    public void crearArchivos() {
        try {
            archivoSopas = new ObjectOutputStream(new FileOutputStream("src/Archivos/Sopas.dat"));
            archivoSegundos = new ObjectOutputStream(new FileOutputStream("src/Archivos/Segundos.dat"));
            archivoPostres = new ObjectOutputStream(new FileOutputStream("src/Archivos/Postres.dat"));
            archivoBebidas = new ObjectOutputStream(new FileOutputStream("src/Archivos/Bebidas.dat"));

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
            System.out.println(ex.getMessage());
        }

    }

    public void modificarMenu(String tipo, int codigo) {
        ArrayList<Plato> porModificar = platos.get(tipo);
        for (Plato platoModificar : porModificar) {
            if (platoModificar.getCodigo() == codigo) {
                
            }
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

    public HashMap<String, ArrayList<Plato>> getPlatos() {
        return platos;
    }
    
    
}
