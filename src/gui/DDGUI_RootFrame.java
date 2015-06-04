/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

/**
 *
 * @author 3flim
 */
public class DDGUI_RootFrame extends JFrame {

    DDGUI_SpielFeld feld = new DDGUI_SpielFeld(this, 1100, 500);
    DDGUI_InfoPanel infopanel = new DDGUI_InfoPanel();

    public DDGUI_SpielFeld getFeld() {
        return feld;
    }

    public void setFeld(DDGUI_SpielFeld feld) {
        this.feld = feld;
    }

    public DDGUI_InfoPanel getInfopanel() {
        return infopanel;
    }

    public void setInfopanel(DDGUI_InfoPanel infopanel) {
        this.infopanel = infopanel;
    }

    public DDGUI_RootFrame() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Class themeClass = null;
        try {
            themeClass = Class.forName("MacMetricsTheme");
            MetalTheme theme = (MetalTheme) themeClass.newInstance();
            MetalLookAndFeel.setCurrentTheme(theme);
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DDGUI_RootFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        feld.setPreferredSize(new Dimension(1100, 500));

        infopanel.setPreferredSize(new Dimension(400, 500));

        contentPanel.add(feld, BorderLayout.CENTER);
        contentPanel.add(infopanel, BorderLayout.EAST);

        Image img = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/logo.png"));
        } catch (IOException ex) {
            Logger.getLogger(DDGUI_RootFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setIconImage(img);
        this.add(contentPanel);
        this.setSize(new Dimension(1500, 1000));
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

}
