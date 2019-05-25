/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static javax.imageio.ImageIO.read;
import javax.swing.*;
import MartaYLasPlantas.Veganos.*;
import MartaYLasPlantas.Plantas.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class GraficosUwU extends JPanel {

    private static int alto = 5;
    private static int ancho = 9;
    private static int dificultad;
    private static int magia = 0;
    private static int vegQuedan;
    private static int vegFinal = 0;
    private static int turnosSinVeganos;
    private static long[] puntuacion;
    private static long puntuacionPartida;
    private static GraficosUwU panelJuego;
    private static Jugador jugador;
    private static Tablero tablero;

    private BufferedImage hierba1, hierba2, cereza, lanzadora, girasol, girasolMagia, minaPatata,
            minaPatataEnt, nuez, veganoComun, veganoCubo, cortacesped, cortacesped2, cemento, veganosMultiples, veganoProteico;
    private boolean secret;
    private int ajusteVegano = 16, ajusteVert = 160, ajusteHorz = 40;

    public GraficosUwU(boolean secret, Tablero tablero) {
        super();
        this.secret = secret;
        this.tablero = tablero;
        // * 64 es por que las imagenes son 64 x 64 p
        alto = tablero.getTerreno().length * 64;
        //+64 para imprimir los cortacesped
        ancho = 64 + tablero.getTerreno()[0].length * 64;
        setup();
    }

    public final void setup() {
        setSize(new Dimension(alto, ancho));
        CargarSprites();
    }

    public final void CargarSprites() {
        try {
            // habrá que explicarlo de algún modo :D
            hierba1 = read(getClass().getClassLoader().getResource("res/Hierba.png"));
            hierba2 = read(getClass().getClassLoader().getResource("res/Hierba2.png"));
            cereza = read(getClass().getClassLoader().getResource("res/Cereza.png"));
            lanzadora = read(getClass().getClassLoader().getResource("res/Lanzadora.png"));
            girasol = read(getClass().getClassLoader().getResource("res/Girasol.png"));
            girasolMagia = read(getClass().getClassLoader().getResource("res/GirasolMagia.png"));
            minaPatata = read(getClass().getClassLoader().getResource("res/Mina-patata.png"));
            minaPatataEnt = read(getClass().getClassLoader().getResource("res/Mina-patataEnterrada.png"));
            nuez = read(getClass().getClassLoader().getResource("res/Nuez.png"));
            veganoComun = read(getClass().getClassLoader().getResource("res/Zombie.png"));
            veganoProteico = read(getClass().getClassLoader().getResource("res/ZombieProteico.png"));
            veganoCubo = read(getClass().getClassLoader().getResource("res/ZombieCubo.png"));
            veganosMultiples = read(getClass().getClassLoader().getResource("res/Zombie++.png"));
            cortacesped = read(getClass().getClassLoader().getResource("res/cortacesped.png"));
            cortacesped2 = read(getClass().getClassLoader().getResource("res/cortacespedOjos.png"));
            cemento = read(getClass().getClassLoader().getResource("res/cemento.png"));
        } catch (IOException ex) {
            System.out.println("Error al cargar las imagenes" + ex);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        int i, j, cCesped, veganos;
        Entidad ent;
        i = ajusteVert;
        cCesped = 0;
        for (Casilla[] fila : tablero.getTerreno()) {
            j = ajusteHorz;
            g2D.drawImage(cemento, j, i, this);
            if (tablero.getCortacesped()[cCesped]) {
                if (secret) {
                    g2D.drawImage(cortacesped2, j, i, this);
                } else {
                    g2D.drawImage(cortacesped, j, i, this);
                }
            }
            j += 64;
            for (Casilla casilla : fila) {
                hierbaAlternada(g2D, j, i);
                veganos = 0;
                for (int k = 0; k < casilla.getEntidades().size(); k++) {
                    ent = casilla.getEntidades().get(k);
                    if (ent instanceof Vegano) {
                        veganos++;
                    }
                }

                for (Entidad entidad : casilla.getEntidades()) {
                    if (entidad instanceof Lanzadora) {
                        g2D.drawImage(lanzadora, j, i, this);
                    } else if (entidad instanceof Girasol) {
                        g2D.drawImage(girasolMagia, j, i, this);
                    } else if (entidad instanceof Nuez) {
                        g2D.drawImage(nuez, j, i, this);
                    } else if (entidad instanceof Cereza) {
                        g2D.drawImage(cereza, j, i, this);
                    } else if (entidad instanceof MinaPatata) {
                        g2D.drawImage(minaPatata, j, i, this);
                    }
                    if (veganos == 1) {
                        if (entidad instanceof VeganoComun) {
                            g2D.drawImage(veganoComun, j, i - ajusteVegano, this);
                        } else if (entidad instanceof VeganoCasco) {
                            g2D.drawImage(veganoCubo, j, i - ajusteVegano, this);
                        } else if (entidad instanceof VeganoProteico) {
                            g2D.drawImage(veganoProteico, j, i - ajusteVegano, this);
                        }
                    }
                }
                if (veganos > 1) {
                    //muchedumbre
                    g2D.drawImage(veganosMultiples, j, i - ajusteVegano, this);
                }
                j += 64;
            }
            i += 64;
            cCesped++;
        }
    }

    /**
     * Dibuja por pantalla la hierba de forma alterna
     *
     * @param g2D
     * @param y
     * @param x
     */
    public void hierbaAlternada(Graphics2D g2D, int y, int x) {
        if ((y / 64) % 2 == (x / 64) % 2) {
            g2D.drawImage(hierba1, y, x, this);
        } else {
            g2D.drawImage(hierba2, y, x, this);
        }
    }

    /**
     *
     * @param jugador
     * @return "true" si se ha podido cargar el archivo con exito
     */
    public boolean cargarPartida(Jugador jugador) {
        FileReader reader;
        BufferedReader breader;
        String linea, tokens[], strCasillas[], strEntidad[];
        boolean cortacesped[] = new boolean[alto];
        int salud = 0, turno = 0;

        try {
            //leer el archivo "Marta.sav"
            reader = new FileReader("partidas/" + jugador.getNombre() + ".sav");
            breader = new BufferedReader(reader);

            //leer linea por linea el archivo
            for (int i = 1; (linea = breader.readLine()) != null; i++) {
                switch (i) {
                    //linea 1: DNI
                    case 1:
                        if (!linea.equals(jugador.getDni())) {
                            breader.close();
                            throw new ExcepcionJuego();
                        }
                        break;

                    //linea 2: nombre
                    case 2:
                        if (!linea.equals(jugador.getNombre())) {
                            breader.close();
                            throw new ExcepcionJuego();
                        }
                        break;

                    //linea 3: magia
                    case 3:
                        tokens = linea.split(" ");
                        if (!tokens[0].equals("magia")) {
                            breader.close();
                            throw new ExcepcionJuego();
                        }
                        magia = Integer.parseInt(tokens[1]);
                        break;

                    //linea 4: turno
                    case 4:
                        tokens = linea.split(" ");
                        if (!tokens[0].equals("turno")) {
                            breader.close();
                            throw new ExcepcionJuego();
                        }
                        tablero.setContador(Integer.parseInt(tokens[1]));
                        break;

                    //linea 5: dificultad
                    case 5:
                        tokens = linea.split(" ");
                        if (!tokens[0].equals("dificultad")) {
                            breader.close();
                            throw new ExcepcionJuego();
                        }
                        dificultad = Integer.parseInt(tokens[1]);
                        if (dificultad > 5 || dificultad < -1) {
                            breader.close();
                            throw new ExcepcionJuego();
                        }
                        break;

                    //linea 6: cortacesped
                    case 6:
                        tokens = linea.split(" ");
                        if (!tokens[0].equals("cortacesped")) {
                            breader.close();
                            throw new ExcepcionJuego();
                        }
                        for (int j = 0; j < alto; j++) {
                            System.out.println(tokens[j + 1]);
                            cortacesped[j] = Boolean.parseBoolean(tokens[j + 1]);
                        }
                        tablero.setCortacesped(cortacesped);
                        break;
                    //linea 7: veganos restantes
                    case 7:
                        tokens = linea.split(" ");
                        if (!tokens[0].equals("VeganosQuedan")) {
                            breader.close();
                            throw new ExcepcionJuego();
                        }
                        vegQuedan = Integer.parseInt(tokens[1]);
                        break;

                    //lineas 8-12: tablero
                    default:
                        strCasillas = linea.split(";");
                        System.out.println(linea);
                        for (int j = 0; j < strCasillas.length; j++) {
                            tokens = strCasillas[j].split(",");
                            for (int k = 0; k < tokens.length; k++) {
                                strEntidad = tokens[k].split(" ");
                                if (strEntidad.length > 1) {
                                    salud = Integer.parseInt(strEntidad[1]);
                                    turno = Integer.parseInt(strEntidad[2]);
                                }
                                switch (strEntidad[0]) {
                                    case "C":
                                        tablero.colocarEntidad(new Cereza(salud, turno), i - 8, j);
                                        System.out.println("cereza");
                                        break;
                                    case "G":
                                        tablero.colocarEntidad(new Girasol(salud, turno), i - 8, j);
                                        System.out.println("girasol");
                                        break;
                                    case "L":
                                        tablero.colocarEntidad(new Lanzadora(salud), i - 8, j);
                                        System.out.println("lanzaguisantes");
                                        break;
                                    case "MP":
                                        tablero.colocarEntidad(new MinaPatata(salud, turno), i - 8, j);
                                        System.out.println("Minapatata");
                                        break;
                                    case "N":
                                        tablero.colocarEntidad(new Nuez(salud), i - 8, j);
                                        System.out.println("nuez");
                                        break;
                                    case "V":
                                        tablero.colocarEntidad(new VeganoComun(salud, turno), i - 8, j);
                                        System.out.println("vegano");
                                        break;
                                    case "VC":
                                        tablero.colocarEntidad(new VeganoCasco(salud, turno), i - 8, j);
                                        System.out.println("vegano casco");
                                        break;
                                    case "VP":
                                        tablero.colocarEntidad(new VeganoProteico(salud, turno), i - 8, j);
                                        System.out.println("vegano proteico");
                                        break;
                                }
                            }
                        }
                }
            }
        } catch (ExcepcionJuego | NumberFormatException ej) {
            System.out.println("Excepcion Juego: " + ej);
            return false;
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
            return false;
        } catch (IOException ioe) {
            System.out.println(ioe);
            return false;
        }
        return true;
    }

    public void guardarPartida() {
        try {
            FileWriter nuevaPartida;
            BufferedWriter bwriter;
            //crear/sobreescribir "Marta.sav"
            new File("partidas").mkdir();
            File archivoPartida = new File("partidas/" + jugador.getNombre() + ".sav");
            archivoPartida.createNewFile();
            nuevaPartida = new FileWriter(archivoPartida);
            bwriter = new BufferedWriter(nuevaPartida);

            //linea 1: DNI
            bwriter.write(jugador.getDni());
            bwriter.newLine();

            //linea 2: nombre
            bwriter.write(jugador.getNombre());
            bwriter.newLine();

            //linea 3: magia
            bwriter.write("magia " + magia);
            bwriter.newLine();

            //linea 4: turno
            bwriter.write("turno " + tablero.getContador());
            bwriter.newLine();

            //linea 5: dificultad
            bwriter.write("dificultad " + dificultad);
            bwriter.newLine();

            //linea 6: cortacesped
            bwriter.write("cortacesped ");
            for (boolean cortacesped : tablero.getCortacesped()) {
                bwriter.write(String.valueOf(cortacesped) + " ");
            }
            bwriter.newLine();

            //linea 7: veganos restantes
            bwriter.write("VeganosQuedan " + vegQuedan);
            bwriter.newLine();

            //lineas 8-12: tablero
            for (Casilla[] fila : tablero.getTerreno()) {
                for (Casilla casilla : fila) {
                    for (Entidad entidad : casilla.getEntidades()) {
                        if (entidad != null) {
                            if (entidad instanceof Cereza) {
                                bwriter.write("C ");
                            } else if (entidad instanceof Girasol) {
                                bwriter.write("G ");
                            } else if (entidad instanceof Lanzadora) {
                                bwriter.write("L ");
                            } else if (entidad instanceof MinaPatata) {
                                bwriter.write("MP ");
                            } else if (entidad instanceof Nuez) {
                                bwriter.write("N ");
                            } else if (entidad instanceof VeganoComun) {
                                bwriter.write("V ");
                            } else if (entidad instanceof VeganoCasco) {
                                bwriter.write("VC ");
                            } else if (entidad instanceof VeganoProteico) {
                                bwriter.write("VP ");
                            }
                            bwriter.write(entidad.getSalud() + " ");
                            bwriter.write(entidad.getTurno() + "");
                            bwriter.write(",");
                        }
                    }
                    bwriter.write(";");
                }
                bwriter.newLine();
            }
            bwriter.close();
        } catch (IOException ioe) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }

    /**
     * Genera veganos en el tablero dependiendo del turno en el que estemos y la
     * dificultad
     */
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

}
