/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

/**
 *
 * @author tw
 */
public class DD_Fähigkeit {
    int manaverbrauch;
    int range;
    int heilung;
    int schaden;
    double treffsicherheit;

    public DD_Fähigkeit(int manaverbrauch, int range, int heilung, int schaden, double treffsicherheit) {
        this.manaverbrauch = manaverbrauch;
        this.range = range;
        this.heilung = heilung;
        this.schaden = schaden;
        this.treffsicherheit = treffsicherheit;
    }

    public int getManaverbrauch() {
        return manaverbrauch;
    }

    public void setManaverbrauch(int manaverbrauch) {
        this.manaverbrauch = manaverbrauch;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getHeilung() {
        return heilung;
    }

    public void setHeilung(int heilung) {
        this.heilung = heilung;
    }

    public int getSchaden() {
        return schaden;
    }

    public void setSchaden(int schaden) {
        this.schaden = schaden;
    }

    public double getTreffsicherheit() {
        return treffsicherheit;
    }

    public void setTreffsicherheit(double treffsicherheit) {
        this.treffsicherheit = treffsicherheit;
    }
}
