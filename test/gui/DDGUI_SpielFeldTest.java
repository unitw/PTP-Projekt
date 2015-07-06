/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import XML.StaxWriter;
import java.awt.Graphics;
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
        DDGUI_SpielFeld instance = null;
        DD_Spieler expResult = null;
        DD_Spieler result = instance.getDD_player();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDd_player method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetDd_player() {
        System.out.println("setDd_player");
        DD_Spieler dd_player = null;
        DDGUI_SpielFeld instance = null;
        instance.setDd_player(dd_player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getField method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetField() {
        System.out.println("getField");
        DDGUI_SpielFeld instance = null;
        Object[][] expResult = null;
        Object[][] result = instance.getField();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setField method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetField() {
        System.out.println("setField");
        Object[][] field = null;
        DDGUI_SpielFeld instance = null;
        instance.setField(field);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRatio method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetRatio() {
        System.out.println("getRatio");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.getRatio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoot method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetRoot() {
        System.out.println("getRoot");
        DDGUI_SpielFeld instance = null;
        DDGUI_RootFrame expResult = null;
        DDGUI_RootFrame result = instance.getRoot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRunde method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetRunde() {
        System.out.println("getRunde");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.getRunde();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRunde method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetRunde() {
        System.out.println("setRunde");
        int runde = 0;
        DDGUI_SpielFeld instance = null;
        instance.setRunde(runde);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoot method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetRoot() {
        System.out.println("setRoot");
        DDGUI_RootFrame root = null;
        DDGUI_SpielFeld instance = null;
        instance.setRoot(root);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveChar method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testMoveChar() {
        System.out.println("moveChar");
        Object o = null;
        int i = 0;
        String dir = "";
        DDGUI_SpielFeld instance = null;
        instance.moveChar(o, i, dir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of moveSomething method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testMoveSomething() {
        System.out.println("moveSomething");
        Object o = null;
        int i = 0;
        String dir = "";
        DDGUI_SpielFeld instance = null;
        instance.moveSomething(o, i, dir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLvl method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetLvl() {
        System.out.println("getLvl");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.getLvl();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLvl method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetLvl() {
        System.out.println("setLvl");
        int lvl = 0;
        DDGUI_SpielFeld instance = null;
        instance.setLvl(lvl);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextRound method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testNextRound() {
        System.out.println("nextRound");
        DDGUI_SpielFeld instance = null;
        instance.nextRound();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SpielerInRange method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSpielerInRange() {
        System.out.println("SpielerInRange");
        int x1 = 0;
        int y1 = 0;
        int range = 0;
        DDGUI_SpielFeld instance = null;
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of monstermovement method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testMonstermovement() {
        System.out.println("monstermovement");
        DDGUI_SpielFeld instance = null;
        instance.monstermovement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayOptionDialog method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testDisplayOptionDialog() {
        System.out.println("displayOptionDialog");
        DDGUI_SpielFeld instance = null;
        instance.displayOptionDialog();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Spielerattack method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSpielerattack() {
        System.out.println("Spielerattack");
        DD_Spieler sp = null;
        int attackNr = 0;
        DDGUI_SpielFeld instance = null;
        instance.Spielerattack(sp, attackNr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Monsterattack method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testMonsterattack() {
        System.out.println("Monsterattack");
        DD_Monster sp = null;
        int attackNr = 0;
        DDGUI_SpielFeld instance = null;
        instance.Monsterattack(sp, attackNr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimer method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetTimer() {
        System.out.println("getTimer");
        DDGUI_SpielFeld instance = null;
        Timer expResult = null;
        Timer result = instance.getTimer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showAttackEffect method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testShowAttackEffect() {
        System.out.println("showAttackEffect");
        int attacknr = 0;
        int xpos = 0;
        int ypos = 0;
        int range = 0;
        DDGUI_SpielFeld instance = null;
        instance.showAttackEffect(attacknr, xpos, ypos, range);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Schadenberechnung method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSchadenberechnung() {
        System.out.println("Schadenberechnung");
        IDD_Movable mon = null;
        int schaden = 0;
        DDGUI_SpielFeld instance = null;
        instance.Schadenberechnung(mon, schaden);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetSpiel method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testResetSpiel() {
        System.out.println("resetSpiel");
        DDGUI_SpielFeld instance = null;
        instance.resetSpiel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Zugmoeglich method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testZugmoeglich() {
        System.out.println("Zugmoeglich");
        int x = 0;
        int y = 0;
        DDGUI_SpielFeld instance = null;
        boolean expResult = false;
        boolean result = instance.Zugmoeglich(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintComponent method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        DDGUI_SpielFeld instance = null;
        instance.paintComponent(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of STAXStore method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSTAXStore() {
        System.out.println("STAXStore");
        StaxWriter staxwriter = null;
        XMLEventFactory eventFactory = null;
        DDGUI_SpielFeld instance = null;
        instance.STAXStore(staxwriter, eventFactory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGesamt method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetGesamt() {
        System.out.println("getGesamt");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.getGesamt();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAktvalue method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetAktvalue() {
        System.out.println("getAktvalue");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.getAktvalue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdentifier method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        DDGUI_SpielFeld instance = null;
        String expResult = "";
        String result = instance.getIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdentifier method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetIdentifier() {
        System.out.println("setIdentifier");
        String s = "";
        DDGUI_SpielFeld instance = null;
        instance.setIdentifier(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXpos method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetXpos() {
        System.out.println("getXpos");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.getXpos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXpos method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetXpos() {
        System.out.println("setXpos");
        String s = "";
        DDGUI_SpielFeld instance = null;
        instance.setXpos(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getYpos method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetYpos() {
        System.out.println("getYpos");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.getYpos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setYpos method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetYpos() {
        System.out.println("setYpos");
        String s = "";
        DDGUI_SpielFeld instance = null;
        instance.setYpos(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getbreite method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGetbreite() {
        System.out.println("getbreite");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.getbreite();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setbreite method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSetbreite() {
        System.out.println("setbreite");
        String s = "";
        DDGUI_SpielFeld instance = null;
        instance.setbreite(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gethoehe method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testGethoehe() {
        System.out.println("gethoehe");
        DDGUI_SpielFeld instance = null;
        int expResult = 0;
        int result = instance.gethoehe();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sethoehe method, of class DDGUI_SpielFeld.
     */
    @Test
    public void testSethoehe() {
        System.out.println("sethoehe");
        String s = "";
        DDGUI_SpielFeld instance = null;
        instance.sethoehe(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
