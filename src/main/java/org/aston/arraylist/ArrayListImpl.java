package org.aston.arraylist;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayListImpl<E>  {
    private E[] array;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MULTIPLIER = 2;
    private int lastIndex = 0;

    /**
     * Creating an empty list with an initial capacity of DEFAULT_CAPACITY.
     */
    public ArrayListImpl() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with an initial capacity of capacity.
     *
     * @param  capacity the initial capacity of the list
     * @throws  IllegalArgumentException - exception throw if the specified initial capacity is illegal
     */
    public ArrayListImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity is illegal: " + capacity);
        }
        this.array = (E[]) new Object[capacity];
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element - to be appended to list.
     */
    public void add(E element) {
        if (lastIndex >= array.length) {
            growArray();
        }
        array[lastIndex] = element;
        lastIndex++;
    }

    /**
     * Inserts the element at the specified position in list.
     * Shifts the element at that position to the right.
     * If inserted at the last position of the list. Adds the new element to the end of the list.
     *
     * @param index   - position to insertion
     * @param element - to be inserted
     * @throws IndexOutOfBoundsException - if index is illegal
     */
    public void insert(int index, E element) {
        if (index == lastIndex) {
            add(element);
            return;
        }
        checkBounds(index);
        if (lastIndex + 1 >= array.length) {
            growArray();
        }
        System.arraycopy(array, index, array, index + 1, lastIndex - index);
        array[index] = element;
        lastIndex++;
    }

    /**
     * Replaces the element at the position in this list with the new element.
     *
     * @param index   - position of element
     * @param element - to be stored
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException - if index is wrong
     */
    public E set(int index, E element) {
        checkBounds(index);
        E oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     *
     * @param index - of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException - if index is invalid
     */
    public E remove(int index) {
        checkBounds(index);
        E element = array[index];
        System.arraycopy(array, index + 1, array, index, lastIndex - index - 1);
        lastIndex--;
        array[lastIndex] = null;
        return element;
    }

    /**
     * Removes the first occurrence of the element from this list.
     * Returns true is any element is removed from the list, or else false.
     *
     * @param removedElement - element to be removed from this list, if present;
     * @return - {@code true} if this list contained the specified element
     */
    public boolean remove(E removedElement) {
        boolean result = false;

        if (removedElement == null) {
            for (int i = 0; i < lastIndex; i++) {
                if (array[i] == null) {
                    remove(i);
                    result = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < lastIndex; i++) {
                if (removedElement.equals(array[i])) {
                    remove(i);
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Returns the element at the position in this list.
     *
     * @param index
     * @return - the element at the position in this list
     * @throws IndexOutOfBoundsException - if index is illegal
     */
    public E get(int index) {
        checkBounds(index);
        return array[index];
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        Arrays.fill(array, null);
        lastIndex = 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return - the number of elements in this list.
     */
    public int size() {
        return lastIndex;
    }

    /**
     Sorts the list according to the order set by the comparator.
     *
     * @param comparator — used to compare the elements of the list
     * @throws NullPointerException — if the list contains null elements
     */
    public void sort(Comparator<? super E> comparator) {
        quickSort(0, lastIndex - 1, comparator);
    }

    /**
     * Realisation of QuickSort.
     *
     * @param low        - bound of array
     * @param high       - bound of array
     * @param comparator - used to compare list elements
     * @throws NullPointerException - if the list contains NULL elements
     */
    private void quickSort(int low, int high, Comparator comparator) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        E wall = array[middle];

        int leftBound = low;
        int rightBound = high;
        while (leftBound <= rightBound) {
            while (comparator.compare(array[leftBound], wall) < 0) {
                leftBound++;
            }
            while (comparator.compare(array[rightBound], wall) > 0) {
                rightBound--;
            }
            if (leftBound <= rightBound) {
                E temp = array[leftBound];
                array[leftBound] = array[rightBound];
                array[rightBound] = temp;
                leftBound++;
                rightBound--;
            }
        }
        if (low < rightBound) {
            quickSort(low, rightBound, comparator);
        }
        if (high > leftBound) {
            quickSort(leftBound, high, comparator);
        }
    }

    /**
     * Resize base array.
     * Create the new array with new capacity. Copy all elements to the new array.
     */
    private void growArray() {
        long newCapacity = (long) array.length * 3 / 2 + 1;

        if (newCapacity > Integer.MAX_VALUE) {
            newCapacity = Integer.MAX_VALUE;
        }

        E[] newArray = (E[]) new Object[(int) newCapacity];
        System.arraycopy(this.array, 0, newArray, 0, array.length);
        this.array = newArray;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= lastIndex) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, lastIndex));
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, 0, lastIndex));
    }
}
