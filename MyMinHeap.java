import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Min Heap implementation using MyArrayList as the underlying data structure.
 * ArrayList is chosen because a heap is typically implemented as an array for efficient
 * parent-child index calculations and access.
 */
public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;
    
    // Constructor
    public MyMinHeap() {
        heap = new MyArrayList<>();
    }
    
    /**
     * Inserts an item into the heap.
     * 
     * @param item the item to be inserted
     */
    public void insert(T item) {
        // Add the item at the end of the heap
        heap.add(item);
        
        // Fix the min heap property if it is violated
        siftUp(heap.size() - 1);
    }
    
    /**
     * Returns the minimum element without removing it.
     * 
     * @return the minimum element
     * @throws NoSuchElementException if the heap is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        
        return heap.get(0);
    }
    
    /**
     * Removes and returns the minimum element.
     * 
     * @return the minimum element
     * @throws NoSuchElementException if the heap is empty
     */
    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        
        // Store the minimum element (root)
        T min = heap.get(0);
        
        // Replace the root with the last element
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        
        // Fix the min heap property if it is violated
        if (!isEmpty()) {
            siftDown(0);
        }
        
        return min;
    }
    
    /**
     * Returns true if the heap is empty.
     * 
     * @return true if the heap contains no elements
     */
    public boolean isEmpty() {
        return heap.size() == 0;
    }
    
    /**
     * Returns the number of elements in the heap.
     * 
     * @return the number of elements in the heap
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * Removes all elements from the heap.
     */
    public void clear() {
        heap.clear();
    }
    
    /**
     * Sifts up the element at the given index to maintain the min heap property.
     * 
     * @param index the index of the element to sift up
     */
    private void siftUp(int index) {
        int parentIndex = getParentIndex(index);
        
        // While not at the root and the parent is greater than the current element
        while (index > 0 && heap.get(parentIndex).compareTo(heap.get(index)) > 0) {
            // Swap the elements
            swap(parentIndex, index);
            
            // Move up in the heap
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
    }
    
    /**
     * Sifts down the element at the given index to maintain the min heap property.
     * 
     * @param index the index of the element to sift down
     */
    private void siftDown(int index) {
        int minIndex = index;
        
        // Get the indices of the left and right children
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        
        // Check if the left child is smaller than the current element
        if (leftChildIndex < heap.size() && heap.get(leftChildIndex).compareTo(heap.get(minIndex)) < 0) {
            minIndex = leftChildIndex;
        }
        
        // Check if the right child is smaller than the current minimum
        if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(minIndex)) < 0) {
            minIndex = rightChildIndex;
        }
        
        // If a child is smaller, swap and continue sifting down
        if (index != minIndex) {
            swap(index, minIndex);
            siftDown(minIndex);
        }
    }
    
    /**
     * Swaps the elements at the given indices.
     * 
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    /**
     * Returns the index of the parent of the element at the given index.
     * 
     * @param index the index of the element
     * @return the index of the parent
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }
    
    /**
     * Returns the index of the left child of the element at the given index.
     * 
     * @param index the index of the element
     * @return the index of the left child
     */
    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }
    
    /**
     * Returns the index of the right child of the element at the given index.
     * 
     * @param index the index of the element
     * @return the index of the right child
     */
    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
    
    /**
     * Returns an iterator over the elements in the heap.
     * Note: Iterating through the heap does not guarantee the elements
     * will be returned in sorted order.
     * 
     * @return an iterator over the elements in the heap
     */
    public Iterator<T> iterator() {
        return heap.iterator();
    }
}