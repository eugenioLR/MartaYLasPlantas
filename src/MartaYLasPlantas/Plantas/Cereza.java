/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas.Plantas;

/**
 *
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class Cereza extends Planta{

    
    /**
     * Constructor de Cereza
     * {@inheritDoc}
     */
    public Cereza(int turno) {
        super(2, 10, turno);
    }
    
    /**
     * Constuctor de Cereza Cargar partida
     * @param salud
     * @param turno 
     */
    public Cereza(int salud, int turno) {
        super(salud, 10, turno);
    }
    
    private static int coste = 50;
    
    public static int getCoste() {
        return coste;
    }       
}
