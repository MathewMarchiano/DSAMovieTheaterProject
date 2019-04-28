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

public class ListArrayBased<T> implements ListInterface<T> {

    protected T[] items;
    protected int numItems;

    public ListArrayBased() {
        items = (T[]) new Object[3];
        numItems = 0;
    }

    public ListArrayBased(int size) {
        items = (T[]) new Object[size];
        numItems = 0;
    }

    public boolean isEmpty() {
        return (numItems == 0);
    }

    public int size() {
        return numItems;
    }

    public void removeAll() {
        int size = items.length;
        items = (T[]) new Object[size];
        numItems = 0;
    }

    public void add(int index, T item)
    throws ListIndexOutOfBoundsException {
        // fixes implementation error
        // fixes programming style error
        if (numItems == items.length) {
            throw new ListException("ListException on add");
        }
        if (index >= 0 && index <= numItems) {
            for (int pos = numItems - 1; pos >= index; pos--) {
                items[pos + 1] = items[pos];
            }
            items[index] = item;
            numItems++;
        } else {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }
    }

    public T get(int index)
    throws ListIndexOutOfBoundsException {
        if (index >= 0 && index < numItems) {
            return items[index];
        } else {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }
    }

    public void remove(int index)
    throws ListIndexOutOfBoundsException {
        if (index >= 0 && index < numItems) {
            for (int pos = index + 1; pos < numItems; pos++) {
                items[pos - 1] = items[pos];
            }
            // fixes memory leak
            items[numItems - 1] = null;
            numItems--;
        } else {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }
    }

}