/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import MartaYLasPlantas.Veganos.*;
import MartaYLasPlantas.Plantas.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class PrincipalGraficos {

    /**
     * @param alto (alto del tablero)
     * @param ancho (ancho del tablero)
     * @param dificultad
     * @param magia (magia global)
     * @param vegQuedan (veganos quedan por generar)
     * @param vegFinal (veganos por generar en la ronda final)
     * @param turnosSinVeganos
     */
    private static int alto;
    private static int ancho;
    private static int dificultad;
    private static int magia = 0;
    private static int vegQuedan;
    private static int vegFinal = 0;
    private static int turnosSinVeganos;
    public static JFrame frame = new JFrame();
    public static GraficosUwU panelJuego;

    private static Tablero tablero;

    /**
     * @param args
     *
     */
    public static void main(String[] args) {
        boolean jugando = true;
        boolean comprobando = true;
        boolean puedePlantar;
        boolean pierdes = false;
        int x = -1, y = -1;

        Scanner scanner = new Scanner(System.in);
        String comando, tokens[];

        HashMap<String, Integer> hashDificultad = new HashMap<>();
        hashDificultad.put("BAJA", 1);
        hashDificultad.put("MEDIA", 2);
        hashDificultad.put("ALTA", 3);
        hashDificultad.put("IMPOSIBLE", 4);

        magia = 50;

        //Comando inicial
        switch (dificultad) {
            case 1:
                vegQuedan = 5;
                break;
            case 2:
                vegQuedan = 15;
                break;
            case 3:
                vegQuedan = 25;
                break;
            case 4:
                vegQuedan = 50;
                break;
            default:
                vegQuedan = 10;
        }

        //reservar Veganos para la ronda final
        vegFinal = vegQuedan / 5;
        vegQuedan -= vegFinal;

        tablero = new Tablero(5, 7);
        tablero.colocarEntidad(new Lanzadora(), 0, 0);
        tablero.colocarEntidad(new Girasol(0), 1, 0);
        tablero.colocarEntidad(new Nuez(), 2, 0);
        tablero.colocarEntidad(new Cereza(0), 3, 0);
        tablero.colocarEntidad(new MinaPatata(0), 4, 0);
        System.out.println("Comienza la partida.");

        panelJuego = new GraficosUwU(true, tablero);
        frame.setSize(new Dimension(32 * 10 + 16, 32 * 6 + 7));
        frame.getContentPane().add(panelJuego);
        frame.setVisible(true);
        panelJuego.setVisible(true);
        //bucle principal del juego
        while (jugando) {
            generarVeganos();
            tablero.setVegQuedan(vegQuedan);
            jugando = tablero.actualiza();
            panelJuego.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrincipalGraficos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //comprobar si hay Veganos en el tablero
        for (Casilla[] fila : tablero.getTerreno()) {
            for (Casilla casilla : fila) {
                for (Entidad entidad : casilla.getEntidades()) {
                    if (pierdes = entidad instanceof Vegano) {
                        break;
                    }
                }
            }
        }

        if (pierdes) {
            System.out.println("Pero que pringao.");
        } else {
            System.out.println("Has ganado.\n"
                    + "¡¡Enhorabuena!!");
        }
    }

    /**
     *
     * @param cantidad Incrementa la magia global del juego.
     */
    public static void incrementarMagia(int cantidad) {
        magia += cantidad;
    }

    /**
     *
     * @param cantidad Disminuye la magia global del juego.
     */
    public static void disminuirMagia(int cantidad) {
        magia -= cantidad;
    }

    /**
     * Genera veganos en el tablero dependiendo del turno en el que estemos y la
     * dificultad
     */
    public static void generarVeganos() {
        // contadores
        int contador = 30 - tablero.getContador();
        int descanso;
        int turnoBoost = 0;
        double probabilidad = 0;
        double prob;
        double ajuste;
        double pfinal;

        switch (dificultad) {
            case 1:
                descanso = 10;
                ajuste = 1.5;
                turnoBoost = 5;
                break;
            case 2:
                descanso = 7;
                ajuste = 2.5;
                turnoBoost = 3;
                break;
            case 3:
                descanso = 5;
                ajuste = 3;
                turnoBoost = 2;
                break;
            case 4:
                descanso = 5;
                ajuste = 1;
                turnoBoost = 0;
                break;
            default:
                ajuste = 1;
                descanso = 5;
        }
        if (contador > 0) {
            if (turnosSinVeganos >= turnoBoost) {
                probabilidad = Math.random() / ajuste;
            }

            prob = ((double) vegQuedan) / ((double) contador) * 0.8;
            pfinal = prob + probabilidad;

            // comienzan a aparecer los zombies.
            if (contador < 30 - descanso) {
                if (contador < 7) {
                    if (pfinal > 1.00 && vegQuedan >= 3) {
                        vegQuedan -= 2 + (int) pfinal;
                        tablero.spawnVeganos(2 + (int) pfinal);
                        turnosSinVeganos = 0;
                    } else if (pfinal >= 0.75 && vegQuedan >= 2) {
                        vegQuedan -= 2;
                        tablero.spawnVeganos(2);
                        turnosSinVeganos = 0;
                    } else if (pfinal >= 0.6 && vegQuedan >= 1) {
                        vegQuedan -= 1;
                        tablero.spawnVeganos(1);
                        turnosSinVeganos = 0;
                    } else {
                        turnosSinVeganos++;
                    }
                } else if (contador == 7) {
                    System.out.println(" FINAL WAVE !!! ");
                    vegQuedan += vegFinal;
                } else {
                    if (pfinal >= 1.00 && vegQuedan >= 3) {
                        vegQuedan -= 2 + (int) pfinal;
                        tablero.spawnVeganos(2 + (int) pfinal);
                        turnosSinVeganos = 0;
                    } else if (pfinal >= 0.75 && vegQuedan >= 2) {
                        vegQuedan -= 2;
                        tablero.spawnVeganos(2);
                        turnosSinVeganos = 0;
                    } else if (pfinal >= 0.6 && vegQuedan >= 1) {
                        vegQuedan -= 1;
                        tablero.spawnVeganos(1);
                        turnosSinVeganos = 0;
                    } else {
                        turnosSinVeganos++;
                    }
                }
            }
        }
    }

    /**
     * imprime por pantalla el tablero del juego
     */
}
