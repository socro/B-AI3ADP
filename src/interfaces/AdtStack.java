package interfaces;

public interface AdtStack extends AdtContainer {
    public void push(int elem);
    public void pop();
    public int top();
    public boolean isEmpty();
}