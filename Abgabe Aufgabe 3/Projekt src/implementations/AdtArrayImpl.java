package implementations;

import interfaces.AdtArray;
import interfaces.AdtList;
import java.util.Iterator;

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
        if (pos >= 0) {
            if (pos > this.length()) {
                for (int i = this.length() + 1; i < pos; i++) {
                    this.liste.insert(i + 1, 0);
                }
                this.liste.insert(pos + 1, elem);
                this.highestWrittenIndex = pos;
            } else {
                this.liste.insert(pos + 1, elem);
                this.liste.delete(pos + 2);
            }

        }

    }

    @Override
    public int get(int pos) {
        return liste.retrieve(pos + 1);
    }

    @Override
    public int length() {
        if (this.liste.isEmpty()) {
            return -1;
        }
        return highestWrittenIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getClass() == o.getClass()) {
            AdtArray that = (AdtArray) o;
            if (this.length() == that.length()) {
                for (int i = 0; i <= this.length(); i++) {
                    if (this.get(i) != that.get(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
