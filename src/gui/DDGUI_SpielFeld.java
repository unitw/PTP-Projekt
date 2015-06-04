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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import listener.DD_Figurkeylistener;

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
    private int playerX = 2;
    private int playerY = 2;

    private BufferedImage ziel;
    private int zielX = 25;
    private int zielY = 25;

    public int x;
    public int y;

    private boolean[][] field;
    private final int ZELLEN = 30;
    public final int ratio;
    DD_Figurkeylistener figurkeylistener = new DD_Figurkeylistener(this);

    public DDGUI_SpielFeld(int width, int height) {

        this.addKeyListener(figurkeylistener);

        this.WIDTH = width;
        this.HEIGHT = height;
        this.ratio = this.WIDTH / this.ZELLEN;

        try {
            this.boden = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/boden.png"));
            this.stein = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/stein.png"));
            this.player = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/player.png"));
            this.ziel = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/ziel.png"));
        } catch (IOException ex) {
            Logger.getLogger(DDGUI_SpielFeld.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.field = new boolean[ZELLEN][ZELLEN];

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {
                if (Math.random() < 0.7f) {
                    this.field[i][j] = true;
                } else {
                    this.field[i][j] = false;
                }
            }
        }

        this.field[this.zielX][this.zielY] = true;
        this.field[this.playerX][this.playerY] = true;
        this.setFocusable(true);
        this.setSize(width, height);
    }

    public void moveCharacter(int i, String dir) {

        if (dir.equals("x")) {

            this.playerX += i;
            if (!Zugmoeglich()) {
                this.playerX -= i;

            }

        }
        if (dir.equals("y")) {
            this.playerY += i;
            if (!Zugmoeglich()) {
                this.playerY -= i;
            }
        }

        repaint();
    }

    public boolean Zugmoeglich() {

        if (playerX == zielX && playerY == zielY) {
            this.removeKeyListener(figurkeylistener);
            JOptionPane.showMessageDialog(this, "Gewonnen! Schatztruhe gefunden");

        }

        return field[this.playerX][this.playerY];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {
                if (this.field[i][j]) {
                    g.drawImage(this.boden, i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                } else {
                    g.drawImage(this.stein, i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                }
            }
        }

        g.drawImage(this.player, this.playerX * this.ratio, this.playerY * this.ratio, this.ratio, this.ratio, null);
        //ziel
        g.drawImage(this.ziel, this.zielX * this.ratio, this.zielY * this.ratio, this.ratio, this.ratio, null);

    }

}
