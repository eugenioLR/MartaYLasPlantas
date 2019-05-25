/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import MartaYLasPlantas.Veganos.*;
import MartaYLasPlantas.Plantas.*;

/**
 *
 * @author EDGENP Eugenio Lorente Darius Tamas
 */
public class Tablero {

    private Casilla terreno[][];
    private boolean cortacesped[];
    private int contador, ancho, alto;
    private int vegQuedan = -1;

    /**
     * Constructor de tablero.
     *
     * @param alto
     * @param ancho
     */
    public Tablero(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
        terreno = new Casilla[alto][ancho];
        cortacesped = new boolean[alto];
        for (int i = 0; i < alto; i++) {
            cortacesped[i] = true;
        }

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                terreno[i][j] = new Casilla();
            }
        }

        contador = 0;
    }

    /**
     * Get the value of contador
     *
     * @return the value of contador
     */
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    /**
     * Devuelve el array de cortacesped
     *
     * @return cortacesped
     */
    public boolean[] getCortacesped() {
        return cortacesped;
    }

    /**
     *
     * @param cortacesped
     */
    public void setCortacesped(boolean[] cortacesped) {
        this.cortacesped = cortacesped;
    }

    /**
     *
     * @param pos
     * @return si hay cortacesped en la posicion pos
     */
    public boolean hayCortacesped(int pos) {
        return cortacesped[pos];
    }

    /**
     * Quita una cortacesped en la posicion pos
     *
     * @param pos
     */
    public void quitarCortacesped(int pos) {
        this.cortacesped[pos] = false;
    }

    /**
     * Coloca una entidad en el tablero en la posicion x y
     *
     * @param entidad
     * @param y
     * @param x
     */
    public void colocarEntidad(Entidad entidad, int y, int x) {
        terreno[y][x].getEntidades().add(entidad);
    }

    /**
     *
     * @return the value of Terreno
     */
    public Casilla[][] getTerreno() {
        return terreno;
    }

    /**
     *
     * @param terreno
     */
    public void setTerreno(Casilla[][] terreno) {
        this.terreno = terreno;
    }

    /**
     * Set the value of vegQuedan
     *
     * @param vegQuedan new value of ataque
     */
    public void setVegQuedan(int vegQuedan) {
        this.vegQuedan = vegQuedan;
    }

    /**
     * Se encarga de la intercaccion entre entidades.
     *
     * @return si se sigue jugando
     */
    public boolean actualiza() {
        boolean hayPlantas, veganoEncontrado = false, hayVeganos;
        int vegTablero;
        Entidad entidad;

        vegTablero = 0;

        //Recorriendo el tablero.
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {

                // Recorriendo las entidades del tablero.
                for (int k = 0; k < terreno[i][j].getEntidades().size(); k++) {
                    entidad = terreno[i][j].getEntidades().get(k);
                    hayPlantas = false;

                    // Acciones del vegano
                    if (entidad instanceof Vegano) {
                        vegTablero++;
                        //Si se encuentra en su turno de moverse.
                        if ((entidad.getTurno() % ((Vegano) entidad).getEspera()) == (contador % ((Vegano) entidad).getEspera()) && (entidad.getTurno() != contador)) {
                            //Mirar en su casilla
                            for (Entidad ent : terreno[i][j].getEntidades()) {
                                //Si encuentra una planta
                                if (ent instanceof Planta) {
                                    ent.reducirSalud(entidad.getAtaque());
                                    hayPlantas = true;
                                }
                            }
                            // Si no encuentra una planta
                            if (!hayPlantas) {
                                if (j > 0) {
                                    terreno[i][j].quitarEntidad(entidad);
                                    terreno[i][j - 1].insertarEntidad(entidad);
                                    //Si encuentra una cortacésped
                                } else if (hayCortacesped(i)) {
                                    for (int l = 0; l < ancho; l++) {
                                        terreno[i][l].vaciar();
                                    }
                                    quitarCortacesped(i);
                                } else {
                                    return false;
                                }
                            }
                        }
                    }

                    // Movimientos posibles de la lanzadora.
                    if (entidad instanceof Lanzadora) {
                        for (int l = j; l < terreno[0].length; l++) {
                            for (Entidad ent : terreno[i][l].getEntidades()) {
                                if ((veganoEncontrado = ent instanceof Vegano)) {
                                    ent.reducirSalud(entidad.getAtaque());
                                    break;
                                }
                            }
                            // solo va a disparar al primer vegano.
                            if (veganoEncontrado) {
                                break;
                            }
                        }
                    }
                    // Movimientos del girasol.
                    if (entidad instanceof Girasol) {
                        if ((entidad.getTurno() % 2) == (contador % 2)) {
                            // generarMagia
                            PrincipalTerminal.incrementarMagia(((Girasol) entidad).getMagiaGenera());
                        }
                    }

                    // Explosion cereza
                    if (entidad instanceof Cereza) {
                        if ((entidad.getTurno() % 2) <= (contador + 2)) {
                            for (int vert = -1; vert <= 1; vert++) {
                                for (int horz = -1; horz <= 1; horz++) {
                                    if (!(((i + vert) > alto || (i + vert) < 0) || ((j + horz) > ancho || (j + horz) < 0))) {
                                        for (Entidad ent : terreno[i + vert][j + horz].getEntidades()) {
                                            ent.reducirSalud(entidad.getAtaque());
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //explosion MinaPatata
                    if (entidad instanceof MinaPatata) {
                        if ((entidad.getTurno() % 2) <= (contador + 2)) {
                            ((MinaPatata) entidad).setEnterrado(false);
                        }

                        if (!((MinaPatata) entidad).isEnterrado()) {
                            hayVeganos = false;
                            
                            for (int l = 0; l < terreno[i][j].getEntidades().size(); l++) {
                                if (hayVeganos = (terreno[i][j].getEntidades().get(l) instanceof Vegano)) {
                                    break;
                                }
                            }
                            
                            if (hayVeganos) {
                                for (Entidad ent : terreno[i][j].getEntidades()) {
                                    ent.reducirSalud(entidad.getAtaque());
                                }
                            }
                        }
                    }
                }
            }
        }

        for (Casilla[] fila : this.getTerreno()) {
            for (Casilla casilla : fila) {
                casilla.actualizar();
            }
        }
        contador++;
        return !((vegTablero == 0) && (vegQuedan == 0));//ganar
    }

    /**
     * Genera tantos veganos como se indique
     *
     * @param cantidad
     */
    public void spawnVeganos(int cantidad) {
        int altoAleatorio;
        double probabilidad;
        Vegano vegano;
        for (int i = 0; i < cantidad; i++) {
            altoAleatorio = (int) (Math.random() * (alto));
            probabilidad = Math.random();
            if (probabilidad < (1.0 / 3)) {
                vegano = new VeganoComun(contador);
            } else if (probabilidad < (2.0 / 3.0)) {
                vegano = new VeganoProteico(contador);
            } else {
                vegano = new VeganoCasco(contador);
            }
            terreno[altoAleatorio][ancho - 1].getEntidades().add(vegano);
        }
        vegQuedan -= cantidad;
    }

    /**
     * Funcion Bomba: Elimina todo el contenido de una casilla, pagando un coste
     * de magia.
     *
     * @param x
     * @param y
     */
    public void Bomba(int x, int y) {
        int veganos = 0;
        int plantas = 0;

        for (int i = 0; i < terreno[y][x].getEntidades().size(); i++) {
            if (terreno[y][x].getEntidades().get(i) instanceof Vegano) {
                veganos++;
            } else if (terreno[y][x].getEntidades().get(i) instanceof Planta) {
                plantas++;
            }

        }
        if (veganos == 0 && plantas == 0) {
            System.out.println("Nada ha sido eliminado con esa bomba.");
        } else {
            System.out.println("Se han eliminado " + veganos + " veganos y " + plantas + " plantas");
        }
        terreno[y][x].getEntidades().clear();
    }
}
