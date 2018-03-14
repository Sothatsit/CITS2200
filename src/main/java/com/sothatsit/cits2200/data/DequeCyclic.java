package com.sothatsit.cits2200.data;

import CITS2200.Deque;
import CITS2200.Overflow;
import CITS2200.Underflow;

public class DequeCyclic implements Deque {

    private final Object[] deque;
    private int left;
    private int length;

    /**
     * Instantiate a new deque of size {@param size}.
     *
     * @param size the maximum number of elements this deque can store.
     */
    public DequeCyclic(int size) {
        this.deque = new Object[size];
        this.left = 1;
    }

    /**
     * Get the index to the right-most element of the deque.
     *
     * This will return left - 1 if this deque is empty.
     *
     * @return the index to the right-most element of this deque
     */
    private int getRightIndex() {
        return mapIndex(left + length - 1);
    }

    /**
     * Check whether this deque is empty.
     *
     * @return true iff the deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Check whether this deque is full.
     *
     * @return true iff the deque is full, false otherwise.
     */
    @Override
    public boolean isFull() {
        return length == deque.length;
    }

    /**
     * Push a value onto the left of this deque.
     *
     * @param value     the value to be pushed onto the deque
     * @throws Overflow if the deque is full
     */
    @Override
    public void pushLeft(Object value) throws Overflow {
        if(isFull())
            throw new Overflow("Attempted to push value onto a full deque");

        left = mapIndex(left - 1);
        length += 1;

        deque[left] = value;
    }

    /**
     * Push a value onto the right of this deque.
     *
     * @param value     the value to be pushed onto the deque
     * @throws Overflow if the deque is full
     */
    @Override
    public void pushRight(Object value) throws Overflow {
        if(isFull())
            throw new Overflow("Attempted to push value onto a full deque");

        length += 1;
        deque[getRightIndex()] = value;
    }

    /**
     * Pop a value from the left of this deque.
     *
     * @return           the value that was pop'ed off of the deque
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object popLeft() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to pop value from an empty deque");

        Object value = deque[left];
        deque[left] = null;

        left = mapIndex(left + 1);
        length -= 1;

        return value;
    }

    /**
     * Pop a value from the right of this deque.
     *
     * @return           the value that was pop'ed off of the deque
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object popRight() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to pop value from an empty deque");

        int right = getRightIndex();
        Object value = deque[right];

        deque[right] = null;
        length -= 1;

        return value;
    }

    /**
     * Peek the right-most value in this deque.
     *
     * @return           the right-most value of this deque
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object peekRight() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to peek value from an empty deque");

        return deque[getRightIndex()];
    }

    /**
     * Peek the left-most value in this deque.
     *
     * @return           the left-most value of this deque
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object peekLeft() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to peek value from an empty deque");

        return deque[left];
    }

    /**
     * Map from a continuous index space repeating every {@link this#deque#length}
     * elements to one wrapped into the range [0, {@link this#deque#length}).
     *
     * @param index the index to map
     * @return      the index mapped into the range [0, {@link this#deque#length})
     */
    private int mapIndex(int index) {
        return (index % deque.length) + (index < 0 ? deque.length : 0);
    }
}
