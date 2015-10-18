package implementations;

import interfaces.AdtList;


class AdtListImpl implements AdtList {
    
    private int[] liste = new int[1];
    
    AdtListImpl(){
            
    }
    
    public AdtListImpl valueOf(){
        return new AdtListImpl();
    }
    
    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public int length() {
        return liste.length-1;
    }

    @Override
    public void insert(int pos, int elem) {
        if(pos > 0 && pos <= liste.length) {            
            int[] temp = new int[liste.length+1];
            
            System.arraycopy(liste, 0, temp, 0, pos);
            temp[pos] = elem;
            if(pos != liste.length){
                System.arraycopy(liste, pos, temp, pos+1, liste.length-pos);
            }            
            
            liste = temp; 
        }
    }

    @Override
    public void delete(int pos) {
        if(pos > 0 && pos <= length()){
            int[] temp = new int[length()];
            
            System.arraycopy(liste, 0, temp, 0, pos);
            System.arraycopy(liste, 0, temp, 0, length()-pos);
            
            liste = temp;
        }
    }

    @Override
    public int find(int elem) {
        liste[0] = elem;
        int iterator = length();
        while(liste[iterator] != elem){
            iterator--;
        }
        return iterator; 
    }

    @Override
    public int retrieve(int pos) {
        if(pos > 0 && pos <= length()){
            return liste[pos];
        }
        return 0;
    }

    @Override
    public void concat(AdtList list) {
        int[] arraylist = new int[list.length()];
        int iterator = 1;
        while(iterator < list.length()+1){
            arraylist[iterator-1] = list.retrieve(iterator);
            iterator++;
        }        
        int[] templist = new int[liste.length + arraylist.length];
        System.arraycopy(liste, 0, templist, 0, liste.length);
        System.arraycopy(arraylist, 0, templist, liste.length, arraylist.length);
        
        liste = templist;
    }    
}
