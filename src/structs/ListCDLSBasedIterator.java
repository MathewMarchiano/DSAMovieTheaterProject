package structs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListCDLSBasedIterator<T> extends ListCDLSBased<T>
    implements Iterator<T> {

    private DNode<T> dummyNode;
    private DNode<T> curr;

    public ListCDLSBasedIterator(ListCDLSBased<T> list) {
        if (list != null) {
            this.dummyNode = list.head;
            this.curr = (DNode<T>) list.head.getNext();
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
        if ((DNode<T>) curr.getNext() == dummyNode) {
            curr = (DNode<T>) curr.getNext().getNext();
        } else {
            curr = (DNode<T>) curr.getNext();
        }
        return result;
    }
}
