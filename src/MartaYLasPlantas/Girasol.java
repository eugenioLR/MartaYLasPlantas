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
     * Constructor de Girasol
     * {@inheritDoc}
     * @param magiaGenera cantidad de magia a generar.
     * <br> el ataque será 0 ya que los girasoles no atacan <br>
     */
    public Girasol(int salud, int turno, int magiaGenera) {
        super(salud, 0, turno);
        this.magiaGenera = magiaGenera;
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
