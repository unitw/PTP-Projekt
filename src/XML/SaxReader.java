/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import spiellogik.DD_Monster;
import spiellogik.DD_Spieler;
import spiellogik.DD_Umgebung;

/**
 *
 * @author win7
 */
public class SaxReader implements ContentHandler {

    public String currentValue;

    int xpos;
    int ypos;
    int breite;
    int hoehe;
    Color color;

    public Object[][] spielfeld = new Object[30][30];
    DD_Monster monster;
    DD_Spieler spieler;
    DD_Umgebung umgebung;
    String Typ;

    // Aktuelle Zeichen die gelesen werden, werden in eine Zwischenvariable
    // gespeichert
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        currentValue = new String(ch, start, length);
    }

    // Methode wird aufgerufen wenn der Parser zu einem Start-Tag kommt
    @Override
    public void startElement(String uri, String localName, String qName,
            org.xml.sax.Attributes atts) throws SAXException {

        if (localName.equals("Spielfeld")) {

            GetAttr(atts);
            spielfeld = new Object[30][30];

        }
        if (localName.equals("Spieler")) {

            GetAttr(atts);
            spieler = new DD_Spieler(xpos, ypos);
        }
        if (localName.equals("Monster")) {

            GetAttr(atts);
            monster = new DD_Monster(xpos, ypos);

        }

        if (localName.equals("boden")) {

            GetAttr(atts);
            umgebung = new DD_Umgebung("boden", xpos, ypos);
        }
        if (localName.equals("baum")) {

            GetAttr(atts);
            umgebung = new DD_Umgebung("baum", xpos, ypos);
        }

    }

    // Methode wird aufgerufen wenn der Parser zu einem End-Tag kommt
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (localName.equals("Spieler")) {
            spielfeld[spieler.getXpos()][spieler.getYpos()] = spieler;

        }
        if ((localName.equals("Monster"))) {
            spielfeld[monster.getXpos()][monster.getYpos()] = monster;

        }
        if ((localName.equals("boden"))) {

            spielfeld[umgebung.getXpos()][umgebung.getYpos()] = umgebung;

        }
        if ((localName.equals("baum"))) {

            spielfeld[umgebung.getXpos()][umgebung.getYpos()] = umgebung;

        }

    }

    //<editor-fold defaultstate="collapsed" desc="not implemented">
    public void endDocument() throws SAXException {
    }

    public void endPrefixMapping(String prefix) throws SAXException {
    }

    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
    }

    public void processingInstruction(String target, String data)
            throws SAXException {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void skippedEntity(String name) throws SAXException {
    }

    public void startDocument() throws SAXException {
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
    }
//</editor-fold>

    public void GetAttr(org.xml.sax.Attributes atts) {

        if (atts.getValue("xPos") != null) {
            xpos = Integer.parseInt(atts.getValue("xPos"));
            ypos = Integer.parseInt(atts.getValue("yPos"));

        }
    }
}
