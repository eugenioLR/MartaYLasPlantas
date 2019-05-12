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
     *{@inheritDoc}
     * @param magiaGenera cantidad de magia a generar.
     * <br> el ataque será 0 ya que los girasoles no atacan <br>
     */
    public Girasol(int salud, int turno, int magiaGenera) {
        super(salud, 0, turno);
        this.magiaGenera = magiaGenera;
    }

    /**
     *
     * @return cantidad de magia a generar una vez llamada la funcion
     */
    public int getMagiaGenera() {
        return magiaGenera;
    }

    /**
     *
     * @return coste en magia de plantar esta planta 
     */
    public static int getCoste() {
        return coste;
    }
}
