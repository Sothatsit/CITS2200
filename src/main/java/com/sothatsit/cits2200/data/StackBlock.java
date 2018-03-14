package com.sothatsit.cits2200.data;

import CITS2200.Overflow;
import CITS2200.Stack;
import CITS2200.Underflow;

public class StackBlock implements Stack {

    private final Object[] stack;
    private int top;

    /**
     * Instantiate a new stack of size {@param size}.
     *
     * @param size the maximum number of elements allowed in this stack
     */
    public StackBlock(int size) {
        this.stack = new Object[size];
        this.top = 0;
    }

    /**
     * Check whether the stack is empty.
     *
     * @return true iff the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Check whether the stack is full.
     *
     * @return true iff the stack is full, false otherwise
     */
    public boolean isFull() {
        return top == stack.length;
    }

    /**
     * Push a value onto the stack.
     *
     * @param value     the value to be pushed onto the stack
     * @throws Overflow if the stack is full
     */
    @Override
    public void push(Object value) throws Overflow {
        if(isFull())
            throw new Overflow("Attempted to push value onto full stack");

        stack[top] = value;

        top += 1;
    }

    /**
     * Get the top element on the stack without modifying the stack.
     *
     * @return           the top element of the stack
     * @throws Underflow if the stack is empty
     */
    @Override
    public Object examine() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to examine empty stack");

        return stack[top - 1];
    }

    /**
     * Pop the top element off of the stack, and return it.
     *
     * @return           the element pop'ed off of the stack
     * @throws Underflow if the stack is empty
     */
    @Override
    public Object pop() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to pop empty stack");

        top -= 1;

        Object value = stack[top];
        stack[top] = null;

        return value;
    }
}
