package implementations;

import interfaces.AdtList;
import interfaces.AdtQueue;
import interfaces.AdtStack;

import java.util.ArrayList;

import implementations.AdtContainerFactory;


final class AdtStackImpl implements AdtStack {
	
	public static void main(String args[]){
		AdtStack stack = AdtContainerFactory.adtStack();
		AdtStack stack2 = AdtContainerFactory.adtStack();
		stack.push(2);
		stack2.push(2);
		stack.push(2);
		boolean ans = stack.equals(stack2);
		System.out.println(ans);
	}
	
	private AdtList stack;
	private int max;
	
	
	private AdtStackImpl(){
		this.stack = AdtContainerFactory.adtList();
		this.max=0;
	}
	
	public static AdtStack valueOf(){
		return new AdtStackImpl();
	}
	
	public AdtList getStack(){
		return this.stack;
	}
	
	public int getMax(){
		return this.max;
	}

	@Override
	public void push(int elem) {
		max++;
		this.getStack().insert(max, elem);
		//max++;
	}

	@Override
	public void pop() {
		this.getStack().delete(this.getStack().retrieve(max));
		max--;
	}

	@Override
	public int top() {
		int elem = this.getStack().retrieve(max);
		return elem;
	}

	@Override
	public boolean isEmpty() {
		return max == 0;
	}
	
	public boolean equals(Object obj){
		if (obj == null){
			return false;
		}	
		if (getClass() != obj.getClass()) {
			return false;
		}
		AdtStackImpl objStack = (AdtStackImpl) obj;
		if (this.max != objStack.max){
			return false;
		}
		if(!(this.stack.equals(objStack.stack))){
			return false;
		}		
		return true;
	}
	
}
