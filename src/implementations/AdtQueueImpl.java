package implementations;

import interfaces.AdtQueue;
import interfaces.AdtStack;

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
        if (output.isEmpty()){
            stackToOutput();
        }
        if (output.isEmpty()){
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
        if(output.isEmpty()){
            stackToOutput();
        }
        output.pop();
    }

    @Override
    public boolean isEmpty() {
        return output.isEmpty() && input.isEmpty();
    }

    private void stackToOutput() {
        while(input.top() != 0){
                output.push(input.top());
            }
    }
    
}
