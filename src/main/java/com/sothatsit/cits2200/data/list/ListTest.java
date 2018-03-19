package com.sothatsit.cits2200.data.list;

import CITS2200.OutOfBounds;
import CITS2200.WindowLinked;

import static com.sothatsit.cits2200.util.Checks.*;

public class ListTest {

    public static void main(String[] args) {
        testListLinked();
    }

    public static void testListLinked() {
        WindowLinked window = new WindowLinked();
        ListLinked list = new ListLinked();

        assertTrue(list.isEmpty());

        list.beforeFirst(window);
        assertTrue(list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        assertThrows(() -> list.examine(window), OutOfBounds.class);

        list.next(window);
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(list.isAfterLast(window));

        assertThrows(() -> list.examine(window), OutOfBounds.class);
        assertThrows(() -> list.next(window), OutOfBounds.class);

        list.previous(window);
        assertTrue(list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        assertThrows(() -> list.examine(window), OutOfBounds.class);
        assertThrows(() -> list.delete(window), OutOfBounds.class);
        assertThrows(() -> list.insertBefore(0, window), OutOfBounds.class);
        assertThrows(() -> list.replace(0, window), OutOfBounds.class);

        list.insertAfter(1, window);
        assertTrue(list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        list.next(window);
        assertEquals(1, list.examine(window));
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        list.next(window);
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(list.isAfterLast(window));

        assertThrows(() -> list.insertAfter(0, window), OutOfBounds.class);

        list.insertBefore(2, window);
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(list.isAfterLast(window));

        list.previous(window);
        assertEquals(2, list.examine(window));
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        list.previous(window);
        assertEquals(1, list.examine(window));
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        list.previous(window);
        assertTrue(list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));
        assertThrows(() -> list.insertBefore(0, window), OutOfBounds.class);
        assertThrows(() -> list.previous(window), OutOfBounds.class);

        list.next(window);
        assertEquals(1, list.examine(window));
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));
        assertEquals(1, list.replace(3, window));
        assertEquals(3, list.examine(window));

        list.previous(window);
        assertTrue(list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        list.next(window);
        assertEquals(3, list.examine(window));
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        list.next(window);
        assertEquals(2, list.examine(window));
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));
        assertEquals(2, list.delete(window));
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(list.isAfterLast(window));

        assertThrows(() -> list.delete(window), OutOfBounds.class);
        assertThrows(() -> list.replace(0, window), OutOfBounds.class);

        list.afterLast(window);
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(list.isAfterLast(window));

        list.previous(window);
        assertEquals(3, list.examine(window));
        assertTrue(!list.isBeforeFirst(window));
        assertTrue(!list.isAfterLast(window));

        System.out.println("ListLinked passed");
    }
}
