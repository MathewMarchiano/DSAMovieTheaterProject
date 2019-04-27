package structs;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StructsDriver {

    public static void main(String[] args) {

        RABDEQ<String> q = new RABDEQ<>();
        q.enqueue("scott");
        q.enqueue("scott2");
        q.enqueueFirst("scott3");
        q.dequeueLast();
        q.dequeue();
        q.enqueueFirst("first");
        q.enqueueFirst("FIRST");
        q.enqueueFirst("FIRST");
        System.out.println("peek first: " + q.peek());
        System.out.println("peek last: " + q.peekLast());
        q.dequeue();
        System.out.println(q.toString());
        System.out.println(q.toString().split(" ").length);

    }

}

