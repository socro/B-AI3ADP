/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rene
 */
public class AdtHashmapImplTest {
    
    public AdtHashmapImplTest() {
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
     * Test of hash method, of class AdtHashmapImpl.
     */
    @Test
    public void testHash() {
        System.out.println("hash");
        String strategy = "";
        String filename = "";
        AdtHashmapImpl.hash(strategy, filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class AdtHashmapImpl.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        AdtHashmapImpl hashmap = null;
        String word = "";
        AdtHashmapImpl.insert(hashmap, word);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class AdtHashmapImpl.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        AdtHashmapImpl hashmap = null;
        String word = "";
        int expResult = 0;
        int result = AdtHashmapImpl.find(hashmap, word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importFile method, of class AdtHashmapImpl.
     */
    @Test
    public void testImportFile() {
        System.out.println("importFile");
        String filename = "";
        ArrayList<String> expResult = null;
        ArrayList<String> result = AdtHashmapImpl.importFile(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
