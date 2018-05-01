package com.sothatsit.cits2200.data.queue;

import CITS2200.Overflow;
import CITS2200.Underflow;
import static com.sothatsit.cits2200.util.Checks.*;

public class QueueTest {

    public static void main(String[] args) {
        testPriorityQueue();
        testQueueCyclic();
        testQueueLinked();
    }

    public static void testPriorityQueue() {
        PriorityQueueLinked<String> queue = new PriorityQueueLinked<>();

        assertTrue(queue.isEmpty());
        assertThrows(queue::examine, Underflow.class);
        assertThrows(queue::dequeue, Underflow.class);

        queue.enqueue("a", 1);

        assertTrue(!queue.isEmpty());
        assertEquals(queue.examine(), "a");

        assertEquals(queue.dequeue(), "a");
        assertTrue(queue.isEmpty());
        assertThrows(queue::examine, Underflow.class);
        assertThrows(queue::dequeue, Underflow.class);

        queue.enqueue("a", 1);
        queue.enqueue("b", 2);

        assertTrue(!queue.isEmpty());
        assertEquals(queue.examine(), "b");

        assertEquals(queue.dequeue(), "b");
        assertTrue(!queue.isEmpty());
        assertEquals(queue.examine(), "a");

        assertEquals(queue.dequeue(), "a");
        assertTrue(queue.isEmpty());
        assertThrows(queue::examine, Underflow.class);
        assertThrows(queue::dequeue, Underflow.class);

        queue.enqueue("a", 1);
        queue.enqueue("b", 2);
        queue.enqueue("c", 3);
        queue.enqueue("d", 2);
        queue.enqueue("e", 1);

        assertEquals(queue.dequeue(), "c");
        assertEquals(queue.dequeue(), "b");
        assertEquals(queue.dequeue(), "d");
        assertEquals(queue.dequeue(), "a");
        assertEquals(queue.dequeue(), "e");

        System.out.println("PriorityQueueLinked passed");
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
