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
    private int c, ancho, alto, k;//contador
    private int turnos = 30;
    private int vegQuedan = 0;

    public Tablero(int ancho, int alto) {
        this.alto = alto;
        this.ancho = ancho;
        terreno = new Casilla[alto][ancho];
        cortacesped = new boolean[alto];
        for (int i = 0; i<alto;i++) {
            cortacesped[i] = true;
        }

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                terreno[i][j] = new Casilla();
            }
        }

        c = 0;
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
        return c;
    }

    public void setContador(int c) {
        this.c = c;
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
        this.c++;
    }

    public void colocarEntidad(Entidad entidad, int x, int y) {
        terreno[y][x].getEntidades().add(entidad);
    }

    public Casilla[][] getTerreno() {
        return terreno;
    }

    public boolean actualiza() {
        boolean hayPlantas, hayVeganos, veganoEncontrado = false;
        int vegTablero = 0;
        int dificultad = Principal.getDificultad();
        Entidad entidad;
        double prob, prioridad = 0.0;
        int auxx = vegQuedan, vegAux = 0;

        if (c > 5) {
            if (auxx != vegQuedan) {
                k = 0;
                prioridad = 0.0;
            }
            //for (int i = 0, k = 0, turnos = 30; turnos > i; turnos--, k++) {
            //comprobando si han aparecido veganos

            /* mecanismo de preemption, 
                 * si han pasado mas de 6 turnos sin que aparezcan veganos 
                 * la probabilidad de que aparezcan es mayor por una cantidad aleatoria
             */
            if (k > 6) {
                prioridad = Math.random() / 2;
            }
            auxx = vegQuedan;
            // Calculando la probabilidad de que un vegano aparezca
            prob = ((double) vegQuedan) / ((double) turnos);

            if (vegQuedan > 0 && c < 30) {
                /* Turnos en los que los veganos aparecen 
                     * y cuantos veganos aparecen dependiendo de la probabilidad
                 */
                if (prob + prioridad >= 1.00) {
                    vegAux = 3 + (int) (prob + prioridad);
                    spawnVeganos(vegAux);
                } else if (prob + prioridad >= 0.85) {
                    vegAux = 3;
                    spawnVeganos(vegAux);
                } else if (prob + prioridad >= 0.7) {
                    vegAux = 2;
                    spawnVeganos(vegAux);
                } else if (prob + prioridad >= 0.5) {
                    vegAux = 1;
                    spawnVeganos(vegAux);
                }
                vegQuedan -= vegAux;
            }
        }
        vegTablero = 0;

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {

                for (int k = 0; k < terreno[i][j].getEntidades().size(); k++) {
                    entidad = terreno[i][j].getEntidades().get(k);
                    hayPlantas = false;
                    hayVeganos = false;

                    if (entidad instanceof Vegano) {
                        entidad.actualizar();
                        vegTablero++;
                        if ((entidad.getTurno() % 2) == (c % 2)) {
                            for (Entidad ent : terreno[i][j].getEntidades()) {
                                if (ent instanceof Planta) {
                                    ent.reducirSalud(ent.getAtaque());
                                    hayPlantas = true;
                                }
                            }

                            if (!hayPlantas) {
                                if (j > 0) {
                                    terreno[i][j].getEntidades().remove(entidad);
                                    terreno[i][j - 1].getEntidades().add(entidad);
                                } else if (hayCortacesped(i)) {
                                    for(int l = 0; l<ancho;l++){
                                        destruirCasilla(terreno[i][l]);
                                    }
                                    quitarCortacesped(i);
                                } else {
                                    //System.exit(0);
                                    return false;
                                }
                            }
                        }
                    }

                    if (entidad instanceof Lanzadora) {
                        for (int l = j; l < terreno.length; l++) {
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
                        Principal.incrementarMagia(((Girasol) entidad).getMagia());
                    }

                }

                limpiarCasilla(terreno[i][j]);
            }
        }

        c++;
        k++;
        if (turnos > 0) {
            turnos--;
        }
        return !((vegTablero == 0) && (vegQuedan == 0));//ganar
    }

    public void limpiarCasilla(Casilla casilla) {
        Entidad entidad;
        for (int i = 0; i < casilla.getEntidades().size(); i++) {
            entidad = casilla.getEntidades().get(i);
            if (entidad.getSalud() < 1) {
                casilla.getEntidades().remove(entidad);
            }
        }
    }
    public void destruirCasilla(Casilla casilla){
        Entidad entidad;
        casilla.getEntidades().clear();
    }

    public void spawnVeganos(int cantidad) {
        int altoAleatorio;
        /*
        while (entidades > 0) {
            entidades--;
         */
        for (int i = 0; i < cantidad; i++) {
            altoAleatorio = (int) (Math.random() * alto);
            terreno[altoAleatorio][ancho - 1].getEntidades().add(new Vegano(5, 1, c));
        }
    }
}
