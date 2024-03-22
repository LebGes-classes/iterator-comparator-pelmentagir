package org.example;

public interface LinkedList<E> {
    void add(E value);
    void add(int position, E value);
    void clear();
    void removeIndex(int index);
    void removeElement(E value);
    E findIndex(int index);
    int findElement(E value);
    E get(int index);
}
