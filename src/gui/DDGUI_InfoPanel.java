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

    JLabel l_leben;
    JLabel l_mana;
    JLabel l_ruestung;
    JLabel l_schaden;
    JLabel l_faehigkeit1;
    JLabel l_faehigkeit2;

    JTextField t_leben;
    JTextField t_mana;
    JTextField t_ruestung;
    JTextField t_schaden;
    JTextField t_faehigkeit1;
    JTextField t_faehigkeit2;

    Font infomenu = new Font("Arial", Font.BOLD, 20);

    public DDGUI_InfoPanel() {

        this.setLayout(new MigLayout("fill"));

        l_leben = new JLabel("Leben");
        l_leben.setFont(infomenu);

        l_mana = new JLabel("Mana");
        l_ruestung = new JLabel("Ruestung");
        l_schaden = new JLabel("Schaden");
        l_faehigkeit1 = new JLabel("Faehigkeit1");
        l_faehigkeit2 = new JLabel("Faehigkeit2");

        t_leben = new JTextField();
        t_mana = new JTextField();
        t_ruestung = new JTextField();
        t_schaden = new JTextField();
        t_faehigkeit1 = new JTextField();
        t_faehigkeit2 = new JTextField();

        this.add(l_leben, "span, split 2, center");

        t_leben.setOpaque(false);
        t_leben.setEditable(false);
        t_leben.setPreferredSize(new Dimension(50, 25));
        this.add(t_leben, "span, split 2, center,wrap");

        this.add(l_mana, "span, split 2, center,wrap");
    }

    public JTextField getT_leben() {
        return t_leben;
    }

    public void setT_leben(JTextField t_leben) {
        this.t_leben = t_leben;
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

    public JTextField getT_faehigkeit1() {
        return t_faehigkeit1;
    }

    public void setT_faehigkeit1(JTextField t_faehigkeit1) {
        this.t_faehigkeit1 = t_faehigkeit1;
    }

    public JTextField getT_faehigkeit2() {
        return t_faehigkeit2;
    }

    public void setT_faehigkeit2(JTextField t_faehigkeit2) {
        this.t_faehigkeit2 = t_faehigkeit2;
    }

    public void setTree(String Typ) {
        switch (Typ) {
            case "boden":
                  System.out.println("boden");
                break;
            case "baum":
                  System.out.println("baum");
                break;
        }
    }

    public void setMonster() {
        System.out.println("Monster");
    }

    public void setSpieler() {
        System.out.println("Spieler");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        Graphics2D g2d = (Graphics2D) g.create();

    }

}
