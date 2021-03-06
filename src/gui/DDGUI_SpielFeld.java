/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import XML.StaxStore;
import XML.StaxWriter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.xml.stream.XMLEventFactory;

import listener.DD_Figurkeylistener;
import listener.DD_Statuslistener;
import net.miginfocom.swing.MigLayout;
import spiellogik.DD_Monster;
import spiellogik.DD_Spieler;
import spiellogik.DD_Umgebung;
import spiellogik.DD_Zug;
import spiellogik.IDD_Movable;
import spiellogik.KI.MonsterKI;
//http://www.google.de/imgres?imgurl=http%3A%2F%2Ffc03.deviantart.com%2Ffs27%2Fi%2F2008%2F035%2F5%2Fd%2FDeveloppers_Icons_by_Sekkyumu.png&imgrefurl=http%3A%2F%2Ffxexperience.com%2F2009%2F07%2Ffree-icons-for-your-javafx-applications%2F&h=288&w=600&tbnid=YqrmDiYd05NwKM%3A&zoom=1&docid=4ky5FndJZy1KPM&ei=_viTVY_YA8T_Uv-mpcAF&tbm=isch&iact=rc&uact=3&dur=144&page=1&start=0&ndsp=51&ved=0CCEQrQMwAA

/**
 *
 *
 * @author 3flim
 */
public class DDGUI_SpielFeld extends JPanel implements StaxStore {

    public final int WIDTH;
    private final int HEIGHT;
    private int lvl;
    private BufferedImage stein;
    private BufferedImage boden;
    private BufferedImage player;
    Image image = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource("resources/feuerball1.gif"));
    Image geist = Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemClassLoader().getResource("resources/geist.gif"));

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

    public DD_Spieler getDD_player() {
        return dd_player;
    }

    public void setDd_player(DD_Spieler dd_player) {
        this.dd_player = dd_player;
    }

    
    
    private Object[][] field;

    
    
     /**
      * 
      * @return das Spielfeld auf dem die Figuren Stehen
      */
    public Object[][] getField() {
        return field;
    }

    
     /**
      * 
      * @param field neues Spielfeld gesetzt(muss quardratisch sein)
      */
    public void setField(Object[][] field) {
        this.field = field;
        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {

                if (this.field[i][j] instanceof DD_Monster) {

                    DD_Monster mon = (DD_Monster) this.field[i][j];
                    monsterlist.add(mon);
                }
                if (this.field[i][j] instanceof DD_Spieler) {
                    this.dd_player = (DD_Spieler) this.field[i][j];
                }

            }
        }

    }
    private final int ZELLEN = 30;
    public final int ratio;

     /**
      * Gibt die für die figuren den faktor zurück,mit dem sie beim zeichen multipliziert werden
      * @return  Ratio
      */
    public int getRatio() {
        return ratio;
    }
    DD_Figurkeylistener figurkeylistener = new DD_Figurkeylistener(this);
    DD_Statuslistener status = new DD_Statuslistener(this);

    DDGUI_RootFrame root;

    private int runde = 0;

     /**
      * für die Testklassen einen einfacheren konstruktor
      * @param WIDTH spalten anzahl
      * @param HEIGHT zeilen anzahl
      */
    public DDGUI_SpielFeld(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.ratio = 1;

        this.field = new Object[WIDTH][HEIGHT];
    }

     /**
      * 
      * @param root Rootframe des Spielfeld
      * @param width breite des Spielfeld
      * @param height höhe des Spielfeld
      */
    public DDGUI_SpielFeld(DDGUI_RootFrame root, int width, int height) {
        this.setLayout(null);
        this.root = root;
        this.addKeyListener(figurkeylistener);
        this.addMouseListener(status);
        this.setBackground(java.awt.Color.white);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.ratio = this.WIDTH / this.ZELLEN;

        try {
            this.boden = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/boden.png"));
            this.stein = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/stein.png"));
            this.ziel = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/ziel.png"));
            this.monster = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/monster.png"));

        } catch (IOException ex) {
            Logger.getLogger(DDGUI_SpielFeld.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.field = new Object[ZELLEN][ZELLEN];

        this.setFocusable(true);
        this.setSize(width, height);

        monstermovement();
    }

     /**
      * getter für das rootframe um auf die info panel zurgreifen zu können
      * @return  Den Rootframe für das Spielfeld
      */
    public DDGUI_RootFrame getRoot() {
        return root;
    }

     /**
      * 
      * @return die aktuelle runde
      */
    public int getRunde() {
        return runde;
    }

     /**
      * 
      * @param runde setzt die runde
      */
    public void setRunde(int runde) {
        this.runde = runde;
    }

     /**
      * Setzt den Rootframe für das Spielfeld
      * @param root Rootframe des Spielfelds 
      */
    public void setRoot(DDGUI_RootFrame root) {
        this.root = root;
    }

     /**
      * bewegt den spieler über das feld
      * @param o Spieler der bewegt werden soll
      * @param i weite des Zuges
      * @param dir  Richtung des Zuges (x,y)
      */
    public void moveChar(Object o, int i, String dir) {

        if (o instanceof IDD_Movable) {
            IDD_Movable move = (IDD_Movable) o;
            int xpos = move.getXpos();
            int ypos = move.getYpos();
            if (dir.equals("x")) {
                xpos += i;
                if (Zugmoeglich(xpos, ypos)) {
                    field[xpos - i][ypos] = new DD_Umgebung("boden", xpos - i, ypos);
                    field[xpos][ypos] = move;
                    move.setXpos(xpos);

                }

            } else {

                ypos += i;
                if (Zugmoeglich(xpos, ypos)) {

                    field[xpos][ypos - i] = new DD_Umgebung("boden", xpos, ypos - i);

                    field[xpos][ypos] = move;
                    move.setYpos(ypos);
                }

            }

        }
        nextRound();
        repaint();
    }

     /**
      * bewegt monster oder andere sachen über das feld
      * @param o zubewegendes Objekt    
      * @param i weite des zuges
      * @param dir richtung des zuges
      */
    public void moveSomething(Object o, int i, String dir) {

        if (o instanceof IDD_Movable) {
            IDD_Movable move = (IDD_Movable) o;
            int xpos = move.getXpos();
            int ypos = move.getYpos();
            if (dir.equals("x")) {
                xpos += i;
                if (Zugmoeglich(xpos, ypos)) {
                    field[xpos - i][ypos] = new DD_Umgebung("boden", xpos - i, ypos);
                    field[xpos][ypos] = move;
                    move.setXpos(xpos);

                }

            } else {

                ypos += i;
                if (Zugmoeglich(xpos, ypos)) {

                    field[xpos][ypos - i] = new DD_Umgebung("boden", xpos, ypos - i);

                    field[xpos][ypos] = move;
                    move.setYpos(ypos);
                }

            }

        }

        repaint();
    }

     /**
      * Gibt das level wider
      * @return momentanes lvl
      */
    public int getLvl() {
        return lvl;
    }

     /**
      * 
      * @param lvl setzt neues lvl
      */
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

     /**
      * nach jeder bewegung des spieler wird der runden counter hochgezählt
      * dabei werden mana und leben wieder aufgefüllt
      */
    public void nextRound() {
//        monstermovement();

        if (getDD_player().getL_leben() < 100) {
            int newleben = getDD_player().getL_leben() + 10;
            if (newleben > 100) {
                newleben = 100;
            }

            getDD_player().setL_leben(newleben);
            this.getRoot().setLeben(newleben * 0.01);

        }
        if (getDD_player().getL_mana() < 30) {

            int newmana = getDD_player().getL_mana() + 7;
            if (newmana > 30) {
                newmana = 30;
            }
            getDD_player().setL_mana(newmana);
            getRoot().setMana(newmana * 0.01);
        }
        runde += 1;

    }

    MonsterKI ki;
    Timer timer1;

     /**
      * sucht in einer umgebung range alle felder ab
      * @param x1 zentrum xypos
      * @param y1 zentrum ypos
      * @param range radius des suchraums
      * @return true wenn der spieler in range ist
      */
    public boolean SpielerInRange(int x1, int y1, int range) {

        int px = this.getDD_player().getXpos();
        int py = this.getDD_player().getYpos();

        for (int x = Math.max(0, x1 - range); x < Math.min(this.getField()[0].length, x1 + range + 1); x++) {
            for (int y = Math.max(0, y1 - range); y < Math.min(this.getField().length, y1 + range + 1); y++) {
                if (this.getField()[x][y] instanceof DD_Spieler) {
                    return true;

                }
            }
        }
        return false;
    }

     /**
      * Alle monster werden jeder sekunde durch einen timer bewegt
      */
    public void monstermovement() {

        timer1 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 1 Sekunde abziehen
                if (!monsterlist.isEmpty()) {
                    for (DD_Monster monster1 : monsterlist) {

                        ki = new MonsterKI(monster1, DDGUI_SpielFeld.this, SpielerInRange(monster1.getXpos(), monster1.getYpos(), 4));
                        if (ki.getDir() != null) {
                            moveSomething(monster1, ki.getWert(), ki.getDir());
                        }
                    }
                }
            }

        });

        timer1.start();

    }

    JDialog optiondia = new JDialog();

     /**
      * 
      */
    public void displayOptionDialog() {

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new MigLayout());

        optionPanel.add(new JButton("Save Game"), "cell 0 0,center");
        optionPanel.add(new JButton("Load Game"), "cell 0 1,center");
        optionPanel.add(new JButton("Game Settings"), "cell 0 2,center");
        optionPanel.add(new JButton("Graphics"), "cell 0 3,center");
        optionPanel.add(new JButton("Sound"), "cell 0 4,center");
        optionPanel.add(new JButton("Control"), "cell 0 5,center");
        optionPanel.add(new JButton("End Game"), "cell 0 6,center");

        optionPanel.add(new JButton("Back"), "cell 0  7,center");
        optiondia.add(optionPanel);
        optionPanel.setBackground(java.awt.Color.white);
        optiondia.pack();
        optiondia.setModal(true);
        //        optiondia.setUndecorated(true);
        optiondia.setLocationRelativeTo(root);
        optiondia.setVisible(true);

    }

     /**
      * Attacke des Spielers wird abgefraft und es wird angegriffen wenn eiu monster in range ist
      * @param sp  Spieler der angreifen ist
      * @param attackNr jeweilige Attacke des spielers
      */
    public void Spielerattack(DD_Spieler sp, int attackNr) {

        int xpos = sp.getXpos();
        int ypos = sp.getYpos();
        int schaden = sp.getAttackNr().get(attackNr).getSchaden();
        int direction = sp.getDir();
        int range = sp.getAttackNr().get(attackNr).getRange();
        int mana = sp.getL_mana();
        if (mana < sp.getAttackNr().get(attackNr).getManaverbrauch()) {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    DDGUI_SpielFeld.this.getRoot().getArea().appendText("KEIN MANA MEHR\n");
                }
            });
            return;
        } else {
            mana = sp.getL_mana() - sp.getAttackNr().get(attackNr).getManaverbrauch();
        }
        int leben;
        if (sp.getMAXLEBEN() < sp.getL_leben() + sp.getAttackNr().get(attackNr).getHeilung()) {

            leben = sp.getMAXLEBEN();
        } else {

            leben = sp.getL_leben() + sp.getAttackNr().get(attackNr).getHeilung();
        }
        Map<Integer, Point> directionMap = new HashMap();
        directionMap.put(0, new Point(xpos, ypos - range));
        directionMap.put(1, new Point(xpos, ypos + range));
        directionMap.put(2, new Point(xpos + range, ypos));
        directionMap.put(3, new Point(xpos - range, ypos));

        if (field[(int) directionMap.get(direction).getX()][(int) directionMap.get(direction).getY()] instanceof DD_Monster) {
            DD_Monster mon = (DD_Monster) field[(int) directionMap.get(direction).getX()][(int) directionMap.get(direction).getY()];
            if (attackNr == 3) {
                showAttackEffect(attackNr, xpos, ypos, -1);
            } else {
                showAttackEffect(attackNr, mon.getXpos(), mon.getYpos(), range);
                Schadenberechnung(mon, schaden);
            }
            sp.setL_mana(mana);
            getRoot().setMana(mana * 0.01);
        }
        sp.setL_leben(leben);
        repaint();

    }

     /**
      * Hier wird der angriff des monsters durchgeführt
      * @param sp Monster das angreift
      * @param attackNr  attacke des Monsters
      */
    public void Monsterattack(DD_Monster sp, int attackNr) {

        int xpos = sp.getXpos();
        int ypos = sp.getYpos();
        int schaden = sp.getL_schaden();

        DD_Spieler player = getDD_player();
        showAttackEffect(attackNr, player.getXpos(), player.getYpos(), 1);
        Schadenberechnung(this.getDD_player(), schaden);
        repaint();

    }

    Timer timer;
    JLabel AttackAnimation;

    public Timer getTimer() {
        return timer;
    }

     /**
      * Hier wird auf der jeweiligen Position die Attacken Animation(gif-Bilder) angezeigt
      * @param attacknr jeweilige Attacke
      * @param xpos Ziel X-Coordinate
      * @param ypos Ziel Y-Coordinate
      * @param range  Reichweite der Attacke
            */
    public void showAttackEffect(int attacknr, int xpos, int ypos, int range) {

        if (AttackAnimation != null) {
            DDGUI_SpielFeld.this.remove(AttackAnimation);
        }
        AttackAnimation = new JLabel();
        if (timer != null) {
            timer.stop();
        } else if (attacknr == 1) {
            AttackAnimation.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/autoattack.gif")));
        }
        if (attacknr == 2) {
            AttackAnimation.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/feuerball1.gif")));
        } else if (attacknr == 3) {
            AttackAnimation.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/wasserball2.gif")));
//todo dritte attacke
        } else if (attacknr == 4) {
            AttackAnimation.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/skull2.gif")));

        }
        AttackAnimation.setBorder(null);
        AttackAnimation.setOpaque(false);

        AttackAnimation.setBounds(xpos * DDGUI_SpielFeld.this.ratio + 5, ypos * DDGUI_SpielFeld.this.ratio + range, DDGUI_SpielFeld.this.ratio, DDGUI_SpielFeld.this.ratio);

        DDGUI_SpielFeld.this.add(AttackAnimation);
        DDGUI_SpielFeld.this.revalidate();
        DDGUI_SpielFeld.this.repaint();

        timer = new Timer(1000, new ActionListener() {
            int counterValue = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                // 1 Sekunde abziehen
                counterValue--;

                // Falls Zähler = 0, Countdown abgelaufen!
                if (counterValue == 0) {
                    Platform.runLater(() -> {
                        if (attacknr == 1) {
                            DDGUI_SpielFeld.this.getRoot().getArea().appendText("Feuerball getroffen\n");

                            System.out.println("Feuerball getroffen");
                        } else if (attacknr == 2) {
                            DDGUI_SpielFeld.this.getRoot().getArea().appendText("Wasserball getroffen\n");
                            System.out.println("Wasserball getroffen");
                        }
                    });

                    // Timer stoppen
                    getTimer().stop();
                    DDGUI_SpielFeld.this.remove(AttackAnimation);
                    DDGUI_SpielFeld.this.revalidate();
                    DDGUI_SpielFeld.this.repaint();
                }
            }
        });

        timer.start();

    }

     /**
      * Hier wird die schadensberechnung für Monster und Spieler druchgeführt
      * @param mon Monster oder Spieler der angegriffen wird
      * @param schaden  schaden der von den Lebenspunkten abgezogen wird
      */
    public void Schadenberechnung(IDD_Movable mon, int schaden) {

        int newleben = (mon.getL_leben() - schaden);

        if (mon instanceof DD_Monster) {
            if (newleben < 0) {
                newleben = 0;

                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        if (mon instanceof DD_Monster) {
                            DDGUI_SpielFeld.this.getRoot().getArea().appendText("Monster besiegt\n");
                        }
                    }
                });

                System.out.println("Monster besiegt");
                monsterlist.remove(mon);
                field[mon.getXpos()][mon.getYpos()] = new DD_Umgebung("boden", mon.getXpos(), mon.getYpos());
                this.remove(mon.getL_gif());
                mon.getMenu().removeAll();

            }
            getRoot().getInfopanel().getMenu().setLeben(newleben * 0.01);

        } else {
            if (newleben < 0) {
                newleben = 0;

                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        Stage dialog = new Stage();
                        dialog.initStyle(StageStyle.UNDECORATED);

                        Button reset = new Button("Try Again");

                        reset.setPrefSize(100, 40);
                        reset.setTranslateX(110);
                        reset.setTranslateY(80);
                        reset.setOnAction((javafx.event.ActionEvent event) -> {
                            dialog.close();
                            DDGUI_SpielFeld.this.removeAll();

                            resetSpiel();
                        });

                        Label l = new Label();
                        l.setPrefSize(324, 137);
                        l.setId("grave");
                        l.getTransforms().add(new Rotate(9, 50, 30));

                        Label t = new Label();
                        //  t.setTranslateY(160);
                        t.setTranslateX(75);
                        t.setTranslateY(10);
                        t.setCache(true);
                        t.setText("GAME OVER");
                        t.setId("GAMEOVER");
                        t.getStyleClass().add("animated-gradient");

                        t.setFont(Font.font(null, FontWeight.BOLD, 30));

                        ObjectProperty<Color> baseColor = new SimpleObjectProperty<>();

                        KeyValue keyValue1 = new KeyValue(baseColor, Color.RED);
                        KeyValue keyValue2 = new KeyValue(baseColor, Color.YELLOW);
                        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
                        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyValue2);
                        Timeline timeline = new Timeline(keyFrame1, keyFrame2);

                        baseColor.addListener((obs, oldColor, newColor) -> {
                            t.setStyle(String.format("-gradient-base: #%02x%02x%02x; ",
                                    (int) (newColor.getRed() * 255),
                                    (int) (newColor.getGreen() * 255),
                                    (int) (newColor.getBlue() * 255)));
                        });

                        timeline.setAutoReverse(true);
                        timeline.setCycleCount(Animation.INDEFINITE);
                        timeline.play();

                        Reflection r = new Reflection();
                        r.setFraction(0.7f);

                        t.setEffect(r);

                        // t.setTranslateY(400);
                        GridPane pane = new GridPane();
                        Scene scene = new Scene(pane);

                        pane.setPrefSize(300, 300);
                        pane.getStyleClass().add("bordered-titled-border");
                        pane.add(l, 0, 0);
                        pane.add(t, 0, 1);
                        pane.add(reset, 0, 2);
                        scene.getStylesheets().add(this.getClass().getResource("link.css").toExternalForm());
                        dialog.setMaxHeight(600);
                        dialog.setTitle("Game Over");
                        dialog.setScene(scene);
                        dialog.show();
                        monsterlist.removeAll(monsterlist);

                    }
                });
            }
            getRoot().setLeben(newleben * 0.01);
        }

        mon.setL_leben(newleben);

        repaint();

    }

     /**
      * Hier wird beim tode des spielers das Spiel resetet
      */
    public void resetSpiel() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                getRoot().contentPanel.removeAll();
                //  getRoot().getContentPane().removeAll();
                getRoot().startSpiel(lvl);

            }
        });

    }

     /**
      * Die Methode prüft ob der gewählte zug möglich ist
      * @param x Ziel X-Coordinate
      * @param y Ziel Y-Coordinate
      * @return true wenn der Zug möglich ist 
      */
    public boolean Zugmoeglich(int x, int y) {
        try {

            if (dd_player.getXpos() == zielX && dd_player.getYpos() == zielY) {
                this.removeKeyListener(figurkeylistener);
                timer.stop();
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {

                        monsterlist.removeAll(monsterlist);

                        Stage dialog1 = new Stage();
                        GridPane pane1 = new GridPane();
                        Scene scene = new Scene(pane1);

                        Label t = new Label();
                        //  t.setTranslateY(160);
                        t.setTranslateX(0);
                        t.setTranslateY(0);
                        t.setCache(true);
                        t.setText("Gewonnen");
                        t.setId("NextLevel");
                        t.getStyleClass().add("animated-gradient");

                        t.setFont(Font.font(null, FontWeight.BOLD, 30));

                        ObjectProperty<Color> baseColor = new SimpleObjectProperty<>();

                        KeyValue keyValue1 = new KeyValue(baseColor, Color.RED);
                        KeyValue keyValue2 = new KeyValue(baseColor, Color.YELLOW);
                        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
                        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyValue2);
                        Timeline timeline = new Timeline(keyFrame1, keyFrame2);

                        baseColor.addListener((obs, oldColor, newColor) -> {
                            t.setStyle(String.format("-gradient-base: #%02x%02x%02x; ",
                                    (int) (newColor.getRed() * 255),
                                    (int) (newColor.getGreen() * 255),
                                    (int) (newColor.getBlue() * 255)));
                        });

                        timeline.setAutoReverse(true);
                        timeline.setCycleCount(Animation.INDEFINITE);
                        timeline.play();

                        Button reset = new Button("Next Level");

                        reset.setPrefSize(100, 40);
                        reset.setTranslateX(110);
                        reset.setTranslateY(80);
                        reset.setOnAction((javafx.event.ActionEvent event) -> {
                            dialog1.close();
                            DDGUI_SpielFeld.this.removeAll();

                        });

                        reset.setTranslateX(95);
                        t.setTranslateX(75);

                        Reflection r = new Reflection();
                        r.setFraction(0.7f);

                        t.setEffect(r);
                        pane1.setPrefSize(300, 300);
                        pane1.getStyleClass().add("bordered-titled-border");
                        scene.getStylesheets().add(this.getClass().getResource("link.css").toExternalForm());

                        pane1.add(t, 0, 0);
                        pane1.add(reset, 0, 1);

                        dialog1.setScene(scene);
                        dialog1.setMaxHeight(600);
                        dialog1.setTitle("Winner");

                        dialog1.show();

                    }
                });

//                ImageIcon winlink= new ImageIcon(ClassLoader.getSystemClassLoader().getResource("/resources/link.jpg"));
//                JOptionPane.showMessageDialog(root, image, "Gewonnen", JOptionPane.CLOSED_OPTION);
//
//                monsterlist.removeAll(monsterlist);
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
                return false;
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
                    if (umgebung.isHasfocus()) {
                        g.setColor(java.awt.Color.white);
                        g.drawRect(i * this.ratio, j * this.ratio, this.ratio, this.ratio);
                    }

                } else if (this.field[i][j] instanceof DD_Monster) {

                    DD_Monster mon = (DD_Monster) this.field[i][j];

                    this.remove(mon.getL_gif());

                    final int x = i;
                    final int y = j;

                    mon.getL_gif().setBounds(x * DDGUI_SpielFeld.this.getRatio(), y * DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio());

                    this.add(mon.getL_gif());

                    // g.drawImage(DDGUI_SpielFeld.this.getGeist(), x * DDGUI_SpielFeld.this.getRatio(), y * DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio(), null);
                    DD_Monster m = (DD_Monster) this.field[i][j];
                    if (m.isHasfocus()) {
                        g.setColor(java.awt.Color.red);
                        g.drawRect(i * this.ratio, j * this.ratio, this.ratio + 1, this.ratio + 1);
                    }
                } else if (this.field[i][j] instanceof DD_Spieler) {

                    //   g.drawImage(this.dd_player.getPlayerImage(), i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                    DD_Spieler sp = (DD_Spieler) this.field[i][j];
                    this.remove(sp.getL_gif());
                    sp.getL_gif().setBounds(i * DDGUI_SpielFeld.this.getRatio(), j * DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio());
                    this.add(sp.getL_gif());

                    if (sp.isHasfocus()) {
                        g.setColor(java.awt.Color.green);
                        g.drawRect(i * this.ratio, j * this.ratio, this.ratio, this.ratio);
                    }
                }
            }
        }

        //ziel
        g.drawImage(this.ziel, this.zielX * this.ratio, this.zielY * this.ratio, this.ratio, this.ratio, null);
        g.drawImage(this.player, this.playerX * this.ratio, this.playerY * this.ratio, this.ratio, this.ratio, null);

    }

    int gesamt;
    int aktvalue;

     /**
      * Hier wird das Field Objekt array zeilen weise in XML wegespeichert
      * @param staxwriter Writer des XML STreams
      * @param eventFactory Handler für die Start und End Tags
      */
    @Override
    public void STAXStore(StaxWriter staxwriter, XMLEventFactory eventFactory) {

        Integer xpos = x;

        Integer ypos = y;

        Integer breite = this.getWidth();

        Integer hoehe = this.getHeight();

        String linespos = "";
        String Identifier = null;

        gesamt = ZELLEN * ZELLEN;

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {
                if (this.field[i][j] instanceof StaxStore) {

                    StaxStore stax = (StaxStore) this.field[i][j];

                    stax.STAXStore(staxwriter, eventFactory);
                    aktvalue++;
//                   

                    System.out.println((aktvalue / gesamt) * 100);
                    //root.xmlprogress.setValue(aktvalue / gesamt);
//                    root.xmlprogress.repaint();
                }
            }
        }

    }

    

    

    
    @Override
    public String getIdentifier() {

        return null;
    }

    
    @Override
    public void setIdentifier(String s
    ) {

    }

   
    @Override
    public int getXpos() {
        return this.getX();
    }

    
    @Override
    public void setXpos(String s) {

    }

    @Override
    public int getYpos() {
        return this.getY();
    }

    @Override
    public void setYpos(String s
    ) {

    }

    @Override
    public int getbreite() {
        return this.getWidth();
    }

    @Override
    public void setbreite(String s
    ) {

    }

    @Override
    public int gethoehe() {
        return this.getHeight();
    }

    @Override
    public void sethoehe(String s
    ) {

    }

}
