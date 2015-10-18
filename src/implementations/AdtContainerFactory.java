package implementations;

import interfaces.AdtList;

public class AdtContainerFactory {

    public static AdtList adtList() {
        return new AdtListImpl();
    }
}
