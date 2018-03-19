package com.sothatsit.cits2200.data;

import com.sothatsit.cits2200.data.deque.DequeTest;
import com.sothatsit.cits2200.data.list.ListTest;
import com.sothatsit.cits2200.data.queue.QueueTest;
import com.sothatsit.cits2200.data.stack.StackTest;

public class DataStructuresTest {

    public static void main(String[] args) {
        DequeTest.testDequeCyclic();
        DequeTest.testDequeLinked();

        ListTest.testListLinked();

        QueueTest.testQueueCyclic();
        QueueTest.testQueueLinked();

        StackTest.testStackBlock();
        StackTest.testStackLinked();
    }
}
