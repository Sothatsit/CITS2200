package com.sothatsit.cits2200.data.tree;

import CITS2200.BinaryTree;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import com.sothatsit.cits2200.data.queue.QueueLinked;

/**
 * An extension of BinaryTree implementing a breadth-first iterator, and an equals method.
 *
 * @param <E> the type of element stored in this tree
 *
 * @author Paddy Lamont
 */
public class BinTree<E> extends BinaryTree<E> {

    /**
     * Construct a leaf node
     */
    public BinTree() {}

    /**
     * Construct a binary tree with value {@param item}, left sub-tree {@param left} and right sub-tree {@param right}.
     *
     * @param item  the item to be stored in this node of a tree
     * @param left  the left sub-tree of this node
     * @param right the right sub-tree of this node
     */
    public BinTree(E item, BinaryTree<E> left, BinaryTree<E> right) {
        super(item, left, right);
    }

    /**
     * An iterator that traverses a BinTree in breadth-first order.
     *
     * @param <E> the type of element within the tree being iterated
     */
    private static class BinTreeIterator<E> implements Iterator<E> {

        private final QueueLinked trees;

        /**
         * Construct an iterator to iterate the binary tree {@param root}.
         *
         * @param root the binary tree to be iterated
         */
        public BinTreeIterator(BinTree<E> root) {
            trees = new QueueLinked();

            enqueueTree(root);
        }

        /**
         * Append the binary tree {@param tree} if it is not a leaf node.
         *
         * @param tree the tree to be potentially iterated in the future
         */
        private void enqueueTree(BinaryTree<E> tree) {
            if(tree.isEmpty())
                return;

            trees.enqueue(tree);
        }

        /**
         * Check whether there are any more items in the tree that have not yet been iterated.
         *
         * @return true iff there are more items to be iterated, false otherwise
         */
        @Override
        public boolean hasNext() {
            return !trees.isEmpty();
        }

        /**
         * Find the next item in the tree, breadth first.
         *
         * @return the next item to be iterated in the tree
         *
         * @throws OutOfBounds if there are no more items to be iterated
         */
        @Override
        public E next() throws OutOfBounds {
            if(trees.isEmpty())
                throw new OutOfBounds("Attempted to next when there are no more items to iterate");

            BinaryTree<E> tree = (BinaryTree<E>) trees.dequeue();

            enqueueTree(tree.getLeft());
            enqueueTree(tree.getRight());

            return tree.getItem();
        }
    }

    /**
     * @return an iterator to allow the traversal of this binary tree
     */
    @Override
    public Iterator<E> iterator() {
        return new BinTreeIterator<E>(this);
    }

    /**
     * Check whether this tree is equal to another BinaryTree.
     * Will return false unless another BinaryTree is supplied.
     *
     * @param object the other tree to check for equality
     *
     * @return whether the tree {@param object} and this tree contain the same items in the same structure
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BinaryTree))
            return false;

        BinaryTree other = (BinaryTree) object;

        if(isEmpty() && other.isEmpty())
            return true;
        if(isEmpty() || other.isEmpty())
            return false;

        return getItem().equals(other.getItem())
                && getLeft().equals(other.getLeft())
                && getRight().equals(other.getRight());

    }
}
