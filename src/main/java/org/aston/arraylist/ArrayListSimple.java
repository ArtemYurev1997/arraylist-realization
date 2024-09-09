package org.aston.arraylist;

public interface ArrayListSimple<E> extends Iterable<E> {
    boolean add(E e);
    void delete(int index);
    E get(int index);
    int size();
    E set(int index, E e);
    boolean remove(E removedElement);
    void clear();
}
