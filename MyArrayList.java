import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom ArrayList implementation that dynamically resizes as elements are added.
 * @param <T> the type of elements in this list
 */
public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
    //Constructs an empty list with an initial capacity of 10.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     * @param initialCapacity the initial capacity of the list
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        elements = new Object[initialCapacity];
        size = 0;
    }

    /**
     * Increases the capacity of this ArrayList instance, if necessary.
     * @param minCapacity the desired minimum capacity
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    /**
     * Appends the specified element to the end of this list.
     * @param item element to be appended to this list
     */
    @Override
    public void add(T item) {
        ensureCapacity(size + 1);
        elements[size++] = item;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index index of the element to replace
     * @param item element to be stored at the specified position
     */
    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = item;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index index at which the specified element is to be inserted
     * @param item element to be inserted
     */
    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     * @param item element to be inserted at the beginning of this list
     */
    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    /**
     * Appends the specified element to the end of this list.
     * @param item element to be appended to this list
     */
    @Override
    public void addLast(T item) {
        add(item);
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    /**
     * Returns the first element in this list.
     * @return the first element in this list
     */
    @Override
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    /**
     * Returns the last element in this list.
     * @return the last element in this list
     */
    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index the index of the element to be removed
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // Let GC do its work
    }

    /**
     * Removes the first element from this list.
     */
    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        remove(0);
    }

    /**
     * Removes the last element from this list.
     */
    @Override
    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        remove(size - 1);
    }

    /**
     * Sorts this list according to the natural ordering of its elements.
     * Elements must implement Comparable interface.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        if (size <= 1) {
            return;
        }
        
        // Simple insertion sort
        for (int i = 1; i < size; i++) {
            T key = (T) elements[i];
            int j = i - 1;
            
            // Move elements that are greater than key to one position ahead
            while (j >= 0 && ((Comparable<T>) elements[j]).compareTo(key) > 0) {
                elements[j + 1] = elements[j];
                j = j - 1;
            }
            elements[j + 1] = key;
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     * @param object element to search for
     * @return the index of the first occurrence of the specified element, or -1 if not found
     */
    @Override
    public int indexOf(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list.
     * @param object element to search for
     * @return the index of the last occurrence of the specified element, or -1 if not found
     */
    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns true if this list contains the specified element.
     * @param object element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    @Override
    public boolean exists(Object object) {
        return indexOf(object) >= 0;
    }

    /**
     * Returns an array containing all of the elements in this list.
     * @return an array containing all of the elements in this list
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
        // Clear to let GC do its work
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this list.
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[currentIndex++];
            }

            @Override
            public void remove() {
                MyArrayList.this.remove(--currentIndex);
            }
        };
    }
}