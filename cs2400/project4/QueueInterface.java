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
import java.util.NoSuchElementException;

/** An interface for the ADT queue. */
public interface QueueInterface<T>
{
    /** Adds a new entry to the back of this queue.
     @param newEntry An object to be added. */
    public void enqueue(T newEntry);
    /** Removes and returns the entry at the front of this queue.
     @return The object at the front of the queue.
     @throws NoSuchElementException if the queue is empty before the operation. */
    public T dequeue();
    /** Retrieves the entry at the front of this queue.
     @return The object at the front of the queue.
     @throws NoSuchElementException if the queue is empty. */
    public T getFront();
    /** Detects whether this queue is empty.
     @return True if the queue is empty, or false otherwise. */
    public boolean isEmpty();
    /** Removes all entries from this queue. */
    public void clear();
} // end QueueInterface
