/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class Ejecutars {

    private static PrincipalGraficos pg;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Menu manu = new Menu();
        manu.setSize(new Dimension(557,450));
        pg = new PrincipalGraficos(new Tablero(5, 9));
        manu.setVisible(true);
        pg.jugar();
        int reply = JOptionPane.showConfirmDialog(null, "Volver al menu", "yoot",  JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            manu.dispose();
            pg.dispose();
            main(args);
        }else{
            System.exit(0);
        }
    }

    public static void tableroVisible() {
        pg.setVisible(true);
    }

}
