/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import implementations.AdtAVLTree;

public class AdtAVLTreeTests {

    AdtAVLTree testtree;

    public AdtAVLTreeTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testtree = AdtAVLTree.create();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void setUpTrees() throws InterruptedException {


        testtree.insert(3);
        testtree.insert(2);
        testtree.insert(1);
        testtree.insert(4);
        testtree.insert(5);
        testtree.insert(6);
        testtree.insert(7);
        testtree.insert(16);
        testtree.insert(15);
        testtree.insert(14);

        testtree.print("graph");
        
    }
}
