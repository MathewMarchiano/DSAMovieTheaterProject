package structs;

public class SLSQueue<T> implements QueueInterface<T> {

    private Node<T> front;
    private Node<T> back;

    public SLSQueue() {
        this.front = null;
        this.back = null;
    }

    @Override
    public boolean isEmpty() {
        return this.front == null;
    }

    @Override
    public void enqueue(T newItem) throws QueueException {

        Node<T> newNode = new Node<T>(newItem);

        if (isEmpty()) {
            this.front = newNode;
            this.back = newNode;
        }
        else {
            this.back.setNext(newNode);
            this.back = this.back.getNext();
        }
    }

    @Override
    public T dequeue() throws QueueException {

        if (isEmpty()) {
            throw new QueueException("QueueException on dequeue");
        }

        Node<T> result;
        if (this.front == this.back) {
            result = this.front;
            this.front = null;
            this.back = null;
        }
        else {
            result = this.front;
            this.front = this.front.getNext();
        }

        return result.getItem();
    }

    @Override
    public void dequeueAll() {
        this.front = null;
        this.back = null;
    }

    @Override
    public T peek() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("QueueException on peek");
        }
        return this.front.getItem();
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node<T> curr = this.front;
        while (curr != null) {
            sb.append(curr.getItem().toString() + " ");
            curr = curr.getNext();
        }

        return sb.toString();
    }

}

