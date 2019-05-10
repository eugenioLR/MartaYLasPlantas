/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.util.ArrayList;
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
    private static int alto, ancho, x, y, dificultad, magia = 0;
    private static Tablero tablero;

    public static void main(String[] args) {
        boolean jugando = true;
        boolean comprobando = true;
        boolean puedePlantar;
        String comando, tokens[];
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> hashDificultad = new HashMap<>();
        hashDificultad.put("BAJA", 0);
        hashDificultad.put("MEDIA", 1);
        hashDificultad.put("ALTA", 2);
        hashDificultad.put("IMPOSIBLE", 3);
        magia = Integer.MAX_VALUE / 2;
        while (comprobando) {
            try {
                comprobando = false;
                comando = scanner.nextLine();
                tokens = comando.split(" ");
                if (!(tokens.length == 4)) {
                    throw new ExcepcionJuego("Número incorrecto de argumentos.");
                }
                if (!tokens[0].equals("N")) {
                    throw new ExcepcionJuego("Para inicializar el tablero introduce: N (alto) (ancho) (dificultad).");
                }
                alto = Integer.parseInt(tokens[1]);
                ancho = Integer.parseInt(tokens[2]);
                if (!hashDificultad.containsKey(tokens[3])) {
                    throw new ExcepcionJuego("Dificultad incorrecta: prueba con BAJA, MEDIA, DIFICIL O IMPOSIBLE.");
                }
                dificultad = hashDificultad.get(tokens[3]);
            } catch (NumberFormatException nfe) {
                System.out.println("Error al procesar los argumentos: " + nfe);
                comprobando = true;
            } catch (ExcepcionJuego ej) {
                System.out.println("Error en el comando: " + ej);
                comprobando = true;
            }
        }
        tablero = new Tablero(alto, ancho);
        System.out.println("Comienza la partida.");
        //programa principal
        while (jugando) {
            ararTerreno();
            tablero.incrementarContador();
            comprobando = true;

            while (comprobando) {
                try {
                    puedePlantar = true;
                    comprobando = false;
                    System.out.print(">");
                    comando = scanner.nextLine();
                    tokens = comando.split(" ");
                    if (tokens.length != 3) {
                        if (comando.equals("")) {
                            break;
                        } else if (comando.equals("ayuda")) {
                            System.out.println("<enter> -> Saltar un turno.\n"
                                    + "S -> Salir.\n"
                                    + "L <x> <y> -> Lanzaguisantes en x y, ataca al primer zombie de la linea en la que esté.\n"
                                    + "G <x> <y> -> Girasól en x y, genera magia.");
                        } else if (tokens[0].charAt(0) != 'S') {
                            throw new ExcepcionJuego("Numero incorrecto de argumentos.");
                        }
                    } else {
                        y = Integer.parseInt(tokens[1]) - 1;
                        x = Integer.parseInt(tokens[2]) - 1;
                        if ((y >= tablero.getAlto() || y < 0) || (x >= tablero.getAncho() || x < 0)) {
                            throw new ExcepcionPlanta("Posición fuera del tablero.");
                        }
                        for (Entidad entidad : tablero.getTerreno()[y][x].getEntidades()) {
                            if (puedePlantar) {
                                puedePlantar = !(entidad instanceof Planta);
                            }
                        }
                    }
                    switch (tokens[0].charAt(0)) {
                        case 'G':
                            if (magia < Girasol.getCoste()) {
                                throw new ExcepcionPlanta("Magia insuficiente.");
                            } else if (!puedePlantar) {
                                throw new ExcepcionPlanta("Ya hay una planta en esa posición.");
                            } else {
                                tablero.colocarEntidad(new Girasol(5, 2, 8, tablero.getContador()), x, y);
                                magia -= Girasol.getCoste();
                            }
                            break;
                        case 'L':
                            if (magia < Lanzadora.getCoste()) {
                                throw new ExcepcionPlanta("Magia insuficiente.");
                            } else if (!puedePlantar) {
                                throw new ExcepcionPlanta("Ya hay una planta en esa posición.");
                            } else {
                                tablero.colocarEntidad(new Lanzadora(5, 1), x, y);
                                magia -= Lanzadora.getCoste();
                            }
                            break;
                        case 'S':
                            System.exit(0);
                        case 'a':
                            comprobando = true;
                            break;
                        default:
                            throw new ExcepcionPlanta("No existe ese comando, escriba \"ayuda\" para obtener la lista de comandos.");
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Error al procesar los argumentos: " + nfe);
                    comprobando = true;
                } catch (ExcepcionPlanta ep) {
                    System.out.println("Error al plantar: " + ep);
                    comprobando = true;
                } catch (ExcepcionJuego ej) {
                    System.out.println("Error en el comando: " + ej);
                    comprobando = true;
                }
            }
            generarVeganos();
            jugando = tablero.actualiza();
        }
        boolean pierdes = false;
        for (Casilla[] fila : tablero.getTerreno()) {
            for (Casilla casilla : fila) {
                for (Entidad entidad : casilla.getEntidades()) {
                    if (pierdes = entidad instanceof Vegano) {
                        break;
                    }
                }
            }
        }
        if(pierdes){
            System.out.println("Pero que pringao.");
        }else{
            System.out.println("OK.");
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

    public static void generarVeganos(){
        //todas esas vainas
    }
    
    public static void ararTerreno() {
        int sumaVida;
        String vidas;
        String strCasilla;
        int espacios = 17, k = 0;
        Casilla[][] terreno = tablero.getTerreno();
        HashMap<String, ArrayList<Integer>> dasdas = new HashMap<>();
        dasdas.put("G", new ArrayList<>());
        dasdas.put("L", new ArrayList<>());
        dasdas.put("V", new ArrayList<>());
        for (Casilla[] fila : terreno) {
            System.out.print(" |");
            for (int i = 0; i < espacios - 1; i++) {
                System.out.print("-");
            }

            for (int i = 0; i < ancho - 1; i++) {
                for (int j = 0; j < espacios; j++) {
                    System.out.print("-");
                }
            }
            System.out.println("|");
            if (tablero.hayCortacesped(k)) {
                System.out.print("C");
            } else {
                System.out.print("_");
            }
            k++;
            for (Casilla posicion : fila) {
                strCasilla = "";
                vidas = "";
                sumaVida = 0;
                dasdas.get("V").clear();
                dasdas.get("L").clear();
                dasdas.get("G").clear();
                System.out.print("|");

                for (Entidad ent : posicion.getEntidades()) {

                    if (ent instanceof Vegano) {
                        dasdas.get("V").add(ent.getSalud());
                    } else if (ent instanceof Lanzadora) {
                        dasdas.get("L").add(ent.getSalud());
                    } else if (ent instanceof Girasol) {
                        dasdas.get("G").add(ent.getSalud());
                    }
                }

                if (!dasdas.get("L").isEmpty()) {
                    strCasilla += "L(" + dasdas.get("L").get(0) + ")";
                }
                if (!dasdas.get("G").isEmpty()) {
                    strCasilla += "G(" + dasdas.get("G").get(0) + ")";
                }
                if (!dasdas.get("V").isEmpty()) {
                    strCasilla += "V(";
                    for (int i = 0; i < dasdas.get("V").size(); i++) {
                        if (i < 4) {
                            vidas += dasdas.get("V").get(i);
                            if (dasdas.get("V").size() > 1 && i < dasdas.get("V").size() - 1) {
                                vidas += ",";
                            }
                        } else {
                            sumaVida += dasdas.get("V").get(i);
                        }
                    }
                    if (sumaVida > 0) {
                        strCasilla += vidas + "" + sumaVida + ")";
                    } else {
                        strCasilla += vidas + ")";
                    }
                }

                while (strCasilla.length() < espacios - 1) {
                    strCasilla += " ";
                }
                System.out.print(strCasilla);
            }

            System.out.println("|");
        }
        System.out.print(" |");
        for (int i = 0; i < espacios - 1; i++) {
            System.out.print("-");
        }
        for (int i = 0; i < ancho - 1; i++) {
            for (int j = 0; j < espacios; j++) {
                System.out.print("-");
            }
        }
        System.out.println("|");
        System.out.println("magia:" + magia);

    }
}
