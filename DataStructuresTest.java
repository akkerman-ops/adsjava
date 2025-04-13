public class DataStructuresTest {
    public static void main(String[] args) {
        testMyArrayList();
        testMyLinkedList();
        testMyStack();
        testMyQueue();
        testMyMinHeap();
    }
    
    // Test MyArrayList
    private static void testMyArrayList() {
        System.out.println("=== Testing MyArrayList ===");
        MyArrayList<Integer> list = new MyArrayList<>();
        
        // Test add
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        printList("After adding elements", list);
        
        // Test add at index
        list.add(1, 10);
        printList("After adding 10 at index 1", list);
        
        // Test addFirst and addLast
        list.addFirst(99);
        list.addLast(100);
        printList("After adding 99 at first and 100 at last", list);
        
        // Test get, getFirst, getLast
        System.out.println("Element at index 2: " + list.get(2));
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());
        
        // Test set
        list.set(3, 77);
        printList("After setting index 3 to 77", list);
        
        // Test remove
        list.remove(2);
        printList("After removing element at index 2", list);
        
        // Test removeFirst and removeLast
        list.removeFirst();
        list.removeLast();
        printList("After removing first and last elements", list);
        
        // Test indexOf and lastIndexOf
        list.add(5);
        list.add(5);
        printList("After adding two 5s", list);
        System.out.println("Index of 5: " + list.indexOf(5));
        System.out.println("Last index of 5: " + list.lastIndexOf(5));
        
        // Test exists
        System.out.println("Exists 77: " + list.exists(77));
        System.out.println("Exists 999: " + list.exists(999));
        
        // Test sort
        list.sort();
        printList("After sorting", list);
        
        // Test clear and size
        System.out.println("Size before clear: " + list.size());
        list.clear();
        System.out.println("Size after clear: " + list.size());
        printList("After clearing", list);
    }
    
    // Test MyLinkedList
    private static void testMyLinkedList() {
        System.out.println("\n=== Testing MyLinkedList ===");
        MyLinkedList<Integer> list = new MyLinkedList<>();
        
        // Test add
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);
        printList("After adding elements", list);
        
        // Test add at index
        list.add(1, 10);
        printList("After adding 10 at index 1", list);
        
        // Test addFirst and addLast
        list.addFirst(99);
        list.addLast(100);
        printList("After adding 99 at first and 100 at last", list);
        
        // Test get, getFirst, getLast
        System.out.println("Element at index 2: " + list.get(2));
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());
        
        // Test set
        list.set(3, 77);
        printList("After setting index 3 to 77", list);
        
        // Test remove
        list.remove(2);
        printList("After removing element at index 2", list);
        
        // Test removeFirst and removeLast
        list.removeFirst();
        list.removeLast();
        printList("After removing first and last elements", list);
        
        // Test indexOf and lastIndexOf
        list.add(5);
        list.add(5);
        printList("After adding two 5s", list);
        System.out.println("Index of 5: " + list.indexOf(5));
        System.out.println("Last index of 5: " + list.lastIndexOf(5));
        
        // Test exists
        System.out.println("Exists 77: " + list.exists(77));
        System.out.println("Exists 999: " + list.exists(999));
        
        // Test sort
        list.sort();
        printList("After sorting", list);
        
        // Test clear and size
        System.out.println("Size before clear: " + list.size());
        list.clear();
        System.out.println("Size after clear: " + list.size());
        printList("After clearing", list);
    }
    
    // Test MyStack
    private static void testMyStack() {
        System.out.println("\n=== Testing MyStack ===");
        MyStack<Integer> stack = new MyStack<>();
        
        // Test push
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        printStack("After pushing elements", stack);
        
        // Test peek
        System.out.println("Top element: " + stack.peek());
        
        // Test pop
        System.out.println("Popped element: " + stack.pop());
        printStack("After popping", stack);
        
        // Test isEmpty and size
        System.out.println("Is empty: " + stack.isEmpty());
        System.out.println("Size: " + stack.size());
        
        // Test clear
        stack.clear();
        System.out.println("After clear - Is empty: " + stack.isEmpty());
        System.out.println("After clear - Size: " + stack.size());
    }
    
    // Test MyQueue
    private static void testMyQueue() {
        System.out.println("\n=== Testing MyQueue ===");
        MyQueue<Integer> queue = new MyQueue<>();
        
        // Test enqueue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        printQueue("After enqueuing elements", queue);
        
        // Test peek
        System.out.println("Front element: " + queue.peek());
        
        // Test dequeue
        System.out.println("Dequeued element: " + queue.dequeue());
        printQueue("After dequeuing", queue);
        
        // Test isEmpty and size
        System.out.println("Is empty: " + queue.isEmpty());
        System.out.println("Size: " + queue.size());
        
        // Test clear
        queue.clear();
        System.out.println("After clear - Is empty: " + queue.isEmpty());
        System.out.println("After clear - Size: " + queue.size());
    }
    
    // Test MyMinHeap
    private static void testMyMinHeap() {
        System.out.println("\n=== Testing MyMinHeap ===");
        MyMinHeap<Integer> heap = new MyMinHeap<>();
        
        // Test insert
        heap.insert(30);
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(15);
        
        // Test peek
        System.out.println("Minimum element: " + heap.peek());
        
        // Test extractMin
        System.out.print("Elements extracted in order: ");
        while (!heap.isEmpty()) {
            System.out.print(heap.extractMin() + " ");
        }
        System.out.println();
        
        // Test isEmpty and size
        System.out.println("Is empty: " + heap.isEmpty());
        
        // Insert more elements
        heap.insert(8);
        heap.insert(12);
        heap.insert(3);
        heap.insert(17);
        System.out.println("Size after inserting more elements: " + heap.size());
        
        // Test clear
        heap.clear();
        System.out.println("Is empty after clear: " + heap.isEmpty());
    }
    
    // Helper method to print a list
    private static <T> void printList(String message, MyList<T> list) {
        System.out.print(message + ": [");
        boolean first = true;
        for (T item : list) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(item);
            first = false;
        }
        System.out.println("]");
    }
    
    // Helper method to print a stack
    private static <T> void printStack(String message, MyStack<T> stack) {
        System.out.print(message + " (top -> bottom): [");
        boolean first = true;
        for (T item : stack) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(item);
            first = false;
        }
        System.out.println("]");
    }
    
    // Helper method to print a queue
    private static <T> void printQueue(String message, MyQueue<T> queue) {
        System.out.print(message + " (front -> rear): [");
        boolean first = true;
        for (T item : queue) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(item);
            first = false;
        }
        System.out.println("]");
    }
}