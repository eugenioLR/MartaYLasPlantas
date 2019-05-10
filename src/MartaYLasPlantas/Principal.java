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
    private static int alto, ancho, dificultad, magia = 0;
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
        //magia = 50;
        magia = Integer.MAX_VALUE;
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

        //programa principal
        while (jugando) {
            tablero.incrementarContador();
            comprobando = true;
            while (comprobando) {
                try {
                    puedePlantar = true;
                    comprobando = false;
                    comando = scanner.nextLine();
                    tokens = comando.split(" ");
                    if (tokens.length != 3) {
                        if (comando.equals("")) {
                            break;
                        } else if (tokens[0].charAt(0) != 'S') {
                            throw new ExcepcionJuego("Numero incorrecto de argumentos.");
                        }
                    } else {
                        alto = Integer.parseInt(tokens[1]) - 1;
                        ancho = Integer.parseInt(tokens[2]) - 1;
                        if ((alto >= tablero.getAlto() || alto < 0) || (ancho >= tablero.getAncho() || ancho < 0)) {
                            throw new ExcepcionPlanta("Posición fuera del tablero.");
                        }
                        for (Entidad entidad : tablero.getTerreno()[alto][ancho].getEntidades()) {
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
                                tablero.colocarEntidad(new Girasol(5, 2, 8, tablero.getContador()), ancho, alto);
                                magia -= Girasol.getCoste();
                            }
                            break;
                        case 'L':
                            if (magia < Lanzadora.getCoste()) {
                                throw new ExcepcionPlanta("Magia insuficiente.");
                            } else if (!puedePlantar) {
                                throw new ExcepcionPlanta("Ya hay una planta en esa posición.");
                            } else {
                                tablero.colocarEntidad(new Lanzadora(5, 1), ancho, alto);
                                magia -= Lanzadora.getCoste();
                            }
                            break;
                        case 'S':
                            System.exit(0);
                        default:
                            throw new ExcepcionPlanta("No existe ese comando, pruebe con L o G, para salir introduzca S");
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

    /*public static void ararCutre() {
        Entidad entidad;
        Casilla[][] terreno = tablero.getTerreno();
        for (Casilla[] fila : terreno) {
            for (Casilla casilla : fila) {
                System.out.print("|");
                casilla.getEntidades().forEach((ent) -> {
                    if (ent == null) {
                        System.out.print("N");
                    } else {
                        System.out.print(((String) (ent.getClass().getSimpleName())).charAt(0) + "" + ent.getSalud());
                    }
                });
            }
            System.out.println();
        }
        System.out.println(magia);
    }*/
    public static void ararTerreno() {
        int sumaVida = 0;
        String vidas = "";
        String strCasilla = "";
        int espacios = 14;
        Casilla[][] terreno = tablero.getTerreno();
        HashMap<String, ArrayList<Integer>> dasdas = new HashMap<>();
        dasdas.put("G", new ArrayList<>());
        dasdas.put("L", new ArrayList<>());
        dasdas.put("V", new ArrayList<>());

        for (Casilla[] fila : terreno) {
            System.out.print("|-------------");
            for (int i = 0; i < ancho - 1; i++) {
                System.out.print("--------------");
                //               "|V(4)  "
            }
            System.out.println("|");

            for (Casilla posicion : fila) {
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
                    strCasilla += "L(" + dasdas.get("V").get(0) + ")";
                } else if (!dasdas.get("G").isEmpty()) {
                    strCasilla += "G(" + dasdas.get("V").get(0) + ")";
                } else if (!dasdas.get("V").isEmpty()) {
                    strCasilla += "V(";
                    for (int i = 0; i < dasdas.get("V").size(); i++) {
                        if (i < 4) {
                            vidas = "" + dasdas.get("V").get(i) + ",";
                        } else {
                            if (i >= 4) {
                                sumaVida += dasdas.get("V").get(i);
                            }
                        }
                    }
                    strCasilla += "" + vidas + "" + sumaVida + ")";

                    while (strCasilla.length() < espacios) {
                        strCasilla += " ";
                    }
                    System.out.print(strCasilla);
                }
                System.out.println("|");

                /*
                if (posicion.getEntidades().isEmpty()) {
                    System.out.print("|      ");
                } else if (dasdas.)
                        (posicion.getEntidades().contains("Vegano")&&
                    (posicion.getEntidades().contains("Girasol")) || 
                    (posicion.getEntidades().contains("Lanzadora"))){
                    while (posicion.getEntidades().iterator().hasNext()) {
                        
                        System.out.println("|V()");
                        
                    }
                }
                 */
            }
        }
    }
}

/*
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
            System.out.println("magia: " + magia + " turno: " + tablero.getContador());
        }*/
class ExcepcionPlanta extends Exception {

    public ExcepcionPlanta(String message) {
        super(message);
    }
}

class ExcepcionJuego extends Exception {

    public ExcepcionJuego(String message) {
        super(message);
    }

}
