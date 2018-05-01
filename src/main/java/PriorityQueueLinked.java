import CITS2200.*;

/**
 * A linked, first-in, first-out, priority queue implementation.
 *
 * @author Paddy Lamont
 */
public class PriorityQueueLinked<E> implements PriorityQueue<E> {

    /**
     * The front element in this queue. The element with the highest priority.
     */
    private PriorityLink<E> front;

    /**
     * Construct a new empty priority queue.
     */
    public PriorityQueueLinked() {
        this.front = null;
    }

    /**
     * @return true iff there are no elements left in this queue, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Enqueue a new element with value {@param value} and priority {@param priority}.
     *
     * @param value    The value to enqueue
     * @param priority The priority of the value
     *
     * @throws IllegalValue is not thrown, is there to satisfy contract
     */
    @Override
    public void enqueue(E value, int priority) throws IllegalValue {
        if(isEmpty() || priority > front.priority) {
            front = new PriorityLink<>(value, front, priority);
            return;
        }

        PriorityLink<E> link = front;

        while(link.next != null && link.next.priority >= priority) {
            link = link.next;
        }

        link.next = new PriorityLink<>(value, link.next, priority);
    }

    /**
     * Get the value of the next element to be dequeue'd.
     *
     * @return The value of the top-most, highest priority link in this queue
     *
     * @throws Underflow If there are no elements currently in the queue
     */
    @Override
    public E examine() throws Underflow {
        if(isEmpty())
            throw new Underflow("attempting to examine empty queue");

        return front.value;
    }

    /**
     * Dequeue the top-most, highest priority link in this queue.
     *
     * @return The dequeue'd value
     *
     * @throws Underflow If there are no elements currently in the queue
     */
    @Override
    public E dequeue() throws Underflow {
        if(isEmpty())
            throw new Underflow("attempting to examine empty queue");

        E value = front.value;
        front = front.next;

        return value;
    }

    /**
     * A Link with an associated priority.
     *
     * @param <E> The type of value held in this link
     */
    private static class PriorityLink<E> {

        /**
         * The value of this link.
         */
        private final E value;

        /**
         * The successor of this link.
         */
        private PriorityLink<E> next;

        /**
         * The priority of this link.
         */
        private final int priority;

        /**
         * Construct a new priority link.
         *
         * @param value    The value to be held in this link
         * @param next     The next link after this link
         * @param priority The priority of this link
         */
        public PriorityLink(E value, PriorityLink<E> next, int priority) {
            this.value = value;
            this.next = next;
            this.priority = priority;
        }
    }

    /**
     * Construct a new iterator to iterate the current state of this priority queue.
     */
    @Override
    public Iterator iterator() {
        return new PriorityQueueLinkedIterator<>(this);
    }

    /**
     * @return a string representation of this priority queue
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");

        PriorityLink<E> link = front;

        while(link != null) {
            builder.append(link.priority).append(": ");
            builder.append(link.value);

            if(link.next != null) {
                builder.append(", ");
            }

            link = link.next;
        }

        builder.append("]");

        return builder.toString();
    }

    /**
     * An iterator made to iterate this priority queue.
     *
     * @param <E> The type of value stored in the queue
     */
    private static class PriorityQueueLinkedIterator<E> implements Iterator<E> {

        /**
         * A copy of the queue to be iterated
         */
        private QueueLinked queue;

        /**
         * Construct a new priority queue iterator to iterate the queue {@param queue}.
         *
         * Copies all the values in {@param queue} so that it is unaffected by modifications to the queue.
         */
        public PriorityQueueLinkedIterator(PriorityQueueLinked<E> queue) {
            this.queue = new QueueLinked();

            // Copy the values of queue
            PriorityLink link = queue.front;

            while(link != null) {
                this.queue.enqueue(link.value);

                link = link.next;
            }
        }

        /**
         * @return The next value in the priority queue
         *
         * @throws OutOfBounds If there are no values left in this iterator
         */
        @Override
        public E next() throws OutOfBounds {
            return (E) queue.dequeue();
        }

        /**
         * @return true iff there are more elements to be iterated, false otherwise
         */
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}
