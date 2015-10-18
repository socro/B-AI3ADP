package interfaces;

public interface AdtArray extends AdtContainer {
    public void set(int pos, int elem);
    public int get(int pos);
    public int length();
}