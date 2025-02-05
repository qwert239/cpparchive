//
//  Name: Choi, James
//  Project: 2
//  Due: 10/21/2024
//  Course: cs-2400-01-f24
//
//  Description:
//      Uses ArrayStack which implements StackInterface to convert infix expressions to postfix expressions
//      and to evaluate the converted postfix expressions
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
