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
public class Girasol extends Planta {

    private static int coste = 20;
    private int magiaGenera;

    /**
     *
     * @param salud
     * @param ataque
     * @param turno
     * @param magiaGenera
     */
    public Girasol(int salud, int ataque, int turno, int magiaGenera) {
        super(salud, ataque, turno);
        this.magiaGenera = magiaGenera;
    }

    /**
     *
     * @return magiaGenera
     */
    public int getMagiaGenera() {
        return magiaGenera;
    }

    /**
     *
     * @return coste
     */
    public static int getCoste() {
        return coste;
    }
}
