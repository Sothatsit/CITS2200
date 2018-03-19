package com.sothatsit.cits2200.data.stack;

import CITS2200.Link;
import CITS2200.Stack;
import CITS2200.Underflow;
import com.sothatsit.cits2200.util.ArrayBuilder;

/**
 * A no capacity, first in last out implementation of a stack.
 *
 * @author Paddy Lamont
 */
public class StackLinked implements Stack {

    private Link top;

    /**
     * Instantiate a new linked stack.
     */
    public StackLinked() {
        this.top = null;
    }

    /**
     * Check whether the stack is empty.
     *
     * @return true iff the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Check whether the stack is full.
     *
     * This is always false as linked stacks have no max size.
     *
     * @return false
     */
    public boolean isFull() {
        return false;
    }

    /**
     * Push a value onto the stack.
     *
     * @param value the value to be pushed onto the stack
     */
    @Override
    public void push(Object value) {
        top = new Link(value, top);
    }

    /**
     * Get the top element on the stack without modifying the stack.
     *
     * @return the top element of the stack
     *
     * @throws Underflow if the stack is empty
     */
    @Override
    public Object examine() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to examine empty stack");

        return top.item;
    }

    /**
     * Pop the top element off of the stack, and return it.
     *
     * @return the element pop'ed off of the stack
     *
     * @throws Underflow if the stack is empty
     */
    @Override
    public Object pop() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to pop empty stack");

        Object value = top.item;
        top = top.successor;

        return value;
    }

    /**
     * @return a string representation of this stack as an array, top-most element at index 0
     */
    @Override
    public String toString() {
        return ArrayBuilder.fromLinked(top);
    }
}
