/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas.Plantas;

/**
 *
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class Girasol extends Planta {

    private static int coste = 20;
    private int magiaGenera = 10;

    /**
     * Constructor de Girasol
     * {@inheritDoc}
     */
    public Girasol(int turno) {
        super(1, 0, turno);
    }

    /**
     * Get the value of magiaGenera
     *
     * @return the value of magiaGenera
     */
    public int getMagiaGenera() {
        return magiaGenera;
    }

    /**
     * Get the value of coste
     *
     * @return the value of coste
     */
    public static int getCoste() {
        return coste;
    }
}
