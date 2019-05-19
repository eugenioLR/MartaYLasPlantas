/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas.Veganos;

import MartaYLasPlantas.Entidad;

/**
 *
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class Vegano extends Entidad {

    private int espera;

    /**
     * Constructor de Veganos. 
     * {@inheritDoc}
     */
    public Vegano(int salud, int ataque, int turno, int espera) {
        super(salud, ataque, turno);
        this.espera = espera;
        
    }

    /**
     * Get the value of espera
     *
     * @return the value of espera
     */
    public int getEspera() {
        return espera;
    }

    /**
     * Set the value of espera
     *
     * @param espera new value of espera
     */
    public void setEspera(int espera) {
        this.espera = espera;
    }

}
