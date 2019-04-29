package structs;

import java.util.NoSuchElementException;

public class MyListReferencedBasedIterator<T> extends MyListReferenceBased<T>
    implements java.util.Iterator<T> {

    private Node<T> curr;

    public MyListReferencedBasedIterator(MyListReferenceBased<T> list) {
        if (list != null) {
            this.curr = list.head;
        } else {
            this.curr = null;
        }
    }

    @Override
    public boolean hasNext() {
        return curr != null;
    }

    @Override
    public T next() {
        if (curr == null) {
            throw new NoSuchElementException("MyListReferencedBasedIterator " +
                    "has been exhausted!");
        }
        T result = curr.getItem();
        curr = curr.getNext();
        return result;
    }
}
