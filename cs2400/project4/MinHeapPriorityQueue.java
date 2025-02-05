//
// Name: James Choi
// Project: 4
// Due: 12/6/2024
// Course: cs-2400-01
//
// Description:
//      A program that uses the graph ADT to implement Dijkstra's algorithm for
//      finding the shortest route between two airports.
//
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MinHeapPriorityQueue<T extends Comparable<T>> implements PriorityQueueInterface<T> {

    private List<T> heap;

    public MinHeapPriorityQueue() {
        this.heap = new ArrayList<>();
    }

    /**
     * Adds a new entry to the priority queue.
     * @param newEntry The item to be added.
     */
    @Override
    public void add(T newEntry) {
        // Add the new entry to the end of the heap
        heap.add(newEntry);
        int index = heap.size() - 1;

        // Bubble up: If the new entry is smaller than its parent, swap them
        while (index > 0 && heap.get(index).compareTo(heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * Removes and returns the smallest item in the priority queue.
     * @return The smallest item from the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty.");
        }

        T min = heap.get(0);
        int lastIndex = heap.size() - 1;
        heap.set(0, heap.get(lastIndex));  // Move the last element to the root
        heap.remove(lastIndex);  // Remove the last element

        if (!isEmpty()) {
            heapifyDown(0);  // Fix the heap property
        }
        return min;
    }

    /**
     * Returns the smallest item in the priority queue without removing it.
     * @return The smallest item in the queue.
     * @throws NoSuchElementException if the queue is empty.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty.");
        }
        return heap.get(0);
    }

    /**
     * Checks if the priority queue is empty.
     * @return true if the queue is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Gets the number of items in the priority queue.
     * @return The number of items in the queue.
     */
    @Override
    public int getSize() {
        return heap.size();
    }

    /**
     * Clears all items from the priority queue.
     */
    @Override
    public void clear() {
        heap.clear();
    }

    /**
     * Helper method to swap two elements in the heap.
     * @param i First index.
     * @param j Second index.
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Maintains the min-heap property by moving an out-of-place node down the heap.
     * @param index The index from which to start heapifying down.
     */
    private void heapifyDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heap.size() && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }

        if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }
}