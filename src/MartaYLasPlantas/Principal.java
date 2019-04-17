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
public class Principal {

    /**
     * @param args the command line arguments
     */
    private static final int ALTO = 4;
    private static int ancho;
    
    public static void main(String[] args) {
        ancho = 7;
        Tablero terreno = new Tablero(ALTO, ancho);
        Casilla mat[][] = terreno.getTerreno(); 
        
    }
    
    public static void ararTerrenio(Tablero tb){
        
    }
    
}
