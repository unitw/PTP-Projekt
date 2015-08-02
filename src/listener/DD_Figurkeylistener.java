/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import gui.DDGUI_SpielFeld;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author tw
 */
public class DD_Figurkeylistener implements KeyListener {

    DDGUI_SpielFeld feld;

    public DD_Figurkeylistener(DDGUI_SpielFeld feld) {
        this.feld = feld;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

     /**
      * Handelt die Tastatur eingaben während des Spiels
      * @param e  KeyEvent 
      */
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            feld.moveChar(feld.getDD_player(), -1, "y");

            feld.getDD_player().setDir(0);

            //   feld.getDD_player().showMenu(feld.getRoot().getInfopanel());
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //  feld.moveCharacter(1, "y");
            feld.moveChar(feld.getDD_player(), 1, "y");

            feld.getDD_player().setDir(1);
            //feld.getDd_player().showMenu(feld.getRoot().getInfopanel());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            feld.moveChar(feld.getDD_player(), 1, "x");

            feld.getDD_player().setDir(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            feld.moveChar(feld.getDD_player(), -1, "x");

            feld.getDD_player().setDir(3);

        }

        if (e.getKeyCode() == KeyEvent.VK_1) {
            feld.Spielerattack(feld.getDD_player(), 1);

        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            feld.Spielerattack(feld.getDD_player(), 2);
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            feld.Spielerattack(feld.getDD_player(), 3);
        }
       
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
