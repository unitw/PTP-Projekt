/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DDGUI_RootFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DDGUI_RootFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        DDGUI_SpielFeld feld = new DDGUI_SpielFeld();
        feld.setPreferredSize(new Dimension(1100, 500));

        DDGUI_InfoPanel infopanel = new DDGUI_InfoPanel();
       
        infopanel.setPreferredSize(new Dimension(400, 500));

        contentPanel.add(feld, BorderLayout.CENTER);
        contentPanel.add(infopanel, BorderLayout.EAST);

        this.add(contentPanel);

        this.setSize(new Dimension(1500, 1000));
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

}
