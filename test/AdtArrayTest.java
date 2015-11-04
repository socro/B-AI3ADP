/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import implementations.AdtContainerFactory;
import interfaces.AdtArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AdtArrayTest {
    
    public AdtArrayTest() {
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
    public void set()  {
        AdtArray testArray1 = AdtContainerFactory.adtArray();
        AdtArray testArray2 = AdtContainerFactory.adtArray();
        
        testArray1.set(-50, 42);
        testArray2.set(17, 42);
        
        assertEquals(-1, testArray1.length());
        assertEquals(17, testArray2.length());
        
        AdtArray emptyAdtArray = AdtContainerFactory.adtArray();
        AdtArray emptyAdtArray2 = AdtContainerFactory.adtArray();
        
        emptyAdtArray2.set(0, 1);							//An letzte Stelle ein Element einf�gen
        emptyAdtArray.set(0, 1);							//An letzte Stelle ein Element einf�gen
        assertEquals(true,emptyAdtArray.equals(emptyAdtArray2));

        emptyAdtArray.set(0, 2);							//Ein Element ver�ndern
        assertNotEquals(true,emptyAdtArray.equals(emptyAdtArray2));
        
    }
    
    @Test
    public void get()  {
        AdtArray testArray1 = AdtContainerFactory.adtArray();
        AdtArray testArray2 = AdtContainerFactory.adtArray();
        
        testArray1.set(-50, 42);
        testArray2.set(16, 42);
        
        assertEquals(0, testArray1.get(-50));
        assertEquals(42, testArray2.get(16));        
    }
    
    @Test
    public void length()  {
        AdtArray testArray = AdtContainerFactory.adtArray();
        
        assertEquals(-1, testArray.length());
        
        testArray.set(0, 1);
        assertEquals(0, testArray.length());
        
        testArray.set(4711, 321);
        assertEquals(4711, testArray.length());
    }
    
    @Test
    public void equals()  {
        AdtArray testArray1 = AdtContainerFactory.adtArray();
        AdtArray testArray2 = AdtContainerFactory.adtArray();
        
        testArray1.set(1, 42);
        testArray1.set(5, 43);
//        testArray1.set(4, 41);
        System.out.println("sadsad");
        testArray2.set(1, 42);
        testArray2.set(5, 43);
        
        assertEquals(true, testArray1.equals(testArray2));
        
//        set methode vom array fixxen
//        length testen wegen 0er insert auf der liste
//        equals vom stack und queueueueue
                
    }
    
    @Test
    public void insertionsort() {
        AdtArray testArray = AdtContainerFactory.adtArray();
        testArray.set(0, 12);
        testArray.set(1, 9);
        testArray.set(2, 4);
        testArray.set(3, 99);
        testArray.set(4, 120);
        testArray.set(5, 1);
        testArray.set(6, 3);
        testArray.set(7, 10);
        
        testArray.insertionsort(1, 7);
        System.out.println("Array sorted");
    }
}
