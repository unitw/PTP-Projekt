/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

/**
 *
 * @author tw
 */
public class DDGUI_CharacterAuwahl extends JFXPanel {

    Button krieger = new Button("Krieger");
    Button Level2 = new Button("Level 2");
    Button Level3 = new Button("Level 3");
    Button Level4 = new Button("Level 4");
    Label star = new Label();

    GridPane pane;
    Scene scene;
    DDGUI_RootFrame root;

    public DDGUI_CharacterAuwahl(DDGUI_RootFrame root) {
        super();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                pane = new GridPane();
                scene = new Scene(pane);

                DDGUI_CharacterAuwahl.this.setScene(scene);
                DDGUI_CharacterAuwahl.this.root = root;

            //    Image img = new Image(getClass().getResourceAsStream("../resources/Linksil.png"));
                pane.setTranslateX(400);
         //       krieger.setGraphic(new ImageView(img));
                krieger.setId("krieger");

                scene.getStylesheets().add(this.getClass().getResource("fxStyle.css").toExternalForm());
                pane.setBackground(Background.EMPTY);
                pane.add(krieger, 0, 0);

                krieger.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {

                        root.startSpiel(1);

                    }
                });

              
                
                DDGUI_CharacterAuwahl.this.setBackground(Color.white);
                DDGUI_CharacterAuwahl.this.setPreferredSize(new Dimension(500, 500));
                DDGUI_CharacterAuwahl.this.setScene(scene);
            }

        }
        );

    }
  
}
