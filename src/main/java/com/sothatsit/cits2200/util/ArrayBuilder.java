package com.sothatsit.cits2200.util;

/**
 * A builder used to construct array strings.
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
     */
    public void append(Object value) {
        if(length != 0) {
            builder.append(", ");
        }

        builder.append(value);
        length += 1;
    }

    /**
     * @return the constructed array string
     */
    @Override
    public String toString() {
        return "[" + builder.toString() + "]";
    }

}
