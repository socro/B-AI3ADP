/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import implementations.AdtContainerFactory;
import interfaces.AdtList;
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
public class AdtListTest {

    public AdtListTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void tollerinserttesterfueradtlisten() {
        AdtList meinetestliste = AdtContainerFactory.adtList();
        
        AdtList meinezweitetestliste = AdtContainerFactory.adtList();
        
        meinezweitetestliste.insert(1, 2);
        meinezweitetestliste.insert(1, 3);
        meinezweitetestliste.insert(1, 4);
        meinezweitetestliste.insert(1, 5);

        assertEquals(true, meinetestliste.isEmpty());
        assertEquals(0, meinetestliste.length());

        meinetestliste.insert(0, 5);
        assertEquals(0, meinetestliste.length());
 
        meinetestliste.insert(1, 3);
        assertEquals(1, meinetestliste.length());

        meinetestliste.insert(2, 2);
        assertEquals(2, meinetestliste.length());

        meinetestliste.insert(2, 1);
        assertEquals(3, meinetestliste.length());
                
        assertEquals(0,meinetestliste.find(4));
        assertEquals(3,meinetestliste.find(2));
        
        meinetestliste.concat(meinezweitetestliste);
        assertEquals(7, meinetestliste.length());
    }
    
    @Test
    public void isEmpty() {
        AdtList testList = AdtContainerFactory.adtList();
        assertEquals(true,testList.isEmpty());
        
        testList.insert(1, 3);
        assertEquals(false,testList.isEmpty());
        
        testList.delete(1);
        assertEquals(true,testList.isEmpty());
    }
    
    @Test
    public void insert() {
        AdtList testList = AdtContainerFactory.adtList();
        testList.insert(4,10);
        assertEquals(0,testList.retrieve(3));
        testList.insert(1,10);
        assertEquals(10,testList.retrieve(1));
        testList.insert(0,10);
        assertEquals(0,testList.retrieve(0));
    }
    
    @Test
    public void delete() {
        AdtList testList = AdtContainerFactory.adtList();
        testList.insert(1,10);
        testList.insert(2,20);
        testList.insert(3,30);
        testList.delete(3);
        
        assertEquals(false,testList.find(10));
    }

}
