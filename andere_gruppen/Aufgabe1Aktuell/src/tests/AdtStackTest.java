package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import implementations.AdtContainerFactory;
import interfaces.AdtStack;
import junit.framework.Assert;

public class AdtStackTest {
	
	
	private AdtStack newAdtStack(int elements){
		AdtStack newAdtStack = AdtContainerFactory.adtStack();
		for(int i = 1; i <= elements; i++){
			newAdtStack.push(i);
		}
		return newAdtStack;
	}

	@Test
	public void testPush() {
		AdtStack emptyAdtStack = newAdtStack(0);
		AdtStack emptyAdtStack2=newAdtStack(1);
		AdtStack midAdtStack = newAdtStack(498);
		AdtStack midAdtStack2=newAdtStack(500);
		AdtStack bigAdtStack = newAdtStack(999);
		AdtStack bigAdtStack2=newAdtStack(1000);
		
		emptyAdtStack.push(1);
		midAdtStack.push(499);
		midAdtStack.push(500);
		bigAdtStack.push(1000);		
		
		Assert.assertEquals(emptyAdtStack, emptyAdtStack2);		
		Assert.assertEquals(midAdtStack, midAdtStack2);	
		Assert.assertEquals(bigAdtStack, bigAdtStack2);	
	}
		
	@Test
	public void testPop(){
		AdtStack emptyAdtStack = newAdtStack(0);
		AdtStack emptyAdtStack2=newAdtStack(1);
		AdtStack midAdtStack = newAdtStack(499);
		AdtStack midAdtStack2=newAdtStack(500);
		AdtStack bigAdtStack = newAdtStack(999);
		AdtStack bigAdtStack2=newAdtStack(1000);
		
		emptyAdtStack2.pop();
		midAdtStack2.pop();
		bigAdtStack2.pop();		
		
		Assert.assertEquals(emptyAdtStack, emptyAdtStack2);		
		Assert.assertEquals(midAdtStack, midAdtStack2);	
		Assert.assertEquals(bigAdtStack, bigAdtStack2);	
	}
	
	@Test
	public void testTop(){
		AdtStack emptyAdtStack = newAdtStack(0);
		AdtStack emptyAdtStack2=newAdtStack(1);
		AdtStack midAdtStack=newAdtStack(500);
		AdtStack bigAdtStack=newAdtStack(1000);
		
		int erg1 = emptyAdtStack.top();
		int erg2 = emptyAdtStack2.top();
		int erg3 = midAdtStack.top();
		int erg4 = bigAdtStack.top();	
		
		Assert.assertEquals(0, erg1);		
		Assert.assertEquals(1, erg2);
		Assert.assertEquals(500, erg3);	
		Assert.assertEquals(1000, erg4);	
	}
	
	@Test
	public void testIsEmpty(){
		AdtStack emptyAdtStack = newAdtStack(0);
		AdtStack emptyAdtStack2=newAdtStack(1);
		AdtStack midAdtStack=newAdtStack(500);
		AdtStack bigAdtStack=newAdtStack(1000);
		
		boolean erg1 = emptyAdtStack.isEmpty();
		boolean erg2 = emptyAdtStack2.isEmpty();
		boolean erg3 = midAdtStack.isEmpty();
		boolean erg4 = bigAdtStack.isEmpty();	
		
		Assert.assertTrue(erg1);		
		Assert.assertFalse(erg2);
		Assert.assertFalse(erg3);	
		Assert.assertFalse(erg4);
	}

}
