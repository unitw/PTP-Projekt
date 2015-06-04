/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import gui.DDGUI_SpielFeld;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author tw
 */
public class DD_Statuslistener implements MouseListener {

    DDGUI_SpielFeld feld;

    public DD_Statuslistener(DDGUI_SpielFeld feld) {
        this.feld = feld;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.getSource();
        int posx = e.getX() / feld.ratio;
        int posy = e.getY() / feld.ratio;
        
        
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
