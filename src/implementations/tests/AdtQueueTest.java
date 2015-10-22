package implementations.tests;

import implementations.AdtContainerFactory;
import interfaces.AdtQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdtQueueTest {
    
    public AdtQueueTest() {
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
    public void front(){
        AdtQueue testQueue = AdtContainerFactory.adtQueue();
        testQueue.enQueue(2453);
        
        assertEquals(2453, testQueue.front());
    }
    
    @Test
    public void enQueue(){
        AdtQueue testQueue = AdtContainerFactory.adtQueue();
        testQueue.enQueue(2453);
        
        assertEquals(2453, testQueue.front());
    }
    
    @Test
    public void deQueue(){
        AdtQueue testQueue = AdtContainerFactory.adtQueue();
        assertEquals(true, testQueue.isEmpty());
        testQueue.enQueue(2453);
        assertEquals(false, testQueue.isEmpty());
        testQueue.deQueue();        
        assertEquals(true, testQueue.isEmpty());
    }
    
    @Test
    public void isEmpty(){
        AdtQueue testQueue = AdtContainerFactory.adtQueue();
        
        assertEquals(true, testQueue.isEmpty());
        
        testQueue.enQueue(453);
        assertEquals(false, testQueue.isEmpty());
    }
    
    @Test
    public void equals(){
        AdtQueue testQueue1 = AdtContainerFactory.adtQueue();
        AdtQueue testQueue2 = AdtContainerFactory.adtQueue();
        
        assertEquals(testQueue1, testQueue2);
        
        testQueue1.enQueue(0);
        testQueue2.enQueue(0);
        testQueue1.enQueue(15);
        testQueue2.enQueue(15);
        assertEquals(testQueue1, testQueue2);
    }
}
