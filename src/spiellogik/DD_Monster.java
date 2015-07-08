/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

import DD_ContextMenu.DD_MonsterMenu;
import XML.StaxStore;
import XML.StaxWriter;
import gui.DDGUI_SpielFeld;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author tw
 */
public class DD_Monster implements IDD_MenuAnzeiger, IDD_Movable, StaxStore {

    DD_MonsterMenu menu = new DD_MonsterMenu();
    Stack<DD_Zug> zuege = new Stack();

    public DD_Zug getZueg() {

        return zuege.pop();

    }

    /**
     *
     * @return Gibt den letzten Zug des Monster zurück
     */
    public Stack<DD_Zug> getZuege() {
        return zuege;
    }

    /**
     * setzt alle Zuege des Stacks
     *
     * @param zuege
     */
    public void setZuege(Stack<DD_Zug> zuege) {
        this.zuege = zuege;
    }

    /**
     * fügt einen Zug hinzu
     *
     * @param zueg
     */
    public void setZuege(DD_Zug zueg) {
        this.zuege.push(zueg);
    }
    /**
     * gibt den Monstern die Werte
     */
    JLabel l_gif = new JLabel();

    String Typ = "Monster";
    int wert = 1;

    String dir = "x";

    int xpos = 0;
    int ypos = 0;

    int l_leben = 80;

    int l_mana = 50;//not used
    int l_ruestung = 15;
    int l_schaden = 30;
    int l_faehigkeit1;
    int l_faehigkeit2;

    /**
     * erstellt ein Monster an einer bestimmten Position
     *
     * @param xpos
     * @param ypos
     */
    public DD_Monster(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        l_gif.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/geist.gif")));
        l_gif.setBorder(null);
        l_gif.setOpaque(true);

    }

    public int getWert() {
        return wert;
    }

    public void setWert(int dir) {
        this.wert = dir;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public DD_MonsterMenu getMenu() {
        return menu;
    }

    public void setMenu(DD_MonsterMenu menu) {
        this.menu = menu;
    }
    boolean hasfocus = false;

    public boolean isHasfocus() {
        return hasfocus;
    }

    public void setHasfocus(boolean hasfocus) {
        this.hasfocus = hasfocus;
    }

    public JLabel getL_gif() {
        return l_gif;
    }

    public void setL_gif(JLabel l) {
        this.l_gif = l;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public int getL_leben() {
        return l_leben;
    }

    public void setL_leben(int l_leben) {
        this.l_leben = l_leben;
    }

    public int getL_mana() {
        return l_mana;
    }

    public void setL_mana(int l_mana) {
        this.l_mana = l_mana;
    }

    public int getL_ruestung() {
        return l_ruestung;
    }

    public void setL_ruestung(int l_ruestung) {
        this.l_ruestung = l_ruestung;
    }

    public int getL_schaden() {
        return l_schaden;
    }

    public void setL_schaden(int l_schaden) {
        this.l_schaden = l_schaden;
    }

    public int getL_faehigkeit1() {
        return l_faehigkeit1;
    }

    public void setL_faehigkeit1(int l_faehigkeit1) {
        this.l_faehigkeit1 = l_faehigkeit1;
    }

    public int getL_faehigkeit2() {
        return l_faehigkeit2;
    }

    public void setL_faehigkeit2(int l_faehigkeit2) {
        this.l_faehigkeit2 = l_faehigkeit2;
    }

    
    public void showMenu(DD_MonsterMenu menu) {

        menu.setLeben(l_leben * 0.01);
        
       
      

    }

    public void moveMonster() {

        if (Math.random() < 0.6) {

        } else {

        }

    }

    @Override
    public void STAXStore(StaxWriter staxwriter, XMLEventFactory eventFactory) {

        Integer xpos1 = xpos;

        Integer ypos1 = ypos;

        String linespos = "";

        try {
            staxwriter.StoreDD_Objekt(staxwriter.writer, Typ, "xPos", xpos1.toString(), "yPos", ypos1.toString(), 1);
        } catch (XMLStreamException ex) {
            Logger.getLogger(DDGUI_SpielFeld.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getIdentifier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setIdentifier(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setXpos(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setYpos(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getbreite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setbreite(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int gethoehe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sethoehe(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showMenu(JPanel panel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
