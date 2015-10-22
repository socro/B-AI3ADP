package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import implementations.AdtContainerFactory;
import implementations.AdtListImpl;
import interfaces.AdtList;
import junit.framework.Assert;

public class AdtListTest {

	private AdtList newAdtList(int elements){
		AdtList newAdtList = AdtContainerFactory.adtList();
		for(int i = 1; i <= elements; i++){
			newAdtList.insert(i, i);
		}
		return newAdtList;
	}
	
	@Test
	public void testIsEmpty() {
		AdtList emptyAdtList = newAdtList(0);//ad_adtContainerFactory.adtList();
		boolean erg1 = emptyAdtList.isEmpty();
		int erg4 = emptyAdtList.length();
				
		AdtList midAdtList = newAdtList(500);//  ad_adtContainerFactory.adtList();
		boolean erg2 = midAdtList.isEmpty();		
		
		AdtList bigAdtList = newAdtList(1000);//  ad_adtContainerFactory.adtList();
		boolean erg3 = bigAdtList.isEmpty();
		
		Assert.assertEquals(true, erg1);
		Assert.assertEquals(false, erg2);	
		Assert.assertEquals(false, erg3);	
	}

	@Test
	public void testLength() {
		AdtList emptyAdtList = newAdtList(0);
		AdtList midAdtList = newAdtList(500);
		AdtList bigAdtList = newAdtList(1000);
		
		int erg1 = emptyAdtList.length();
		int erg2 = midAdtList.length();
		int erg3 = bigAdtList.length();
		
		Assert.assertEquals(0,erg1);		
		Assert.assertEquals(500, erg2);	
		Assert.assertEquals(1000, erg3);
	}

	@Test
	public void testInsert() {
		AdtList emptyAdtList = newAdtList(0);
		AdtList midAdtList = newAdtList(499);
		AdtList bigAdtList = newAdtList(999);
		
		emptyAdtList.insert(1, 1);
		midAdtList.insert(500,500);
		bigAdtList.insert(1000,1000);
		
		AdtList emptyAdtList2 = newAdtList(1);
		AdtList midAdtList2 = newAdtList(500);
		AdtList bigAdtList2 = newAdtList(1000);
		
		Assert.assertEquals(emptyAdtList2, emptyAdtList);		
		Assert.assertEquals(midAdtList2, midAdtList);	
		Assert.assertEquals(bigAdtList2, bigAdtList);
	}

	@Test
	public void testDelete() {
		AdtList emptyAdtList = newAdtList(1);
		AdtList midAdtList = newAdtList(500);
		AdtList bigAdtList = newAdtList(1000);
		
		AdtList emptyAdtList2 = newAdtList(0);
		AdtList midAdtList2 = newAdtList(499);
		AdtList bigAdtList2 = newAdtList(999);
		
		emptyAdtList.delete(1);
		midAdtList.delete(500);
		bigAdtList.delete(1000);
		
		Assert.assertEquals(emptyAdtList2, emptyAdtList);		
		Assert.assertEquals(midAdtList2, midAdtList);	
		Assert.assertEquals(bigAdtList2, bigAdtList);
	}

	@Test
	public void testFind() {
		AdtList emptyAdtList = newAdtList(1);
		AdtList midAdtList = newAdtList(500);
		AdtList bigAdtList = newAdtList(1000);
		
		int erg1 = emptyAdtList.find(1);
		int erg2 = midAdtList.find(250);
		int erg3 = bigAdtList.find(500);
		
		Assert.assertEquals(1, erg1);		
		Assert.assertEquals(250, erg2);	
		Assert.assertEquals(500, erg3);	
	}

	@Test
	public void testRetrieve() {
		AdtList emptyAdtList = newAdtList(1);
		AdtList midAdtList = newAdtList(500);
		AdtList bigAdtList = newAdtList(1000);
		
		int erg1 = emptyAdtList.retrieve(1);
		int erg2 = midAdtList.retrieve(250);
		int erg3 = bigAdtList.retrieve(500);
		
		Assert.assertEquals(1, erg1);
		Assert.assertEquals(250, erg2);	
		Assert.assertEquals(500, erg3);	
		
		AdtList midAdtList2 = newAdtList(500);
		Assert.assertEquals(midAdtList, midAdtList2);
	}

	@Test
	public void testConcat() {
		//fail();
		AdtList emptyAdtList = newAdtList(2);
		AdtList emptyAdtList2 = newAdtList(1);
		AdtList emptyAdtList3 = newAdtList(1);
		emptyAdtList3.delete(1);
		emptyAdtList3.insert(1,2);
		emptyAdtList2.concat(emptyAdtList3);
		
		AdtList newAdtList = AdtContainerFactory.adtList();
		int x = 1;
		int elements = 500;
		for(int i = 1; i <= elements; i++){
			if(x > (elements / 2)){
				x = 1;
			}
			newAdtList.insert(i, x);
			x++;
		}
		AdtList midAdtList = newAdtList;
		AdtList midAdtList2 = newAdtList(250);
		AdtList midAdtList3 = newAdtList(250);
		midAdtList2.concat(midAdtList3);

		
		AdtList newAdtList2 = AdtContainerFactory.adtList();
		int x2 = 1;
		int elements2 = 1000;
		for(int i = 1; i <= elements2; i++){
			if(x2 > (elements2 / 2)){
				x2 = 1;
			}
			newAdtList2.insert(i, x2);
			x2++;
		}
		AdtList bigAdtList = newAdtList2;
		AdtList bigAdtList2 = newAdtList(500);
		AdtList bigAdtList3 = newAdtList(500);
		bigAdtList2.concat(bigAdtList3);
		
		Assert.assertEquals(emptyAdtList2, emptyAdtList);
		Assert.assertEquals(midAdtList2, midAdtList);
		Assert.assertEquals(bigAdtList2, bigAdtList);
	}

}
