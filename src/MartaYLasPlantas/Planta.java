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
    
    private static int coste;
    
    public Planta(int salud, int ataque, int turno) {
        super(salud, ataque, turno);
    }    

    public static int getCoste() {
        return coste;
    }
}
