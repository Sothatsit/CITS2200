import CITS2200.Deque;
import CITS2200.Underflow;

public class DequeLinked implements Deque {

    private DequeLinked child;
    private Object value;

    /**
     * Instantiate a new deque.
     */
    public DequeLinked() {
        this.child = null;
        this.value = null;
    }

    /**
     * Insantiate a new deque node with value {@param value} and child {@param child}.
     * 
     * @param child the child of this node in the linked deque
     * @param value the value of this node in the linked deque
     */
    private DequeLinked(DequeLinked child, Object value) {
        this.child = child;
        this.value = value;
    }

    /**
     * Check whether this deque is empty.
     *
     * @return true iff the deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return value == null;
    }

    /**
     * Check whether this deque is full.
     *
     * This is always false as linked deques have no maximum size.
     *
     * @return false
     */
    @Override
    public boolean isFull() {
        return false;
    }

    /**
     * Push a value onto the left of this deque.
     *
     * @param value     the value to be pushed onto the deque
     */
    @Override
    public void pushLeft(Object value) {
        this.child = new DequeLinked(this.child, this.value);
        this.value = value;
    }

    /**
     * Push a value onto the right of this deque.
     *
     * @param value     the value to be pushed onto the deque
     */
    @Override
    public void pushRight(Object value) {
        if(!isEmpty()) {
            child.pushRight(value);
            return;
        }

        this.child = new DequeLinked();
        this.value = value;
    }

    /**
     * Pop a value from the left of this deque.
     *
     * @return           the value that was pop'ed off of the deque
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object popLeft() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to pop value from an empty deque");

        Object popped = this.value;

        this.value = child.value;
        this.child = child.child;

        return popped;
    }

    /**
     * Pop a value from the right of this deque.
     *
     * @return           the value that was pop'ed off of the deque
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object popRight() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to pop value from an empty deque");

        if(!child.isEmpty())
            return child.popRight();

        Object popped = this.value;

        this.value = null;
        this.child = null;

        return popped;
    }

    /**
     * Peek the right-most value in this deque.
     *
     * @return           the right-most value of this deque
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object peekRight() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to peek value from an empty deque");

        if(!child.isEmpty())
            return child.peekRight();

        return value;
    }

    /**
     * Peek the left-most value in this deque.
     *
     * @return           the left-most value of this deque
     * @throws Underflow if the deque is empty
     */
    @Override
    public Object peekLeft() throws Underflow {
        if(isEmpty())
            throw new Underflow("Attempted to peek value from an empty deque");

        return value;
    }
}
