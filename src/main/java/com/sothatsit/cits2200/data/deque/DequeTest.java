package com.sothatsit.cits2200.data.deque;

import static com.sothatsit.cits2200.test.Checks.*;

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

        assertTrue(deque.isEmpty());

        deque.pushLeft(1);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 1);
        assertEquals(deque.peekRight(), 1);

        assertEquals(deque.popRight(), 1);

        assertTrue(deque.isEmpty());

        deque.pushRight(2);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 2);
        assertEquals(deque.peekRight(), 2);

        deque.pushLeft(3);
        deque.pushRight(4);
        deque.pushLeft(5);
        deque.pushRight(6);

        assertTrue(!deque.isEmpty());
        assertTrue(deque.isFull());
        assertEquals(deque.peekLeft(), 5);
        assertEquals(deque.peekRight(), 6);

        assertThrows(() -> deque.pushLeft(8), Overflow.class);
        assertThrows(() -> deque.pushRight(9), Overflow.class);

        assertEquals(deque.popLeft(), 5);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 3);
        assertEquals(deque.peekRight(), 6);

        assertEquals(deque.popRight(), 6);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 3);
        assertEquals(deque.peekRight(), 4);

        assertEquals(deque.popRight(), 4);
        assertEquals(deque.popRight(), 2);
        assertEquals(deque.popRight(), 3);

        assertTrue(deque.isEmpty());
        assertThrows(deque::popLeft, Underflow.class);
        assertThrows(deque::popRight, Underflow.class);

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

        System.out.println("com.sothatsit.cits2200.data.deque.DequeCyclic passed");
    }

    public static void testDequeLinked() {
        DequeLinked deque = new DequeLinked();

        assertTrue(deque.isEmpty());

        deque.pushLeft(1);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 1);
        assertEquals(deque.peekRight(), 1);

        assertEquals(deque.popRight(), 1);

        assertTrue(deque.isEmpty());

        deque.pushRight(2);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 2);
        assertEquals(deque.peekRight(), 2);

        deque.pushLeft(3);
        deque.pushRight(4);
        deque.pushLeft(5);
        deque.pushRight(6);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 5);
        assertEquals(deque.peekRight(), 6);

        assertEquals(deque.popLeft(), 5);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 3);
        assertEquals(deque.peekRight(), 6);

        assertEquals(deque.popRight(), 6);

        assertTrue(!deque.isEmpty());
        assertTrue(!deque.isFull());
        assertEquals(deque.peekLeft(), 3);
        assertEquals(deque.peekRight(), 4);

        assertEquals(deque.popRight(), 4);
        assertEquals(deque.popRight(), 2);
        assertEquals(deque.popRight(), 3);

        assertTrue(deque.isEmpty());
        assertThrows(deque::popLeft, Underflow.class);
        assertThrows(deque::popRight, Underflow.class);

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
