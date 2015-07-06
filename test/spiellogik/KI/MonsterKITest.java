/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiellogik.KI;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spiellogik.DD_Zug;

/**
 *
 * @author tw
 */
public class MonsterKITest {
    
    public MonsterKITest() {
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
     * Test of initZuege method, of class MonsterKI.
     */
    @Test
    public void testInitZuege() {
        System.out.println("initZuege");
        MonsterKI instance = null;
        instance.initZuege();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWert method, of class MonsterKI.
     */
    @Test
    public void testGetWert_ArrayList() {
        System.out.println("getWert");
        ArrayList<DD_Zug> zuege = null;
        MonsterKI instance = null;
        int expResult = 0;
        int result = instance.getWert(zuege);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDir method, of class MonsterKI.
     */
    @Test
    public void testGetDir() {
        System.out.println("getDir");
        MonsterKI instance = null;
        String expResult = "";
        String result = instance.getDir();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWert method, of class MonsterKI.
     */
    @Test
    public void testGetWert_0args() {
        System.out.println("getWert");
        MonsterKI instance = null;
        int expResult = 0;
        int result = instance.getWert();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
