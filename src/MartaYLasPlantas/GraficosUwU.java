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

/**
 * @author EDGENP: Eugenio Lorente Darius Tamas
 */
public class GraficosUwU extends JPanel {

    private BufferedImage hierba1, hierba2, cereza, lanzadora, girasol, girasolMagia, minaPatata,
            minaPatataEnt, nuez, veganoComun, veganoCubo, cortacesped, cortacesped2
            ,cemento , veganosMultiples, veganoProteico;
    private boolean secret;
    private int alto, ancho, ajusteVegano = 16, ajusteVert = 160, ajusteHorz = 40;
    private Tablero tablero;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
    }

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
                            g2D.drawImage(veganoComun, j, i-ajusteVegano, this);
                        } else if (entidad instanceof VeganoCasco) {
                            g2D.drawImage(veganoCubo, j, i-ajusteVegano, this);
                        } else if (entidad instanceof VeganoProteico) {
                            g2D.drawImage(veganoProteico, j, i-ajusteVegano, this);
                        }
                    }
                }
                if (veganos > 1) {
                    //muchedumbre
                    g2D.drawImage(veganosMultiples, j, i-ajusteVegano, this);
                }
                j += 64;
            }
            i += 64;
            cCesped++;
        }
    }

    public void hierbaAlternada(Graphics2D g2D, int y, int x) {
        if ((y / 64) % 2 == (x / 64) % 2) {
            g2D.drawImage(hierba1, y, x, this);
        } else {
            g2D.drawImage(hierba2, y, x, this);
        }
    }

}
