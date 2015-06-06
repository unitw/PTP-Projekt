/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import gui.DDGUI_SpielFeld;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;
import spiellogik.DD_Monster;
import spiellogik.DD_Spieler;
import spiellogik.DD_Umgebung;
import spiellogik.IDD_MenuAnzeiger;

/**
 *
 * @author tw
 */
public class DD_Statuslistener implements MouseListener {

    DDGUI_SpielFeld feld;
    Stack<Object> focusstack = new Stack();

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

        if (!focusstack.empty()) {

            IDD_MenuAnzeiger menu = (IDD_MenuAnzeiger) focusstack.pop();
            menu.setHasfocus(false);
            feld.repaint();

        }

        if (feld.getField()[posx][posy] instanceof DD_Umgebung) {

            DD_Umgebung umg = (DD_Umgebung) feld.getField()[posx][posy];
            umg.setHasfocus(true);
            focusstack.push(umg);
             System.out.println(umg.getTyp());


        } else if (feld.getField()[posx][posy] instanceof DD_Spieler) {
            DD_Spieler player = (DD_Spieler) feld.getField()[posx][posy];
            player.setHasfocus(true);
            focusstack.push(player);
            player.showMenu(feld.getRoot().getInfopanel());
            System.out.println("Spieler");

        } else if (feld.getField()[posx][posy] instanceof DD_Monster) {

            DD_Monster mon = (DD_Monster) feld.getField()[posx][posy];
            mon.setHasfocus(true);
            focusstack.push(mon);
             System.out.println("Monster");
            mon.showMenu(feld.getRoot().getInfopanel());

        }

        feld.repaint();
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
