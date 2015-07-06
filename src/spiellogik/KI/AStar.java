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

/**
 * The data map from our example game. This holds the state and context of each
 * tile on the map. It also implements the interface required by the path
 * finder. It's implementation of the path finder related methods add specific
 * handling for the types of units and terrain in the example game.
 *
 * @author Kevin Glass
 */
public class AStar implements TileBasedMap {

    /**
     * The map width in tiles
     */
    public static final int WIDTH = 30;
    /**
     * The map height in tiles
     */
    public static final int HEIGHT = 30;

    /**
     * The terrain settings for each tile in the map
     */
    private Object[][] field = new Object[WIDTH][HEIGHT];
    /**
     * The unit in each tile of the map
     */
    private int[][] units = new int[WIDTH][HEIGHT];
    /**
     * Indicator if a given tile has been visited during the search
     */
    private boolean[][] visited = new boolean[WIDTH][HEIGHT];

    DDGUI_SpielFeld spiel;
    Map<Float, DD_Zug> zugMap = new HashMap<>();

    public AStar(DDGUI_SpielFeld spiel) {
        this.spiel = spiel;
        this.field = spiel.getField();

    }

    public DD_Zug setzüge(ArrayList<DD_Zug> zugliste) {
        float heuristic = 0;

        int px = spiel.getDD_player().getXpos();
        int py = spiel.getDD_player().getYpos();
        for (DD_Zug zug : zugliste) {

        //   if()
            if (blocked(zug.getXpos(), zug.getYpos())) {
                zugMap.put(getCost(zug.getXpos(), zug.getYpos(), px, py), zug);
                if (getCost(zug.getXpos(), zug.getYpos(), px, py) > heuristic) {
                    heuristic = getCost(zug.getXpos(), zug.getYpos(), px, py);
                }
            }
        }

       // setvisited(zugMap.get(heuristic).getXpos(), zugMap.get(heuristic).getYpos());
        return zugMap.get(heuristic);
    }

    /**
     * Clear the array marking which tiles have been visted by the path finder.
     */
    public void clearVisited() {
        for (int x = 0; x < getWidthInTiles(); x++) {
            for (int y = 0; y < getHeightInTiles(); y++) {
                visited[x][y] = false;
            }
        }
    }

    /**
     * @see TileBasedMap#visited(int, int)
     */
    public boolean visited(int x, int y) {
        return visited[x][y];
    }

   
    
    
    /**
     * Get the terrain at a given location
     *
     * @param x The x coordinate of the terrain tile to retrieve
     * @param y The y coordinate of the terrain tile to retrieve
     * @return The terrain tile at the given location
     */
    public Object getTerrain(int x, int y) {
        return field[x][y];
    }

    /**
     * Get the unit at a given location
     *
     * @param x The x coordinate of the tile to check for a unit
     * @param y The y coordinate of the tile to check for a unit
     * @return The ID of the unit at the given location or 0 if there is no unit
     */
    public int getUnit(int x, int y) {
        return units[x][y];
    }

    /**
     * Set the unit at the given location
     *
     * @param x The x coordinate of the location where the unit should be set
     * @param y The y coordinate of the location where the unit should be set
     * @param unit The ID of the unit to be placed on the map, or 0 to clear the
     * unit at the given location
     */
    public void setUnit(int x, int y, int unit) {
        units[x][y] = unit;
    }

    /**
     * @see TileBasedMap#blocked(Mover, int, int)
     */
    public boolean blocked(int x, int y) {
        // if theres a unit at the location, then it's blocked

        if (field[x][y] instanceof DD_Umgebung) {
            DD_Umgebung umg = (DD_Umgebung) field[x][y];
            if (umg.getTyp().equals("boden")) {
                return false;
            } else {
                return true;
            }

        } else if (field[x][y] instanceof DD_Spieler) {
            DD_Spieler umg = (DD_Spieler) field[x][y];

            return true;

        }

        return true;
    }

    /**
     * @param sx
     * @param sy
     * @param ty
     * @param tx
     * @return
     * @see TileBasedMap#getCost(Mover, int, int, int, int)
     */
    public float getCost(int sx, int sy, int tx, int ty) {

        int dx = abs(tx - sx);
        int dy = abs(ty - sy);
        int heuristic = dx + dy;

        return heuristic;
    }

    /**
     * @see TileBasedMap#getHeightInTiles()
     */
    public int getHeightInTiles() {
        return WIDTH;
    }

    /**
     * @see TileBasedMap#getWidthInTiles()
     */
    public int getWidthInTiles() {
        return HEIGHT;
    }

    /**
     * @see TileBasedMap#pathFinderVisited(int, int)
     */
    public void pathFinderVisited(int x, int y) {
        visited[x][y] = true;
    }

}
