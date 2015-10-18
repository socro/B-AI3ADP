package interfaces;

public interface AdtQueue extends AdtContainer {
    public int front();
    public void enQueue(int elem);
    public void deQueue();
    public boolean isEmpty();
}