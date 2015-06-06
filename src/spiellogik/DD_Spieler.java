/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

import DD_ContextMenu.DD_SpielerMenu;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author tw
 */
public class DD_Spieler implements IDD_MenuAnzeiger, IDD_Movable {

    DD_SpielerMenu menu = new DD_SpielerMenu(true);
    int xpos;
    int ypos;

    private BufferedImage playerUnten;
    private BufferedImage playerOben;
    private BufferedImage playerRechts;
    private BufferedImage playerLinks;
    private BufferedImage playerImageAktuell;

    public boolean isHasfocus() {
        return hasfocus;
    }

    public void setHasfocus(boolean hasfocus) {
        this.hasfocus = hasfocus;
    }
    boolean hasfocus = false;

    public DD_Spieler(int xpos, int ypos) {
        try {
            this.xpos = xpos;
            this.ypos = ypos;
            this.playerUnten = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/playerUnten.png"));
            this.playerOben = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/playerOben.png"));
            this.playerRechts = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/playerRechts.png"));
            this.playerLinks = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("resources/playerLinks.png"));
        } catch (IOException ex) {
            Logger.getLogger(DD_Spieler.class.getName()).log(Level.SEVERE, null, ex);
        }

        playerImageAktuell = playerUnten;
    }
    int i_leben = 100;
    int i_mana = 30;
    int i_ruestung = 50;
    int i_schaden;
    int i_faehigkeit1;
    int i_faehigkeit2;

    public BufferedImage getPlayerImage() {
        return playerImageAktuell;
    }

    public void setPlayerImage(BufferedImage image) {
        playerImageAktuell = image;
    }

    public BufferedImage getPlayerUnten() {
        return playerUnten;
    }

    public BufferedImage getPlayerOben() {
        return playerOben;
    }

    public BufferedImage getPlayerRechts() {
        return playerRechts;
    }

    public BufferedImage getPlayerLinks() {
        return playerLinks;
    }

    public int getL_leben() {
        return i_leben;
    }

    public void setL_leben(int l_leben) {
        this.i_leben = l_leben;
    }

    public int getL_mana() {
        return i_mana;
    }

    public void setL_mana(int l_mana) {
        this.i_mana = l_mana;
    }

    public int getL_ruestung() {
        return i_ruestung;
    }

    public void setL_ruestung(int l_ruestung) {
        this.i_ruestung = l_ruestung;
    }

    public int getL_schaden() {
        return i_schaden;
    }

    public void setL_schaden(int l_schaden) {
        this.i_schaden = l_schaden;
    }

    public int getL_faehigkeit1() {
        return i_faehigkeit1;
    }

    public void setL_faehigkeit1(int l_faehigkeit1) {
        this.i_faehigkeit1 = l_faehigkeit1;
    }

    public int getL_faehigkeit2() {
        return i_faehigkeit2;
    }

    public void setL_faehigkeit2(int l_faehigkeit2) {
        this.i_faehigkeit2 = l_faehigkeit2;
    }

    @Override
    public void showMenu(JPanel panel) {

        panel.removeAll();

        menu.getT_leben().setValue(i_leben);
        menu.getT_leben().setString(i_leben + "");
        menu.getT_mana().setText(i_mana + "");
        menu.getT_ruestung().setText(i_ruestung + "");

        panel.add(menu);
        panel.revalidate();
        panel.repaint();
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

}
