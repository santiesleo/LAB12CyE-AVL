package model;

public interface IAVL<K, T> {

    public void insert(K key, T value);
    public String inOrder();
    public String preOrder();

}
