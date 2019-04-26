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

    private int turno;

    public Vegano(int salud, int ataque, int turno) {
        super(salud, ataque, turno);
    }

    /**
     * Get the value of turno
     *
     * @return the value of turno
     */
    public int getVelocidad() {
        return turno;
    }

    /**
     * Set the value of turno
     *
     * @param turno new value of turno
     */
    public void setVelocidad(int turno) {
        this.turno = turno;
    }

    @Override
    public void actualizar() {
        turno++;
        if(turno % 2 == 0){
            
        }
    }
}
