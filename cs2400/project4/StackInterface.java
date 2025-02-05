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
import java.util.EmptyStackException;

/** An interface for the ADT stack. */
public interface StackInterface<T>
{
    /** Adds a new entry to the top of this stack.
     @param newEntry An object to be added to the stack. */
    public void push(T newEntry);
    /** Removes and returns this stack's top entry.
     @return The object at the top of the stack.
     @throws EmptyStackException if the stack is empty before the operation. */
    public T pop();
    /** Retrieves this stack's top entry.
     @return The object at the top of the stack.
     @throws EmptyStackException if the stack is empty. */
    public T peek();
    /** Detects whether this stack is empty.
     @return True if the stack is empty. */
    public boolean isEmpty();
    /** Removes all entries from this stack. */
    public void clear();
} // end StackInterface
