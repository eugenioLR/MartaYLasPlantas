/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

/**
 *
 * @author EDGENP
 *         Eugenio Lorente
 *         Darius Tamas
 */
public class Tablero {

    private Casilla terreno[][];
    private boolean cortacesped[];
    private int contador, ancho, alto;
    private int vegQuedan = -1;

    /**
     * Constructor de tablero.
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
     * 
     * @return ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     *
     * @param ancho
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     *
     * @return alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     *
     * @param alto
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     *
     * @return
     */
    public int getContador() {
        return contador;
    }

    /**
     *
     * @param contador
     */
    public void setContador(int contador) {
        this.contador = contador;
    }

    /**
     * Comprueba si hay cortacesped
     * @return cortacesped
     */
    public boolean[] getCortacesped() {
        return cortacesped;
    }

    /**
     *
     * @param pos
     * @return
     */
    public boolean hayCortacesped(int pos) {
        return cortacesped[pos];
    }

    /**
     *
     * @param pos
     */
    public void quitarCortacesped(int pos) {
        this.cortacesped[pos] = false;
    }

    /**
     *
     */
    public void incrementarContador() {
        this.contador++;
    }

    /**
     *
     * @param entidad
     * @param y
     * @param x
     */
    
    public void colocarEntidad(Entidad entidad, int y, int x) {
        //toca explicar esta wea
        terreno[y][x].getEntidades().add(entidad);
    }

    /**
     *
     * @return
     */
    public Casilla[][] getTerreno() {
        return terreno;
    }

    /**
     *
     * @param vegQuedan
     */
    public void setVegQuedan(int vegQuedan) {
        this.vegQuedan = vegQuedan;
    }

    /**
     *
     * @return
     */
    public boolean actualiza() {
        boolean hayPlantas, veganoEncontrado = false;
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
                        if ((entidad.getTurno() % 2) == (contador % 2) && (entidad.getTurno() != contador)) {
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
                            Principal.incrementarMagia(((Girasol) entidad).getMagiaGenera());
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
     *
     * @param cantidad
     */
    public void spawnVeganos(int cantidad) {
        int altoAleatorio;
        for (int i = 0; i < cantidad; i++) {
            altoAleatorio = (int) (Math.random() * (alto));
            terreno[altoAleatorio][ancho - 1].getEntidades().add(new Vegano(5, 1, contador));
        }
        vegQuedan -= cantidad;
    }
}
