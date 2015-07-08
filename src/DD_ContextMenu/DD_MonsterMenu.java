/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DD_ContextMenu;

import java.awt.Dimension;
import java.awt.Font;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author
 */
public class DD_MonsterMenu extends JPanel {

    Font infomenu = new Font("Arial", Font.BOLD, 20);
    GridPane pane = new GridPane();

    JFXPanel SpielerSkillBar = new JFXPanel();
    Image autoattackLabel;

    ProgressBar life;

    Text leben1;

    /**
     * Hier wird die Statusleiste implementiert
     *
     * @param player
     */
    public DD_MonsterMenu() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                //      autoattackLabel = new Image(getClass().getResourceAsStream("../resources/skull2.gif"));
                GridPane pane = new GridPane();

                //   TitledPane t1 = new TitledPane("SkillBar Spieler", pane);
                Label skill1 = new Label("Geisterriss");
                //    skill1.setGraphic(new ImageView(autoattackLabel));

                skill1.setPrefSize(100, 50);

                life = new ProgressBar();
                life.setProgress(0.8);

                // life.setPreferredSize(new Dimension(100, 50));
                Text lebenanz = new Text("Leben: ");
                leben1 = new Text("100");
                leben1.setTranslateX(35);
                pane.add(skill1, 0, 0);
                pane.add(lebenanz, 1, 0);
                pane.add(life, 1, 1);
                pane.add(leben1, 1, 1);

                Scene scene = new Scene(pane);

                SpielerSkillBar.setScene(scene);
                DD_MonsterMenu.this.add(SpielerSkillBar);

            }
        });

    }

    public void setLeben(double leben) {

        life.setProgress(leben * 0.01);
        leben1.setText(leben + "");

    }
}
