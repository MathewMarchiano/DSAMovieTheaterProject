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

public class DNode<T> extends Node<T> {

    private DNode back;

    public DNode(T newItem) {
        super(newItem);
        this.back = this;
        setNext(this);
    }

    public DNode(T newItem, DNode<T> back, DNode<T> next) {
        super(newItem, next);
        this.back = back;
    }

    public DNode getBack() {
        return back;
    }

    public void setBack(DNode back) {
        this.back = back;
    }
}
