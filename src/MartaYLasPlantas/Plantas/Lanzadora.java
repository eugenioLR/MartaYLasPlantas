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
public class Lanzadora extends Planta {

    private static int coste = 50;

    /**
     *{@inheritDoc}
     * turno será 1 ya que no se usa, ataca cada turno
     */
    public Lanzadora() {
        super(3, 1, 1);
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
