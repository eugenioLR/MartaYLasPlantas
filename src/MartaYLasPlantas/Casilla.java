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

    private int salud, ataque, posicion[] = new int[2], turno;
    private static int dificultad = 1; // pide que se inicialice 

    public Casilla(int salud, int ataque, int turno) {
        this.salud = salud;
        this.ataque = ataque;
        this.turno = turno;
    }

    /**
     * Get the value of turno
     *
     * @return the value of turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Set the value of turno
     *
     * @param turno new value of turno
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void reducirSalud(int cantidad) {
        this.salud -= cantidad;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int[] getPosicion() {
        return posicion;
    }

    public void setPosicion(int[] posicion) {
        this.posicion = posicion;
    }

    public int getDificultad() {
        return dificultad;
    }

    public abstract void actualizar();
}
