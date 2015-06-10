/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author 3flim
 */
public class DDGUI_RootFrame extends JFrame {

    private DDGUI_SpielFeld feld = new DDGUI_SpielFeld(this, 800, 500);
    private DDGUI_InfoPanel infopanel = new DDGUI_InfoPanel();
    private JTextArea output = new JTextArea();
    private JMenuBar spielmenuBar = new JMenuBar();

    public JTextArea getOutput() {
        return output;
    }

    public void setOutput(JTextArea output) {
        this.output = output;
    }

    public DDGUI_SpielFeld getFeld() {
        return feld;
    }

    public void setFeld(DDGUI_SpielFeld feld) {
        this.feld = feld;
    }

    public JPanel getInfopanel() {
        return infopanel;
    }

    public DDGUI_RootFrame() {
        this.setLayout(new BorderLayout());

        setUpMenubar(spielmenuBar);
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new MigLayout());

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

        feld.setPreferredSize(new Dimension(800, 810));

        infopanel.setPreferredSize(new Dimension(200, 600));
        output.setPreferredSize(new Dimension(800, 100));

        JScrollPane sp_feld = new JScrollPane(feld);
        sp_feld.setBorder(null);
        JScrollPane sp_info = new JScrollPane(infopanel);
        sp_info.setBorder(null);
        JScrollPane sp_output = new JScrollPane(output);
        sp_output.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp_output.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp_output.setBorder(null);

        //  contentPanel.add(spielmenuBar,"span 3");
        contentPanel.add(feld, "span 2");
        contentPanel.add(infopanel, "span 1,wrap");
        contentPanel.add(sp_output, "push x");

        Image img = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/logo.png"));
        } catch (IOException ex) {
            Logger.getLogger(DDGUI_RootFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setIconImage(img);
        this.add(spielmenuBar, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.setSize(new Dimension(1100, 900));
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public final void setUpMenubar(JMenuBar menu) {

        JMenu spielmenu = new JMenu("Spiel Optionen");
        JButton speichern = new JButton("Spiel speichern");
        speichern.setBorder(null);

        JButton info = new JButton("Info");
        info.setBorder(null);

        spielmenu.add(speichern);
        spielmenu.add(info);

        spielmenu.add(new JMenu("Spiel Optionen"));

        menu.add(spielmenu);
        menu.setBackground(Color.white);

    }

}
