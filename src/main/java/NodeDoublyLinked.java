/**
 * A node in a linked data structure that stores references to its previous and next nodes.
 */
public class NodeDoublyLinked {

    private final Object value;
    private NodeDoublyLinked previous;
    private NodeDoublyLinked next;

    /**
     * Instantiate a new doubly linked node with the value {@param value} that is not connected to any other nodes.
     *
     * @param value the value of this link
     */
    public NodeDoublyLinked(Object value) {
        this(value, null, null);
    }

    /**
     * Instantiate a new doubly linked node with the value {@param value} linked to the successor {@param successor}.
     *
     * @param value    the value of this link
     * @param previous the previous node this node is linked to, nullable
     * @param next     the next node this node is linked to, nullable
     */
    public NodeDoublyLinked(Object value, NodeDoublyLinked previous, NodeDoublyLinked next) {
        Checks.assertNonNull(value, "value");

        this.value = value;
        this.previous = previous;
        this.next = next;
    }

    /**
     * @return the value of this node
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return true iff this node has a previous node, false otherwise
     */
    public boolean hasPrevious() {
        return previous != null;
    }

    /**
     * @return true iff this node has a next node, false otherwise
     */
    public boolean hasNext() {
        return next != null;
    }

    /**
     * Set the next node of this node to {@param next}.
     * Or if {@param next} is null, remove the link to the next node.
     *
     * @param next the node to link this node next to, or null to remove the link
     */
    public void linkTo(NodeDoublyLinked next) {
        this.next = next;
        this.next.previous = this;
    }

    /**
     * Set the previous node of this node to {@param previous}.
     * Or if {@param previous} is null, remove the link to the previous node.
     *
     * @param previous the node this node is linked from
     */
    public void linkFrom(NodeDoublyLinked previous) {
        this.previous = previous;
        this.previous.next = this;
    }

    /**
     * @return the node this node is linked to
     */
    public NodeDoublyLinked getNext() {
        return next;
    }

    /**
     * @return the node this node is linked from
     */
    public NodeDoublyLinked getPrevious() {
        return previous;
    }
}
