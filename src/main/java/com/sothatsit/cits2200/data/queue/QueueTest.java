package com.sothatsit.cits2200.data.queue;

import CITS2200.Overflow;
import CITS2200.Underflow;
import static com.sothatsit.cits2200.util.Checks.*;

public class QueueTest {

    public static void main(String[] args) {
        testQueueCyclic();
        testQueueLinked();
    }

    public static void testQueueCyclic() {
        QueueCyclic queue = new QueueCyclic(5);

        assertTrue(queue.isEmpty());
        assertTrue(!queue.isFull());
        assertThrows(queue::examine, Underflow.class);
        assertThrows(queue::dequeue, Underflow.class);

        queue.enqueue(5);

        assertTrue(!queue.isEmpty());
        assertTrue(!queue.isFull());
        assertEquals(queue.examine(), 5);

        queue.enqueue(4);
        queue.enqueue(3);

        assertTrue(!queue.isEmpty());
        assertTrue(!queue.isFull());
        assertEquals(queue.examine(), 5);

        assertEquals(queue.dequeue(), 5);

        assertTrue(!queue.isEmpty());
        assertTrue(!queue.isFull());
        assertEquals(queue.examine(), 4);

        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(0);

        assertTrue(!queue.isEmpty());
        assertTrue(queue.isFull());
        assertThrows(() -> queue.enqueue(-1), Overflow.class);

        assertTrue(!queue.isEmpty());
        assertEquals(queue.examine(), 4);

        assertEquals(queue.dequeue(), 4);
        assertEquals(queue.dequeue(), 3);
        assertEquals(queue.dequeue(), 2);
        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 0);
        assertTrue(queue.isEmpty());
        assertTrue(!queue.isFull());

        System.out.println("QueueCyclic passed");
    }

    public static void testQueueLinked() {
        QueueLinked queue = new QueueLinked();

        assertTrue(queue.isEmpty());
        assertThrows(queue::examine, Underflow.class);
        assertThrows(queue::dequeue, Underflow.class);

        queue.enqueue(5);

        assertTrue(!queue.isEmpty());
        assertEquals(queue.examine(), 5);

        queue.enqueue(4);
        queue.enqueue(3);

        assertTrue(!queue.isEmpty());
        assertEquals(queue.examine(), 5);

        assertEquals(queue.dequeue(), 5);

        assertTrue(!queue.isEmpty());
        assertEquals(queue.examine(), 4);

        queue.enqueue(2);

        assertTrue(!queue.isEmpty());
        assertEquals(queue.examine(), 4);

        assertEquals(queue.dequeue(), 4);
        assertEquals(queue.dequeue(), 3);
        assertEquals(queue.dequeue(), 2);
        assertTrue(queue.isEmpty());

        System.out.println("QueueLinked passed");
    }
}
