package com.sothatsit.cits2200.data.list;

import CITS2200.Link;
import CITS2200.List;
import CITS2200.OutOfBounds;
import CITS2200.WindowLinked;
import com.sothatsit.cits2200.util.ArrayBuilder;

/**
 * A linked, no capacity implementation of a list.
 *
 * @author Paddy Lamont
 */
public class ListLinked implements List {

    private Link before;
    private Link after;

    public ListLinked() {
        after = new Link(null, null);
        before = new Link(null, after);
    }

    /**
     * @return true iff this list is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return before.successor == after;
    }

    /**
     * Check whether {@param window} is pointing to before the start of this list.
     *
     * @param window the window pointing into the list
     *
     * @return true iff {@param window} is point to before the start of this list, otherwise false
     */
    @Override
    public boolean isBeforeFirst(WindowLinked window) {
        return window.link == before;
    }

    /**
     * Check whether {@param window} is pointing to after the end of this list.
     *
     * @param window the window pointing into the list
     *
     * @return true iff {@param window} is point to after the end of this list, otherwise false
     */
    @Override
    public boolean isAfterLast(WindowLinked window) {
        return window.link == after;
    }

    /**
     * Set {@param window} to point to before the start of this list.
     *
     * @param window the window object to set to point to before the start of this list
     */
    @Override
    public void beforeFirst(WindowLinked window) {
        window.link = before;
    }

    /**
     * Set {@param window} to point to after the end of this list.
     *
     * @param window the window objet to set to point to after the end of this list
     */
    @Override
    public void afterLast(WindowLinked window) {
        window.link = after;
    }

    /**
     * Shift {@param window} to point to the next element in the list.
     *
     * @param window a location in or before the list
     *
     * @throws OutOfBounds if there are no elements after {@param window} in this list
     */
    @Override
    public void next(WindowLinked window) throws OutOfBounds {
        if(isAfterLast(window))
            throw new OutOfBounds("Trying to next past the end of the list");

        window.link = window.link.successor;
    }

    /**
     * Shift {@param window} to point to the previous element in the list.
     *
     * @param window a location in or after the list
     *
     * @throws OutOfBounds if there are no elements before {@param window} in this list
     */
    @Override
    public void previous(WindowLinked window) throws OutOfBounds {
        if(isBeforeFirst(window))
            throw new OutOfBounds("Trying to previous past the start of the list");

        Link current = before;

        while(current.successor != window.link) {
            current = current.successor;
        }

        window.link = current;
    }

    /**
     * Insert the value {@param value} into this list after the element at {@param window}.
     *
     * @param value  the value to insert into the list
     * @param window the location to insert the value after
     *
     * @throws OutOfBounds if {@param window} is after the end of this list
     */
    @Override
    public void insertAfter(Object value, WindowLinked window) throws OutOfBounds {
        if(isAfterLast(window))
            throw new OutOfBounds("Trying to insert after the end of the list");

        window.link.successor = new Link(value, window.link.successor);
    }

    /**
     * Insert the value {@param value} into this list before the element at {@param window}.
     *
     * @param value  the value to insert into this list
     * @param window the location to insert the value before
     *
     * @throws OutOfBounds if {@param window} is before the start of this list
     */
    @Override
    public void insertBefore(Object value, WindowLinked window) throws OutOfBounds {
        if(isBeforeFirst(window))
            throw new OutOfBounds("Trying to insert before the start of the list");

        window.link.successor = new Link(window.link.item, window.link.successor);
        window.link.item = value;

        if(isAfterLast(window)) {
            after = window.link.successor;
        }

        window.link = window.link.successor;
    }

    /**
     * Examine the value at the location {@param window} in this list.
     *
     * @param window the location in this window to examine
     *
     * @return the value at {@param window} in this list
     *
     * @throws OutOfBounds if {@param window} is before the start of the list or after the end of the list
     */
    @Override
    public Object examine(WindowLinked window) throws OutOfBounds {
        if(isBeforeFirst(window))
            throw new OutOfBounds("Trying to examine before the start of the list");

        if(isAfterLast(window))
            throw new OutOfBounds("Trying to examine after the end of the list");

        return window.link.item;
    }

    /**
     * Replace the element at {@param window} in this list with the value {@param value}.
     *
     * @param value  the value to replace the previous value at {@param window} with
     * @param window the location of the element to be replaced
     *
     * @return the value of the element at {@param window} that was replaced
     *
     * @throws OutOfBounds if {@param window} is before the start of the list or after the end of the list
     */
    @Override
    public Object replace(Object value, WindowLinked window) throws OutOfBounds {
        Object previousValue = examine(window);

        window.link.item = value;

        return previousValue;
    }

    /**
     * Delete the element at {@param window} in this list.
     * Moves {@param window} to point to its successor, which can be after the end of the list.
     *
     * @param window the location of the element to be deleted
     *
     * @return the value of the element at {@param window} that was deleted
     *
     * @throws OutOfBounds if {@param window} is before the start of the list or after the end of the list
     */
    @Override
    public Object delete(WindowLinked window) throws OutOfBounds {
        Object previousValue = examine(window);

        if(after == window.link.successor) {
            after = window.link;
        }

        window.link.item = window.link.successor.item;
        window.link.successor = window.link.successor.successor;

        return previousValue;
    }

    /**
     * @return a string representation of this list, left-most element at index 0
     */
    @Override
    public String toString() {
        return ArrayBuilder.fromLinkedWithTerminator(before.successor);
    }
}
