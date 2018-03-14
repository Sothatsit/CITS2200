import CITS2200.Underflow;

public class QueueTest {

    public static void main(String[] args) {
        testQueueLinked();
    }

    public static void testQueueLinked() {
        QueueLinked queue = new QueueLinked();

        Checks.assertTrue(queue.isEmpty());
        Checks.assertThrows(queue::examine, Underflow.class);
        Checks.assertThrows(queue::dequeue, Underflow.class);

        queue.enqueue(5);

        Checks.assertTrue(!queue.isEmpty());
        Checks.assertEquals(queue.examine(), 5);

        queue.enqueue(4);
        queue.enqueue(3);

        Checks.assertTrue(!queue.isEmpty());
        Checks.assertEquals(queue.examine(), 5);

        Checks.assertEquals(queue.dequeue(), 5);

        Checks.assertTrue(!queue.isEmpty());
        Checks.assertEquals(queue.examine(), 4);

        queue.enqueue(2);

        Checks.assertTrue(!queue.isEmpty());
        Checks.assertEquals(queue.examine(), 4);

        Checks.assertEquals(queue.dequeue(), 4);
        Checks.assertEquals(queue.dequeue(), 3);
        Checks.assertEquals(queue.dequeue(), 2);
        Checks.assertTrue(queue.isEmpty());

        System.out.println("QueueLinked passed");
    }
}
