/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class Jugador {

    private String dni;
    private String nombre;
    private long puntuacion[] = new long[4];
    private boolean partidaCreada;
    private int[] partidasGanadas;
    private int[] partidasPerdidas;
    private static int indice;
    private static HashMap<String, Jugador> jugadores = new HashMap<>();
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

    public static Jugador registrarse(String dni) {
        Jugador jugadorAux;
        leerJugadores();
        //String dni = JOptionPane.showInputDialog("DNI: ");
        if (jugadores.containsKey(dni)) {
            jugadorAux = jugadores.get(dni);
        } else {
            // añadimos al jugador.
            String nombre = JOptionPane.showInputDialog("Nombre: ");
            jugadorAux = new Jugador(dni, nombre, true);
            int reply = JOptionPane.showConfirmDialog(null, "crear ficha", "Guardar Partida", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                jugadorAux.crearFicha();
            }

            jugadores.put(dni, jugadorAux);
            setIndice(jugadores.size());
        }
        return jugadorAux;
    }

    public static void leerJugadores() {
        try {
            FileReader reader = new FileReader("jugadores.dat");
            BufferedReader breader = new BufferedReader(reader);
            String linea, tokens[], nombre = null, dni = null;
            Jugador jugadorAux;
            int ptdsGanadas[] = new int[4], ptdsPerdidas[] = new int[4];
            long pts[] = new long[4];
            for (int i = 0; (linea = breader.readLine()) != null; i++) {
                switch (i % 6) {
                    case 0:
                        tokens = linea.split(" ");
                        dni = tokens[1];
                        break;
                    case 1:
                        tokens = linea.split(" ");
                        nombre = tokens[1];
                        break;
                    case 2:
                        tokens = linea.split(" ");
                        for (int j = 0; j < 4; j++) {
                            ptdsGanadas[j] = Integer.parseInt(tokens[j + 1]);
                        }
                        break;
                    case 3:
                        tokens = linea.split(" ");
                        for (int j = 0; j < 4; j++) {
                            ptdsPerdidas[j] = Integer.parseInt(tokens[j + 1]);
                        }
                        break;
                    case 4:
                        tokens = linea.split(" ");
                        for (int j = 0; j < 4; j++) {
                            pts[j] = Integer.parseInt(tokens[j + 1]);
                        }
                        break;
                    default:
                        jugadorAux = new Jugador(dni, nombre, false);
                        jugadorAux.setPartidasGanadas(ptdsGanadas);
                        jugadorAux.setPartidasPerdidas(ptdsPerdidas);
                        jugadorAux.setPuntuacion(pts);
                        jugadores.put(dni, jugadorAux);
                        System.out.println(dni);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
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

            //linea1: dni
            bwriter.write("dni " + dni);
            bwriter.newLine();

            //linea2: nombre
            bwriter.write("nombre " + nombre);
            bwriter.newLine();

            //linea3: PartidasGanadas
            bwriter.write("ParitdasGanadas ");
            for (int partida : partidasGanadas) {
                bwriter.write(partida + " ");
            }
            bwriter.newLine();

            //linea4: PartidasPerdidas
            bwriter.write("ParitdasPerdidas ");
            for (int partida : partidasPerdidas) {
                bwriter.write(partida + " ");
            }
            bwriter.newLine();

            //linea4: Puntuacion
            bwriter.write("Puntuacion ");
            for (long pts : puntuacion) {
                bwriter.write(pts + " ");
            }
            bwriter.newLine();
            bwriter.newLine();
            bwriter.close();

            //Registro Jugadores
            String registroAux = "", registroAux2 = "", linea, tokens[];
            File registro = new File("jugadores.dat");
            registro.createNewFile();
            FileReader reader = new FileReader(registro);
            BufferedReader breader = new BufferedReader(reader);
            while ((linea = breader.readLine()) != null) {
                tokens = linea.split(" ");
                if (tokens[0].equals("dni")) {
                    if (tokens[1].equals(dni)) {
                        for (int j = 0; j < 5; j++) {
                            breader.readLine();
                        }
                    }
                }
                registroAux += linea + "\n";

            }

            registroJugadores = new File("jugadores.dat");
            registroJugadores.createNewFile();
            nuevoRegistro = new FileWriter(registroJugadores);
            bwriter = new BufferedWriter(nuevoRegistro);

            //linea1: dni
            bwriter.write("dni " + dni);
            bwriter.newLine();

            //linea2: nombre
            bwriter.write("nombre " + nombre);
            bwriter.newLine();

            //linea3: PartidasGanadas
            bwriter.write("ParitdasGanadas ");
            for (int partida : partidasGanadas) {
                bwriter.write(partida + " ");
            }
            bwriter.newLine();

            //linea4: PartidasPerdidas
            bwriter.write("ParitdasPerdidas ");
            for (int partida : partidasPerdidas) {
                bwriter.write(partida + " ");
            }
            bwriter.newLine();

            //linea5: puntuacion
            bwriter.write("Puntuacion ");
            for (long pts : puntuacion) {
                bwriter.write(pts + " ");
            }
            bwriter.newLine();
            bwriter.newLine();
            bwriter.write(registroAux);
            bwriter.close();

        } catch (IOException ex) {
            Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * 
     * @param dificultad
     * @return 
     */
    public static Jugador[] ranking(int dificultad) {
        leerJugadores();
        Jugador arrJugadores[] = new Jugador[jugadores.size()];
        
        //hashMap -> array
        int i = 0;
        for (String clave : jugadores.keySet()) {
            arrJugadores[i] = jugadores.get(clave);
            i++;

        }

        //ordenamiento burbuja
        boolean ordenado = false;
        Jugador jugadorAux;
        while (!ordenado) {
            ordenado = true;
            for (int j = 0; j < arrJugadores.length - 1; j++) {
                if (arrJugadores[j].getPuntuacion()[dificultad - 1] > arrJugadores[j + 1].getPuntuacion()[dificultad - 1]) {
                    jugadorAux = arrJugadores[j];
                    arrJugadores[j] = arrJugadores[j + 1];
                    arrJugadores[j + 1] = jugadorAux;
                    ordenado = false;
                }
            }
        }
        return arrJugadores;
    }

    @Override
    public String toString() {
        return "Jugador{" + "dni=" + dni + ", nombre=" + nombre + ", puntuacion=" + puntuacion + "}";
    }
}
