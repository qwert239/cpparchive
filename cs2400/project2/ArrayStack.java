import java.util.EmptyStackException;
import java.util.Arrays;
public class ArrayStack<T> implements StackInterface<T> {
    private T[] stack; //array stack
    private int topOfStack; // index of top of stack
    private static final int DEFAULT_CAPACITY=5;
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayStack(int capacity) {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[capacity];
        stack=tempStack;
        topOfStack=-1;
    }
    public void push(T newEntry) {
        if (fullCapacity()) doubleCapacity();
        stack[++topOfStack]=newEntry;
    }
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T a=stack[topOfStack];
        stack[topOfStack--]=null;
        return a;
    }
    public T peek(){
        if (isEmpty()) throw new EmptyStackException();
        return stack[topOfStack];
    }
    public boolean isEmpty() {
        return topOfStack==-1;
    }
    public void clear() {
        while (topOfStack!=-1) stack[topOfStack--]=null;
    }
    public boolean fullCapacity() {
        return topOfStack==stack.length-1;
    }
    public void doubleCapacity() {
        stack = Arrays.copyOf(stack,2*stack.length);
    }

}
