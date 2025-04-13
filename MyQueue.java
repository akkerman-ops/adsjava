import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom queue implementation using MyLinkedList as the underlying data structure.
 * @param <T> the type of elements in this queue
 */
public class MyQueue<T> implements Iterable<T> {
    private MyLinkedList<T> list; // Using LinkedList for O(1) operations at both ends

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        list = new MyLinkedList<>();
    }

    /**
     * Inserts the specified element into this queue.
     * @param item the element to add
     */
    public void enqueue(T item) {
        list.addLast(item);
    }

    /**
     * Retrieves and removes the head of this queue.
     * @return the head of this queue
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = list.getFirst();
        list.removeFirst();
        return item;
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     * @return the head of this queue
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.getFirst();
    }

    /**
     * Tests if this queue is empty.
     * @return true if this queue contains no elements; false otherwise
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns the number of elements in this queue.
     * @return the number of elements in this queue
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes all of the elements from this queue.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Returns an iterator over the elements in this queue in proper sequence.
     * @return an iterator over the elements in this queue in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}