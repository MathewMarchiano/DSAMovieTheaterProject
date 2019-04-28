package structs;

public class RABQueue<T> implements QueueInterface<T> {

    private static final int INITIAL_CAPACITY = 3;

    protected int front;
    protected int back;
    protected T[] items;

    public RABQueue() {
        this.front = 0;
        this.back = 0;
        this.items = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return items[front] == null;
    }

    @Override
    public void enqueue(T newItem) throws QueueException {

        // Because of resizable array QueueException is not
        // thrown.

        if (isFull()) {
            resize();
        }

        items[back] = newItem;
        back = (back + 1) % items.length;

    }

    @Override
    public T dequeue() throws QueueException {
        if (isEmpty()) {
            throw new QueueException("QueueException on dequeue");
        }

        T result = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        return result;

    }

    @Override
    public void dequeueAll() {
        this.items = (T[]) new Object[INITIAL_CAPACITY];
        this.front = 0;
        this.back = 0;
    }

    @Override
    public T peek() throws QueueException {
        if (isEmpty()) {
            String msg = "QueueException on peek";
            throw new QueueException(msg);
        }
        return items[front];
    }

    public boolean isFull() {
        return !(items[back] == null);
    }

    protected void resize() {
        int length = items.length;
        int newLength = length << 1; // multiply by two
        T[] newArray = (T[]) new Object[newLength];
        int i = 0;
        int j = front;
        do {
            newArray[i] = items[j];
            i++;
            j = (j + 1) % items.length;
        } while (j != front);
        front = 0;
        back = length;
        items = newArray;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!isEmpty()) {
            int index = front;
            T curr = items[index];
            do {
                sb.append(curr);
                index = incrementIndex(index);
                curr = items[index];
            } while (curr != null && index != front);
        }

        return sb.toString();
    }

    protected int decrementIndex(int index) {

        // To give the index the ability to wrap around
        // it will follow this formula (Same as
        // the modulo operator in Python):
        // (a % b) = -[ (a//b) * b - a]
        // where a//b is integer division

        // Have integer division apply the floor
        // operation to negative numbers.
        int intFloorDiv = (index - 1) / items.length;
        intFloorDiv = (index - 1) < 0 ? intFloorDiv - 1 : intFloorDiv;

        // Apply formula
        index = -(intFloorDiv * items.length - (index - 1));

        return index;
    }

    protected int incrementIndex(int index) {

        return (index + 1) % items.length;

    }

}
