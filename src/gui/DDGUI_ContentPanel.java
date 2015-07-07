/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author rw
 */
public class DDGUI_ContentPanel extends JPanel {

    public DDGUI_ContentPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public DDGUI_ContentPanel(LayoutManager layout) {
        super(layout);
    }

    public DDGUI_ContentPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public DDGUI_ContentPanel() {
        super();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("../resources/logo_2.png"));
        } catch (IOException ex) {
            Logger.getLogger(DDGUI_ContentPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            g.drawImage(img, 250, 250,null);
    }

    
    
}
