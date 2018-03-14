package com.sothatsit.cits2200.data.deque;

import CITS2200.Deque;
import CITS2200.Underflow;
import com.sothatsit.cits2200.data.NodeDoublyLinked;

/**
 * A linked, no capacity implementation of a double ended queue.
 *
 * @author Paddy Lamont
 */
public class DequeLinked implements Deque {

    private NodeDoublyLinked left;
    private NodeDoublyLinked right;

    /**
     * Instantiate a new deque.
     */
    public DequeLinked() {
        this.left = null;
        this.right = null;
    }

    /**
     * @return true iff the deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return left == null;
    }

    /**
     * Check whether this deque is full.
     *
     * This is always false as linked deques have no maximum size.
     *
     * @return false
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * Push a value onto the left of this deque.
     *
     * @param value the value to be pushed onto the deque
     */
    @Override
    public void pushLeft(Object value) {
        // Special case for empty deque
        if(isEmpty()) {
            left = new NodeDoublyLinked(value);
            right = left;
            return;
        }

        NodeDoublyLinked node = new NodeDoublyLinked(value);
        left.linkLeft(node);
        left = node;
    }

    /**
     * Push a value onto the right of this deque.
     *
     * @param value the value to be pushed onto the deque
     */
    @Override
    public void pushRight(Object value) {
        // Special case for empty deque
        if(isEmpty()) {
            right = new NodeDoublyLinked(value);
            left = right;
            return;
        }

        NodeDoublyLinked node = new NodeDoublyLinked(value);
        right.linkRight(node);
        right = node;
    }

    /**
     * Pop a value from the left of this deque.
     *
     * @return the value that was popped from the left of the deque
     *
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object popLeft() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to pop value from an empty deque");

        Object value = left.getValue();
        left = left.getRight();

        if(left != null) {
            left.linkLeft(null);
        }

        // Special case when emptying the deque
        if(left == null) {
            right = null;
        }

        return value;
    }

    /**
     * Pop a value from the right of this deque.
     *
     * @return the value that was popped from the right of the deque
     *
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object popRight() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to pop value from an empty deque");

        Object value = right.getValue();
        right = right.getLeft();

        if(right != null) {
            right.linkRight(null);
        }

        // Special case when emptying the deque
        if(right == null) {
            left = null;
        }

        return value;
    }

    /**
     * Peek at the right-most value in this deque.
     *
     * @return the right-most value of this deque
     *
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object peekRight() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to peek value from an empty deque");

        return right.getValue();
    }

    /**
     * Peek the left-most value in this deque.
     *
     * @return the left-most value of this deque
     *
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object peekLeft() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to peek value from an empty deque");

        return left.getValue();
    }

    /**
     * @return a string representation of this deque as an array
     */
    @Override
    public String toString() {
        return left.walkRight();
    }
}
