/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

import DD_ContextMenu.DD_SpielerMenu;
import XML.StaxStore;
import XML.StaxWriter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.stream.XMLEventFactory;

/**
 *
 * @author tw
 */
public class DD_Monster implements IDD_MenuAnzeiger, MonsterKI, IDD_Movable,StaxStore {

    DD_SpielerMenu menu = new DD_SpielerMenu(false);

    JLabel l_gif = new JLabel();

    public DD_SpielerMenu getMenu() {
        return menu;
    }

    public void setMenu(DD_SpielerMenu menu) {
        this.menu = menu;
    }
    boolean hasfocus = false;

    public boolean isHasfocus() {
        return hasfocus;
    }

    public void setHasfocus(boolean hasfocus) {
        this.hasfocus = hasfocus;
    }
    int xpos;
    int ypos;

    int l_leben = 80;

    int l_mana = 50;
    int l_ruestung = 200;
    int l_schaden;
    int l_faehigkeit1;
    int l_faehigkeit2;

    public DD_Monster(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
        l_gif.setIcon(new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/geist.gif")));
        l_gif.setBorder(null);
        l_gif.setOpaque(true);

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

    @Override
    public void showMenu(JPanel panel) {
        panel.removeAll();
        menu.getT_leben().setValue(l_leben);
        menu.getT_leben().setString(l_leben + "");
        menu.getT_mana().setText(l_mana + "");
        menu.getT_ruestung().setText(l_ruestung + "");

        panel.add(menu);
        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void moveMonster() {

        if (Math.random() < 0.6) {

        } else {

        }

    }

    @Override
    public void STAXStore(StaxWriter staxwriter, XMLEventFactory eventFactory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
