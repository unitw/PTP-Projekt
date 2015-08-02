/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik.KI;

import gui.DDGUI_SpielFeld;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spiellogik.DD_Spieler;
import spiellogik.DD_Umgebung;
import spiellogik.DD_Zug;


public class AStar {

    public static final int WIDTH = 30;

    public static final int HEIGHT = 30;

    private Object[][] field = new Object[WIDTH][HEIGHT];

    private int[][] units = new int[WIDTH][HEIGHT];

    private boolean[][] visited = new boolean[WIDTH][HEIGHT];

    DDGUI_SpielFeld spiel;
    Map<Float, DD_Zug> zugMap = new HashMap<>();

     /**
      * 
      * @param spiel  das betreffende Spielfed
      */
    public AStar(DDGUI_SpielFeld spiel) {
        this.spiel = spiel;
        this.field = spiel.getField();

    }

     /**
      * Hier wird für jeden Zug in der Liste eine Heristic berechnet,der Zug mit der Besten Heuristic (niedrigsten Wert) zurück gegeben
      * @param zugliste liste der möglichen züge
      * @return bester Zug
      */
    public DD_Zug setzüge(ArrayList<DD_Zug> zugliste) {
        float heuristic = 0;

        int px = spiel.getDD_player().getXpos();
        int py = spiel.getDD_player().getYpos();
        for (DD_Zug zug : zugliste) {
            if (spiel.Zugmoeglich(zug.getXpos(), zug.getYpos())) {
                if (!visited(zug.getXpos(), zug.getYpos())) {
                    setvisited(zug.getXpos(), zug.getYpos());
                    zugMap.put(getCost(zug.getXpos(), zug.getYpos(), px, py), zug);

                    if (heuristic == 0) {
                        heuristic = getCost(zug.getXpos(), zug.getYpos(), px, py);
                    } else if (getCost(zug.getXpos(), zug.getYpos(), px, py) < heuristic) {
                        heuristic = getCost(zug.getXpos(), zug.getYpos(), px, py);
                    }
                }
            }
        }

        return zugMap.get(heuristic);
    }

     /**
      * 
      * @param x xpos
      * @param y ypos
      * @return  wenn schon besucht true
      */
    public boolean visited(int x, int y) {
        return visited[x][y];
    }

     /**
      * 
      * @param x xpos
      * @param y  ypos
      */
    public void setvisited(int x, int y) {
        visited[x][y] = true;
    }

     /**
      * Manhattan distanz zur heuristischen berechnet
      * @param sx Start Coordinate X
      * @param sy Start Coordinate Y
      * @param tx Ziel Coordinate  X
      * @param ty Ziel Coordinate  Y
      * @return  heuristic des Zuges
      */
    public float getCost(int sx, int sy, int tx, int ty) {

        int dx = abs(tx - sx);
        int dy = abs(ty - sy);
        int heuristic = dx + dy;

        return heuristic;
    }

}
