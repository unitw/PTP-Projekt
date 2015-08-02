/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import XML.StaxWriter;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.Timer;
import javax.xml.stream.XMLEventFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spiellogik.DD_Monster;
import spiellogik.DD_Spieler;
import spiellogik.IDD_Movable;

/**
 *
 * @author tw
 */
public class DDGUI_SpielFeldTest {

    public DDGUI_SpielFeldTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getDD_player method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetDD_player() {
        System.out.println("getDD_player");
        DDGUI_SpielFeld instance = new DDGUI_SpielFeld(30, 30);
        instance.setDd_player(new DD_Spieler(2, 2));
        int xpos = instance.getDD_player().getXpos();
        int ypos = instance.getDD_player().getYpos();

        assertEquals(2, xpos);
        assertEquals(2, ypos);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setDd_player method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetDd_player() {
        System.out.println("setDd_player");
        DD_Spieler dd_player = new DD_Spieler(2, 2);
        DDGUI_SpielFeld instance = new DDGUI_SpielFeld(30, 30);
        instance.setDd_player(dd_player);
        instance.getDD_player().setXpos(5);
        instance.getDD_player().setYpos(6);
        assertEquals(5, instance.getDD_player().getXpos());
        assertEquals(6, instance.getDD_player().getYpos());

    }

    /**
     * Test of getField method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetFieldLenght() {
        System.out.println("getField");

        int lenght = instance.getField().length;

        assertEquals(30, lenght);
    }

    /**
     * Test of getRoot method, of class DDGUI_SpielFeld.
     */
    DDGUI_SpielFeld instance = new DDGUI_SpielFeld(30, 30);

    /**
     * Test of getRunde method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetRunde() {
        System.out.println("getRunde");

        instance.setRunde(2);
        int result = instance.getRunde();
        assertEquals(2, result);

    }

    /**
     * Test of moveChar method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testMoveChar() {
        System.out.println("moveChar");
        Object o = null;
        int i = -1;
        String dir = "x";
        instance.setDd_player(new DD_Spieler(2, 2));
        Point spp = new Point(instance.getDD_player().getXpos(), instance.getDD_player().getYpos());

        instance.moveChar(instance.getDD_player(), i, dir);
        Point spf = new Point(instance.getDD_player().getXpos(), instance.getDD_player().getYpos());

        assertEquals(spp, spf);

    }

// 
}
