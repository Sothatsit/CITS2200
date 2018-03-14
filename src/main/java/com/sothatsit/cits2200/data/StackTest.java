package com.sothatsit.cits2200.data;

import CITS2200.Overflow;
import CITS2200.Underflow;
import static com.sothatsit.cits2200.test.Checks.*;

public class StackTest {

    public static void main(String[] args) {
        testStackBlock();
        testStackLinked();
    }

    public static void testStackBlock() {
        StackBlock stack = new StackBlock(5);

        assertTrue(stack.isEmpty());

        stack.push(3);

        assertTrue(!stack.isEmpty());
        assertTrue(!stack.isFull());
        assertEquals(stack.examine(), 3);

        stack.push(2);
        stack.push(1);

        assertTrue(!stack.isEmpty());
        assertTrue(!stack.isFull());
        assertEquals(stack.examine(), 1);

        stack.pop();

        assertTrue(!stack.isEmpty());
        assertTrue(!stack.isFull());
        assertEquals(stack.examine(), 2);

        stack.push(1);
        stack.push(3);
        stack.push(4);

        assertTrue(!stack.isEmpty());
        assertTrue(stack.isFull());
        assertEquals(stack.examine(), 4);

        try {
            stack.push(7);
            fail();
        } catch (Overflow ignored) {}

        assertTrue(!stack.isEmpty());
        assertTrue(stack.isFull());
        assertEquals(stack.examine(), 4);

        for(int i=0; i < 5; ++i) {
            stack.pop();
        }

        assertTrue(stack.isEmpty());

        try {
            stack.pop();
            fail();
        } catch (Underflow ignored) {}

        try {
            stack.examine();
            fail();
        } catch (Underflow ignored) {}

        System.out.println("StackBlock passed");
    }

    public static void testStackLinked() {
        StackLinked stack = new StackLinked();

        assertTrue(stack.isEmpty());

        stack.push(3);

        assertTrue(!stack.isEmpty());
        assertTrue(!stack.isFull());
        assertEquals(stack.examine(), 3);

        stack.push(2);
        stack.push(1);

        assertTrue(!stack.isEmpty());
        assertTrue(!stack.isFull());
        assertEquals(stack.examine(), 1);

        stack.pop();

        assertTrue(!stack.isEmpty());
        assertTrue(!stack.isFull());
        assertEquals(stack.examine(), 2);

        stack.push(1);
        stack.push(3);
        stack.push(4);

        assertTrue(!stack.isEmpty());
        assertTrue(!stack.isFull());
        assertEquals(stack.examine(), 4);

        stack.push(7);

        assertTrue(!stack.isEmpty());
        assertTrue(!stack.isFull());
        assertEquals(stack.examine(), 7);

        for(int i=0; i < 6; ++i) {
            stack.pop();
        }

        assertTrue(stack.isEmpty());

        try {
            stack.pop();
            fail();
        } catch (Underflow ignored) {}

        try {
            stack.examine();
            fail();
        } catch (Underflow ignored) {}

        System.out.println("StackLinked passed");
    }
}
