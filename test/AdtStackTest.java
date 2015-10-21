/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import implementations.AdtContainerFactory;
import interfaces.AdtList;
import interfaces.AdtStack;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
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
public class AdtStackTest {

    AdtStack testStack;
    AdtStack testStack2;
    Random r;
    Set<Integer> numbers;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        testStack = AdtContainerFactory.adtStack();
        testStack2 = AdtContainerFactory.adtStack();
        r = new Random();
        numbers = new HashSet<>();

        while (numbers.size() < 1000) {
            numbers.add(r.nextInt(9000) + 1000);
        }

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
    public void pushTest() {
        for (Integer num : numbers) {
            testStack.push(num);
            testStack2.push(num);
        }
        assertEquals(testStack, testStack2);
        System.out.println("pushTest() Ende");
    }

    @Test
    public void popTest() {
        pushTest();
        while (!testStack.isEmpty()) {
            testStack.pop();
        }
        assertEquals(true, testStack.isEmpty());
        System.out.println("popTest() Ende");
    }

    @Test
    public void topTest() {
        pushTest();

        while (!testStack.isEmpty() && !testStack2.isEmpty()) {
            assertEquals(testStack.top(), testStack2.top());
            testStack.pop();
            testStack2.pop();
        }

        assertEquals(testStack.isEmpty(), testStack2.isEmpty());

        System.out.println("topTest() Ende");

    }

    @Test
    public void isEmptyTest() {
        assertEquals(true, testStack.isEmpty());
        assertEquals(true, testStack2.isEmpty());
        
        pushTest();
        
        assertEquals(false, testStack.isEmpty());
        assertEquals(false, testStack2.isEmpty());
        

        System.out.println("isEmptyTest()");
    }
    
    @Test
    public void isEqualTest() {
        pushTest();
        
        assertEquals(testStack, testStack2);
        
        System.out.println("isEqualsTest()");
    }
}
