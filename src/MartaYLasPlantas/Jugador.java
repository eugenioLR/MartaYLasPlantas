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
public class Jugador {

    private String dni;
    private String nombre;
    private long puntuacion;
    private boolean partidaCreada;
    private int[] partidasGanadas;
    private int[] partidasPerdidas;

    public Jugador(String dni, String nombre, long puntuacion, boolean partidaCreada) {
        this.dni = dni;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.partidaCreada = partidaCreada;
        partidasPerdidas = new int[4];
        partidasGanadas = new int[4];
    }

    /**
     * Get the value of partidasPerdidas
     *
     * @return the value of partidasPerdidas
     */
    public int[] getPartidasPerdidas() {
        return partidasPerdidas;
    }

    /**
     * Set the value of partidasPerdidas
     *
     * @param partidasPerdidas new value of partidasPerdidas
     */
    public void setPartidasPerdidas(int[] partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    /**
     * Get the value of partidasGanadas
     *
     * @return the value of partidasGanadas
     */
    public int[] getPartidasGanadas() {
        return partidasGanadas;
    }

    /**
     * Set the value of partidasGanadas
     *
     * @param partidasGanadas new value of partidasGanadas
     */
    public void setPartidasGanadas(int[] partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    /**
     * Get the value of dni
     *
     * @return the value of dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of dni
     *
     * @param dni new value of dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of puntuacion
     *
     * @return the value of puntuacion
     */
    public long getPuntuacion() {
        return puntuacion;
    }

    /**
     * Set the value of puntuacion
     *
     * @param puntuacion new value of puntuacion
     */
    public void setPuntuacion(long puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Get the value of partidaCreada
     *
     * @return the value of partidaCreada
     */
    public boolean isPartidaCreada() {
        return partidaCreada;
    }

    /**
     * Set the value of partidaCreada
     *
     * @param partidaCreada new value of partidaCreada
     */
    public void setPartidaCreada(boolean partidaCreada) {
        this.partidaCreada = partidaCreada;
    }

    public void crearFicha(String nif) {

    }

    public void guardarPartida(Tablero tablero, int magia) {

    }

    public void cargarPartida() {

    }
    
    public void actualizarFicha() {

    }

    @Override
    public String toString() {
        return "Jugador{" + "dni=" + dni + ", nombre=" + nombre + ", puntuacion=" + puntuacion + "}";
    }
}   
