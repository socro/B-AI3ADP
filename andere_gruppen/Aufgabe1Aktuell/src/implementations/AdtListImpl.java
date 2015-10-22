package implementations;

import java.util.ArrayList;

import interfaces.AdtList;

public class AdtListImpl implements AdtList{
	
//	public static void main(String args[]){
//		AdtList liste = AdtContainerFactory.adtList();
//		AdtList liste1 = AdtContainerFactory.adtList();
//		
//		liste.insert(1, 2);
//		liste.insert(2, 3);
//		liste.insert(3, 6);
//		liste1.insert(1, 2);
//		liste1.insert(2, 3);
//		liste1.insert(3, 6);
//		
//		boolean ans= liste.equals(liste1);
//		System.out.println(ans);
//	}

	private int[] internArray;
	
	// CREATION
	
	private AdtListImpl(int[] internArray){
		this.internArray = internArray;
	}
	
	public static AdtList valueOf(){
		int[] newInternArray = new int[1];
		newInternArray[0] = 0;
		
		return new AdtListImpl(newInternArray);
	}
	
	// OPERATIONS
	
	@Override
	public boolean isEmpty() {return (this.length() == 0);}

	@Override
	public int length() {return (this.internArray.length -1);}

	@Override
	public void insert(int pos, int elem) {
		int newlength = (this.internArray.length +1);
		if(pos <= newlength){
			int[] newInternArray = new int[newlength];
			int[] oldInternArray = this.internArray;
			int oldElem;
			for(int i = 1; i < newlength; i++){
				if(i < pos){
					oldElem = oldInternArray[i];
					newInternArray[i] = oldElem;
				} else if(i == pos){
					newInternArray[pos] = elem;
				} else if(i > pos && i > 2){
					oldElem = oldInternArray[i-1];
					newInternArray[i] = oldElem;
				}
			}
			this.internArray = newInternArray;
		}
		
	}

	@Override
	public void delete(int pos) {
		int newlength = (this.internArray.length -1);
		if(pos <= newlength){
			int[] newInternArray = new int[newlength];
			int[] oldInternArray = this.internArray;
			int oldElem;
			for(int i = 1; i < newlength; i++){
				if(i < pos){
					oldElem = oldInternArray[i];
					newInternArray[i] = oldElem;
				} /*else if(i == pos){
					newInternArray[pos] = oldInternArray[pos+1];
				}*/ else if((i >= pos) && (i > 2)){
					oldElem = oldInternArray[i+1];
					newInternArray[i] = oldElem;
				}
			}
			this.internArray = newInternArray;
		}
		
	}

	@Override
	public int find(int elem) {
		int pos = 0;
		int listLength = this.length();
		for(int i=1; i<= listLength; i++){
			if(this.retrieve(i) == elem){
				pos = i;
			}
		}
		return pos;
	}

	@Override
	public int retrieve(int pos) {
		int elem;
		int[] array = this.internArray;
		
		if(pos > this.length()){
			elem = 0;
		} else {
			elem = array[pos];
		}
		return elem;
	}

	@Override
	public void concat(AdtList list) {
		for(int i = 1; i <= list.length();i++){
			int newElem = list.retrieve(i);
			this.insert(this.length()+1, newElem);
		}
	}
	
	public boolean equals(Object obj){
		if (obj == null){
			return false;
		}	
		if (getClass() != obj.getClass()) {
			return false;
		}
		AdtList other = (AdtList) obj;
		if (this.length() != other.length()){
			return false;
		}
		int laenge = this.length();
		for(int i = 1; i <= laenge; i++){
			if(this.retrieve(i) != other.retrieve(i) ){
				return false;
			};
		}
		return true;
	}
}
