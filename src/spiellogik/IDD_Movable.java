/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik;

import javax.swing.JLabel;

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

}
