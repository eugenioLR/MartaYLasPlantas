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
<<<<<<< HEAD
        /*Menu manu = new Menu();
        pg = new PrincipalGraficos(false , new Tablero(5,9));
=======

        Menu manu = new Menu();
        pg = new PrincipalGraficos(false, new Tablero(5, 9));
>>>>>>> ef3d850cd14473e82d031d3517ba00ef73f93160
        manu.setVisible(true);
        pg.____();*/
        Jugador kfc = new Jugador("345445V", "Chicken", false);
        kfc.crearFicha();
    }

    public static void tableroVisible() {
        pg.setVisible(true);
    }

}
