/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_ContextMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author rw
 */
public class DD_SpielerMenu extends JPanel {

    boolean player = false;

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    JLabel l_leben;
    JLabel l_mana;
    JLabel l_ruestung;
    JLabel l_schaden;

    JLabel l_faehigkeit1;
    JLabel l_faehigkeit1gif;

    JLabel l_faehigkeit2;
    JLabel l_faehigkeit2gif;

    JProgressBar pb_leben;
    JTextField t_mana;
    JTextField t_ruestung;
    JTextField t_schaden;
    JButton t_faehigkeit1;
    JButton t_faehigkeit2;

    Font infomenu = new Font("Arial", Font.BOLD, 20);

    public DD_SpielerMenu(boolean player) {
        this.player = player;

       
        
        this.setPreferredSize(new Dimension(400, 600));
        this.setLayout(new MigLayout("fill"));

        l_leben = new JLabel("Leben");
        l_leben.setFont(infomenu);

        l_mana = new JLabel("Mana");
        l_ruestung = new JLabel("Ruestung");
        l_schaden = new JLabel("Schaden");
        l_faehigkeit1 = new JLabel("Feuerball");
        l_faehigkeit2 = new JLabel("Wasserball");

        l_faehigkeit1gif = new JLabel();
        l_faehigkeit2gif = new JLabel();

        pb_leben = new JProgressBar();
        t_mana = new JTextField();
        t_ruestung = new JTextField();
        t_schaden = new JTextField();
        t_faehigkeit1 = new JButton("Attack");
        t_faehigkeit2 = new JButton("Attack");

        this.add(l_leben, "span, split 2, center");
        l_leben.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/Basic_set/Basic_set_Mac/Basic_set_Mac/Png/heart_64.png")));
        pb_leben.setOpaque(false);
        pb_leben.setForeground(Color.red);

        pb_leben.setPreferredSize(new Dimension(50, 25));
        pb_leben.setStringPainted(true);
        this.add(pb_leben, "span, split 2, wrap");

        this.add(l_mana, "span, split 2, center,");
        l_mana.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/mana.png")));
        t_mana.setPreferredSize(new Dimension(50, 25));
        this.add(t_mana, "span, split 2, center,wrap");
        t_mana.setEditable(false);

        this.add(l_ruestung, "span, split 2, center,");
        l_ruestung.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/shield1.png")));
        t_ruestung.setPreferredSize(new Dimension(50, 25));
        this.add(t_ruestung, "span, split 2, center,wrap");
        t_ruestung.setEditable(false);

        if (player) {
            this.add(l_faehigkeit1, "span, split 2, center,");

            this.l_faehigkeit1.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/feuerball1.gif")));

            t_faehigkeit1.setPreferredSize(new Dimension(50, 25));
            this.add(t_faehigkeit1, "span, split 2, center,wrap");

            this.add(l_faehigkeit2, "span, split 2, center,");
            this.l_faehigkeit2.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/wasserball2.gif")));

            t_faehigkeit2.setPreferredSize(new Dimension(50, 25));

            this.add(t_faehigkeit2, "span, split 2, center,wrap");
        }

    }

    public JProgressBar getT_leben() {
        return pb_leben;
    }

    public void setT_leben(JProgressBar t_leben) {
        this.pb_leben = t_leben;
    }

    public JTextField getT_mana() {
        return t_mana;
    }

    public void setT_mana(JTextField t_mana) {
        this.t_mana = t_mana;
    }

    public JTextField getT_ruestung() {
        return t_ruestung;
    }

    public void setT_ruestung(JTextField t_ruestung) {
        this.t_ruestung = t_ruestung;
    }

    public JTextField getT_schaden() {
        return t_schaden;
    }

    public void setT_schaden(JTextField t_schaden) {
        this.t_schaden = t_schaden;
    }

    public JButton getT_faehigkeit1() {
        return t_faehigkeit1;
    }

    public void setT_faehigkeit1(JButton t_faehigkeit1) {
        this.t_faehigkeit1 = t_faehigkeit1;
    }

    public JButton getT_faehigkeit2() {
        return t_faehigkeit2;
    }

    public void setT_faehigkeit2(JButton t_faehigkeit2) {
        this.t_faehigkeit2 = t_faehigkeit2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

    }

}
