package implementations;

import interfaces.AdtArray;
import interfaces.AdtList;

class AdtArrayImpl implements AdtArray {

    private final AdtList liste;

    private int highestWrittenIndex = 0;

    AdtArrayImpl() {
        this.liste = AdtContainerFactory.adtList();
    }

    public static AdtArray valueOf() {
        return new AdtArrayImpl();
    }

    @Override
    public void set(int pos, int elem) {
        if (highestWrittenIndex >= pos) {
            liste.delete(pos + 1);
            liste.insert(pos + 1, elem);
        } else {
            int diffToPos = pos - highestWrittenIndex;
            while (diffToPos > highestWrittenIndex) {
                liste.insert(diffToPos, 0);

                diffToPos--;
            }
            highestWrittenIndex = pos;
            set(pos, elem);
        }
    }

    @Override
    public int get(int pos) {
        return liste.retrieve(pos + 1);
    }

    @Override
    public int length() {
        return liste.length() + 1;
    }

}
