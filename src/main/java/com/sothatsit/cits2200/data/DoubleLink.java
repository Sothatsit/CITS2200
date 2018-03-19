package com.sothatsit.cits2200.data;

import com.sothatsit.cits2200.util.ArrayBuilder;
import com.sothatsit.cits2200.util.Checks;

/**
 * A link in a linked data structure that stores references to a left and a right link.
 *
 * @author Paddy Lamont
 */
public class DoubleLink {

    private final Object value;
    private DoubleLink left;
    private DoubleLink right;

    /**
     * Instantiate a new doubly linked link with the value {@param value} that is not connected to any other links.
     *
     * @param value the value of this link
     */
    public DoubleLink(Object value) {
        this(value, null, null);
    }

    /**
     * Instantiate a new doubly linked link with the value {@param value}
     * linked on the left to {@param left} and on the right to {@param right}.
     *
     * @param value the value of this link
     * @param left  the left link this link is to be linked to, nullable
     * @param right the right link this link is to be linked to, nullable
     */
    public DoubleLink(Object value, DoubleLink left, DoubleLink right) {
        Checks.assertNonNull(value, "value");

        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * @return the value of this link
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return true iff this link is linked to the left, false otherwise
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * @return true iff this link is linked to the right, false otherwise
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Set the right link of this link to {@param right}.
     * Or if {@param right} is null, remove the link to the right.
     *
     * Will also update the left reference of {@param right} to this
     * link, and will clear the left reference of {@link #right}.
     *
     * @param right the link to be linked to the right, or null to remove the link
     */
    public void linkRight(DoubleLink right) {
        if(this.right != null) {
            this.right.left = null;
        }

        this.right = right;

        if(this.right != null) {
            this.right.left = this;
        }
    }

    /**
     * Set the left link of this link to {@param left}.
     * Or if {@param left} is null, remove the link to the left.
     *
     * Will also update the right reference of {@param left} to this
     * link, and will clear the right reference of {@link #left}.
     *
     * @param left the link to be linked to the left, or null to remove the link
     */
    public void linkLeft(DoubleLink left) {
        if(this.left != null) {
            this.left.right = null;
        }

        this.left = left;

        if(this.left != null) {
            this.left.right = this;
        }
    }

    /**
     * @return the link to the right of this link, or null if there is no link
     */
    public DoubleLink getRight() {
        return right;
    }

    /**
     * @return the link to the left of this link, or null if there is no link
     */
    public DoubleLink getLeft() {
        return left;
    }

    /**
     * Convert this to a string by walking right over this linked list.
     *
     * @return a string representation of the right of this link as an array with this link at index 0
     */
    public String walkRight() {
        ArrayBuilder builder = new ArrayBuilder();

        DoubleLink current = this;
        while(current != null) {
            builder.append(current.getValue());
            current = current.getRight();
        }

        return builder.toString();
    }
}
