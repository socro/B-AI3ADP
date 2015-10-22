package implementations;

import interfaces.AdtQueue;
import interfaces.AdtStack;
import java.util.ArrayList;
import java.util.Collections;

class AdtQueueImpl implements AdtQueue {

    static AdtQueue valueOf() {
        return new AdtQueueImpl();
    }

    private final AdtStack input;
    private final AdtStack output;

    AdtQueueImpl() {
        this.input = AdtContainerFactory.adtStack();
        this.output = AdtContainerFactory.adtStack();
    }

    @Override
    public int front() {
        if (output.isEmpty()) {
            stackToOutput();
        }
        if (output.isEmpty()) {
            return 0;
        }
        return output.top();
    }

    @Override
    public void enQueue(int elem) {
        input.push(elem);
    }

    @Override
    public void deQueue() {
        if (output.isEmpty()) {
            stackToOutput();
        }
        output.pop();
    }

    @Override
    public boolean isEmpty() {
        return output.isEmpty() && input.isEmpty();
    }

    private void stackToOutput() {
        while (!input.isEmpty()) {
            output.push(input.top());
            input.pop();
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean returnValue = false;
        boolean differFlag = false;
        
        

        AdtQueue memoryThis = AdtContainerFactory.adtQueue();
        AdtQueue memoryThat = AdtContainerFactory.adtQueue();

        if (this == o) {
            return true;
        } else if (this.getClass() == o.getClass()) {
            AdtQueue that = (AdtQueue) o;

            while (!this.isEmpty() && !that.isEmpty()) {
                if (this.front() != that.front()) {
                    differFlag = true;
                }
                memoryThis.enQueue(this.front());
                memoryThat.enQueue(that.front());

                this.deQueue();
                that.deQueue();
            }

            if (this.isEmpty() && that.isEmpty()) {
                if (!differFlag) {
                    returnValue = true;
                }
            } else {
                while (!this.isEmpty()) {
                    memoryThis.enQueue(this.front());
                    this.deQueue();
                }
                while (!that.isEmpty()) {
                    memoryThat.enQueue(that.front());
                    that.deQueue();
                }
            }
            while (!memoryThis.isEmpty()) {
                    this.enQueue(memoryThis.front());
                    memoryThis.deQueue();
                }            
            that = memoryThat;
        }
        return returnValue;
    }
}
