package com.sothatsit.cits2200.data.queue;

import CITS2200.Overflow;

/**
 * A block implementation of the heap data structure, where lower weight values are pushed to the top.
 *
 * @param <E> the type of objects to be stored in this heap
 */
public class MinHeap<E> extends MaxHeap<E> {

    /**
     * Construct a heap of size {@param size}.
     *
     * @param size the size of the heap
     */
    public MinHeap(int size) {
        super(size);
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
        super.enqueue(value, -weight);
    }
}
