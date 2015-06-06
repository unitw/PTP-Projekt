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
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //  feld.moveCharacter(1, "y");
            feld.moveChar(feld.getDd_player(), 1, "y");
            feld.getDd_player().setPlayerImage(feld.getDd_player().getPlayerUnten());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //   feld.moveCharacter(1, "x");
            feld.moveChar(feld.getDd_player(), 1, "x");
            feld.getDd_player().setPlayerImage(feld.getDd_player().getPlayerRechts());
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //feld.moveCharacter(-1, "x");
            feld.moveChar(feld.getDd_player(), -1, "x");
            feld.getDd_player().setPlayerImage(feld.getDd_player().getPlayerLinks());
        }

        if (e.getKeyCode() == KeyEvent.VK_1) {

        }
        if (e.getKeyCode() == KeyEvent.VK_2) {

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
