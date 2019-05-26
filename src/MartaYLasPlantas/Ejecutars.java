/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

/**
 *
 * @author ACER
 */
public class Ejecutars {

    private static PrincipalGraficos pg;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu manu = new Menu();
        pg = new PrincipalGraficos(new Tablero(5, 9));
        manu.setVisible(true);
        pg.____();
        /*
        Jugador kfc = new Jugador("jfibb994", "mopa", false);
        kfc.crearFicha();*/
    }

    public static void tableroVisible() {
        pg.setVisible(true);
    }

}
