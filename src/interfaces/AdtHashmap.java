package interfaces;

public interface AdtHashmap extends AdtContainer{
    public AdtHashmap hash(String strategs, String filename);
    public AdtHashmap create(int size, String strategy);
    public AdtHashmap insert(AdtHashmap hashmap, String word);
    public int find(AdtHashmap hashmap, String word);
}
