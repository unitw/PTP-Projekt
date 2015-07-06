/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

import DD_ContextMenu.DD_SpielerMenu;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author tw
 */
public interface IDD_Movable {

    public int getXpos();

    public void setXpos(int xpos);

    public int getYpos();

    public void setYpos(int ypos);

    public JLabel getL_gif();

    public void setL_gif(JLabel label);

    public int getL_leben();

    public void setL_leben(int l_leben);

    public void showMenu(JPanel panel);

    public void setMenu(DD_SpielerMenu menu);
     public DD_SpielerMenu getMenu();

}
