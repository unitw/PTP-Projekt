/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
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
import spiellogik.IDD_Movable;

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

    ArrayList<DD_Monster> monsterlist = new ArrayList();
    DD_Spieler dd_player = new DD_Spieler(2, 2);

    public DD_Spieler getDd_player() {
        return dd_player;
    }

    public void setDd_player(DD_Spieler dd_player) {
        this.dd_player = dd_player;
    }

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

    private int runde = 0;

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

                    DD_Monster mon = new DD_Monster(i, j);
                    monsterlist.add(mon);
                    this.field[i][j] = mon;//Monster

                }
            }
        }

        this.field[this.zielX][this.zielY] = new DD_Umgebung("boden");
        this.field[dd_player.getXpos()][dd_player.getYpos()] = dd_player;
        this.setFocusable(true);
        this.setSize(width, height);
    }

    public DDGUI_RootFrame getRoot() {
        return root;
    }

    public int getRunde() {
        return runde;
    }

    public void setRunde(int runde) {
        this.runde = runde;
    }

    public void setRoot(DDGUI_RootFrame root) {
        this.root = root;
    }

    public void moveCharacter(int i, String dir) {

        Object o = null;
        if (dir.equals("x")) {
            field[this.playerX][this.playerY] = new DD_Umgebung("boden");

            this.playerX += i;

//            if (playerX == -1) {
//                playerX = 0;
//                repaint();
//                return;
//            }
//            if (playerX == 31) {
//                playerX = 30;
//                repaint();
//                return;
//            }
//            if (playerX < 0) {
//                playerX -= i;
//                return;
//            }
//
//            if (playerX > ZELLEN) {
//                playerX -= i;
//                return;
//            }
            o = field[this.playerX][this.playerY];

            if (Zugmoeglich(this.playerX, this.playerY)) {
                field[this.playerX][this.playerY] = dd_player;
            } else {
                field[this.playerX][this.playerY] = o;

                this.playerX -= i;

                field[this.playerX][this.playerY] = dd_player;

            }

        }
        if (dir.equals("y")) {
            field[this.playerX][this.playerY] = new DD_Umgebung("boden");

            this.playerY += i;

            if (field[this.playerX][this.playerY] != null) {

//                if (playerY < 0 || playerY > ZELLEN) {
//                    playerY -= i;
//                    return;
//                }
                o = field[this.playerX][this.playerY];

                if (Zugmoeglich(this.playerX, this.playerY)) {
                    field[this.playerX][this.playerY] = dd_player;
                } else {

                    field[this.playerX][this.playerY] = o;

                    this.playerY -= i;
                    field[this.playerX][this.playerY] = dd_player;

                }
            }
            nextRound();

            repaint();
        }
    }

    public void moveChar(Object o, int i, String dir) {

        if (o instanceof IDD_Movable) {
            IDD_Movable move = (IDD_Movable) o;
            int xpos = move.getXpos();
            int ypos = move.getYpos();
            if (dir.equals("x")) {
                xpos += i;
                if (Zugmoeglich(xpos, ypos)) {
                    field[xpos - i][ypos] = new DD_Umgebung("boden");
                    field[xpos][ypos] = move;
                    move.setXpos(xpos);

                }

            } else {

                ypos += i;
                if (Zugmoeglich(xpos, ypos)) {

                    field[xpos][ypos - i] = new DD_Umgebung("boden");

                    field[xpos][ypos] = move;
                    move.setYpos(ypos);
                }

            }

        }
        nextRound();
        repaint();
    }

    public void moveSomething(Object o, int i, String dir) {

        if (o instanceof IDD_Movable) {
            IDD_Movable move = (IDD_Movable) o;
            int xpos = move.getXpos();
            int ypos = move.getYpos();
            if (dir.equals("x")) {
                xpos += i;
                if (Zugmoeglich(xpos, ypos)) {
                    field[xpos - i][ypos] = new DD_Umgebung("boden");
                    field[xpos][ypos] = move;
                    move.setXpos(xpos);

                }

            } else {

                ypos += i;
                if (Zugmoeglich(xpos, ypos)) {

                    field[xpos][ypos - i] = new DD_Umgebung("boden");

                    field[xpos][ypos] = move;
                    move.setYpos(ypos);
                }

            }

        }
      
        repaint();
    }

    public void nextRound() {
        monstermovement();
        runde += 1;
    }

    public void monstermovement() {
        int i = 0;
        String dir = "";

        for (DD_Monster monster1 : monsterlist) {

            if (Math.random() > 0.5) {
                i = -1;
            } else {
                i = 1;
            }

            if (Math.random() > 0.5) {
                dir = "x";
            } else {
                dir = "y";
            }

            moveSomething(monster1, i, dir);
        }
    }

    public boolean Zugmoeglich(int x, int y) {
        try {
            if (dd_player.getXpos() == zielX && dd_player.getYpos() == zielY) {
                this.removeKeyListener(figurkeylistener);
                JOptionPane.showMessageDialog(this, "Gewonnen! Schatztruhe gefunden");
                return false;
            }

            if (field[x][y] instanceof DD_Umgebung) {
                DD_Umgebung umg = (DD_Umgebung) field[x][y];

                if (umg.getTyp().equals("boden")) {
                    return true;
                }

            }

            if (field[x][y] instanceof DD_Monster) {
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
