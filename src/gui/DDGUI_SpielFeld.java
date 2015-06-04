/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import listener.DD_Figurkeylistener;
import listener.DD_Statuslistener;
import spiellogik.DD_Monster;
import spiellogik.DD_Spieler;
import spiellogik.DD_Umgebung;

/**
 *
 * @author 3flim
 */
public class DDGUI_SpielFeld extends JPanel {

    public final int WIDTH;
    private final int HEIGHT;

    private BufferedImage stein;
    private BufferedImage boden;
    private BufferedImage player;
    private BufferedImage monster;
    private int playerX = 2;
    private int playerY = 2;

    private BufferedImage ziel;
    private int zielX = 25;
    private int zielY = 25;

    public int x;
    public int y;

    DD_Spieler dd_player = new DD_Spieler();

    private Object[][] field;

    public Object[][] getField() {
        return field;
    }

    public void setField(Object[][] field) {
        this.field = field;
    }
    private final int ZELLEN = 30;
    public final int ratio;
    DD_Figurkeylistener figurkeylistener = new DD_Figurkeylistener(this);
    DD_Statuslistener status = new DD_Statuslistener(this);

    DDGUI_RootFrame root;

    public DDGUI_SpielFeld(DDGUI_RootFrame root, int width, int height) {

        this.root = root;
        this.addKeyListener(figurkeylistener);
        this.addMouseListener(status);

        this.WIDTH = width;
        this.HEIGHT = height;
        this.ratio = this.WIDTH / this.ZELLEN;

        try {
            this.boden = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/boden.png"));
            this.stein = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/stein.png"));
            this.player = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/player.png"));
            this.ziel = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/ziel.png"));
            this.monster = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/monster.png"));

        } catch (IOException ex) {
            Logger.getLogger(DDGUI_SpielFeld.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.field = new Object[ZELLEN][ZELLEN];

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {
                if (Math.random() < 0.7f) {
                    this.field[i][j] = new DD_Umgebung("boden");//boden
                } else {
                    this.field[i][j] = new DD_Umgebung("baum");//baum
                }
                if (Math.random() > 0.95f) {
                    this.field[i][j] = new DD_Monster();//Monster
                }
            }
        }

        this.field[this.zielX][this.zielY] = new DD_Umgebung("boden");
        this.field[this.playerX][this.playerY] = dd_player;
        this.setFocusable(true);
        this.setSize(width, height);
    }

    public DDGUI_RootFrame getRoot() {
        return root;
    }

    public void setRoot(DDGUI_RootFrame root) {
        this.root = root;
    }

    public void moveCharacter(int i, String dir) {

        if (dir.equals("x")) {
            field[this.playerX][this.playerY] = new DD_Umgebung("boden");
            this.playerX += i;
            field[this.playerX][this.playerY] = dd_player;
            if (!Zugmoeglich()) {
                field[this.playerX][this.playerY] = new DD_Umgebung("boden");
                this.playerX -= i;
                field[this.playerX][this.playerY] = dd_player;
            }

        }
        if (dir.equals("y")) {
            field[this.playerX][this.playerY] = new DD_Umgebung("boden");

            this.playerY += i;
            field[this.playerX][this.playerY] = dd_player;
            if (!Zugmoeglich()) {
                field[this.playerX][this.playerY] = new DD_Umgebung("boden");
                this.playerY -= i;
                field[this.playerX][this.playerY] = dd_player;
            }
        }

        repaint();
    }

    public boolean Zugmoeglich() {
        try {
            if (playerX == zielX && playerY == zielY) {
                this.removeKeyListener(figurkeylistener);
                JOptionPane.showMessageDialog(this, "Gewonnen! Schatztruhe gefunden");

            }

            if (field[this.playerX][this.playerY] instanceof DD_Umgebung) {
                DD_Umgebung umg = (DD_Umgebung) field[this.playerX][this.playerY];

                if (umg.getTyp().equals("boden")) {
                    return true;
                }

            }

            if (field[this.playerX][this.playerY] instanceof DD_Monster) {
                System.out.println("Kampf");
                return true;
            }
        } catch (Exception ex) {

        }
        return false;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {
                if (this.field[i][j] instanceof DD_Umgebung) {

                    DD_Umgebung umgebung = (DD_Umgebung) this.field[i][j];

                    switch (umgebung.getTyp()) {
                        case "boden":
                            g.drawImage(this.boden, i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                            break;
                        case "baum":
                            g.drawImage(this.stein, i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                            break;
                    }

                } else if (this.field[i][j] instanceof DD_Monster) {
                    g.drawImage(this.monster, i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                } else if (this.field[i][j] instanceof DD_Spieler) {
                    g.drawImage(this.player, i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                }
            }
        }

        //ziel
        g.drawImage(this.ziel, this.zielX * this.ratio, this.zielY * this.ratio, this.ratio, this.ratio, null);
        //g.drawImage(this.player, this.playerX * this.ratio, this.playerY * this.ratio, this.ratio, this.ratio, null);

    }

}
