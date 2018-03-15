package com.sothatsit.cits2200.data.queue;

import CITS2200.Overflow;
import CITS2200.Queue;
import CITS2200.Underflow;
import com.sothatsit.cits2200.data.NodeSinglyLinked;

/**
 * A cylic, fixed capacity, first in first out implementation of a queue.
 *
 * @author Paddy Lamont
 */
public class QueueCyclic implements Queue {

    private final Object[] queue;
    private int first;
    private int length;

    /**
     * Instantiate a new linked queue with capacity {@param capacity}.
     */
    public QueueCyclic(int capacity) {
        this.queue = new Object[capacity];
        this.first = 0;
        this.length = 0;
    }

    /**
     * @return true iff this queue has no elements left, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * @return true iff this queue is full, false otherwise
     */
    public boolean isFull() {
        return length == queue.length;
    }

    /**
     * Enqueue {@param value} to the end of this queue.
     *
     * @param value the value to append to the end of this queue
     */
    @Override
    public void enqueue(Object value) throws Overflow {
        if(isFull())
            throw new Overflow("attempting to enqueue into a full queue");

        int last = mapIndex(first + length);
        queue[last] = value;
        length += 1;
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

        return queue[first];
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

        Object value = queue[first];
        queue[first] = null;

        first = mapIndex(first + 1);
        length -= 1;

        return value;
    }

    /**
     * Map from a continuous index space repeating every {@link #queue#length}
     * elements to one wrapped into the range [0, {@link #queue#length}).
     *
     * @param index the index to map
     * @return      the index mapped into the range [0, {@link #queue#length})
     */
    private int mapIndex(int index) {
        return (index % queue.length) + (index < 0 ? queue.length : 0);
    }
}
