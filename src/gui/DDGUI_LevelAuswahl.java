/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 *
 * @author tw
 */
public class DDGUI_LevelAuswahl extends JFXPanel {

    Button Level1 = new Button("Level 1");
    StackPane pane = new StackPane();
    Scene scene = new Scene(pane);

    public DDGUI_LevelAuswahl() {
        super();
        Level1.setPrefSize(100, 100);

        pane.getChildren().add(Level1);
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(500, 500));
        this.setScene(scene);
    }

}
