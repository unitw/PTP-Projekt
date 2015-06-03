/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

/**
 *
 * @author 3flim
 */
public class DDGUI_RootFrame extends JFrame{
    
    
      Class themeClass = Class.forName("MacMetricsTheme");
        MetalTheme theme = (MetalTheme) themeClass.newInstance();
        MetalLookAndFeel.setCurrentTheme(theme);

        UIManager.setLookAndFeel(new MetalLookAndFeel());
    
    
    
    
    
}
