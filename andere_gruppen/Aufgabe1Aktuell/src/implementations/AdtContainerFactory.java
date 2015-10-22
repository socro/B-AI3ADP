package implementations;

import interfaces.AdtArray;
import interfaces.AdtList;
import interfaces.AdtStack;
import interfaces.AdtQueue;
import java.util.ArrayList;

public class AdtContainerFactory {

	private AdtContainerFactory(){}
	
	public static AdtList adtList(){
		return AdtListImpl.valueOf();
	}
	
	public static AdtStack adtStack(){
		return AdtStackImpl.valueOf();
	}
	
	public static AdtQueue adtQueue(){
		return AdtQueueImpl.valueOf();
	}
	
	public static AdtArray adtArray(){
		return AdtArrayImpl.valueOf();
	}
}
