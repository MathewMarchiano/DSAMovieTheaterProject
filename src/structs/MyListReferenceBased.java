package structs;

/*
 * Purpose: Data Structure and Algorithms Lab 3 Problem 1
 * Status: Complete and thoroughly tested
 * Last update: 03/03/19
 * Submitted:  03/04/19
 * Comment: This is a redo
 * @author: Scott Zockoll
 * @version: 2019.03.03
 */

public class MyListReferenceBased<T> implements ListInterface<T>
{

    protected Node<T> head;

    public MyListReferenceBased()
    {
        head = null;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    // iterates through all nodes to compute size
    public int size()
    {
        int count = 0;
        Node<T> curr = head;

        while (curr != null) {
            count++;
            curr = curr.getNext();
        }

        return count;

    }

    private Node<T> find(int index)
    {
        Node<T> curr = head;
        for (int skip = 0; skip < index; skip++)
        {
            curr = curr.getNext();
        }
        return curr;
    }

    public T get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < size())
        {

            Node<T> curr = find(index);
            return curr.getItem();
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "List index out of bounds exception on get");
        }
    }

    public void add(int index, T item)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < size()+1)
        {
            if (index == 0)
            {
                head = new Node<T>(item, head);
            }
            else
            {
                Node<T> prev = find(index-1);
                Node<T> newNode = new Node<T>(item, prev.getNext());
                prev.setNext(newNode);
            }
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "List index out of bounds exception on add");
        }
    }

    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < size())
        {
            if (index == 0)
            {

                head = head.getNext();
            }
            else
            {
                Node<T> prev = find(index-1);
                Node<T> curr = prev.getNext();
                prev.setNext(curr.getNext());
            }
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "List index out of bounds exception on remove");
        }
    }

    public void removeAll()
    {
        head = null;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node<T> curr = head;
        boolean lastNodeFound = false;

        if (curr == null) {
            lastNodeFound = true;
        }

        while (lastNodeFound == false) {
            sb.append(curr.getItem() + " ");
            curr = curr.getNext();
            if (curr == null) {
                lastNodeFound = true;
            }
        }

        return sb.toString();

    }

    public java.util.Iterator iterator() {
        return new MyListReferencedBasedIterator(this);
    }

}
