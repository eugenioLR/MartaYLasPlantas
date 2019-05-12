/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

/**
 *
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class Lanzadora extends Planta {

    private static int coste = 50;

    /**
     *
     * @param salud
     * @param ataque
     */
    public Lanzadora(int salud, int ataque) {
        super(salud, ataque, 1);
    }

    /**
     *
     * @return coste
     */
    public static int getCoste() {
        return coste;
    }
}
