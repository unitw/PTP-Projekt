/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import XML.SaxReader;
import XML.StaxWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import net.miginfocom.swing.MigLayout;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author 3flim
 */
public class DDGUI_RootFrame extends JFrame {

    private DDGUI_SpielFeld feld = new DDGUI_SpielFeld(this, 800, 775);
    private DDGUI_InfoPanel infopanel = new DDGUI_InfoPanel();
    private DDGUI_LevelAuswahl lvlselect = new DDGUI_LevelAuswahl(this);
    private DDGUI_CharacterAuwahl charselect = new DDGUI_CharacterAuwahl(this);
    private JMenuBar spielmenuBar = new JMenuBar();
    private JPanel gesamtPane = new JPanel();

    DDGUI_ContentPanel contentPanel = new DDGUI_ContentPanel();

     /**
      * Spielfeld getter
      * @return das aktuelle Spielfeld
      */
    public DDGUI_SpielFeld getFeld() {
        return feld;
    }

     /**
      * Kann ein neues Spielfeld gesetzt werden
      * @param feld neues Spielfeld
      */
    public void setFeld(DDGUI_SpielFeld feld) {
        this.feld = feld;
    }

     /**
      * Gibt das InfoPanel zum Anzeigen der Monster Informationen 
      * @return  DDGUI_InfoPanel
      */
    public DDGUI_InfoPanel getInfopanel() {
        return infopanel;
    }

     /**
      * Gibt die Area zum anzeigen der Spielfeld ereignisse wider
      * @return FXArea
      */
    public TextArea getArea() {
        return area;
    }

    
     /**
      * 
      * @param area  den Spielfeld output
      */
    public void setArea(TextArea area) {
        this.area = area;
    }

     /**
      * Konstruktor
      */
    public DDGUI_RootFrame() {
        gesamtPane.setLayout(new BorderLayout());

        //  setUpMenubar(spielmenuBar);
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        feld.setPreferredSize(new Dimension(775, 775));

        infopanel.setPreferredSize(new Dimension(200, 600));

        contentPanel.add(lvlselect, BorderLayout.CENTER);
        contentPanel.add(charselect, BorderLayout.SOUTH);

        java.awt.Image img = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/logoklein.png"));

        } catch (IOException ex) {
            Logger.getLogger(DDGUI_RootFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setIconImage(img);
        gesamtPane.add(FXMenuBar(), BorderLayout.NORTH);
        gesamtPane.add(contentPanel, BorderLayout.CENTER);
        this.setSize(new Dimension(1100, 900));
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(gesamtPane);
    }

    Map<Integer, URL> lvlXml = new HashMap();

     /**
      * hier werden die level auswahl gesetzt und aus den mit dem Editor erstellte xml geladen
      * @param i Level nummer
      */
    public void startSpiel(int i) {
        lvlXml.put(1, getClass().getResource("/resources/FirstLevel.xml"));
        lvlXml.put(2, getClass().getResource("/resources/Test.xml"));

        XMLReader xmlReader = null;
        try {
            xmlReader = XMLReaderFactory.createXMLReader();
        } catch (SAXException ex) {
            System.out.println("kein Pfad ausgewählt");
        }
        SaxReader stxrd = new SaxReader();

        //InputStream istream = getClass().getResourceAsStream("/resources/FirstLevel.xml");
        // Pfad zur XML Datei
        FileReader reader = null;
        try {
            reader = new FileReader(lvlXml.get(i).getPath());
        } catch (FileNotFoundException ex) {
            System.out.println("kein Pfad ausgewählt");
        }
        InputSource inputSource = new InputSource(reader);

        xmlReader.setContentHandler(stxrd);
        try {
            // Parsen wird gestartet
            xmlReader.parse(inputSource);
        } catch (IOException | SAXException ex) {
            System.out.println("kein Pfad ausgewählt");
        }

        feld.setLvl(i);
        feld.setField(stxrd.spielfeld);
        feld.repaint();

        contentPanel.removeAll();
        TextArea();
        JPanel panNorth = new JPanel();
        JPanel AreaInfopanel = new JPanel();
        AreaInfopanel.setPreferredSize(new Dimension(300, 350));

        AreaInfopanel.setLayout(new MigLayout());
        infopanel.setPreferredSize(new Dimension(200, 700));
        AreaInfopanel.add(infopanel, "cell 0 0");
        AreaInfopanel.add(fxPanelTextArea, "cell 0 1");

        panNorth.setLayout(new BorderLayout());
        //panNorth.add(FXMenuBar(), BorderLayout.NORTH);^
        panNorth.add(feld, BorderLayout.CENTER);
        contentPanel.add(panNorth, BorderLayout.NORTH);
        panNorth.add(AreaInfopanel, BorderLayout.EAST);
        SpielerSkillbar();
        panNorth.add(SpielerSkillBar, BorderLayout.PAGE_END);
        //SpielerSkillbar().setPreferredSize(new Dimension(400,60));

        this.revalidate();
        this.repaint();

    }

    // //<editor-fold defaultstate="collapsed" desc="old menubar">
    //public final void setUpMenubar(JMenuBar menu) {
//
//        JMenu spielmenu = new JMenu("Spiel Optionen");
//        JButton speichern = new JButton("Spiel speichern");
//        speichern.setBorder(null);
//        speichern.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                String path = null;
//                try {
//                    StaxWriter staxwriter = new StaxWriter();
//                    final JFileChooser fc = new JFileChooser("C:/");
//                    FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter(
//                            ".xml", ".xml");
//                    fc.setFileFilter(xmlFilter);
//                    int a = fc.showSaveDialog(speichern);
//
//                    if (fc.getSelectedFile().getPath() != null) {
//                        path = fc.getSelectedFile().getPath();
//                        if (path.endsWith(path)) {
//                            path = path + ".xml";
//                        }
//                    }
//
//                    if (path != null) {
//                        staxwriter.writer = staxwriter.outputFactory.createXMLEventWriter(
//                                new FileOutputStream(path));
//
//                        StoreProject(staxwriter);
//                    }
//                } catch (Exception ex) {
//                    System.out.println("kein Pfad ausgewählt");
//
//                }
//                System.out.println("Gespeichert in " + path);
//            }
//        });
//
//        JButton open = new JButton("Spiel oeffnen");
//
//        open.setBorder(null);
//        open.addActionListener((ActionEvent ae) -> {
//            final JFileChooser fc1 = new JFileChooser("C:/");
//
//            XMLReader xmlReader = null;
//            try {
//                xmlReader = XMLReaderFactory.createXMLReader();
//            } catch (SAXException ex) {
//                System.out.println("kein Pfad ausgewählt");
//            }
//            SaxReader stxrd = new SaxReader();
//            int a1 = fc1.showOpenDialog(open);
//            if (fc1.getSelectedFile().getPath() == null) {
//                return;
//            }
//            String path1 = fc1.getSelectedFile().getPath();
//            // Pfad zur XML Datei
//            FileReader reader = null;
//            try {
//                reader = new FileReader(path1);
//            } catch (FileNotFoundException ex) {
//                System.out.println("kein Pfad ausgewählt");
//            }
//            InputSource inputSource = new InputSource(reader);
//
//            xmlReader.setContentHandler(stxrd);
//            try {
//                // Parsen wird gestartet
//                xmlReader.parse(inputSource);
//            } catch (IOException | SAXException ex) {
//                System.out.println("kein Pfad ausgewählt");
//            }
//
//            feld.setField(stxrd.spielfeld);
//            feld.repaint();
//            System.out.println(path1 + " geoeffnet");
//        });
//
//        spielmenu.add(speichern);
//
//        spielmenu.add(open);
//
//        spielmenu.add(new JMenu("Spiel Optionen"));
//
//        menu.add(spielmenu);
//
//        menu.setBackground(Color.white);
//
//    }
//</editor-fold>
    
     /**
      * Obere menu leiste wird erstellt
      * @return Menubar oberer Rand
      */
    public JFXPanel FXMenuBar() {
        JFXPanel menubar = new JFXPanel();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                Scene scene = new Scene(new VBox(), 1000, 25);
                scene.setFill(javafx.scene.paint.Color.OLDLACE);

                MenuBar menuBar = new MenuBar();

                // --- Menu File
                Menu optionen = new Menu("Optionen");
                MenuItem open = new MenuItem("Spiel oeffnen");
                open.setOnAction(new EventHandler<javafx.event.ActionEvent>() {

                    @Override
                    public void handle(javafx.event.ActionEvent event) {

                        final JFileChooser fc1 = new JFileChooser("C:/");

                        XMLReader xmlReader = null;
                        try {
                            xmlReader = XMLReaderFactory.createXMLReader();
                        } catch (SAXException ex) {
                            System.out.println("kein Pfad ausgewählt");
                        }
                        SaxReader stxrd = new SaxReader();
                        int a1 = fc1.showOpenDialog(null);
                        if (fc1.getSelectedFile().getPath() == null) {
                            return;
                        }
                        String path1 = fc1.getSelectedFile().getPath();
                        // Pfad zur XML Datei
                        FileReader reader = null;
                        try {
                            reader = new FileReader(path1);
                        } catch (FileNotFoundException ex) {
                            System.out.println("kein Pfad ausgewählt");
                        }
                        InputSource inputSource = new InputSource(reader);

                        xmlReader.setContentHandler(stxrd);
                        try {
                            // Parsen wird gestartet
                            xmlReader.parse(inputSource);
                        } catch (IOException | SAXException ex) {
                            System.out.println("kein Pfad ausgewählt");
                        }

                        feld.setField(stxrd.spielfeld);
                        feld.repaint();
                        System.out.println(path1 + " geoeffnet");
                    }
                });

                MenuItem save = new MenuItem("Spiel speichern");
                save.setOnAction(new EventHandler<javafx.event.ActionEvent>() {

                    @Override
                    public void handle(javafx.event.ActionEvent event) {
                        String path = null;
                        try {
                            StaxWriter staxwriter = new StaxWriter();
                            final JFileChooser fc = new JFileChooser("C:/");
                            FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter(
                                    ".xml", ".xml");
                            fc.setFileFilter(xmlFilter);
                            int a = fc.showSaveDialog(null);

                            if (fc.getSelectedFile().getPath() != null) {
                                path = fc.getSelectedFile().getPath();
                                if (path.endsWith(path)) {
                                    path = path + ".xml";
                                }
                            }

                            if (path != null) {
                                staxwriter.writer = staxwriter.outputFactory.createXMLEventWriter(
                                        new FileOutputStream(path));

                                StoreProject(staxwriter);
                            }
                        } catch (Exception ex) {
                            System.out.println("kein Pfad ausgewählt");

                        }
                        System.out.println("Gespeichert in " + path);
                    }
                });

                optionen.getItems().addAll(open, save);

                // --- Menu View
                Menu menuView = new Menu("Help");
                Image imageOk = new Image(getClass().getResourceAsStream("/resources/help.gif"));
                Button but = new Button("", new ImageView(imageOk));

                MenuItem spielanleitung = new MenuItem("Spielanleitung", but);
                spielanleitung.setOnAction((javafx.event.ActionEvent event) -> {
                    Stage webdialog = new Stage();
                    webdialog.initStyle(StageStyle.DECORATED);
                    WebView browser = new WebView();

                    URL url = getClass().getResource("/resources/Spielanleitung.html");
                    WebEngine webEngine = browser.getEngine();
                    webEngine.load(url.toString());
                    Scene scene1 = new Scene(browser);

                    webdialog.setMaxHeight(600);
                    webdialog.setTitle("Spielanleitung");
                    webdialog.setScene(scene1);
                    webdialog.show();

                });
                menuView.getItems().add(spielanleitung);

                menuBar.getMenus().addAll(optionen, menuView);

                ((VBox) scene.getRoot()).getChildren().addAll(menuBar);

                menubar.setScene(scene);

            }
        });
        return menubar;
    }

     /**
      *  
      * @param staxwriter Writer des XML Streams(STAX)
      * @throws Exception Fehler Exception
      */
    public void StoreProject(StaxWriter staxwriter) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        // create an EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        // create and write Start Tag
        StartDocument startDocument = eventFactory.createStartDocument();
        staxwriter.writer.add(startDocument);

        // create config open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", "config");
        staxwriter.writer.add(configStartElement);
        staxwriter.writer.add(end);

        feld.STAXStore(staxwriter, eventFactory);
        staxwriter.writer.add(eventFactory.createEndElement("", "", "config"));
        staxwriter.writer.add(end);
        staxwriter.writer.add(eventFactory.createEndDocument());

        staxwriter.writer.close();

    }

    JFXPanel fxPanelTextArea = new JFXPanel();
    TextArea area = new TextArea();

    public JFXPanel TextArea() {

        Platform.runLater(() -> {
            TitledPane t1 = new TitledPane("Spielausgabe", area);

            Scene scene = new Scene(t1);

            area.setPrefSize(300, 150);
            t1.setPrefSize(300, 150);
            fxPanelTextArea.setScene(scene);

            fxPanelTextArea.setPreferredSize(new Dimension(200, 500));
        });

        return fxPanelTextArea;
    }

    JFXPanel SpielerSkillBar = new JFXPanel();
    Image autoattackLabel;
    Image feuerballLabel;
    Image wasserballLabel;

    ProgressBar life;
    ProgressBar Mana;
    Text leben1;
    Text mana1;

     /**
      * Setzt das leben des Spielers
      * @param leben Leben des Spierls
      */
    public void setLeben(double leben) {
        Platform.runLater(() -> {
            life.setProgress(leben);
            leben1.setText(leben * 100 + "");
        });

    }

     /**
      * Gibt das Mana zurück
      * @param mana Mana des Spielers
      */
    public void setMana(double mana) {
        Platform.runLater(() -> {
            Mana.setProgress(mana);
            mana1.setText(mana * 100 + "");
        });
    }

     /**
      * Spielerskillbar wird erstellt
      * @return Skillbar des spielers in einem JFXPanel
      */
    public JFXPanel SpielerSkillbar() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                autoattackLabel = new Image(getClass().getResourceAsStream("../resources/autoattack.gif"));
                feuerballLabel = new Image(getClass().getResourceAsStream("../resources/feuerball1.gif"));
                wasserballLabel = new Image(getClass().getResourceAsStream("../resources/wasserball2.gif"));

                GridPane pane = new GridPane();
                //   TitledPane t1 = new TitledPane("SkillBar Spieler", pane);
                Label skill1 = new Label("Schlag");
                skill1.setGraphic(new ImageView(autoattackLabel));
                skill1.setId("skill1");
                Label skill2 = new Label("Feuerball");
                skill1.setId("skill2");
                skill2.setGraphic(new ImageView(feuerballLabel));

                Label skill3 = new Label("Wasserheilung");
                skill1.setId("skill3");
                skill3.setGraphic(new ImageView(wasserballLabel));

                skill1.setStyle("-fx-border-color: white;");
                skill2.setStyle("-fx-border-color: white;");
                skill3.setStyle("-fx-border-color: white;");

                skill1.setPrefSize(100, 50);
                skill2.setPrefSize(100, 50);
                skill3.setPrefSize(150, 50);

                life = new ProgressBar(1.0);
                life.setId("leben");
                Mana = new ProgressBar(0.3);
                life.setPrefSize(100, 50);
                Mana.setPrefSize(100, 50);

                leben1 = new Text("100");
                leben1.setTranslateX(35);

                Text lebenanz = new Text("Leben: ");
                Text manaanz = new Text("Mana: ");

                leben1.setFill(javafx.scene.paint.Color.BLACK);
                mana1 = new Text("30");
                mana1.setTranslateX(35);
                //mana1.setFill(javafx.scene.paint.Color.WHITE);

                pane.add(skill1, 0, 0);
                pane.add(skill2, 1, 0);
                pane.add(skill3, 2, 0);
                pane.add(lebenanz, 3, 0);
                pane.add(life, 4, 0);
                pane.add(leben1, 4, 0);
                pane.add(manaanz, 5, 0);

                pane.add(Mana, 6, 0);
                pane.add(mana1, 6, 0);

                pane.setTranslateX(100);
                Reflection r = new Reflection();
                r.setFraction(0.7f);

                pane.setEffect(r);
                Scene scene = new Scene(pane);
                scene.getStylesheets().add(this.getClass().getResource("link.css").toExternalForm());

                SpielerSkillBar.setScene(scene);
            }
        });

        return fxPanelTextArea;
    }

}
