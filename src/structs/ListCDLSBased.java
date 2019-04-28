/*
 * Purpose: Data Structure and Algorithms Lab 4 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 03/30/19
 * Submitted:  03/31/19
 * Comment: This is a redo
 * @author: Scott Zockoll
 * @version: 2019.03.30
 */

package structs;

public class ListCDLSBased<T> implements ListInterface<T> {

    protected DNode<T> head;
    private int numItems;

    public ListCDLSBased() {
        // using dummy node
        this.head = new DNode<T>(null);
        this.head.setBack(this.head);
        this.head.setNext(this.head);
        this.numItems = 0;
    }

    public boolean isEmpty() {
        return this.numItems == 0;
    }

    public int size() {
        return this.numItems;
    }

    // Can give index one greater to add to the end
    public void add(int index, T item) throws ListIndexOutOfBoundsException {

        // index check
        if (index >= 0 && index < this.numItems + 1) {

            if (index == this.numItems) {
                DNode<T> newNode = new DNode<>(item, this.head.getBack(), this.head);
                this.head.setBack(newNode);
                newNode.getBack().setNext(newNode);
            }
            else {
                DNode<T> curr = (DNode<T>) find(index);
                DNode<T> newNode = new DNode<>(item, curr.getBack(), curr);
                curr.setBack(newNode);
                newNode.getBack().setNext(newNode);
            }


            this.numItems++;

        } else {
            throw new ListIndexOutOfBoundsException("List index out of bounds exception on add");
        }

    }

    public T get(int index) throws ListIndexOutOfBoundsException {

        // index check
        if (index >= 0 && index < numItems) {

            DNode<T> nodeToGet = find(index);
            return nodeToGet.getItem();

        } else {
            throw new ListIndexOutOfBoundsException("List index out of bounds exception on get");
        }

    }

    @Override
    public void remove(int index) throws ListIndexOutOfBoundsException {

        if (index >= 0 && index < this.numItems) {

            DNode<T> curr = find(index);

            DNode<T> currNext = (DNode<T>) curr.getNext();
            curr.getBack().setNext(currNext);
            ((DNode<T>) curr.getNext()).setBack(curr.getBack());

            this.numItems--;

        } else {
            throw new ListIndexOutOfBoundsException("List index out of bounds exception on remove");

        }

    }


    @Override
    public void removeAll() {
        // using dummy node
        this.head = new DNode<T>(null);
        this.head.setBack(this.head);
        this.head.setNext(this.head);
        this.numItems = 0;

    }

    @Override
    public String toString() {

        String outString;

        // if empty
        if (this.numItems == 0) {
            outString = "";
        } else {

            StringBuilder sb = new StringBuilder();
            DNode<T> curr = (DNode<T>) this.head.getNext();

            do {
                sb.append(curr.getItem() + " ");
                curr = (DNode<T>) curr.getNext();
            } while(curr != head);

            outString = sb.toString();

        }

        return outString;

    }

    private DNode<T> find(int index) throws ListIndexOutOfBoundsException {

        // index check
        if (index >= 0 && index < this.numItems + 1) {

            DNode<T> curr;

            // index is in the first half:
            // traverse forwards
            if (index < (this.numItems / 2) + 1) {
                curr = (DNode<T>) this.head.getNext();
                for (int i = 0; i < index; i++) {
                    curr = (DNode<T>) curr.getNext();
                }
            }
            // index is in second half:
            // traverse backwards
            else {
                curr = (DNode<T>) this.head.getBack();
                for (int i = this.numItems - 1; i > index; i--) {
                    curr = (DNode<T>) curr.getBack();
                }
            }

            return curr;

        } else {
            throw new ListIndexOutOfBoundsException("List index out of bounds on find");
        }

    }

    public java.util.Iterator iterator() {
        return new ListCDLSBasedIterator(this);
    }

}
