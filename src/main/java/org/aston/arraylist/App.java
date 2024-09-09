package org.aston.arraylist;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Main class for testing app.
 *
 */

public class App 
{
    public static void main( String[] args )
    {
        ArrayListImpl<Integer> arrayListSimple = new ArrayListImpl<>();
        arrayListSimple.add(7);
        arrayListSimple.add(2);
        arrayListSimple.add(5);
        arrayListSimple.add(3);
        arrayListSimple.add(4);
        arrayListSimple.add(9);
        arrayListSimple.add(1);
        arrayListSimple.add(10);
        arrayListSimple.add(15);
        arrayListSimple.add(12);
        System.out.println(arrayListSimple.get(0));
        System.out.println("************");

        int n = arrayListSimple.remove(0);
        System.out.println(n);
        System.out.println("************");

        System.out.println(arrayListSimple);
        arrayListSimple.sort(Comparator.naturalOrder());
        System.out.println(arrayListSimple);
        System.out.println("************");

        arrayListSimple.insert(2, 18);
        System.out.println(arrayListSimple);
    }
}
