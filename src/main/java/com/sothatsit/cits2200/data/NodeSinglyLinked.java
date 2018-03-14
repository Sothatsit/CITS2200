package com.sothatsit.cits2200.data;

import com.sothatsit.cits2200.test.Checks;

/**
 * A node in a linked data structure that stores a reference to its successor.
 */
public class NodeSinglyLinked {

    private final Object value;
    private NodeSinglyLinked successor;

    /**
     * Instantiate a new singly linked node with the value {@param value} that is not connected to any successor.
     *
     * @param value the value of this link
     */
    public NodeSinglyLinked(Object value) {
        this(value, null);
    }

    /**
     * Instantiate a new singly linked node with the value {@param value} linked to the successor {@param successor}.
     *
     * @param value     the value of this link
     * @param successor the next node this node is linked to, nullable
     */
    public NodeSinglyLinked(Object value, NodeSinglyLinked successor) {
        Checks.assertNonNull(value, "value");

        this.value = value;
        this.successor = successor;
    }

    /**
     * @return the value of this node
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return true iff this node has a successor, false otherwise
     */
    public boolean hasSuccessor() {
        return successor != null;
    }

    /**
     * Set the successor of this node to {@param successor}.
     * Or if {@param successor} is null, remove the link of this node.
     *
     * @param successor the node to link this node to, or null to remove the link
     */
    public void setSuccessor(NodeSinglyLinked successor) {
        this.successor = successor;
    }

    /**
     * @return the successor of this node, or null
     */
    public NodeSinglyLinked getSuccessor() {
        return successor;
    }

    /**
     * Convert this to a string by walking over this linked list.
     *
     * @return an array string representation of the successors of this node
     */
    public String walk() {
        StringBuilder builder = new StringBuilder();

        builder.append('[');

        boolean first = true;
        NodeSinglyLinked current = this;
        while(current != null) {
            if(!first) {
                builder.append(", ");
            } else {
                first = false;
            }

            builder.append(current.getValue());
            current = current.getSuccessor();
        }

        builder.append(']');

        return builder.toString();
    }
}
