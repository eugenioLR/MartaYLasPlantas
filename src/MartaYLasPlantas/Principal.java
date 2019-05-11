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
    private static int alto, ancho, x, y, dificultad, magia = 0, vegQuedan, vegFinal = 0, turnosSinVeganos;
    private static Tablero tablero;

    public static void main(String[] args) {
        boolean jugando = true;
        boolean comprobando = true;
        boolean puedePlantar;
        String comando, tokens[];
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> hashDificultad = new HashMap<>();
        hashDificultad.put("BAJA", 1);
        hashDificultad.put("MEDIA", 2);
        hashDificultad.put("ALTA", 3);
        hashDificultad.put("IMPOSIBLE", 4);
        
        magia = 50;
        
        System.out.println("Si no sabes como comenzar escribe \"ayuda\".");
        while (comprobando) {
            try {
                comprobando = false;
                comando = scanner.nextLine();
                tokens = comando.split(" ");
                
                if (!(tokens.length == 4)) {
                    if (comando.equals("ayuda")) {
                        System.out.println("Para inicializar el tablero introduce: N (alto) (ancho) (dificultad)."
                                + "\nDificultades: BAJA, MEDIA, ALTA O IMPOSIBLE.");
                        comprobando = true;
                    } else {
                        throw new ExcepcionJuego("Número incorrecto de argumentos.");
                    }
                } else {
                    if (!tokens[0].equals("N")) {
                        throw new ExcepcionJuego("Comando incorrecto.");
                    }
                    alto = Integer.parseInt(tokens[1]);
                    ancho = Integer.parseInt(tokens[2]);
                    if (!hashDificultad.containsKey(tokens[3])) {
                        throw new ExcepcionJuego("Dificultad incorrecta: prueba con BAJA, MEDIA, ALTA O IMPOSIBLE.");
                    }
                    dificultad = hashDificultad.get(tokens[3]);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error al procesar los argumentos: " + nfe);
                comprobando = true;
            } catch (ExcepcionJuego ej) {
                System.out.println("Error en el comando: " + ej);
                comprobando = true;
            }
        }
        switch (dificultad) {
            case 1:
                vegQuedan = 5;
                break;
            case 2:
                vegQuedan = 15;
                break;
            case 3:
                vegQuedan = 25;
                break;
            case 4:
                vegQuedan = 50;
                break;
            default:
                vegQuedan = 10;
        }
        vegFinal = vegQuedan / 5;
        vegQuedan -= vegFinal;
        tablero = new Tablero(ancho, alto);
        System.out.println("Comienza la partida.");
        //programa principal
        while (jugando) {
            ararTerreno();
            comprobando = true;

            while (comprobando) {
                magia += 5;
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
                                tablero.colocarEntidad(new Girasol(5, 2, tablero.getContador(), 10), x, y);
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
            tablero.setVegQuedan(vegQuedan);
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
        if (pierdes) {
            System.out.println("Pero que pringao.");
        } else {
            System.out.println("Has ganado. \n¡¡Enhorabuena!!");
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

    public static void generarVeganos() {
        // contadores
        int contador = 30 - tablero.getContador();
        int descanso;
        int turnoBoost = 0;
        double probabilidad = 0;
        double prob;
        double ajuste;
        double pfinal;

        switch (dificultad) {
            case 1:
                descanso = 10;
                ajuste = 1.5;
                turnoBoost = 5;
                break;
            case 2:
                descanso = 7;
                ajuste = 2.5;
                turnoBoost = 3;
                break;
            case 3:
                descanso = 5;
                ajuste = 3;
                turnoBoost = 2;
                break;
            case 4:
                descanso = 5;
                ajuste = 1;
                turnoBoost = 0;
                break;
            default:
                ajuste = 1;
                descanso = 5;
        }
        if (contador > 0) {
            //reset al boost y al contador.
            if (turnosSinVeganos >= turnoBoost) {
                probabilidad = Math.random() / ajuste;
            }

            prob = ((double) vegQuedan) / ((double) contador) * 0.8;
            pfinal = prob + probabilidad;

            // comienzan a aparecer los zombies.
            if (contador < 30 - descanso) {
                if (contador < 7) {
                    if (pfinal > 1.00 && vegQuedan >= 3) {
                        vegQuedan -= 2 + (int) pfinal;
                        tablero.spawnVeganos(2 + (int) pfinal);
                        turnosSinVeganos = 0;
                    } else if (pfinal >= 0.75 && vegQuedan >= 2) {
                        vegQuedan -= 2;
                        tablero.spawnVeganos(2);
                        turnosSinVeganos = 0;
                    } else if (pfinal >= 0.6 && vegQuedan >= 1) {
                        vegQuedan -= 1;
                        tablero.spawnVeganos(1);
                        turnosSinVeganos = 0;
                    } else {
                        turnosSinVeganos++;
                    }
                } else if (contador == 7) {
                    System.out.println(" FINAL WAVE !!! ");
                    vegQuedan += vegFinal;
                } else {
                    if (pfinal >= 1.00 && vegQuedan >= 3) {
                        vegQuedan -= 2 + (int) pfinal;
                        tablero.spawnVeganos(2 + (int) pfinal);
                        turnosSinVeganos = 0;
                    } else if (pfinal >= 0.75 && vegQuedan >= 2) {
                        vegQuedan -= 2;
                        tablero.spawnVeganos(2);
                        turnosSinVeganos = 0;
                    } else if (pfinal >= 0.6 && vegQuedan >= 1) {
                        vegQuedan -= 1;
                        tablero.spawnVeganos(1);
                        turnosSinVeganos = 0;
                    } else {
                        turnosSinVeganos++;
                    }
                }
            }
        }
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
        System.out.println("Turno: " + tablero.getContador() + "\nMagia: " + magia);

    }
}

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
