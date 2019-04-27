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

public class RABList<T> extends ListArrayBased<T> {

    public RABList() {
        super();
    }

    public void add(int index, T item)
    throws ListIndexOutOfBoundsException {

        if (super.numItems == super.items.length) {
            resize();
        }

        super.add(index, item);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numItems; i++) {
            sb.append(super.items[i] + " ");
        }

        return sb.toString();

    }

    public void reverse() {

        T[] temp = (T[]) new Object[super.items.length];
        for (int i = numItems - 1, j = 0; i >= 0; i--, j++) {
            temp[j] = super.items[i];
        }

        super.items = temp;

    }

    private void resize() {

        T[] newItems = (T[]) new Object[super.items.length << 1];
        for (int i = 0; i < super.items.length; i++) {
            newItems[i] = super.items[i];
        }

        super.items = newItems;

    }

}