package implementations;

import interfaces.*;

public class AdtContainerFactory {

    public static AdtList adtList() {
        return AdtListImpl.valueOf();
    }
    
    public static AdtArray adtArray() {
        return AdtArrayImpl.valueOf();
    }
}
