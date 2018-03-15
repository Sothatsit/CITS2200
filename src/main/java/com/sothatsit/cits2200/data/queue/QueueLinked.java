package com.sothatsit.cits2200.data.queue;

import CITS2200.Queue;
import CITS2200.Underflow;
import com.sothatsit.cits2200.data.NodeSinglyLinked;

/**
 * A linked, no capacity, first in first out implementation of a queue.
 *
 * @author Paddy Lamont
 */
public class QueueLinked implements Queue {

    private NodeSinglyLinked top;
    private NodeSinglyLinked bottom;

    /**
     * Instantiate a new linked queue.
     */
    public QueueLinked() {
        this.top = null;
        this.bottom = null;
    }

    /**
     * @return true iff this queue has no elements left, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Enqueue {@param value} to the end of this queue.
     *
     * @param value the value to append to the end of this queue
     */
    @Override
    public void enqueue(Object value) {
        // Special case for empty queue
        if(isEmpty()) {
            top = new NodeSinglyLinked(value, null);
            bottom = top;
            return;
        }

        NodeSinglyLinked node = new NodeSinglyLinked(value, null);

        bottom.setSuccessor(node);
        bottom = node;
    }

    /**
     * Examine the element at the front of this queue.
     *
     * @return the value at the front of this queue
     *
     * @throws Underflow if the queue is empty
     */
    @Override
    public Object examine() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempting to examine an empty queue");

        return top.getValue();
    }

    /**
     * Take the value at the front of the queue off of the queue.
     *
     * @return the value that was at the front of the queue
     *
     * @throws Underflow if the queue is empty
     */
    @Override
    public Object dequeue() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempting to examine an empty queue");

        Object value = top.getValue();
        top = top.getSuccessor();

        // Special case for emptying queue
        if(top == null) {
            bottom = null;
        }

        return value;
    }

    /**
     * @return a string representation of this queue as an array
     */
    @Override
    public String toString() {
        return top.walk();
    }
}
