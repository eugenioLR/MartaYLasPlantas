/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author EDGENP
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    private static int alto, ancho, dificultad, magia = 0;
    private static Tablero tablero;

    public static void main(String[] args) {
        /*
        alto = 12;
        ancho = 12;
        tablero = new Tablero(12, 12);
        Lanzadora l1 = new Lanzadora((int)Math.PI,2);
        Vegano v1 = new Vegano(2,3,3);
        Girasoles g1 = new Girasoles(2,9,8);
        tablero.colocarCasilla(l1, 0, 1);
        tablero.colocarCasilla(v1, 1, 3);
        tablero.colocarCasilla(g1, 3, 6);ner(System.in);
        HashMap<String, Integer> hashDificultad = new HashMap<>(); 
        hashDificultad.put("BAJA", 0);
        hashDificultad.put("MEDIA", 1);
        hashDificultad.put("ALTA", 2);
        hashDificultad.put("IMPOSIBLE", 3);
        while (comprobando) {
        
         */

        boolean jugando = true;
        boolean comprobando = true;
        String comando, tokens[];
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> hashDificultad = new HashMap<>();
        hashDificultad.put("BAJA", 0);
        hashDificultad.put("MEDIA", 1);
        hashDificultad.put("ALTA", 2);
        hashDificultad.put("IMPOSIBLE", 3);
        magia = 50;
        while (comprobando) {
            try {
                comprobando = false;
                comando = scanner.nextLine();
                tokens = comando.split(" ");
                if (!(tokens.length == 4)) {
                    throw new EresMongolo("numero incorrecto de argumentos");
                }
                if (!tokens[0].equals("N")) {
                    throw new EresMongolo("No es una n, primera advertencia");
                }
                alto = Integer.parseInt(tokens[1]);
                ancho = Integer.parseInt(tokens[2]);
                if(!hashDificultad.containsKey(tokens[3])){
                    throw new EresMongolo("dificultad incorrecta");
                }
                dificultad = hashDificultad.get(tokens[3]);
            } catch (NumberFormatException e) {
                System.out.println("jaja pringao, eres un inutil. " + e);
                comprobando = true;
            } catch (EresMongolo E) {
                System.out.println(E);
                comprobando = true;
            }
        }
        tablero = new Tablero(alto, ancho);

        //programa principal
        while (jugando) {
            ararTerreno();
            tablero.incrementarContador();
            comprobando = true;
            while (comprobando) {
                try {
                    comprobando = false;
                    comando = scanner.next();
                    tokens = comando.split(" ");
                    alto = Integer.parseInt(tokens[1]);
                    ancho = Integer.parseInt(tokens[2]);
                    if (!(tokens.length == 4)) {
                        throw new EresMongolo("numero incorrecto de argumentos");
                    }
                    if ((alto > tablero.getAlto() - 1 || alto < 0)
                            || ancho > tablero.getAncho() - 1 || ancho < 0) {
                        throw new EresMongolo("fuera del tablero");
                    }
                    switch (tokens[0].charAt(0)) {
                        case 'G':
                            if (magia >= Girasol.getCoste()) {
                                tablero.colocarCasilla(new Girasol(0, 2, 3, tablero.getContador()), alto, ancho);
                                magia -= Girasol.getCoste();
                            }else{
                                throw new EresMongolo("magia insuficientes");
                            }
                            break;
                        case 'L':
                            if (magia >= Lanzadora.getCoste()) {
                                tablero.colocarCasilla(new Lanzadora(0, 1), alto, ancho);
                                magia -= Lanzadora.getCoste();
                            }else{
                                throw new EresMongolo("magia insuficientes");
                            }
                            break;
                        case 'S':
                            jugando = false;
                            break;
                        case ' ':
                            break;
                        default:
                            throw new EresMongolo("eres mongolo");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("jaja pringao, eres un inutil. " + e);
                    comprobando = true;
                } catch (EresMongolo em) {
                    System.out.println(em);
                    comprobando = true;
                }
            }
            tablero.actualiza();
        }
    }

    public static void incrementarMagia(int cantidad) {
        magia += cantidad;
    }

    public static void disminuirMagia(int cantidad) {
        magia -= cantidad;
    }

    public static int getDificultad() {
        return dificultad;
    }

    public static void ararTerreno() {
        Casilla casilla, terreno[][] = tablero.getTerreno();
        for (Casilla[] fila : terreno) {
            System.out.print("|------");
            for (int i = 0; i < ancho - 1; i++) {
                System.out.print("-------");
                //                             "|V(4)  "
            }
            System.out.println("|");
            for (Casilla posicion : fila) {
                if (posicion == null) {
                    System.out.print("|      ");
                } else if (posicion instanceof Vegano) {
                    System.out.print("|V(" + posicion.getSalud() + ")  ");
                } else if (posicion instanceof Lanzadora) {
                    System.out.print("|L(" + posicion.getSalud() + ")  ");
                } else if (posicion instanceof Girasol) {
                    System.out.print("|G(" + posicion.getSalud() + ")  ");
                }
            }
            System.out.println("|");
        }
        System.out.print("|------");
        for (int i = 0; i < ancho - 1; i++) {
            System.out.print("-------");
            //               "|V(4)  "
        }
        System.out.println("|");
        System.out.println("magia: "+magia+" turno: "+ tablero.getContador());
    }
}

class EresMongolo extends Exception {

    public EresMongolo(String message) {
        super(message);
    }

}
