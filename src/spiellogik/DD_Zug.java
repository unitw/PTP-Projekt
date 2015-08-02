/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

/**
 *
 * @author rw
 */
public class DD_Zug {
    int xpos;
    int ypos;
     int wert;
     String Dir;

      /**
       * 
       * @param xpos
       * @param ypos
       * @param wert
       * @param Dir 
       */
    public DD_Zug(int xpos, int ypos, int wert, String Dir) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.wert = wert;
        this.Dir = Dir;
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

    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }

    public String getDir() {
        return Dir;
    }

    public void setDir(String Dir) {
        this.Dir = Dir;
    }
    
    
}
