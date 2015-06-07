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

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            feld.moveChar(feld.getDd_player(), -1, "y");
            feld.getDd_player().setPlayerImage(feld.getDd_player().getPlayerOben());
            feld.getDd_player().setDir(0);

            feld.getDd_player().showMenu(feld.getRoot().getInfopanel());
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //  feld.moveCharacter(1, "y");
            feld.moveChar(feld.getDd_player(), 1, "y");
            feld.getDd_player().setPlayerImage(feld.getDd_player().getPlayerUnten());
            feld.getDd_player().setDir(1);
            //feld.getDd_player().showMenu(feld.getRoot().getInfopanel());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //   feld.moveCharacter(1, "x");
            feld.moveChar(feld.getDd_player(), 1, "x");
            feld.getDd_player().setPlayerImage(feld.getDd_player().getPlayerRechts());
            feld.getDd_player().setDir(2);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //feld.moveCharacter(-1, "x");
            feld.moveChar(feld.getDd_player(), -1, "x");
            feld.getDd_player().setPlayerImage(feld.getDd_player().getPlayerLinks());
            feld.getDd_player().setDir(3);

        }

        if (e.getKeyCode() == KeyEvent.VK_1) {
            feld.attack(feld.getDd_player(), 1);

        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            feld.attack(feld.getDd_player(), 2);
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            feld.attack(feld.getDd_player(), 3);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
