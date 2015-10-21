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
            liste.delete(pos);
            liste.insert(pos, elem);
        } else if(pos >= 0){
            int diffToPos = 1;
            while (diffToPos < pos) {
                liste.insert(diffToPos, 0);

                diffToPos++;
            }
            highestWrittenIndex = pos;
            set(pos, elem);
        }
    }

    @Override
    public int get(int pos) {
        return liste.retrieve(pos);
    }

    @Override
    public int length() {
        if (this.liste.isEmpty()) {
            return -1;            
        }
        return highestWrittenIndex;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        } else if(this.getClass() == o.getClass()){
            AdtArray that = (AdtArray) o;
            if(this.length() == that.length()){
                for(int i = 0; i > this.length(); i++){
                    if(this.get(i) != that.get(i)){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

}
