package structs;

import java.util.Arrays;

public class RABStack<T> implements StackInterface<T> {

    private static final int INITIAL_CAPACITY = 3;

    private T[] items;
    private int numItems;

    public RABStack() {
        this.numItems = 0;
        items = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public void push(T item) {
        if (numItems == items.length ) {
            resize();
        }
        items[numItems++] = item;
    }

    @Override
    public T pop() {
        T result = null;
        if (numItems > 0) {
            result = items[numItems-1];
            items[numItems-1] = null;
            numItems--;
        }

        return result;
    }

    @Override
    public T peek() {
        if (numItems == 0) {
            throw new StackException("StackException on peek");
        }
        return items[numItems-1];
    }

    @Override
    public void popAll() {
        for (int i = 0; i < numItems; i++) {
            items[i] = null;
        }
        numItems = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = numItems - 1; i >= 0; i--) {
            sb.append(items[i] + " ");
        }
        return sb.toString();
    }

    private void resize() {
        int length = items.length;
        length = length << 1; // multiply by two
        items = Arrays.copyOf(items, length);
    }

}

