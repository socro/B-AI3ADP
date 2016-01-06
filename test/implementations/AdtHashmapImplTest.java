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
    public void testHash() throws Exception {
        System.out.println("hash");
        String strategy = "";
        String filename = "src/io/textb.txt";
        AdtHashmapImpl.hash(AdtHashmapImpl.DOUBLEHASH, filename);
        System.out.println(AdtHashmapImpl.find("amet,"));
    }

    /**
     * Test of insert method, of class AdtHashmapImpl.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        AdtHashmapImpl hashmap = null;
        String word = "";
        AdtHashmapImpl.insert(word);
    }

    /**
     * Test of find method, of class AdtHashmapImpl.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        AdtHashmapImpl hashmap = null;
        String word = "";
        int expResult = 0;
        int result = AdtHashmapImpl.find(word);
    } 
    
    /**
     * Test of find method, of class AdtHashmapImpl.
     */
    @Test
    public void testCalcPrime(){
        System.out.println("optimus prime");
        String word = "";
        long expResult = 701;
        long result = AdtHashmapImpl.calcPrime(692);
        assertEquals(expResult, result);
    } 
    
}
