/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import DD_ContextMenu.DD_MonsterMenu;
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
    DD_MonsterMenu menu= new DD_MonsterMenu();
    
    
    public DDGUI_InfoPanel() {
        super();
        this.setBackground(Color.white);
        this.add(menu);
        menu.setallVisible(false);
    }

    public DD_MonsterMenu getMenu() {
        return menu;
    }

    
    
    
}
