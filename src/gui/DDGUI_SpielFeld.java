/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author 3flim
 */
public class DDGUI_SpielFeld extends JPanel {

    public DDGUI_SpielFeld() {

    }

    
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        Graphics2D g2d = (Graphics2D) g.create();

        BufferedImage img = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/gamehintergrund.png"));
        } catch (IOException e) {
            System.err.println("kein bild da");
        }

        g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);

    }

}


