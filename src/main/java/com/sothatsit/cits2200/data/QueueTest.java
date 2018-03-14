package com.sothatsit.cits2200.data;

import CITS2200.Underflow;
import static com.sothatsit.cits2200.test.Checks.*;

public class QueueTest {

    public static void main(String[] args) {
        testQueueLinked();
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
