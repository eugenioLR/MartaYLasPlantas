/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

/**
 *
 * @author EDGENP
 *         Eugenio Lorente
 *         Darius Tamas
 */
public abstract class Entidad {

    private int salud, ataque, turno;
    private static int dificultad;

    /**
     * Constructor de Entidades
     * @param salud
     * @param ataque
     * @param turno
     */
    public Entidad(int salud, int ataque, int turno) {
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

    /**
     * Incrementa el contador propio de cada entidad
     *
     */
    public void incrementarTurno() {
        this.turno++;
    }

    /**
     * Get the value of salud
     *
     * @return the value of salud
     */
    public int getSalud() {
        return salud;
    }

    /**
     * Set the value of salud
     *
     * @param salud new value of salud
     */
    public void setSalud(int salud) {
        this.salud = salud;
    }

    /**
     * Quita tanta salud como cantidad introducida
     *
     * @param cantidad
     */
    public void reducirSalud(int cantidad) {
        this.salud -= cantidad;
    }

    /**
     * Get the value of ataque
     *
     * @return the value of ataque
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Set the value of ataque
     *
     * @param ataque new value of ataque
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    /**
     * Get the value of dificultad
     *
     * @return the value of dificultad
     */
    public int getDificultad() {
        return dificultad;
    }
}
