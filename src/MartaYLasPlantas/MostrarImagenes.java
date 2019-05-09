/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartaYLasPlantas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author ACER
 */
public class MostrarImagenes extends JPanel{
    private String[] sprites = {"src/Sprites/Girasol.png", "src/Sprites/Lanzadora.png"};
    
    public MostrarImagenes(){
        super();
        setSize(100,100);        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        File archivo;
        BufferedImage imagen;
        archivo = new File(sprites[0]);
        try {
            imagen = ImageIO.read(archivo);
            g2D.drawImage(imagen,0,0,this);
        } catch (IOException ex) {
            System.out.println("Press f t pay respects");
            //cuando pulses f la has liao macho...
        }
    }
    
    public static void main(String[] args) {
        MostrarImagenes pipas = new MostrarImagenes();
        JFrame frame = new JFrame("Marta Y Las Plantucas");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        frame.show();
        frame.getContentPane().add(pipas);
    }
    
}
