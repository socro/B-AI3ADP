package implementations.test.extern;

import static org.junit.Assert.*;

import org.junit.Test;

import implementations.AdtContainerFactory;
import interfaces.AdtArray;
import interfaces.AdtStack;

public class AdtArrayTest {
	
	private AdtArray newAdtArray(int elements){
		AdtArray newAdtArray = AdtContainerFactory.adtArray();
		for(int i = 0; i < elements; i++){
			newAdtArray.set(i,i + 1);
		}
		return newAdtArray;
	}

	@Test
	public void testSet() {
		AdtArray emptyAdtArray = newAdtArray(0);
		AdtArray emptyAdtArray2=newAdtArray(1);
		AdtArray midAdtArray = newAdtArray(499);
		AdtArray midAdtArray2=newAdtArray(500);
		AdtArray bigAdtArray = newAdtArray(999);
		AdtArray bigAdtArray2=newAdtArray(1000);
		assertFalse(emptyAdtArray.equals(emptyAdtArray2));
		
		emptyAdtArray.set(0, 1);							//An letzte Stelle ein Element einf�gen
		assertTrue(emptyAdtArray.equals(emptyAdtArray2));
		
		emptyAdtArray.set(0, 2);							//Ein Element ver�ndern
		assertFalse(emptyAdtArray.equals(emptyAdtArray2));
                
                System.out.println("");
		
		assertFalse(midAdtArray.equals(midAdtArray2));
		
		midAdtArray.set(499, 500);							//An letzte Stelle ein Element einf�gen
		assertTrue(midAdtArray.equals(midAdtArray2));
		
		assertFalse(bigAdtArray.equals(bigAdtArray2));
		
		bigAdtArray.set(999, 1000);
		assertTrue(bigAdtArray.equals(bigAdtArray2));		//An letzte Stelle ein Element einf�gen
		
		AdtArray repeatElem = AdtContainerFactory.adtArray();	//Wiederholende Elemente und Zuf�lliges einf�gen von Elementen
		repeatElem.set(0, 1);
		repeatElem.set(4, 1);
		repeatElem.set(2, 2);
		repeatElem.set(1, 3);
		
		AdtArray repeatElem1 = AdtContainerFactory.adtArray();
		repeatElem1.set(0, 1);
		repeatElem1.set(1, 3);
		repeatElem1.set(2, 2);
		repeatElem1.set(4, 1);
		
		assertEquals(true,repeatElem.equals(repeatElem1));
		
		AdtArray max = AdtContainerFactory.adtArray();
		max.set(50, 10);
		assertTrue(max.length() == 50);
		
		max.set(5000, 10);
		assertTrue(max.length() == 5000);
		
	}
	
	@Test public void testGet(){
		
		AdtArray emptyAdtArray = newAdtArray(0);
		AdtArray emptyAdtArray2=newAdtArray(1);
		AdtArray midAdtArray = newAdtArray(499);
		AdtArray midAdtArray2=newAdtArray(500);
		AdtArray bigAdtArray = newAdtArray(999);
		AdtArray bigAdtArray2=newAdtArray(1000);
		
		assertTrue(emptyAdtArray.get(0) == 0);
		assertTrue(emptyAdtArray.get(10000) == 0);
		assertTrue(emptyAdtArray2.get(0) == 1);
		
		assertTrue(midAdtArray.get(498) == 499);
		assertTrue(midAdtArray.get(499) == 0);
		
		assertTrue(bigAdtArray.get(998) == 999);
		assertTrue(bigAdtArray2.get(999) == 1000);
		assertTrue(bigAdtArray.get(500000) == 0);
		
	}
	
	@Test public void testLength(){
		
		
		AdtArray emptyAdtArray = newAdtArray(0);
		AdtArray emptyAdtArray2=newAdtArray(1);
		AdtArray midAdtArray = newAdtArray(499);
		AdtArray midAdtArray2=newAdtArray(500);
		AdtArray bigAdtArray = newAdtArray(999);
		AdtArray bigAdtArray2=newAdtArray(1000);
		
		assertTrue(emptyAdtArray.length() == -1);
		assertTrue(emptyAdtArray2.length() == 0);
		
		assertTrue(midAdtArray.length() == 498);
		
//		assertTrue(bigAdtArray2.length() == 999);
		
	}

}
