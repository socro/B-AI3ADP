package implementations.test.extern;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import implementations.AdtContainerFactory;
import interfaces.AdtQueue;
import interfaces.AdtStack;

public class AdtQueueTest {
	
	private AdtQueue newAdtQueue(){
		AdtQueue neueAdtQueue = AdtContainerFactory.adtQueue();
		return neueAdtQueue;
	}
	
	@Test
	public void testFront(){
		AdtQueue emptyAdtQueue = newAdtQueue();
		int erg1 = emptyAdtQueue.front();
		
		AdtQueue midAdtQueue = newAdtQueue();
		for(int i=1; i <= 500; i++){
			midAdtQueue.enQueue(i);
		}
		int erg2 = midAdtQueue.front();
		
		
		AdtQueue bigAdtQueue = newAdtQueue();
		for(int i = 2; i<= 10001; i++){
			bigAdtQueue.enQueue(i);
		}
		int erg3 = bigAdtQueue.front();
		
		Assert.assertEquals(0, erg1);
		Assert.assertEquals(1, erg2);
		Assert.assertEquals(2, erg3);
	}
	
	@Test
	public void testEnQueue(){
		AdtQueue emptyAdtQueue = newAdtQueue();
		emptyAdtQueue.enQueue(1);
		int erg1 = emptyAdtQueue.front();
		
		Assert.assertEquals(1, erg1);
	}
	
	@Test
	public void testDeQueue(){
//		fail();
		AdtQueue emptyAdtQueue = newAdtQueue();
		emptyAdtQueue.enQueue(1);
		//emptyAdtQueue.deQueue();
		AdtQueue emptyAdtQueue2 = newAdtQueue();
				
		AdtQueue midAdtQueue = newAdtQueue();
		for(int i=1; i <= 500; i++){
			midAdtQueue.enQueue(i);
		}
		for(int i=1; i<= 500;i++){
			midAdtQueue.deQueue();
		}
		
		AdtQueue bigAdtQueue = newAdtQueue();
		for(int i = 2; i<= 1001; i++){
			bigAdtQueue.enQueue(i);
		}
		for(int i=1; i<= 1000;i++){
			bigAdtQueue.deQueue();
		}
		
		AdtQueue emptyAdtQueue3 = emptyAdtQueue2;
		emptyAdtQueue3.deQueue();
		
		Assert.assertNotEquals(emptyAdtQueue, emptyAdtQueue2);
		Assert.assertEquals(midAdtQueue, emptyAdtQueue2);
		Assert.assertEquals(bigAdtQueue, emptyAdtQueue2);
		Assert.assertEquals(emptyAdtQueue3, emptyAdtQueue2);
}
	
	@Test
	public void testIsEmpty(){
		AdtQueue emptyAdtQueue = newAdtQueue();
		
		AdtQueue midAdtQueue = newAdtQueue();
		for(int i=1; i <= 500; i++){
			midAdtQueue.enQueue(i);
		}
		
		AdtQueue bigAdtQueue = newAdtQueue();
		for(int i = 2; i<= 10001; i++){
			bigAdtQueue.enQueue(i);
		}

		Assert.assertTrue(emptyAdtQueue.isEmpty());
		Assert.assertFalse(midAdtQueue.isEmpty());
		Assert.assertFalse(bigAdtQueue.isEmpty());
	}

}
