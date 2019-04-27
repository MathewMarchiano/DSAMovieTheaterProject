package structs;

public class RABDEQ<T> extends RABQueue<T> implements ExtendedQueueInterface<T> {

    @Override
    public void enqueueFirst(T newItem) throws ExtendedQueueException {

        if (super.isFull()) {
            super.resize();
        }

        front = decrementIndex(front);
        items[front] = newItem;

    }

    @Override
    public T dequeueLast() throws ExtendedQueueException {

        T result;

        if (super.isEmpty()) {
            String msg = "ExtendedQueueException on dequeueLast";
            throw new ExtendedQueueException(msg);
        }
        else {
            back = decrementIndex(back);
            result = items[back];
            items[back] = null;
        }

        return result;


    }

    @Override
    public T peekLast() throws ExtendedQueueException {
        if (isEmpty()) {
            String msg = "QueueException on peek";
            throw new QueueException(msg);
        }

        return items[decrementIndex(back)];
    }



}
