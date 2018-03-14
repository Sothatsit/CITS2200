import CITS2200.Overflow;
import CITS2200.Underflow;

import java.util.Random;

public class DequeTest {

    public static void main(String[] args) {
        testDequeCyclic();
        testDequeLinked();
    }

    public static void testDequeCyclic() {
        DequeCyclic deque = new DequeCyclic(5);

        Checks.assertTrue(deque.isEmpty());

        deque.pushLeft(1);

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 1);
        Checks.assertEquals(deque.peekRight(), 1);

        deque.popRight();

        Checks.assertTrue(deque.isEmpty());

        deque.pushRight(2);

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 2);
        Checks.assertEquals(deque.peekRight(), 2);

        deque.pushLeft(3);
        deque.pushRight(4);
        deque.pushLeft(5);
        deque.pushRight(6);

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 5);
        Checks.assertEquals(deque.peekRight(), 6);

        Checks.assertThrows(() -> deque.pushLeft(8), Overflow.class);
        Checks.assertThrows(() -> deque.pushRight(9), Overflow.class);

        deque.popLeft();

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 3);
        Checks.assertEquals(deque.peekRight(), 6);

        deque.popRight();

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 3);
        Checks.assertEquals(deque.peekRight(), 4);

        deque.popRight();
        deque.popRight();
        deque.popRight();

        Checks.assertTrue(deque.isEmpty());
        Checks.assertThrows(deque::popLeft, Underflow.class);
        Checks.assertThrows(deque::popRight, Underflow.class);

        // Stress test
        Random random = new Random();
        for(int i=0; i < 1_000; ++i) {
            boolean pop = (deque.isFull() || (!deque.isEmpty() && random.nextBoolean()));
            boolean left = random.nextBoolean();

            if(pop) {
                if(left) deque.popLeft();
                else     deque.popRight();
            } else {
                int value = random.nextInt();

                if(left) deque.pushLeft(value);
                else     deque.pushRight(value);
            }
        }

        System.out.println("DequeCyclic passed");
    }

    public static void testDequeLinked() {
        DequeLinked deque = new DequeLinked();

        Checks.assertTrue(deque.isEmpty());

        deque.pushLeft(1);

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 1);
        Checks.assertEquals(deque.peekRight(), 1);

        deque.popRight();

        Checks.assertTrue(deque.isEmpty());

        deque.pushRight(2);

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 2);
        Checks.assertEquals(deque.peekRight(), 2);

        deque.pushLeft(3);
        deque.pushRight(4);
        deque.pushLeft(5);
        deque.pushRight(6);

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 5);
        Checks.assertEquals(deque.peekRight(), 6);

        deque.popLeft();

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 3);
        Checks.assertEquals(deque.peekRight(), 6);

        deque.popRight();

        Checks.assertTrue(!deque.isEmpty());
        Checks.assertTrue(!deque.isFull());
        Checks.assertEquals(deque.peekLeft(), 3);
        Checks.assertEquals(deque.peekRight(), 4);

        deque.popRight();
        deque.popRight();
        deque.popRight();

        Checks.assertTrue(deque.isEmpty());
        Checks.assertThrows(deque::popLeft, Underflow.class);
        Checks.assertThrows(deque::popRight, Underflow.class);

        // Stress test
        Random random = new Random();
        for(int i=0; i < 1_000; ++i) {
            boolean pop = (deque.isFull() || (!deque.isEmpty() && random.nextBoolean()));
            boolean left = random.nextBoolean();

            if(pop) {
                if(left) deque.popLeft();
                else     deque.popRight();
            } else {
                int value = random.nextInt();

                if(left) deque.pushLeft(value);
                else     deque.pushRight(value);
            }
        }

        System.out.println("DequeLinked passed");
    }
}
