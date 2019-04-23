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
public class Vegano extends Casilla {

    private int velocidad;
    private int c;//contador

    public Vegano(int salud, int ataque, int velocidad) {
        super(salud, ataque);
        this.velocidad = velocidad;
    }

    /**
     * Get the value of velocidad
     *
     * @return the value of velocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Set the value of velocidad
     *
     * @param velocidad new value of velocidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public void actualizar() {
        c++;
        if(c % 2 == 0){
            
        }

    }
}
