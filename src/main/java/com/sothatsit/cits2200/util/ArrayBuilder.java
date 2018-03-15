package com.sothatsit.cits2200.util;

/**
 * A builder used to construct array strings.
 *
 * @author Paddy Lamont
 */
public class ArrayBuilder {

    private final StringBuilder builder;
    private int length;

    /**
     * Instantiate an array string builder.
     */
    public ArrayBuilder() {
        this.builder = new StringBuilder();
        this.length = 0;
    }

    /**
     * Append a value to this array string builder.
     *
     * @param value the value to append
     *
     * @return this array builder to allow chaining of calls
     */
    public ArrayBuilder append(Object value) {
        if(length != 0) {
            builder.append(", ");
        }

        builder.append(value);
        length += 1;

        return this;
    }

    /**
     * Append the array {@param array} to this array builder.
     *
     * @param array the array of values to append
     *
     * @return this array builder to allow chaining of calls
     */
    public ArrayBuilder appendArray(Object[] array) {
        return appendArray(array, 0, array.length);
    }

    /**
     * Append the range of values [{@param first}, {@param last}] from {@param array} to this array builder.
     *
     * @param array the array in which the values are held
     * @param first the index of the first element to be appended in {@param array}, inclusive
     * @param last  the index of the last element to be appended in {@param array}, inclusive
     *
     * @return this array builder to allow chaining of calls
     */
    public ArrayBuilder appendArray(Object[] array, int first, int last) {
        for(int index = first; index <= last; ++index) {
            append(array[index]);
        }

        return this;
    }

    /**
     * Append the range of values [{@param first}, {@param first} + {@param length})
     * from the cyclic array {@param array} to this array builder.
     *
     * @param array  the cyclic array in which the values are held
     * @param first  the index of the first element to be appended in {@param array}
     * @param length the number of filled slots in {@param array} from {@param first}
     *
     * @return this array builder to allow chaining of calls
     */
    public ArrayBuilder appendCyclicArray(Object[] array, int first, int length) {
        for(int i = 0; i < length; ++i) {
            int index = (first + i) % length;

            append(array[index]);
        }

        return this;
    }

    /**
     * @return the constructed array string
     */
    @Override
    public String toString() {
        return "[" + builder.toString() + "]";
    }

    /**
     * Construct a string representation from the range of values in the array {@param array}.
     *
     * @param array the array of values to append
     *
     * @return the stringified representation of this range of the array
     */
    public static String fromArray(Object[] array) {
        return new ArrayBuilder().appendArray(array).toString();
    }

    /**
     * Construct a string representation from the range of values
     * [{@param first}, {@param last}] in the array {@param array}.
     *
     * @param array the array in which the values are held
     * @param first the index of the first element to be appended in {@param array}, inclusive
     * @param last  the index of the last element to be appended in {@param array}, inclusive
     *
     * @return the stringified representation of this range of the array
     */
    public static String fromArray(Object[] array, int first, int last) {
        return new ArrayBuilder().appendArray(array, first, last).toString();
    }

    /**
     * Construct a string representation from the range of values
     * [{@param first}, {@param first} + {@param length}) in the
     * cyclic array {@param array}.
     *
     * @param array  the cyclic array in which the values are held
     * @param first  the index of the first element to be appended in {@param array}
     * @param length the number of filled slots in {@param array} from {@param first}
     *
     * @return this array builder to allow chaining of calls
     */
    public static String fromCyclicArray(Object[] array, int first, int length) {
        return new ArrayBuilder().appendCyclicArray(array, first, length).toString();
    }
}
