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
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class PrincipalGraficos extends JFrame {

    private static int alto = 5;
    private static int ancho = 9;
    private static int altopx = 5;
    private static int anchopx = 9;
    private static int dificultad;
    private static int magia = 50;
    private static int vegQuedan;
    private static int vegFinal = 0;
    private static int turnosSinVeganos;
    private static long puntuacionPartida;
    private static PrincipalGraficos panelJuego;
    private static Jugador jugador;
    private static Tablero tablero;

    private BufferedImage hierba1, hierba2, cereza, lanzadora, girasol, girasolMagia, minaPatata,
            minaPatataEnt, nuez, veganoComun, veganoCubo, cortacesped, cortacesped2, cemento,
            veganosMultiples, veganoProteico, veganoZombie, veganoGolem, veganoEsqueloide, hierbaM,
            hierbaM2, grava, veganosMultiplesM, zombieVegano, zombieVeganoCubo, zombieVeganoProteico, zombiesVeganosMultiples, fondo;
    private static boolean secret, minecraft, magos, zombies, partidaCagada = false;
    private int ajusteVegano = 16, ajusteVert = 195, ajusteHorz = 469;

    public PrincipalGraficos(Tablero tablero) {

        super();
        PrincipalGraficos.tablero = tablero;
        // * 64 es por que las imagenes son 64 x 64 p
        altopx = tablero.getTerreno().length * 64;
        //+64 para imprimir los cortacesped
        anchopx = 64 + tablero.getTerreno()[0].length * 64;
        setup();
    }

    public static void setSecret(boolean secret) {
        PrincipalGraficos.secret = secret;
    }

    public static void setMinecraft(boolean minecraft) {
        PrincipalGraficos.minecraft = minecraft;
    }

    public static void setMagos(boolean magos) {
        PrincipalGraficos.magos = magos;
    }

    public static void setPartidaCagada(boolean partidaCagada) {
        PrincipalGraficos.partidaCagada = partidaCagada;
    }

    public static void setVeganos(boolean veganos) {
        PrincipalGraficos.zombies = veganos;
    }

    public final void setup() {
        setVisible(false);
        setSize(new Dimension(alto + 32, ancho));
        CargarSprites();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                /*int reply = JOptionPane.showConfirmDialog(null, "Quieres guardar?", "Guardar Partida", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    guardarPartida();
                    JOptionPane.showMessageDialog(null, "Partida guardada con exito.");
                }*/
                System.exit(0);
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public final void CargarSprites() {
        try {
            // habrá que explicarlo de algún modo :D
            fondo = read(getClass().getClassLoader().getResource("res/xombi.png"));
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
            zombieVegano = read(getClass().getClassLoader().getResource("res/ZombieVegano.png"));
            zombieVeganoProteico = read(getClass().getClassLoader().getResource("res/ZombieProteicoVegano.png"));
            zombiesVeganosMultiples = read(getClass().getClassLoader().getResource("res/Zombie++Vegano.png"));
            veganoProteico = read(getClass().getClassLoader().getResource("res/ZombieProteico.png"));
            veganosMultiples = read(getClass().getClassLoader().getResource("res/Zombie++.png"));
            veganoCubo = read(getClass().getClassLoader().getResource("res/zombieCubo.png"));
            veganoGolem = read(getClass().getClassLoader().getResource("res/zombieCuboMinecraft.png"));
            zombieVeganoCubo = read(getClass().getClassLoader().getResource("res/zombieCuboVegano.png"));
            cortacesped = read(getClass().getClassLoader().getResource("res/cortacesped.png"));
            cortacesped2 = read(getClass().getClassLoader().getResource("res/cortacespedOjos.png"));
            veganoZombie = read(getClass().getClassLoader().getResource("res/ZombieMinecraft.png"));
            veganoEsqueloide = read(getClass().getClassLoader().getResource("res/ZombieProteicoMinecraft.png"));
            hierbaM = read(getClass().getClassLoader().getResource("res/HierbaMinecraft.png"));
            hierbaM2 = read(getClass().getClassLoader().getResource("res/Hierba2Minecraft.png"));
            cemento = read(getClass().getClassLoader().getResource("res/cemento.png"));
            grava = read(getClass().getClassLoader().getResource("res/cementoMinecraft.png"));
            veganosMultiplesM = read(getClass().getClassLoader().getResource("res/ZombieMaincra++.png"));

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
        g2D.drawImage(fondo, 0, 32, this);
        for (Casilla[] fila : tablero.getTerreno()) {
            j = ajusteHorz;
            if (minecraft) {
                g2D.drawImage(grava, j, i, this);
            } else {
                g2D.drawImage(cemento, j, i, this);
            }
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
                        if (magos) {
                            g2D.drawImage(girasolMagia, j, i, this);
                        } else {
                            g2D.drawImage(girasol, j, i, this);
                        }

                    } else if (entidad instanceof Nuez) {
                        g2D.drawImage(nuez, j, i, this);
                    } else if (entidad instanceof Cereza) {
                        g2D.drawImage(cereza, j, i, this);
                    } else if (entidad instanceof MinaPatata) {
                        if (((MinaPatata) entidad).isEnterrado()) {
                            g2D.drawImage(minaPatataEnt, j, i, this);
                        } else {
                            g2D.drawImage(minaPatata, j, i, this);
                        }
                    }
                    if (veganos == 1) {

                        if (entidad instanceof VeganoComun) {
                            if (minecraft) {
                                g2D.drawImage(veganoZombie, j, i - ajusteVegano, this);
                            } else if (zombies) {
                                g2D.drawImage(zombieVegano, j, i - ajusteVegano, this);
                            } else {
                                g2D.drawImage(veganoComun, j, i - ajusteVegano, this);
                            }

                        } else if (entidad instanceof VeganoCasco) {
                            if (minecraft) {
                                g2D.drawImage(veganoGolem, j, i - ajusteVegano, this);
                            } else if (zombies) {
                                g2D.drawImage(zombieVeganoCubo, j, i - ajusteVegano, this);
                            } else {
                                g2D.drawImage(veganoCubo, j, i - ajusteVegano, this);
                            }
                        } else if (entidad instanceof VeganoProteico) {
                            if (minecraft) {
                                g2D.drawImage(veganoEsqueloide, j, i - ajusteVegano, this);
                            } else if (zombies) {
                                g2D.drawImage(zombieVeganoProteico, j, i - ajusteVegano, this);
                            } else {
                                g2D.drawImage(veganoProteico, j, i - ajusteVegano, this);
                            }

                        }

                    }
                }
                if (veganos > 1) {
                    if (minecraft) {
                        g2D.drawImage(veganosMultiplesM, j, i - ajusteVegano, this);
                    } else if (zombies) {
                        g2D.drawImage(zombiesVeganosMultiples, j, i - ajusteVegano, this);
                    } else {
                        g2D.drawImage(veganosMultiples, j, i - ajusteVegano, this);
                    }
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
            if (minecraft) {
                g2D.drawImage(hierbaM, y, x, this);
            } else {
                g2D.drawImage(hierba1, y, x, this);
            }
        } else {
            if (minecraft) {
                g2D.drawImage(hierbaM2, y, x, this);
            } else {
                g2D.drawImage(hierba2, y, x, this);
            }
        }

    }

    public void jugar() throws IOException {
        boolean comprobando, saltaTurno;
        while (!this.isVisible()) {
            System.out.print("");
        }
        setSize(new Dimension(1282, 724));

        if (!partidaCagada) {
            HashMap<String, Integer> hashDificultad = new HashMap<>();
            hashDificultad.put("BAJA", 1);
            hashDificultad.put("MEDIA", 2);
            hashDificultad.put("ALTA", 3);
            hashDificultad.put("IMPOSIBLE", 4);

            JPanel panelDificultad = new JPanel();
            Object[] opcionesDificultad = {"Elegir", "Salir"};
            JComboBox boxDificultad = new JComboBox();
            boxDificultad.addItem("BAJA");
            boxDificultad.addItem("MEDIA");
            boxDificultad.addItem("ALTA");
            boxDificultad.addItem("IMPOSIBLE");

            panelDificultad.add(boxDificultad);

            int opcionDificultad = JOptionPane.showOptionDialog(null, panelDificultad, "Elegir dificultad",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, opcionesDificultad, null);
            String strDificultad = (String) boxDificultad.getSelectedItem();
            switch (opcionDificultad) {
                case JOptionPane.YES_OPTION:
                    dificultad = hashDificultad.get(strDificultad);
                    break;
                default:
                    System.exit(0);
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
        }

        this.repaint();
        try {
            boolean jugando = true, puedePlantar;
            String comando;
            String[] tokens;
            int x, y;
            while (jugando) {
                magia += 5;
                repaint();
                comprobando = true;
                while (comprobando) {
                    try {

                        puedePlantar = true;
                        comprobando = false;
                        saltaTurno = false;

                        Object[] opcionesComando = {"Ejecutar", "Saltar Turno", "Guardar y salir"};

                        JComboBox boxPlanta = new JComboBox();
                        boxPlanta.addItem("(50) LANZAGUISANTES");
                        boxPlanta.addItem("(20) GIRASOL");
                        boxPlanta.addItem("(50) CEREZA");
                        boxPlanta.addItem("(50) NUEZ");
                        boxPlanta.addItem("(25) MINA-PATATA");
                        boxPlanta.addItem("(200) BOMBA");

                        JComboBox boxX = new JComboBox();
                        for (int i = 1; i <= 9; i++) {
                            boxX.addItem(i);
                        }

                        JComboBox boxY = new JComboBox();
                        for (int i = 1; i <= 5; i++) {
                            boxY.addItem(i);
                        }

                        JPanel global = new JPanel();
                        global.setLayout(new BorderLayout());
                        JPanel titulo = new JPanel();
                        JPanel panelComando = new JPanel();
                        JTextField textField = new JTextField(10);
                        titulo.add(new JLabel("magia: " + magia + " turno: " + tablero.getContador()));
                        panelComando.add(new JLabel("Planta"));
                        panelComando.add(boxPlanta);
                        panelComando.add(new JLabel("X"));
                        panelComando.add(boxX);
                        panelComando.add(new JLabel("Y"));
                        panelComando.add(boxY);
                        global.add(titulo, BorderLayout.NORTH);
                        global.add(panelComando, BorderLayout.CENTER);

                        int opcionComando = JOptionPane.showOptionDialog(null, global, "A PLANTAR",
                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                null, opcionesComando, null);
                        switch (opcionComando) {
                            case 0:
                                break;
                            case 1:
                                saltaTurno = true;
                                break;
                            case 2:
                                HashMap<String, Jugador> jugadores = Jugador.getJugadores();
                                if (jugadores.containsKey(jugador.getDni())) {
                                    JOptionPane.showMessageDialog(null, "Partida guardada con exito.");
                                    guardarPartida();
                                } else {
                                    JOptionPane.showMessageDialog(null, "No existe ficha de usuario. Imposible guardar");
                                }

                            default:
                                System.exit(0);
                        }
                        y = (int) boxY.getSelectedItem() - 1;
                        x = (int) boxX.getSelectedItem() - 1;
                        //comprobar si hay una planta en la posicion x y
                        for (Entidad entidad : tablero.getTerreno()[y][x].getEntidades()) {
                            if (puedePlantar) {
                                puedePlantar = !(entidad instanceof Planta);
                            }
                        }
                        if (!saltaTurno) {
                            switch ((String) boxPlanta.getSelectedItem()) {
                                case "(20) GIRASOL":
                                    if (magia < Girasol.getCoste()) {
                                        throw new ExcepcionPlanta("Magia insuficiente.");
                                    } else if (!puedePlantar) {
                                        throw new ExcepcionPlanta("Ya hay una planta en esa posición.");
                                    } else {
                                        tablero.colocarEntidad(new Girasol(tablero.getContador()), y, x);
                                        magia -= Girasol.getCoste();
                                    }
                                    break;
                                case "(50) LANZAGUISANTES":
                                    if (magia < Lanzadora.getCoste()) {
                                        throw new ExcepcionPlanta("Magia insuficiente.");
                                    } else if (!puedePlantar) {
                                        throw new ExcepcionPlanta("Ya hay una planta en esa posición.");
                                    } else {
                                        tablero.colocarEntidad(new Lanzadora(), y, x);
                                        magia -= Lanzadora.getCoste();
                                    }
                                    break;
                                case "(50) CEREZA":
                                    if (magia < Cereza.getCoste()) {
                                        throw new ExcepcionPlanta("Magia insuficiente.");
                                    } else if (!puedePlantar) {
                                        throw new ExcepcionPlanta("Ya hay una planta en esa posición.");
                                    } else {
                                        tablero.colocarEntidad(new Cereza(tablero.getContador()), y, x);
                                        magia -= Cereza.getCoste();
                                    }
                                    break;
                                case "(50) NUEZ":
                                    if (magia < Nuez.getCoste()) {
                                        throw new ExcepcionPlanta("Magia insuficiente.");
                                    } else if (!puedePlantar) {
                                        throw new ExcepcionPlanta("Ya hay una planta en esa posición.");
                                    } else {
                                        tablero.colocarEntidad(new Nuez(), y, x);
                                        magia -= Nuez.getCoste();
                                    }
                                    break;
                                case "(25) MINA-PATATA":

                                    if (magia < MinaPatata.getCoste()) {
                                        throw new ExcepcionPlanta("Magia insuficiente.");
                                    } else if (!puedePlantar) {
                                        throw new ExcepcionPlanta("Ya hay una planta en esa posición.");
                                    } else {
                                        tablero.colocarEntidad(new MinaPatata(tablero.getContador()), y, x);
                                        magia -= MinaPatata.getCoste();
                                    }
                                    break;
                                case "(200) BOMBA":
                                    if (magia < 200) {
                                        throw new ExcepcionPlanta("Magia insuficiente.");
                                    } else {
                                        tablero.Bomba(x, y);
                                        magia -= 200;
                                        break;
                                    }
                            }
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null,
                                nfe,
                                "Error al procesar los argumentos",
                                JOptionPane.WARNING_MESSAGE);
                        comprobando = true;
                    } catch (ExcepcionPlanta ep) {
                        JOptionPane.showMessageDialog(null,
                                ep,
                                "Error al plantar",
                                JOptionPane.WARNING_MESSAGE);
                        comprobando = true;
                    }

                }
                generarVeganos();
                jugando = tablero.actualiza();
                tablero.setVegQuedan(vegQuedan);
                this.repaint();
                Thread.sleep(500);

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(PrincipalGraficos.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        boolean pierdes = false;
        puntuacionPartida = 100 * magia;

        for (Casilla[] fila
                : tablero.getTerreno()) {
            for (Casilla casilla : fila) {
                for (Entidad entidad : casilla.getEntidades()) {
                    if (pierdes = entidad instanceof Vegano) {
                        puntuacionPartida = 0;
                        break;
                    } else if (entidad instanceof Planta) {
                        puntuacionPartida += 100;
                    }
                }
            }
        }
        if (pierdes) {

            int partidasPerdidas[] = jugador.getPartidasPerdidas();
            partidasPerdidas[dificultad - 1]++;
            jugador.setPartidasPerdidas(partidasPerdidas);
            JOptionPane.showMessageDialog(null, "Has perdido. \n"
                    + " más suerte la próxima vez.");

        } else {
            int partidasGanadas[] = jugador.getPartidasGanadas();
            partidasGanadas[dificultad - 1]++;
            jugador.setPartidasGanadas(partidasGanadas);

            long puntuaciones[] = jugador.getPuntuacion();
            puntuaciones[dificultad - 1] += puntuacionPartida;
            jugador.setPuntuacion(puntuaciones);

            JOptionPane.showMessageDialog(null, "Has ganado.\n"
                    + "¡¡Enhorabuena!!\nPuntuacion: " + puntuacionPartida);
        }
    }

    /**
     *
     * @param jugador
     * @return "true" si se ha podido cargar el archivo con exito
     * @throws java.io.FileNotFoundException
     */
    public static boolean cargarPartida(Jugador jugador) throws FileNotFoundException {
        FileReader reader;
        BufferedReader breader;
        String linea, tokens[], strCasillas[], strEntidad[];
        boolean hayCortacesped[] = new boolean[alto];
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
                            hayCortacesped[j] = Boolean.parseBoolean(tokens[j + 1]);
                        }
                        tablero.setCortacesped(hayCortacesped);
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
                                        break;
                                    case "G":
                                        tablero.colocarEntidad(new Girasol(salud, turno), i - 8, j);
                                        break;
                                    case "L":
                                        tablero.colocarEntidad(new Lanzadora(salud), i - 8, j);
                                        break;
                                    case "MP":
                                        tablero.colocarEntidad(new MinaPatata(salud, turno), i - 8, j);
                                        break;
                                    case "N":
                                        tablero.colocarEntidad(new Nuez(salud), i - 8, j);
                                        break;
                                    case "V":
                                        tablero.colocarEntidad(new VeganoComun(salud, turno), i - 8, j);
                                        break;
                                    case "VC":
                                        tablero.colocarEntidad(new VeganoCasco(salud, turno), i - 8, j);
                                        break;
                                    case "VP":
                                        tablero.colocarEntidad(new VeganoProteico(salud, turno), i - 8, j);
                                        break;
                                }
                            }
                        }
                }
            }
        } catch (ExcepcionJuego | NumberFormatException ej) {
            System.out.println("Excepcion Juego: " + ej);
            return false;
        } catch (IOException ioe) {
            System.out.println(ioe);
            return false;
        }
        return true;
    }

    public void guardarPartida() throws IOException {

        FileWriter nuevaPartida;
        BufferedWriter bwriter;

        //crear/sobreescribir "Marta.sav"
        new File("partidas").mkdir();
        File archivoPartida = new File("partidas/" + jugador.getNombre() + ".sav");

        if (!archivoPartida.exists() && Jugador.existeJugador(jugador.getDni())) {
            archivoPartida.createNewFile();
        }
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

    /**
     *
     * @param cantidad Incrementa la magia global del juego.
     */
    public static void incrementarMagia(int cantidad) {
        magia += cantidad;
    }

    /**
     *
     * @param jugador
     */
    public static void setJugador(Jugador jugador) {
        PrincipalGraficos.jugador = jugador;

    }

    public void setMagia(int magia) {
        PrincipalGraficos.magia = magia;
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

    public ExcepcionJuego() {
        super("archivo de guardado no valido");
    }

}
