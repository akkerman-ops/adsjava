import java.util.Iterator;

/**
 * Custom stack implementation using MyArrayList as the underlying data structure.
 * @param <T> the type of elements in this stack
 */
public class MyStack<T> implements Iterable<T> {
    private MyArrayList<T> list; // Using ArrayList for O(1) add/remove at the end

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        list = new MyArrayList<>();
    }

    /**
     * Pushes an item onto the top of this stack.
     * @param item the item to be pushed onto this stack
     */
    public void push(T item) {
        list.addLast(item);
    }

    /**
     * Removes the object at the top of this stack and returns it.
     * @return the object at the top of this stack
     */
    public T pop() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        T item = list.getLast();
        list.removeLast();
        return item;
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     * @return the object at the top of this stack
     */
    public T peek() {
        if (isEmpty()) {
            throw new java.util.EmptyStackException();
        }
        return list.getLast();
    }

    /**
     * Tests if this stack is empty.
     * @return true if this stack contains no items; false otherwise
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Returns the number of elements in this stack.
     * @return the number of elements in this stack
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes all of the elements from this stack.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Returns an iterator over the elements in this stack in proper sequence.
     * @return an iterator over the elements in this stack in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        // Create an array list with a copy of elements in stack order (top to bottom)
        MyArrayList<T> reversed = new MyArrayList<>(list.size());
        for (int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }
        return reversed.iterator();
    }
}