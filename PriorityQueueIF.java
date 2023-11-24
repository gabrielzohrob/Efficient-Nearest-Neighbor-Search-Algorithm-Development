public interface PriorityQueueIF<E> {

    // Inserts the specified element into this queue if it is possible to do so immediately 
	// without violating capacity restrictions.
    boolean offer(E e);

    // Retrieves and removes the head of this queue, or returns null if this queue is empty.
    E poll();

    // Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    E peek();

    // Returns the number of elements in this queue.
    int size();

    // Returns true if this queue contains no elements.
    boolean isEmpty();
}
