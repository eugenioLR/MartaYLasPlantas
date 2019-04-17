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
public abstract class Casilla {
	//hhi darius, if you are reading this U SUCC.
    private int salud, ataque;
    private final int dificultad;

    public Casilla(int salud, int ataque, int dificultad ) {
        this.salud = salud;
        this.ataque = ataque;
        this.dificultad = dificultad;
    }
    
    
    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }
    
    public void reducirSalud(int cantidad){
        this.salud -= cantidad;
    }
    
    public int getAtaque() {
        return ataque;
    }
    
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    // dificultad no necesita ni get ni set.

    public abstract void actualizar();
}
