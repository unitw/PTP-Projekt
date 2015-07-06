/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik.KI;

import gui.DDGUI_SpielFeld;
import java.util.ArrayList;
import spiellogik.DD_Monster;
import spiellogik.DD_Zug;

/**
 *
 * @author tw
 */
public class MonsterKI {

    DD_Monster mon;
    DDGUI_SpielFeld feld;
    String Dir;
    int wert;
    boolean chase = false;
    ArrayList<DD_Zug> zuege = new ArrayList();
    AStar astern;

    public MonsterKI(DD_Monster mon, DDGUI_SpielFeld feld, boolean b) {
        chase = b;
        this.feld = feld;
        astern = new AStar(feld);
        this.mon = mon;
        initZuege();
        getWert(zuege);
    }

    public final void initZuege() {

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

        //if (chase) {
        DD_Zug heuristicZug = astern.setzüge(zuege);

        Dir = heuristicZug.getDir();
        wert = heuristicZug.getWert();
        mon.setZuege(heuristicZug);
        return 0;

        //} 
        //else {
//
//            if (!mon.getZuege().isEmpty()) {
//
//                DD_Zug zug = mon.getZueg();
//                String dir1 = zug.getDir();
//                int wert1 = zug.getWert();
//
//                if (zug.getDir().equals("x")) {
//                    if (feld.Zugmoeglich((int) zug.getXpos() + wert1, (int) zug.getYpos())) {
//
//                        Dir = zug.getDir();
//                        wert = zug.getWert();
//
//                        mon.setZuege(new DD_Zug(mon.getXpos() + wert1, mon.getYpos(), wert, Dir));
//                        return 0;
//                    }
//                } else {
//                    if (feld.Zugmoeglich((int) zug.getXpos(), (int) zug.getYpos() + wert1)) {
//                        Dir = zug.getDir();
//                        wert = zug.getWert();
//
//                        mon.setZuege(new DD_Zug(mon.getXpos(), mon.getYpos() + wert1, wert, Dir));
//                        return 0;
//                    }
//                }
//
//            }
//
//            for (DD_Zug p : zuege) {
//                if (feld.Zugmoeglich((int) p.getXpos(), (int) p.getYpos())) {
//
//                    Dir = p.getDir();
//                    wert = p.getWert();
//                    mon.setZuege(p);
//
//                    break;
//                }
//
//            }
//        }
//        return -1;
    }

    public String getDir() {
        return Dir;
    }

    public int getWert() {
        return wert;
    }

}
