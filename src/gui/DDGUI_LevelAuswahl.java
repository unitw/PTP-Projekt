/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

/**
 *
 * @author tw
 */
public class DDGUI_LevelAuswahl extends JFXPanel {

    Button Level1 = new Button("Level 1");
    Button Level2 = new Button("Level 2");
    Button Level3 = new Button("Level 3");
    Button Level4 = new Button("Level 4");
    Label star = new Label();

    GridPane pane = new GridPane();
    Scene scene = new Scene(pane);
    DDGUI_RootFrame root;

    public DDGUI_LevelAuswahl(DDGUI_RootFrame root) {
        super();
        this.setScene(scene);
        this.root = root;
        Level1.setPrefSize(100, 100);
        Level2.setPrefSize(100, 100);
        Level3.setPrefSize(100, 100);
        Level4.setPrefSize(100, 100);

        Level2.setDisable(true);
        Level3.setDisable(true);
        Level4.setDisable(true);

        star.setPrefSize(50, 50);

        scene.getStylesheets().add(this.getClass().getResource("fxStyle.css").toExternalForm());
        pane.setBackground(Background.EMPTY);
        pane.add(Level1, 0, 0);
        pane.add(star, 0, 1);
        pane.add(Level2, 1, 0);
        pane.add(Level3, 2, 0);
        pane.add(Level4, 3, 0);

        Level1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                root.startSpiel();

            }
        });

        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(500, 500));
        this.setScene(scene);
    }

}
