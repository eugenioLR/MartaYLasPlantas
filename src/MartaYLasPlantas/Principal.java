/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author EDGENP
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    private static int alto, ancho;
    private static Tablero tablero;

    public static void main(String[] args) {
        boolean jugando = true;
        boolean comprobando = true;
        String comando, tokens[];

        Scanner scanner = new Scanner(System.in);
        while (comprobando) {
            try {
                comprobando = false;
                comando = scanner.next();
                tokens = comando.split(" ");
                if (!tokens[0].equals("N")) {
                    throw new NumberFormatException();
                }
                alto = Integer.parseInt(tokens[1]);
                ancho = Integer.parseInt(tokens[2]);
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("jaja pringao, eres un inutil. ");
                comprobando = true;
            }
        }

        Tablero terreno = new Tablero(alto, ancho);
        Casilla mat[][] = terreno.getTerreno();

        while (jugando) {
            comprobando = true;
            while (comprobando) {
                try {
                    comprobando = false;
                    comando = scanner.next();
                    tokens = comando.split(" ");
                    switch (tokens[0].charAt(0)) {
                        case 'G':
                            break;
                        case 'L':
                            break;
                        case 'S':
                            break;
                        case ' ':
                            break;
                        default:
                            throw new EresMongolo("eres mongolo");
                    }
                    alto = Integer.parseInt(tokens[1]);
                    ancho = Integer.parseInt(tokens[2]);
                    throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.out.println("jaja pringao, eres un inutil. ");
                    comprobando = true;
                } catch (EresMongolo em) {
                }
            }
        }

    }

    public static void ararTerrenio() {

    }

}

class EresMongolo extends Exception {

    public EresMongolo(String message) {
        super(message);
    }

}
