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
    public void isEmpty() {
        AdtList testList = AdtContainerFactory.adtList();
        assertEquals(true,testList.isEmpty());
        
        testList.insert(1, 3);
        assertEquals(false,testList.isEmpty());
        
        testList.delete(1);
        assertEquals(true,testList.isEmpty());
    }
    
    @Test
    public void length() {
        AdtList testList = AdtContainerFactory.adtList();
        assertEquals(0,testList.length());
        
        testList.insert(1,3);
        assertEquals(1,testList.length());
        
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
        
        assertEquals(1,testList.length());
    }
    
    @Test
    public void delete() {
        AdtList testList = AdtContainerFactory.adtList();
        testList.insert(1,10);
        testList.insert(2,20);
        testList.insert(3,30);
        testList.delete(2);
        
        assertEquals(2,testList.find(30));
        
        testList.delete(4);
        assertEquals(2,testList.length());
    }
    
    @Test
    public void find() {
        AdtList testList = AdtContainerFactory.adtList();
        testList.insert(1, 22);
        testList.insert(2, 23);
        testList.insert(3, 2);
        
        assertEquals(0,testList.find(4));
        assertEquals(3,testList.find(2));        
    }
    
    @Test
    public void retrieve() {
        AdtList testList = AdtContainerFactory.adtList();
        testList.insert(1, 22);
        testList.insert(2, 23);
        testList.insert(3, 2);
        
        assertEquals(22,testList.retrieve(1));
        assertEquals(2,testList.retrieve(3)); 
        assertEquals(0,testList.retrieve(5)); 
    }
    
    @Test
    public void concat() {
        AdtList listeeins = AdtContainerFactory.adtList();
        AdtList listezwei = AdtContainerFactory.adtList();
        AdtList listedrei = AdtContainerFactory.adtList();
        
        listeeins.insert(1, 3);
        listeeins.insert(2, 4);
        listeeins.insert(3, 6);
        listedrei.insert(1, 3);
        listedrei.insert(2, 4);
        listedrei.insert(3, 6);
        
        listezwei.insert(1, 8);
        listezwei.insert(2, 9);
        listezwei.insert(3, 7);
        listedrei.insert(4, 8);
        listedrei.insert(5, 9);
        listedrei.insert(6, 7);
        
        listeeins.concat(listezwei);
        
        assertEquals(listeeins, listedrei);        
    }    
    
    @Test
    public void equals(){
        AdtList listeeins = AdtContainerFactory.adtList();
        AdtList listezwei = AdtContainerFactory.adtList();
        
        assertEquals(listeeins, listezwei);
        
        listeeins.insert(1, 5);
        listezwei.insert(1, 5);
        assertEquals(listeeins, listezwei);
        
        listeeins.delete(1);
        
        assertNotEquals(listezwei, listeeins);
    }
}