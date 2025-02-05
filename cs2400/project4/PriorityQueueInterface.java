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
public interface PriorityQueueInterface<T> {
    public void add(T newEntry);
    public T remove();
    public T peek();
    public boolean isEmpty();
    public int getSize();
    public void clear();
}
