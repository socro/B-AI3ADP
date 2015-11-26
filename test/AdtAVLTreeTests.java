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

/**
 *
 * @author Rene
 */
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void setUpTrees() throws InterruptedException {
        testtree.insert(8);
        testtree.insert(9);
        testtree.insert(10);
        AdtAVLTree root = testtree;
        while(root.parent != null){
            root = root.parent;
        }
        root.print("graph");
    }
}
