package com.sothatsit.cits2200.data.queue;

import CITS2200.IllegalValue;
import CITS2200.Overflow;
import CITS2200.Underflow;

/**
 * A block implementation of the heap data structure, where lower weight values are
 * pushed to the top and where updating the weights of random elements is possible.
 *
 * Implemented specifically for use in a priority first search.
 *
 * Stores int index values, where the indexes should only be in the range [0, size).
 */
public class RandomAccessMinHeap {

    private final int[] values;
    private final int[] weights;
    private final int[] valueIndexes;
    private int length;

    /**
     * Construct a random access min heap of size {@param size}.
     *
     * @param size the size of the heap
     */
    public RandomAccessMinHeap(int size) {
        this.values = new int[size];
        this.weights = new int[size];
        this.valueIndexes = new int[size];
        this.length = 0;

        for(int index = 0; index < size; ++index) {
            values[index] = -1;
            weights[index] = -1;
            valueIndexes[index] = -1;
        }
    }

    /**
     * @return true iff this heap is empty, false otherwise
     */
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
     * Relax the weight of {@param value} to {@param weight} if
     * {@param weight} is less than the values current weight.
     *
     * @param value  the value to be relaxed
     * @param weight the new weight for {@param value}
     */
    public void relax(int value, int weight) {
        if(value < 0 || value >= values.length || valueIndexes[value] == -1)
            throw new IllegalValue("value " + value + " does not exist in this heap");

        int index = valueIndexes[value];

        if(weights[index] <= weight)
            return;

        weights[index] = weight;

        do {
            int parent = getParent(index);

            if(parent == -1 || weight >= weights[parent])
                break;

            swap(index, parent);

            index = parent;
        } while(true);

        heapify(index);
    }

    /**
     * Enqueue {@param value} with weight {@param weight} into the heap.
     *
     * @param value  the value to be inserted into the heap
     * @param weight the weight of the value to be inserted into the heap
     *
     * @throws Overflow     iff the heap is full
     * @throws IllegalValue iff value is not in the range [0, heap size)
     */
    public void enqueue(int value, int weight) throws Overflow, IllegalValue {
        if(isFull())
            throw new Overflow("Cannot enqueue into a full heap");

        if(value < 0 || value >= values.length)
            throw new IllegalValue("value must be an index in the range [0, heap size).");

        int index = length;
        length += 1;

        do {
            int parent = getParent(index);

            if(parent == -1 || weight >= weights[parent])
                break;

            swap(index, parent);

            index = parent;
        } while(true);

        weights[index] = weight;
        values[index] = value;
        valueIndexes[value] = index;
    }

    /**
     * @return the highest weight value in the heap
     */
    public int examine() {
        if(isEmpty())
            throw new Underflow("Cannot examine an empty heap");

        return values[0];
    }

    /**
     * Dequeue the highest weight value in the heap.
     *
     * @return the value that was dequeue'd
     */
    public int dequeue() {
        if(isEmpty())
            throw new Underflow("Cannot examine an empty heap");

        int value = values[0];

        length -= 1;

        values[0] = -1;
        weights[0] = -1;
        valueIndexes[value] = -1;

        swap(0, length);

        heapify();

        return value;
    }

    /**
     * Move the element at the root of this heap into its correct position in the heap.
     */
    private void heapify() {
        heapify(0);
    }

    /**
     * Move the element at {@param index} in this heap into its correct position in the heap.
     *
     * @param index the index of the value to be moved
     */
    private void heapify(int index) {
        int weight = weights[index];

        do {
            int leftIndex = getLeftChild(index);
            int rightIndex = getRightChild(index);

            int swapIndex = -1;
            int swapWeight = weight;

            if(leftIndex >= 0 && swapWeight > weights[leftIndex]) {
                swapIndex = leftIndex;
                swapWeight = weights[leftIndex];
            }

            if(rightIndex >= 0 && swapWeight > weights[rightIndex]) {
                swapIndex = rightIndex;
            }

            if(swapIndex == -1)
                break;

            swap(index, swapIndex);
            index = swapIndex;
        } while(true);
    }

    /**
     * Swap the values at indexes {@param indexA} and {@param indexB}.
     *
     * @param indexA the index of the first element
     * @param indexB the index of the second element
     */
    private void swap(int indexA, int indexB) {
        int valueA = values[indexA];
        int valueB = values[indexB];
        int weightA = weights[indexA];
        int weightB = weights[indexB];

        values[indexA] = valueB;
        values[indexB] = valueA;

        weights[indexA] = weightB;
        weights[indexB] = weightA;

        // Update index references
        if(valueA != -1) valueIndexes[valueA] = indexB;
        if(valueB != -1) valueIndexes[valueB] = indexA;
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

    /**
     * @return a string representation of this heap with each line containing all elements of that row of the tree
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int level = 1;

        while((1 << level) >> 1 < values.length) {
            int to = (1 << level) - 1;
            int from = to / 2;

            for(int index = from; index < to; ++index) {
                if(index != from) {
                    builder.append(", ");
                }

                builder.append(index < length ? values[index] : "_");
            }

            builder.append('\n');

            level += 1;
        }

        return builder.toString();
    }
}
