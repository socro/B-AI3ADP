package interfaces;

import utils.Sortable;

public interface AdtArray extends AdtContainer, Sortable {
    public void set(int pos, int elem);
    public int get(int pos);
    public int length();
}