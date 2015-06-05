/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 * http://www.migcalendar.com/miglayout/whitepaper.html
 *
 * @author 3flim
 */
public class DDGUI_InfoPanel extends JPanel {

    int WIDTH;
    int HEIGHT;
    int ratio;
    int ZELLEN = 30;

    private BufferedImage stein;
    private BufferedImage boden;
    boolean bodenjn = true;

    public DDGUI_InfoPanel() {
        super();
        this.WIDTH = this.getWidth();
        this.HEIGHT = this.getHeight();
        this.ratio = this.WIDTH / this.ZELLEN;
        try {
            this.stein = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/stein.png"));
            this.boden = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/boden.png"));
        } catch (IOException ex) {
            Logger.getLogger(DDGUI_InfoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setboden() {

        bodenjn = true;

    }

    public void setbaum() {

        bodenjn = false;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {

                if (bodenjn) {
                    g.drawImage(this.boden, i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                }else{
                    g.drawImage(this.stein, i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                }
            }
        }
    }

}
