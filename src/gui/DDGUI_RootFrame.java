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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
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

    private DDGUI_SpielFeld feld = new DDGUI_SpielFeld(this, 800, 500);
    private DDGUI_InfoPanel infopanel = new DDGUI_InfoPanel();
    private DDGUI_LevelAuswahl lvlselect = new DDGUI_LevelAuswahl(this);
    private JMenuBar spielmenuBar = new JMenuBar();
    JPanel contentPanel = new JPanel();

    public DDGUI_SpielFeld getFeld() {
        return feld;
    }

    public void setFeld(DDGUI_SpielFeld feld) {
        this.feld = feld;
    }

    public JPanel getInfopanel() {
        return infopanel;
    }

    public TextArea getArea() {
        return area;
    }

    public void setArea(TextArea area) {
        this.area = area;
    }

    public DDGUI_RootFrame() {
        this.setLayout(new BorderLayout());

        setUpMenubar(spielmenuBar);

        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //umgebunng ziel noch ienbaue
        feld.setPreferredSize(new Dimension(800, 810));

        infopanel.setPreferredSize(new Dimension(200, 600));

        contentPanel.add(lvlselect, BorderLayout.CENTER);
//          contentPanel.add(spielmenuBar,"span 3");
//        contentPanel.add(feld, "span 2");
//        contentPanel.add(infopanel, "span 1,wrap");
//        contentPanel.add(fxPanelTextArea(), "push x");

        Image img = null;
        try {
            img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/logoklein.png"));

        } catch (IOException ex) {
            Logger.getLogger(DDGUI_RootFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setIconImage(img);
        this.add(spielmenuBar, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.setSize(new Dimension(1100, 850));
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    Map<Integer, URL> lvlXml = new HashMap();

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
        fxPanel();
        JPanel panNorth = new JPanel();
        JPanel AreaInfopanel = new JPanel();
        AreaInfopanel.setPreferredSize(new Dimension(300, 380));
        AreaInfopanel.setLayout(new BorderLayout());

        AreaInfopanel.add(infopanel, BorderLayout.NORTH);
        AreaInfopanel.add(fxPanelTextArea, BorderLayout.SOUTH);
        panNorth.setLayout(new BorderLayout());

        panNorth.add(spielmenuBar, BorderLayout.NORTH);
        panNorth.add(feld, BorderLayout.CENTER);
        contentPanel.add(panNorth, BorderLayout.NORTH);
        panNorth.add(AreaInfopanel, BorderLayout.EAST);

        this.revalidate();
        this.repaint();

    }

    public final void setUpMenubar(JMenuBar menu) {

        JMenu spielmenu = new JMenu("Spiel Optionen");
        JButton speichern = new JButton("Spiel speichern");
        speichern.setBorder(null);
        speichern.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String path = null;
                try {
                    StaxWriter staxwriter = new StaxWriter();
                    final JFileChooser fc = new JFileChooser("C:/");
                    FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter(
                            ".xml", ".xml");
                    fc.setFileFilter(xmlFilter);
                    int a = fc.showSaveDialog(speichern);

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

        JButton open = new JButton("Spiel oeffnen");

        open.setBorder(null);
        open.addActionListener((ActionEvent ae) -> {
            final JFileChooser fc1 = new JFileChooser("C:/");

            XMLReader xmlReader = null;
            try {
                xmlReader = XMLReaderFactory.createXMLReader();
            } catch (SAXException ex) {
                System.out.println("kein Pfad ausgewählt");
            }
            SaxReader stxrd = new SaxReader();
            int a1 = fc1.showOpenDialog(open);
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
        });

        spielmenu.add(speichern);

        spielmenu.add(open);

        spielmenu.add(new JMenu("Spiel Optionen"));

        menu.add(spielmenu);

        menu.setBackground(Color.white);

    }

    JDialog dia;
    JProgressBar xmlprogress;

    public void ProgressXML(JDialog dia) {

        xmlprogress = new JProgressBar();
        xmlprogress.setPreferredSize(new Dimension(50, 25));
        dia.add(xmlprogress);
        dia.pack();
        dia.setLocationRelativeTo(this);
        dia.setVisible(true);
    }

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
        dia.setVisible(false);
    }

    JFXPanel fxPanelTextArea = new JFXPanel();
    TextArea area = new TextArea();

    public JFXPanel fxPanel() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                TitledPane t1 = new TitledPane("Spielausgabe", area);

                Scene scene = new Scene(t1);

                area.setPrefSize(300, 100);

                fxPanelTextArea.setScene(scene);

                fxPanelTextArea.setPreferredSize(new Dimension(780, 200));
            }
        });

        return fxPanelTextArea;
    }

}
