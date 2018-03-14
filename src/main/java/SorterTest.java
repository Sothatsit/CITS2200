import java.util.Arrays;
import java.util.Random;

public class SorterTest {

    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        Random rand = new Random();

        long[] ascending = new long[16];
        long[] descending = new long[16];
        long[] random = new long[16];

        for(int index = 0; index < 16; ++index) {
            ascending[index] = index;
            descending[index] = 16 - index;
            random[index] = rand.nextInt(100);
        }

        testSort(sorter, ascending);
        System.out.println();

        testSort(sorter, descending);
        System.out.println();

        testSort(sorter, random);
    }

    public static void testSort(Sorter sorter, long[] array) {
        System.out.println("Sort " + Arrays.toString(array));

        long[] insertion = Arrays.copyOf(array, array.length);
        {
            sorter.reset();
            sorter.insertionSort(insertion);
            System.out.println("   Insertion: " + Arrays.toString(insertion) + " (" + sorter.getCount() + ")");
        }

        long[] quicksort = Arrays.copyOf(array, array.length);
        {
            sorter.reset();
            sorter.quickSort(quicksort);
            System.out.println("   QuickSort: " + Arrays.toString(insertion) + " (" + sorter.getCount() + ")");
        }

        long[] mergesort = Arrays.copyOf(array, array.length);
        {
            sorter.reset();
            sorter.mergeSort(mergesort);
            System.out.println("   MergeSort: " + Arrays.toString(insertion) + " (" + sorter.getCount() + ")");
        }

        sorter.reset();
    }
}
