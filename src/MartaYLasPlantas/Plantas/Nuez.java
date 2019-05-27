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
public class Nuez extends Planta {

    private static int coste = 50;

    /**
     * Constructor de Nueces {@inheritDoc}
     */
    public Nuez() {
        super(10, 0, 0);
    }

    public Nuez(int salud) {
        super(salud, 0, 0);
    }

    public static int getCoste() {
        return coste;
    }
}
