package implementations;

import interfaces.AdtArray;
import interfaces.AdtList;
import interfaces.AdtStack;


class AdtArrayImpl implements AdtArray {
	
	
	public static void main(String args[]){
		AdtArray repeatElem = AdtContainerFactory.adtArray();
		repeatElem.set(0, 1);
		repeatElem.set(4, 1);
		repeatElem.set(2, 2);
		repeatElem.set(1, 3);
		
		AdtArray repeatElem1 = AdtContainerFactory.adtArray();
		repeatElem1.set(0, 1);
		repeatElem1.set(1, 3);
		repeatElem1.set(2, 2);
		
		repeatElem1.set(4, 1);
		System.out.println(repeatElem1.get(4));
		
		
//		int elem = 2;
//		System.out.println(repeatElem.get(elem));
		
	}
	
	private AdtList array;
	private int max;
	
	
	
	
	
	//Creation
	private AdtArrayImpl(){
		this.max = -1;
		this.array = AdtContainerFactory.adtList();
	}
	
	public static AdtArray valueOf(){
		return new AdtArrayImpl();
	}
	
	//Selectors
	
	public AdtList getArray(){
		return this.array;
	}
	
	
	public void setMax(int pos){
		if(pos > this.max){
			this.max = pos;
		}
	}

	@Override
	public void set(int pos, int elem) {
		
		if(pos >= 0){
			if(pos > this.length()){
				for(int i = this.length() + 1 ; i < pos ; i++){
					this.array.insert(i +  1, 0);
				}
				this.array.insert(pos + 1 , elem);
				this.setMax(pos);
			}else{
				this.array.insert(pos + 1, elem);
				this.array.delete(pos + 2);
			}
			
		}
		
	}

	@Override
	public int get(int pos) {
		return this.array.retrieve(pos + 1);
		
	}

	@Override
	public int length() {
		return this.max;
	}
	
	
	public boolean equals(Object obj){
		if (obj == null){
			return false;
		}	
		if (getClass() != obj.getClass()) {
			return false;
		}
		AdtArrayImpl objStack = (AdtArrayImpl) obj;
		if (this.max != objStack.max){
			return false;
		}
		if(!(this.array.equals(objStack.array))){
			return false;
		}		
		return true;
	}

}
