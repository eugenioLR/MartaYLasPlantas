/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author EDGENP
 */
public class Jugador {

    private String dni;
    private String nombre;
    private long puntuacion[] = new long[4];
    private boolean partidaCreada;
    private int[] partidasGanadas;
    private int[] partidasPerdidas;
    private static int indice;
    private static HashMap jugadores = new HashMap<>();
    private HashMap puntuaciones = new HashMap<>();

    public Jugador(String dni, String nombre, boolean partidaCreada) {
        this.dni = dni;
        this.nombre = nombre;
        this.partidaCreada = partidaCreada;
        partidasPerdidas = new int[4];
        partidasGanadas = new int[4];
    }

    /**
     * Get the value of partidasPerdidas
     *
     * @return the value of partidasPerdidas
     */
    public int[] getPartidasPerdidas() {
        return partidasPerdidas;
    }

    /**
     * Set the value of partidasPerdidas
     *
     * @param partidasPerdidas new value of partidasPerdidas
     */
    public void setPartidasPerdidas(int[] partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    /**
     * Get the value of partidasGanadas
     *
     * @return the value of partidasGanadas
     */
    public int[] getPartidasGanadas() {
        return partidasGanadas;
    }

    /**
     * Set the value of partidasGanadas
     *
     * @param partidasGanadas new value of partidasGanadas
     */
    public void setPartidasGanadas(int[] partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    /**
     * Get the value of dni
     *
     * @return the value of dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of dni
     *
     * @param dni new value of dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of puntuacion
     *
     * @return the value of puntuacion
     */
    public long[] getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(long[] puntuacion) {
        this.puntuacion = puntuacion;
    }

    public static int getIndice() {
        return indice;
    }

    public static void setIndice(int indice) {
        Jugador.indice = indice;
    }

    /**
     * Get the value of partidaCreada
     *
     * @return the value of partidaCreada
     */
    public boolean isPartidaCreada() {
        return partidaCreada;
    }

    /**
     * Set the value of partidaCreada
     *
     * @param partidaCreada new value of partidaCreada
     */
    public void setPartidaCreada(boolean partidaCreada) {
        this.partidaCreada = partidaCreada;
    }

    public static void registrarse() {
        String dni = JOptionPane.showInputDialog("DNI: ");
        if (jugadores.containsValue(dni)) {
            // empezaríamos el juego
        } else {
            // añadimos al jugador.
            String nombre = JOptionPane.showInputDialog("Nombre: ");
            jugadores.put(dni, nombre);
            setIndice(jugadores.size());
        }
    }

    public static void leerJugadores() {

    }

    public void crearFicha() {
        try {
            FileWriter nuevaFicha, nuevoRegistro;
            BufferedWriter bwriter;
            new File("jugadores").mkdir();
            File ficha, registroJugadores;
            ficha = new File("jugadores/" + dni + ".dat");
            ficha.createNewFile();
            nuevaFicha = new FileWriter(ficha);
            bwriter = new BufferedWriter(nuevaFicha);

            bwriter.write("nombre " + nombre);
            bwriter.newLine();

            bwriter.write("dni " + dni);
            bwriter.newLine();

            bwriter.write("ParitdasGanadas ");
            for (int partida : partidasGanadas) {
                bwriter.write(partida + " ");
            }

            bwriter.write("ParitdasPerdidas ");
            for (int partida : partidasPerdidas) {
                bwriter.write(partida + " ");
            }
            bwriter.close();

            registroJugadores = new File("jugadores.dat");
            registroJugadores.createNewFile();
            nuevoRegistro = new FileWriter(registroJugadores);
            bwriter = new BufferedWriter(nuevoRegistro);

            bwriter.write("nombre " + nombre);
            bwriter.newLine();

            bwriter.write("dni " + dni);
            bwriter.newLine();

            bwriter.write("ParitdasGanadas ");
            for (int partida : partidasGanadas) {
                bwriter.write(partida + " ");
            }

            bwriter.write("ParitdasPerdidas ");
            for (int partida : partidasPerdidas) {
                bwriter.write(partida + " ");
            }
        } catch (IOException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarFicha() {

    }

    public void ranking() {
        //HashMap auxx = new HashMap<>();
        switch (PrincipalTerminal.getDificultad()) {

            case 1:

            case 2:

            case 3:

            case 4:

        }

    }

    @Override
    public String toString() {
        return "Jugador{" + "dni=" + dni + ", nombre=" + nombre + ", puntuacion=" + puntuacion + "}";
    }
}
