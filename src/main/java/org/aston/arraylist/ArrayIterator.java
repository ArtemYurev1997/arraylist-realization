package org.aston.arraylist;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {
    private int index = 0;
    E[] values;

    public ArrayIterator(E[] values) {
        this.values = values;
    }

    /**
     * Returns true if there is an element located after the pointer
     *
     * @return - the element at the position in this list
     */
    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    /**
     * Returns the next element after the pointer. If there is none, a NoSuchElementException is thrown.
     *
     * @return - the element at the position in this list
     */

    @Override
    public E next() {
        return values[index++];
    }
}
