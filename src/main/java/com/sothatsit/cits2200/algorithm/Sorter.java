package com.sothatsit.cits2200.algorithm;

import CITS2200.Sort;

/**
 * Allow using an insertion sort, quick sort or merge sort to sort an array of longs.
 * Counts the number of assignments done by each sorting algorithm in sorting an array.
 *
 * @author Paddy Lamont
 */
public class Sorter implements Sort {

    private int assignmentCount;

    /**
     * @return the number of array assignments that happened since the last {@link #reset()}.
     */
    public int getCount() {
        return assignmentCount;
    }

    /**
     * Reset the count of array assignments to zero.
     */
    public void reset() {
        assignmentCount = 0;
    }

    //
    // INSERTION SORT
    //

    /**
     * Sort {@param array} using an insertion sort.
     *
     * @param array the array to be sorted
     */
    public void insertionSort(long[] array) {
        for(int j = 1; j < array.length; ++j) {
            long key = array[j];

            int i = j - 1;
            while(i >= 0 && array[i] > key) {
                array[i + 1] = array[i];
                assignmentCount += 1;
                i -= 1;
            }

            array[i + 1] = key;
            assignmentCount += 1;
        }
    }


    //
    // QUICK SORT
    //

    /**
     * Sort {@param array} using a quick sort.
     *
     * @param array the array to be sorted
     */
    public void quickSort(long[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(long[] array, int start, int end) {
        if(start < end) {
            int pivotIndex = partition(array, start, end);

            quickSort(array, start, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }

    /**
     * Select and sort a pivot into its correct position in array between start and end inclusive.
     *
     * @param array the array to partition
     * @param start the start of the partition, inclusive
     * @param end   the end of the partition, inclusive
     *
     * @return the index of the new location of the pivot
     */
    private int partition(long[] array, int start, int end) {
        long pivot = array[end];
        int pivotIndex = start - 1;

        for (int j = start; j < end; ++j) {
            if (array[j] <= pivot) {
                pivotIndex += 1;
                swap(array, pivotIndex, j);
            }
        }

        pivotIndex += 1;
        swap(array, pivotIndex, end);
        return pivotIndex;
    }

    /**
     * Swap the elements at {@param index1} and {@param index2} of {@param array}.
     *
     * @param array  the array in which the two elements will be swapped
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     */
    private void swap(long[] array, int index1, int index2) {
        long temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        assignmentCount += 2;
    }

    //
    // MERGE SORT
    //
    // Author: Tim French
    //

    /**
     * Executes the merge sort algorithm sorting the argument array.
     * There is no return as the parameter is to be mutated.
     *
     * @param a the array of long integers to be sorted
     **/
    public void mergeSort(long[] a) {
        mergeSort(a, 0, a.length-1);
    }

    /**
     * A private method to merge the elements in the array between p and r.
     * the array a, between the indices p and r, inclusive.
     * Given the array is sorted between p and q and q+1 and r
     * sorts the array between p and r.
     *
     * @param a the array to be sorted, which is mutated by the method
     * @param p the lower index of the range to be partitioned
     * @param q the midpoint of the two sorted sections.
     * @param r the upper index of the range to be paritioned
     **/
    private void merge(long[] a, int p, int q, int r) {
        int n = q-p+1;
        int m = r-q;
        long[] an = new long[n];
        long[] am = new long[m];
        for(int i = 0; i<n; i++) {
            an[i] = a[p+i];
            assignmentCount++;
        }
        for(int i = 0; i<m; i++){
            am[i] = a[q+i+1];
            assignmentCount++;
        }
        int i = 0;
        int j = 0;
        for(int k = p; k<=r; k++){
            if(i==n) a[k] = am[j++];
            else if(j==m || an[i]<am[j]) a[k] = an[i++];
            else a[k] = am[j++];
            assignmentCount++;
        }
    }

    /**
     * Overloads the mergeSort method with parameters to set the
     * range to be sorted.
     **/
    private void mergeSort(long[] a, int p, int r) {
        if(p < r) {
            int i = (p+r)/2;
            mergeSort(a,p,i);
            mergeSort(a,i+1,r);
            merge(a, p,i,r);
        }
    }
}