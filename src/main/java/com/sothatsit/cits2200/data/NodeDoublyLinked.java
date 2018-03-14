package com.sothatsit.cits2200.data;

import com.sothatsit.cits2200.test.Checks;

/**
 * A node in a linked data structure that stores references to a left and a right node.
 */
public class NodeDoublyLinked {

    private final Object value;
    private NodeDoublyLinked left;
    private NodeDoublyLinked right;

    /**
     * Instantiate a new doubly linked node with the value {@param value} that is not connected to any other nodes.
     *
     * @param value the value of this link
     */
    public NodeDoublyLinked(Object value) {
        this(value, null, null);
    }

    /**
     * Instantiate a new doubly linked node with the value {@param value}
     * linked on the left to {@param left} and on the right to {@param right}.
     *
     * @param value the value of this link
     * @param left  the left node this node is linked to, nullable
     * @param right the right node this node is linked to, nullable
     */
    public NodeDoublyLinked(Object value, NodeDoublyLinked left, NodeDoublyLinked right) {
        Checks.assertNonNull(value, "value");

        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * @return the value of this node
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return true iff this node has a left node, false otherwise
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * @return true iff this node has a right node, false otherwise
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Set the right node of this node to {@param right}.
     * Or if {@param right} is null, remove the link to the right node.
     *
     * Will also update the left reference of {@param right} to this
     * node, and will clear the left reference of {@link this#right}.
     *
     * @param right the node to be linked to the right, or null to remove the link
     */
    public void linkRight(NodeDoublyLinked right) {
        if(this.right != null) {
            this.right.left = null;
        }

        this.right = right;

        if(this.right != null) {
            this.right.left = this;
        }
    }

    /**
     * Set the left node of this node to {@param left}.
     * Or if {@param left} is null, remove the link to the left node.
     *
     * Will also update the right reference of {@param left} to this
     * node, and will clear the right reference of {@link this#left}.
     *
     * @param left the node to be linked to the left, or null to remove the link
     */
    public void linkLeft(NodeDoublyLinked left) {
        if(this.left != null) {
            this.left.right = null;
        }

        this.left = left;

        if(this.left != null) {
            this.left.right = this;
        }
    }

    /**
     * @return the node this node is linked to on the left
     */
    public NodeDoublyLinked getRight() {
        return right;
    }

    /**
     * @return the node this node is linked to on the right
     */
    public NodeDoublyLinked getLeft() {
        return left;
    }
}
