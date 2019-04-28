package structs;

import java.util.NoSuchElementException;

public class ListCDLSBasedIterator<T> extends ListCDLSBased implements java.util.Iterator {

    private DNode<T> curr;

    public ListCDLSBasedIterator(ListCDLSBased<T> list) {
        this.curr = list.head;
    }

    @Override
    public boolean hasNext() {
        return curr.getNext() != null;
    }

    @Override
    public T next() {
        if (curr == null) {
            throw new NoSuchElementException("Iterator has been exhausted!");
        }
        curr = (DNode<T>) curr.getNext();
        return curr.getItem();
    }
}
