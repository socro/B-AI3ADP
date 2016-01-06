package interfaces;

public interface AdtList extends AdtContainer {
    public boolean isEmpty();
    public int length();
    public void insert(int pos, int elem);
    public void delete(int pos);
    public int find(int elem);
    public int retrieve(int pos);
    public void concat(AdtList list);
}