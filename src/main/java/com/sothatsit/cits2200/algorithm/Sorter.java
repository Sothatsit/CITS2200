package com.sothatsit.cits2200.algorithm;

import CITS2200.Sort;

public class Sorter implements Sort {

    private int assignmentCount;

    public int getCount() {
        return assignmentCount;
    }

    public void reset() {
        assignmentCount = 0;
    }

    //
    // INSERTION SORT
    //

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

    private void swap(long[] array, int index1, int index2) {
        long temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        assignmentCount += 2;
    }

    //
    // MERGE SORT
    //

    public void mergeSort(long[] a) {
        mergeSort(a, 0, a.length-1);
    }

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

    private void mergeSort(long[] a, int p, int r) {
        if(p < r) {
            int i = (p+r)/2;
            mergeSort(a,p,i);
            mergeSort(a,i+1,r);
            merge(a, p,i,r);
        }
    }
}