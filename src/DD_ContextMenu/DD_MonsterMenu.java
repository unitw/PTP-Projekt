/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_ContextMenu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
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
public class DD_MonsterMenu extends JFXPanel {

    boolean player = false;

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    Label l_leben;
    Label l_mana;
    Label l_ruestung;
    Label l_schaden;

    Label l_faehigkeit1;
    Label l_faehigkeit1gif;

    Label l_faehigkeit2;
    Label l_faehigkeit2gif;

    ProgressBar pb_leben;
    Text t_mana;
    Text t_leben;
    Text t_ruestung;
    Text t_schaden;
    Button t_faehigkeit1;
    Button t_faehigkeit2;

    Font infomenu = new Font("Arial", Font.BOLD, 20);
    GridPane pane = new GridPane();

    public DD_MonsterMenu(boolean player) {

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);

        this.player = player;
        this.setPreferredSize(new Dimension(400, 600));
        this.setLayout(new MigLayout("fill"));

        t_leben = new Text();
        l_leben = new Label("Leben");

        l_mana = new Label("Mana");
        l_ruestung = new Label("Ruestung");
        l_schaden = new Label("Schaden");
        l_faehigkeit1 = new Label("Feuerball");
        l_faehigkeit2 = new Label("Wasserball");

        l_faehigkeit1gif = new Label();
        l_faehigkeit2gif = new Label();

        pb_leben = new ProgressBar();
        pb_leben.setProgress(100);
        t_mana = new Text();
        t_ruestung = new Text();
        t_schaden = new Text();
        t_faehigkeit1 = new Button("Attack");
        t_faehigkeit2 = new Button("Attack");

        pane.add(l_leben, 0, 0);
        pane.add(pb_leben, 1, 0);
        pane.add(t_leben, 2, 0);
        pane.add(l_ruestung, 0, 1);
        pane.add(t_ruestung, 1, 1);
        pane.add(l_mana, 0, 2);
        pane.add(t_mana, 1, 2);

        this.setPreferredSize(new Dimension(300, 200));
        this.setScene(scene);

    }

    public Text getText_leben() {
        return t_leben;
    }

    public void setText_leben(Text t_leben) {
        this.t_leben = t_leben;
        pb_leben.setProgress(Double.parseDouble(t_leben.getText()));

    }

    public void updateLeben(int leben) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                t_leben.setText(leben + "");

                new Thread() {
                    public void run() {

                        Platform.runLater(() -> pb_leben.setProgress((double) leben));

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }

                    }
                }.start();

            }
        });

    }

    public ProgressBar getT_leben() {
        return pb_leben;
    }

    public void setT_leben(ProgressBar t_leben) {
        this.pb_leben = t_leben;
    }

    public Text getT_mana() {
        return t_mana;
    }

    public void setT_mana(Text t_mana) {
        this.t_mana = t_mana;
    }

    public Text getT_ruestung() {
        return t_ruestung;
    }

    public void setT_ruestung(Text t_ruestung) {
        this.t_ruestung = t_ruestung;
    }

    public Text getT_schaden() {
        return t_schaden;
    }

    public void setT_schaden(Text t_schaden) {
        this.t_schaden = t_schaden;
    }

    public Button getT_faehigkeit1() {
        return t_faehigkeit1;
    }

    public void setT_faehigkeit1(Button t_faehigkeit1) {
        this.t_faehigkeit1 = t_faehigkeit1;
    }

    public Button getT_faehigkeit2() {
        return t_faehigkeit2;
    }

    public void setT_faehigkeit2(Button t_faehigkeit2) {
        this.t_faehigkeit2 = t_faehigkeit2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

    }

}
