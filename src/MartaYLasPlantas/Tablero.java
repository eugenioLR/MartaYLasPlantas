/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

/**
 *
 * @author EDGENP
 */
public class Tablero {

    // NUESTRO METODO PINTARTABLERO(), SE VA A LLAMAR ArarTerreno()
    //MAGIA!!!! MAGIA!!!! MAGIA!!!!
    private Casilla terreno[][];
    private boolean cortacesped[];
    private int contador, ancho, alto;
    private int vegQuedan = -1;

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

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public boolean[] getCortacesped() {
        return cortacesped;
    }

    public boolean hayCortacesped(int pos) {
        return cortacesped[pos];
    }

    public void quitarCortacesped(int pos) {
        this.cortacesped[pos] = false;
    }

    public void incrementarContador() {
        this.contador++;
    }

    public void colocarEntidad(Entidad entidad, int y, int x) {
        terreno[y][x].getEntidades().add(entidad);
    }

    public Casilla[][] getTerreno() {
        return terreno;
    }

    public void setVegQuedan(int vegQuedan) {
        this.vegQuedan = vegQuedan;
    }

    public boolean actualiza() {
        boolean hayPlantas, veganoEncontrado = false;
        int vegTablero;
        Entidad entidad;

        vegTablero = 0;
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {

                for (int k = 0; k < terreno[i][j].getEntidades().size(); k++) {
                    entidad = terreno[i][j].getEntidades().get(k);
                    hayPlantas = false;

                    if (entidad instanceof Vegano) {
                        vegTablero++;
                        if ((entidad.getTurno() % 2) == (contador % 2) && (entidad.getTurno() != contador)) {
                            for (Entidad ent : terreno[i][j].getEntidades()) {
                                if (ent instanceof Planta) {
                                    ent.reducirSalud(ent.getAtaque());
                                    hayPlantas = true;
                                }
                            }

                            if (!hayPlantas) {
                                if (j > 0) {
                                    terreno[i][j].quitarEntidad(entidad);
                                    terreno[i][j - 1].insertarEntidad(entidad);
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

                    if (entidad instanceof Lanzadora) {
                        for (int l = j; l < terreno[0].length; l++) {
                            for (Entidad ent : terreno[i][l].getEntidades()) {
                                if ((veganoEncontrado = ent instanceof Vegano)) {
                                    ent.reducirSalud(entidad.getAtaque());
                                    break;
                                }
                            }
                            if (veganoEncontrado) {
                                break;
                            }
                        }
                    }

                    if (entidad instanceof Girasol) {
                        if ((entidad.getTurno() % 2) == (contador % 2)) {
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

    public void spawnVeganos(int cantidad) {
        int altoAleatorio;
        for (int i = 0; i < cantidad; i++) {
            altoAleatorio = (int) (Math.random() * (alto));
            terreno[altoAleatorio][ancho - 1].getEntidades().add(new Vegano(5, 1, contador));
        }
        vegQuedan -= cantidad;
    }
}
