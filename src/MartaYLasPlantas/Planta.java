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
public abstract class Planta extends Casilla {
    
    private int coste;
    
    public Planta(int salud, int ataque, int coste) {
        super(salud, ataque);
        this.coste = coste;
    }    

}
