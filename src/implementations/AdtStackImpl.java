package implementations;

import interfaces.AdtList;
import interfaces.AdtStack;

class AdtStackImpl implements AdtStack {

    static AdtStack valueOf() {
        return new AdtStackImpl();
    }
    
    private final AdtList liste;

    AdtStackImpl() {
        this.liste = AdtContainerFactory.adtList();
    }

    @Override
    public void push(int elem) {
        liste.insert(liste.length()+1, elem);
    }

    @Override
    public void pop() {
        if(!isEmpty()) {
            liste.delete(liste.length());
        }
    }

    @Override
    public int top() {
        if(!isEmpty()) {
            return liste.retrieve(liste.length());
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return liste.length() == 0;
    }
    
    @Override
    public boolean equals(Object o){        
        if((o == this)) {
            return true;
        }
        if(this.getClass() == o.getClass()){
            AdtStack that = (AdtStack) o;
                                    
            while(!this.isEmpty() && !that.isEmpty()){
                if(!(this.top() == that.top())) {
                    return false;
                }               
                this.pop();
                that.pop();
            }            
            
            if(this.isEmpty() && that.isEmpty()){
                return true;
            }
        }        
        return false;
    }
}
