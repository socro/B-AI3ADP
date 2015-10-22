package implementations;

import interfaces.AdtStack;
import interfaces.AdtList;
import interfaces.AdtQueue;

public class AdtQueueImpl implements AdtQueue{

	private AdtStack stackIn;
	private AdtStack stackOut;
	
	// CREATION
	
	private AdtQueueImpl(AdtStack stackIn, AdtStack stackOut){
		this.stackIn = stackIn;
		this.stackOut = stackOut;
	}
	
	public static AdtQueue valueOf(){
		AdtStack stackIn = AdtContainerFactory.adtStack();
		AdtStack stackOut= AdtContainerFactory.adtStack();
		return new AdtQueueImpl(stackIn, stackOut);
	}
	
	@Override
	public int front() {
		int elem = 0;
		
		if(!(this.stackOut.isEmpty())){
			this.stackOut.top();
		} else if(this.stackIn.isEmpty() && this.stackOut.isEmpty()){
			elem = 0;
		} else if(this.stackOut.isEmpty() && (!(this.stackIn.isEmpty()))){
			while(!(this.stackIn.isEmpty())){
				int newElem = this.stackIn.top();
				this.stackOut.push(newElem);
				this.stackIn.pop();
			}
		} 
		return elem;
		/*
		if(this.stackOut.isEmpty() && this.stackIn.isEmpty()){
			elem = 0;
		} else if(this.stackOut.isEmpty()){
			while(!(this.stackIn.isEmpty())){
				int newElem = this.stackIn.top();
				this.stackIn.pop();
				this.stackOut.push(newElem);
			}
		} else {
			elem = this.stackOut.top();
		}
		return elem;*/
	}

	@Override
	public void enQueue(int elem) {
		//if(this.stackIn.isEmpty())
		this.stackIn.push(elem);
	}

	@Override
	public void deQueue() {
		this.front();
		this.stackOut.pop();
	}

	@Override
	public boolean isEmpty() {
		return (this.stackOut.isEmpty() && this.stackIn.isEmpty());
	}
	
	public boolean equals(Object obj){
		if (obj == null){
			return false;
		}	
		if (getClass() != obj.getClass()) {
			return false;
		}
		AdtQueue objQueue = (AdtQueue) obj;
		while(!(this.isEmpty() || objQueue.isEmpty())){
			int thisElem = this.front();
			int objElem = objQueue.front();
			if(thisElem != objElem){
				return false;
			}
			this.deQueue();
			objQueue.deQueue();
		}
		return true;
	}
}
