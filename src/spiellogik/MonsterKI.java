/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

import gui.DDGUI_SpielFeld;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author tw
 */
public class MonsterKI {

    DD_Monster mon;
    DDGUI_SpielFeld feld;
    String Dir;
    int wert;

    ArrayList<DD_Zug> zuege = new ArrayList();

    public MonsterKI(DD_Monster mon, DDGUI_SpielFeld feld) {
        this.feld = feld;
        this.mon = mon;
        initZuege();
        getWert(zuege);
    }

    public void initZuege() {
        if (!mon.getZuege().isEmpty()) {
            DD_Zug p0 = mon.getZuege().pop();
            zuege.add(p0);
        }
        DD_Zug p1 = new DD_Zug(mon.getXpos() + 1, mon.getYpos(), 1, "x");
        DD_Zug p3 = new DD_Zug(mon.getXpos(), mon.getYpos() + 1, +1, "y");
        DD_Zug p2 = new DD_Zug(mon.getXpos() - 1, mon.getYpos(), -1, "x");
        DD_Zug p4 = new DD_Zug(mon.getXpos(), mon.getYpos() - 1, -1, "y");

        zuege.add(p1);

        zuege.add(p3);
        zuege.add(p2);
        zuege.add(p4);
    }

    public final int getWert(ArrayList<DD_Zug> zuege) {

        for (DD_Zug p : zuege) {
            if (feld.Zugmoeglich((int) p.getXpos(), (int) p.getYpos())) {

                Dir = p.getDir();
                wert = p.getWert();
                mon.setZuege(p);

                break;
            }

        }

        return -1;
    }

    public String getDir() {
        return Dir;
    }

    public int getWert() {
        return wert;
    }

}
