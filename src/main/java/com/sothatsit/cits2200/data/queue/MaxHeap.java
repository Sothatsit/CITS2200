package com.sothatsit.cits2200.data.queue;

import CITS2200.*;

/**
 * A block implementation of the heap data structure, where higher weight values are pushed to the top.
 *
 * @param <E> the type of objects to be stored in this heap
 */
public class MaxHeap<E> implements PriorityQueue<E>  {

    private final Object[] values;
    private final int[] weights;
    private int length;

    /**
     * Construct a heap of size {@param size}.
     *
     * @param size the size of the heap
     */
    public MaxHeap(int size) {
        this.values = new Object[size];
        this.weights = new int[size];
        this.length = 0;
    }

    /**
     * @return true iff this heap is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return length <= 0;
    }

    /**
     * @return true iff this heap is full, false otherwise
     */
    public boolean isFull() {
        return length >= weights.length;
    }

    /**
     * Enqueue {@param value} with weight {@param weight} into the heap.
     *
     * @param value  the value to be inserted into the heap
     * @param weight the weight of the value to be inserted into the heap
     *
     * @throws Overflow iff the heap is full
     */
    @Override
    public void enqueue(E value, int weight) throws Overflow {
        if(isFull())
            throw new Overflow("Cannot enqueue into a full heap");

        int index = length;
        length += 1;

        do {
            int parent = getParent(index);

            if(parent == -1 || weight <= weights[parent])
                break;

            weights[index] = weights[parent];
            values[index] = values[parent];
            index = parent;
        } while(true);

        weights[index] = weight;
        values[index] = value;
    }

    /**
     * @return the highest weight value in the heap
     */
    @Override
    @SuppressWarnings("unchecked")
    public E examine() {
        if(isEmpty())
            throw new Underflow("Cannot examine an empty heap");

        return (E) values[0];
    }

    /**
     * Dequeue the highest weight value in the heap, and call heapify to restore the heap property.
     *
     * @return the value that was dequeue'd
     */
    @Override
    @SuppressWarnings("unchecked")
    public E dequeue() {
        if(isEmpty())
            throw new Underflow("Cannot examine an empty heap");

        E value = (E) values[0];

        length -= 1;
        int lastIndex = length;

        weights[0] = weights[lastIndex];
        values[0] = values[lastIndex];

        weights[lastIndex] = 0;
        values[lastIndex] = null;

        heapify();

        return value;
    }

    /**
     * Move the element at the root of this heap into its correct position in the heap.
     */
    private void heapify() {
        int index = 0;
        int weight = weights[0];
        Object value = values[0];

        do {
            int leftIndex = getLeftChild(index);

            if(leftIndex >= 0 && weight < weights[leftIndex]) {
                weights[index] = weights[leftIndex];
                values[index] = values[leftIndex];
                index = leftIndex;
                continue;
            }

            int rightIndex = getRightChild(index);

            if(rightIndex >= 0 && weight < weights[rightIndex]) {
                weights[index] = weights[rightIndex];
                values[index] = values[rightIndex];
                index = rightIndex;
                continue;
            }

            break;
        } while(true);

        weights[index] = weight;
        values[index] = value;
    }

    /**
     * @return an Iterator instance to iterate all values of this heap
     */
    @Override
    public Iterator<E> iterator() {
        return new HeapIterator();
    }

    /**
     * An iterator implementation to iterate all values in this heap.
     */
    private class HeapIterator implements Iterator<E> {

        private int index = 0;

        /**
         * Get the value of the next element from the heap.
         *
         * @return the next value in the heap
         *
         * @throws OutOfBounds if there are no elements left to be iterated
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() throws OutOfBounds {
            if(index >= length)
                throw new OutOfBounds("");

            return (E) values[index++];
        }

        /**
         * @return whether there are more values left to be iterated
         */
        @Override
        public boolean hasNext() {
            return index < length;
        }
    }

    /**
     * @param index the index to find the left child of
     *
     * @return the index of the left child of {@param index}, or -1 if there is no left child
     */
    private int getLeftChild(int index) {
        int leftIndex = index * 2 + 1;

        return (leftIndex < length ? leftIndex : -1);
    }

    /**
     * @param index the index to find the right child of
     *
     * @return the index of the right child of {@param index}, or -1 if there is no right child
     */
    private int getRightChild(int index) {
        int rightIndex = index * 2 + 2;

        return (rightIndex < length ? rightIndex : -1);
    }

    /**
     * @param index the index to find the parent of, assuming that index exists in the heap
     *
     * @return the index of the parent of {@param index}, or -1 if there is no parent
     */
    private int getParent(int index) {
        return (index + 1) / 2 - 1;
    }
}
