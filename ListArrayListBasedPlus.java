package structs;

/*
 * Purpose: Data Structure and Algorithms Lab 2 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 03/03/19
 * Submitted:  03/04/19
 * Comment: This is a redo
 * @author: Scott Zockoll
 * @version: 2019.03.03
 */

import java.util.ArrayList;

public class ListArrayListBasedPlus<T> implements ListInterface<T> {

    private ArrayList<T> items;

    public ListArrayListBasedPlus() {
        this.items = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return items.size() == 0;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void add(int index, T item) throws ListIndexOutOfBoundsException {
        items.add(index, item);
    }

    @Override
    public T get(int index) throws ListIndexOutOfBoundsException {
        return items.get(index);
    }

    @Override
    public void remove(int index) throws ListIndexOutOfBoundsException {
        items.remove(index);
    }

    @Override
    public void removeAll() {
        items = new ArrayList<>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T i : items) {
            sb.append(i + " ");
        }
        return sb.toString();
    }

}
