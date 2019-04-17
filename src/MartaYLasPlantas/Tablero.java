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
    private int c;//contador

    public Tablero(int ancho, int alto) {
        terreno = new Casilla[alto][ancho];
    }

    public Casilla[][] getTerreno() {
        return terreno;
    }

    public boolean actualiza() {
        boolean veganoEncontrado;
        int vegTablero = 0;
        int vegQuedan = 15;
        int danioCausado;

        if (c > 5) {
            //aparecen zombies
            for (int i = 0; i < 0; i++) {
                veganoEncontrado = false;
                danioCausado = 0;
                for (int j = 0; j < 0; j++) {
                    terreno[i][j].actualizar();
                    String className = terreno[i][j].getClass().getSimpleName();
                    if (className.equals("Vegano")) {
                        vegTablero++;
                        
                    }
                    if (className.equals("Lanzadora")) {
                        danioCausado += terreno[i][j].getAtaque();

                    }
                    if (className.equals("Vegano") && !veganoEncontrado) {
                        terreno[i][j].reducirSalud(danioCausado);
                        // si no se han encontrado plantas, se reduce en 0 la salud
                    }
                }
            }
        }
        return ((vegTablero == 0) && (vegQuedan == 0));
    }
}
