import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom doubly-linked list implementation.
 * @param <T> the type of elements in this list
 */
public class MyLinkedList<T> implements MyList<T> {
    /**
     * Node class for the doubly-linked list.
     */
    private class MyNode {
        T element;
        MyNode next;
        MyNode prev;

        MyNode(T element, MyNode prev, MyNode next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    /**
     * Constructs an empty list.
     */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the node at the specified position in this list.
     * @param index index of the node to return
     * @return the node at the specified position in this list
     */
    private MyNode getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        MyNode current;
        if (index < size / 2) {
            // Search from the beginning
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            // Search from the end
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param item element to be appended to this list
     */
    @Override
    public void add(T item) {
        addLast(item);
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index index of the element to replace
     * @param item element to be stored at the specified position
     */
    @Override
    public void set(int index, T item) {
        MyNode node = getNode(index);
        node.element = item;
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

        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            MyNode nextNode = getNode(index);
            MyNode prevNode = nextNode.prev;
            MyNode newNode = new MyNode(item, prevNode, nextNode);
            prevNode.next = newNode;
            nextNode.prev = newNode;
            size++;
        }
    }

    /**
     * Inserts the specified element at the beginning of this list.
     * @param item element to be inserted at the beginning of this list
     */
    @Override
    public void addFirst(T item) {
        MyNode newNode = new MyNode(item, null, head);
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param item element to be appended to this list
     */
    @Override
    public void addLast(T item) {
        MyNode newNode = new MyNode(item, tail, null);
        if (tail != null) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    @Override
    public T get(int index) {
        return getNode(index).element;
    }

    /**
     * Returns the first element in this list.
     * @return the first element in this list
     */
    @Override
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    /**
     * Returns the last element in this list.
     * @return the last element in this list
     */
    @Override
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.element;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index the index of the element to be removed
     */
    @Override
    public void remove(int index) {
        MyNode nodeToRemove = getNode(index);
        removeNode(nodeToRemove);
    }

    /**
     * Helper method to remove a node from the list.
     * @param node the node to remove
     */
    private void removeNode(MyNode node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        node.prev = node.next = null; // Help with garbage collection
        size--;
    }

    /**
     * Removes the first element from this list.
     */
    @Override
    public void removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        removeNode(head);
    }

    /**
     * Removes the last element from this list.
     */
    @Override
    public void removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        removeNode(tail);
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

        // Convert to array, sort, and then back to linked list
        Object[] array = toArray();
        
        // Insertion sort
        for (int i = 1; i < array.length; i++) {
            Comparable<T> key = (Comparable<T>) array[i];
            int j = i - 1;
            
            while (j >= 0 && key.compareTo((T) array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        
        // Copy back to linked list
        MyNode current = head;
        for (Object o : array) {
            current.element = (T) o;
            current = current.next;
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     * @param object element to search for
     * @return the index of the first occurrence of the specified element, or -1 if not found
     */
    @Override
    public int indexOf(Object object) {
        int index = 0;
        MyNode current = head;
        
        if (object == null) {
            while (current != null) {
                if (current.element == null) {
                    return index;
                }
                current = current.next;
                index++;
            }
        } else {
            while (current != null) {
                if (object.equals(current.element)) {
                    return index;
                }
                current = current.next;
                index++;
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
        int index = size - 1;
        MyNode current = tail;
        
        if (object == null) {
            while (current != null) {
                if (current.element == null) {
                    return index;
                }
                current = current.prev;
                index--;
            }
        } else {
            while (current != null) {
                if (object.equals(current.element)) {
                    return index;
                }
                current = current.prev;
                index--;
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
        MyNode current = head;
        for (int i = 0; i < size; i++) {
            result[i] = current.element;
            current = current.next;
        }
        return result;
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
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
            private MyNode current = head;
            private MyNode lastReturned = null;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = current;
                current = current.next;
                return lastReturned.element;
            }

            @Override
            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }
                
                MyLinkedList.this.removeNode(lastReturned);
                lastReturned = null;
            }
        };
    }
}