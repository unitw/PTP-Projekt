/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import XML.StaxStore;
import XML.StaxWriter;
import java.awt.Color;
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
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
import spiellogik.MonsterKI;
//http://www.google.de/imgres?imgurl=http%3A%2F%2Ffc03.deviantart.com%2Ffs27%2Fi%2F2008%2F035%2F5%2Fd%2FDeveloppers_Icons_by_Sekkyumu.png&imgrefurl=http%3A%2F%2Ffxexperience.com%2F2009%2F07%2Ffree-icons-for-your-javafx-applications%2F&h=288&w=600&tbnid=YqrmDiYd05NwKM%3A&zoom=1&docid=4ky5FndJZy1KPM&ei=_viTVY_YA8T_Uv-mpcAF&tbm=isch&iact=rc&uact=3&dur=144&page=1&start=0&ndsp=51&ved=0CCEQrQMwAA

/**
 *
 *
 * @author 3flim
 */
public class DDGUI_SpielFeld extends JPanel implements StaxStore {

    public final int WIDTH;
    private final int HEIGHT;

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

    public Object[][] getField() {
        return field;
    }

    public void setField(Object[][] field) {
        this.field = field;
    }
    private final int ZELLEN = 30;
    public final int ratio;

    public int getRatio() {
        return ratio;
    }
    DD_Figurkeylistener figurkeylistener = new DD_Figurkeylistener(this);
    DD_Statuslistener status = new DD_Statuslistener(this);

    DDGUI_RootFrame root;

    private int runde = 0;

    public DDGUI_SpielFeld(DDGUI_RootFrame root, int width, int height) {
        this.setLayout(null);
        this.root = root;
        this.addKeyListener(figurkeylistener);
        this.addMouseListener(status);
        this.setBackground(Color.white);
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

        for (int i = 0; i < ZELLEN; i++) {
            for (int j = 0; j < ZELLEN; j++) {
                if (Math.random() < 0.7f) {
                    this.field[i][j] = new DD_Umgebung("boden", i, j);//boden
                } else {
                    this.field[i][j] = new DD_Umgebung("baum", i, j);//baum
                }
                if (Math.random() > 0.991f) {

                    DD_Monster mon = new DD_Monster(i, j);
                    monsterlist.add(mon);
                    this.field[i][j] = mon;//Monster
//
                }
            }
        }

        this.field[this.zielX][this.zielY] = new DD_Umgebung("boden", this.zielX, this.zielY);
        this.field[dd_player.getXpos()][dd_player.getYpos()] = dd_player;
//        DD_Monster mon = new DD_Monster(3, 3);
//        monsterlist.add(mon);
//           this.field[3][3] = mon;//Monster

        this.setFocusable(true);
        this.setSize(width, height);
        monstermovement();
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

    public void nextRound() {
//        monstermovement();

        if (getDD_player().getL_mana() < 30) {
            getDD_player().setL_mana(getDD_player().getL_mana() + 7);
        }
        runde += 1;
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                DDGUI_SpielFeld.this.getRoot().getArea().appendText("Runde:" + runde + "\n");
                System.out.println("Runde:" + runde);
            }
        });

    }

    MonsterKI ki;
    Timer timer1;

    public void monstermovement() {

        timer1 = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 1 Sekunde abziehen
                for (DD_Monster monster1 : monsterlist) {
                    ki = new MonsterKI(monster1, DDGUI_SpielFeld.this);
                    if (ki.getDir() != null) {
                        moveSomething(monster1, ki.getWert(), ki.getDir());
                    }
                }
            }

        });

        timer1.start();

    }

    JDialog optiondia = new JDialog();

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
        optionPanel.setBackground(Color.white);
        optiondia.pack();
        optiondia.setModal(true);
        //        optiondia.setUndecorated(true);
        optiondia.setLocationRelativeTo(root);
        optiondia.setVisible(true);

    }

    public void attack(DD_Spieler sp, int attackNr) {

        int mana = sp.getL_mana();
        if (mana < 10) {
            return;
        }
        int xpos = sp.getXpos();
        int ypos = sp.getYpos();
        int schaden = sp.getL_schaden();
        int direction = sp.getDir();
        int range = sp.getFaehigkeitRange(attackNr);

        Map<Integer, Point> directionMap = new HashMap();

        directionMap.put(0, new Point(xpos, ypos - range));
        directionMap.put(1, new Point(xpos, ypos + range));
        directionMap.put(2, new Point(xpos + range, ypos));
        directionMap.put(3, new Point(xpos - range, ypos));

        if (field[(int) directionMap.get(direction).getX()][(int) directionMap.get(direction).getY()] instanceof DD_Monster) {
            DD_Monster mon = (DD_Monster) field[(int) directionMap.get(direction).getX()][(int) directionMap.get(direction).getY()];
            showAttackEffect(attackNr, mon.getXpos(), mon.getYpos(), range);
            Schadenberechnung(mon, schaden);

            sp.setL_mana(mana - 5);
        }

        repaint();
        nextRound();
    }
    Timer timer;
    JLabel AttackAnimation;

    public Timer getTimer() {
        return timer;
    }

    public void showAttackEffect(int attacknr, int xpos, int ypos, int range) {

        if (AttackAnimation != null) {
            DDGUI_SpielFeld.this.remove(AttackAnimation);
        }
        AttackAnimation = new JLabel();
        if (timer != null) {
            timer.stop();
        }

        if (attacknr == 1) {
            AttackAnimation.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/feuerball1.gif")));
        } else if (attacknr == 2) {
            AttackAnimation.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/wasserball2.gif")));
//todo dritte attacke
        } else if (attacknr == 3) {
            AttackAnimation.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/wasserball2.gif")));

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

    public void Schadenberechnung(DD_Monster mon, int schaden) {

        int newleben = (mon.getL_leben() - schaden);
        if (newleben < 0) {
            newleben = 0;

            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    DDGUI_SpielFeld.this.getRoot().getArea().appendText("Monster besiegt\n");

                }
            });

            System.out.println("Monster besiegt");
            monsterlist.remove(mon);
            field[mon.getXpos()][mon.getYpos()] = new DD_Umgebung("boden", mon.getXpos(), mon.getYpos());
            this.remove(mon.getL_gif());
            mon.getMenu().removeAll();

        }
        mon.setL_leben(newleben);
        mon.showMenu(root.getInfopanel());
        mon.getMenu().revalidate();
        mon.getMenu().repaint();
        repaint();
    }

    public boolean Zugmoeglich(int x, int y) {
        try {

            if (dd_player.getXpos() == zielX && dd_player.getYpos() == zielY) {
                this.removeKeyListener(figurkeylistener);
                timer.stop();
               
                Stage dialog = new Stage();
                dialog.initStyle(StageStyle.UTILITY);
                Scene scene = new Scene(new Group(new Text(25, 25, "Gewonnen! Schatztruhe gefunden")));
                dialog.setScene(scene);
                dialog.show();

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
                        g.setColor(Color.white);
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
                        g.setColor(Color.red);
                        g.drawRect(i * this.ratio, j * this.ratio, this.ratio + 1, this.ratio + 1);
                    }
                } else if (this.field[i][j] instanceof DD_Spieler) {

                    //   g.drawImage(this.dd_player.getPlayerImage(), i * this.ratio, j * this.ratio, this.ratio, this.ratio, null);
                    DD_Spieler sp = (DD_Spieler) this.field[i][j];
                    this.remove(sp.getL_gif());
                    sp.getL_gif().setBounds(i * DDGUI_SpielFeld.this.getRatio(), j * DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio(), DDGUI_SpielFeld.this.getRatio());
                    this.add(sp.getL_gif());

                    if (sp.isHasfocus()) {
                        g.setColor(Color.green);
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

    public int getGesamt() {
        return gesamt;
    }

    public int getAktvalue() {
        return aktvalue;
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
    public void setXpos(String s
    ) {

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
