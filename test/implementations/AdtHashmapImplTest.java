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

import static implementations.AdtHashmapImpl.*;

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
    //##########################################################################
    //##########################################################################
    //##########################################################################
    //##########################################################################
    // Runtime Tests with every probing-method
    /**
     * Test of hash method, of class AdtHashmapImpl.
     */
    @Test
    public void testHashRTTextADOUBLEHASH() throws Exception {
        System.out.println("testHashRTTextADOUBLEHASH");
        String strategy = DOUBLEHASH;
        String filename = "src/io/texta.txt";
        AdtHashmapImpl.hash(strategy,filename);
    }
    /**
     * Test of hash method, of class AdtHashmapImpl.
     */
    @Test
    public void testHashRTTextBDOUBLEHASH() throws Exception {
        System.out.println("testHashRTTextBDOUBLEHASH");
        String strategy = DOUBLEHASH;
        String filename = "src/io/textb.txt";
        AdtHashmapImpl.hash(strategy,filename);
    }
    /**
     * Test of hash method, of class AdtHashmapImpl.
     */
    @Test
    public void testHashRTTextALINEAR() throws Exception {
        System.out.println("testHashRTTextALINEAR");
        String strategy = LINEAR;
        String filename = "src/io/texta.txt";
        AdtHashmapImpl.hash(strategy,filename);
    }
    /**
     * Test of hash method, of class AdtHashmapImpl.
     */
    @Test
    public void testHashRTTextBLINEAR() throws Exception {
        System.out.println("testHashRTTextBLINEAR");
        String strategy = LINEAR;
        String filename = "src/io/textb.txt";
        AdtHashmapImpl.hash(strategy,filename);
    }
    /**
     * Test of hash method, of class AdtHashmapImpl.
     */
    @Test
    public void testHashRTTextAQUADRATIC() throws Exception {
        System.out.println("testHashRTTextAQUADRATIC");
        String strategy = QUADRATIC;
        String filename = "src/io/texta.txt";
        AdtHashmapImpl.hash(strategy,filename);
    }
    /**
     * Test of hash method, of class AdtHashmapImpl.
     */
    @Test
    public void testHashRTTextBQUADRATIC() throws Exception {
        System.out.println("testHashRTTextBQUADRATIC");
        String strategy = QUADRATIC;
        String filename = "src/io/textb.txt";
        AdtHashmapImpl.hash(strategy,filename);
    }
    //##########################################################################
    //##########################################################################
    //##########################################################################
    //##########################################################################
}