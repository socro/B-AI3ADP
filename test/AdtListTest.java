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
        
        AdtList diefolgendeadtlisteissponsoredbycocacolameinezweitelistedieichzumkopierenbrauche = AdtContainerFactory.adtList();
        
        diefolgendeadtlisteissponsoredbycocacolameinezweitelistedieichzumkopierenbrauche.insert(1, 2);
        diefolgendeadtlisteissponsoredbycocacolameinezweitelistedieichzumkopierenbrauche.insert(1, 3);
        diefolgendeadtlisteissponsoredbycocacolameinezweitelistedieichzumkopierenbrauche.insert(1, 4);
        diefolgendeadtlisteissponsoredbycocacolameinezweitelistedieichzumkopierenbrauche.insert(1, 5);

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
        
        meinetestliste.concat(diefolgendeadtlisteissponsoredbycocacolameinezweitelistedieichzumkopierenbrauche);
        assertEquals(7, meinetestliste.length());
    }

}
