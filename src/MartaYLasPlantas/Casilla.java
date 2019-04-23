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
    private static int dificultad = 1; // pide que se inicialice 

    public Casilla(int salud, int ataque ) {
        this.salud = salud;
        this.ataque = ataque;
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

    public static int getDificultad() {
        return dificultad;
    }
    
    public abstract void actualizar();
}
