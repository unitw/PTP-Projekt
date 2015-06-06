/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

import javax.swing.JPanel;

/**
 *
 * @author tw
 */
public class DD_Umgebung implements  IDD_MenuAnzeiger{
    String Typ;

    public boolean isHasfocus() {
        return hasfocus;
    }

    public void setHasfocus(boolean hasfocus) {
        this.hasfocus = hasfocus;
    }
    boolean hasfocus=false;
    
    
    public DD_Umgebung(String Typ) {
        this.Typ = Typ;
    }

    public String getTyp() {
        return Typ;
    }

    public void setTyp(String Typ) {
        this.Typ = Typ;
    }

    @Override
    public void showMenu(JPanel panel) {
  
    }
}
