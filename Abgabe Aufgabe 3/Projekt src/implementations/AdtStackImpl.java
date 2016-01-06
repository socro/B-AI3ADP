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
        liste.insert(liste.length() + 1, elem);
    }

    @Override
    public void pop() {
        if (!isEmpty()) {
            liste.delete(liste.length());
        }
    }

    @Override
    public int top() {
        if (!isEmpty()) {
            return liste.retrieve(liste.length());
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return liste.length() == 0;
    }

    @Override
    public boolean equals(Object o) {
        boolean returnValue = false;
        boolean differFlag = false;

        AdtStack memoryThat = AdtContainerFactory.adtStack();
        AdtStack memoryThis = AdtContainerFactory.adtStack();

        if ((o == this)) {
            return true;
        }
        if (this.getClass() == o.getClass()) {
            AdtStack that = (AdtStack) o;

            while (!this.isEmpty() && !that.isEmpty()) {
                if (this.top() != that.top()) {
                    differFlag = true;
                }
                memoryThis.push(this.top());
                memoryThat.push(that.top());

                this.pop();
                that.pop();
            }

            if (this.isEmpty() && that.isEmpty()) {
                if (!differFlag) {
                    returnValue = true;
                }
            } else {
                while (!this.isEmpty()) {
                    memoryThis.push(this.top());
                    this.pop();
                }
                while (!that.isEmpty()) {
                    memoryThat.push(that.top());
                    that.pop();
                }
            }
            while (!memoryThis.isEmpty()) {
                this.push(memoryThis.top());
                memoryThis.pop();
            }
            while (!memoryThat.isEmpty()) {
                that.push(memoryThat.top());
                memoryThat.pop();
            }

        }
        return returnValue;
    }
}
