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
    private int c, ancho, alto;//contador

    public Tablero(int ancho, int alto) {
        this.alto = alto;
        this.ancho = ancho;
        terreno = new Casilla[alto][ancho];
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

    public void incrementarContador() {
        this.c++;
    }

    public void colocarCasilla(Casilla casilla, int x, int y) {
        int posAux[] = {x, y};
        casilla.setPosicion(posAux);
        terreno[y][x] = casilla;
    }

    public Casilla[][] getTerreno() {
        return terreno;
    }

    public boolean actualiza() {
        boolean veganoEncontrado;
        int vegTablero = 0;
        int dificultad = Principal.getDificultad();
        int vegQuedan = 5;
        int danioCausado;
        int altoAleatorio;
        int altoAleatorio1;
        Casilla vegAux;
        double prioridad = 0.0;
        int auxx = vegQuedan;
        double prob;
        // anthony was here

        if (c > 5) {
            //aparecen zombies

            for (int i = 0, k = 0, turnos = 30; turnos > i; turnos--, k++) {
                if (auxx != vegQuedan) {
                    k = 0;
                    prioridad = 0.0;  //reset a la prioridad
                }
                if (k > 6) {
                    prioridad = Math.random() / 2;
                }
                auxx = vegQuedan;
                prob = ((double) vegQuedan) / ((double) turnos);

                if (vegQuedan > 0) {
                    //en que turnos aparecen los veganos
                    if (prob + prioridad >= 1.00) {
                        altoAleatorio = (int) (Math.random() * alto);
                        altoAleatorio1 = (int) (Math.random()* alto);
                        terreno[altoAleatorio][ancho - 1] = new Vegano(5, 5, c);
                        //terreno[]
                        //vegQuedan -= 3;
                        
                    } else if (prob + prioridad >= 0.85) {

                    } else if (prob + prioridad >= 0.7) {
                        
                    } else if (prob + prioridad >= 0.5){
                        
                    }

                }
            }
            vegTablero = 0;
            for (int i = 0; i < 0; i++) {
                veganoEncontrado = false;
                danioCausado = 0;
                for (int j = 0; j < 0; j++) {
                    terreno[i][j].actualizar();
                    String className = terreno[i][j].getClass().getSimpleName();
                    if (className.equals("Vegano")) {//accion vegano
                        vegTablero++;
                        if (j > 0 && (terreno[i][j].getTurno() % 2 == c % 2)) {
                            //moverse
                            if (terreno[i][j - 1] == null) {
                                vegAux = terreno[i][j];
                                terreno[i][j] = null;
                                terreno[i][j - 1] = vegAux;
                            } else if ((terreno[i][j - 1]).getClass().getSimpleName().equals("Lanzadora")
                                    || (terreno[i][j - 1]).getClass().getSimpleName().equals("Girasol")) {
                                terreno[i][j - 1].reducirSalud(terreno[i][j].getAtaque());
                            }
                        }

                        if (className.equals("Lanzadora")) {
                            danioCausado += terreno[i][j].getAtaque();
                        }

                        if (className.equals("Vegano") && !veganoEncontrado) {
                            terreno[i][j].reducirSalud(danioCausado);
                            // si no se han encontrado plantas, se reduce en 0 la salud
                        }
                        if (terreno[i][j] != null && terreno[i][j].getSalud() <= 0) {
                            terreno[i][j] = null;
                            if (className.equals("Vegano")) {
                                vegTablero--;
                            }
                        }
                    }
                }
            }
            c++;

        }
        return ((vegTablero == 0) && (vegQuedan == 0));
    }
}
