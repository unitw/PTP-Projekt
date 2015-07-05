/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author tw
 */
public class DDGUI_Launcher extends JDialog {

    public DDGUI_Launcher() {
        super();
        this.setLayout(new BorderLayout());
        JLabel l = new JLabel();
        l.setBackground(Color.white);
        l.setOpaque(false);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/Logo_2.png"));

        l.setIcon(icon);
        setUndecorated(true);
        this.add(l, BorderLayout.NORTH);
        this.add(fxPanel(), BorderLayout.SOUTH);
        this.setBackground(Color.white);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    JFXPanel fxPanel = new JFXPanel();
    ProgressBar bar;
    Button b_start;

    public JFXPanel fxPanel() {
        bar = new ProgressBar();
        Platform.runLater(new Runnable() {

            public void run() {
                BorderPane border = new BorderPane();
                Scene scene = new Scene(border);
                b_start = new Button("Start");
                b_start.setDisable(true);
                b_start.setPrefSize(100, 25);
                b_start.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        DDGUI_RootFrame root = new DDGUI_RootFrame();
                        DDGUI_Launcher.this.dispose();
                    }
                });
                bar.setPrefSize(600, 50);
                bar.setProgress(0.0);

                final Task<Boolean> login = doLogin();

                login.setOnSucceeded((WorkerStateEvent t) -> {
                    b_start.setDisable(false);
                });

                Thread t = new Thread(login);
                t.start();
                //  scene.getStylesheets().add(this.getClass().getResource("fxStyle.css").toExternalForm());
                border.setTop(bar);
                border.setBottom(b_start);
                fxPanel.setScene(scene);

                fxPanel.setPreferredSize(new Dimension(780, 200));
            }
        });

        return fxPanel;
    }
    double i = 0;

    public Task<Boolean> doLogin() {
        return new Task<Boolean>() {
            @Override
            protected Boolean call() {
                Boolean result = null;
                startProgressbar();
                if (bar.getProgress() > 100) {
                    result = Boolean.TRUE;
                }

                return result;
            }
        };
    }

    public void startProgressbar() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500), new KeyValue(bar.progressProperty(), 1))
        );

        timeline.play();
    }

}
