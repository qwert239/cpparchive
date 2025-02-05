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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class Vertex<T> implements VertexInterface<T>{
    private T label;
    private LinkedList<Edge> edgeList; // Edges to neighbors
    private boolean visited; // True if visited
    private VertexInterface<T> previousVertex; // On path to this vertex
    private double cost; // Of path to this vertex
    public Vertex(T vertexLabel)
    {
        label = vertexLabel;
        edgeList = new LinkedList<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    } // end connect

    public boolean connect(VertexInterface<T> endVertex, double edgeWeight){
        boolean result = false;
        if (!this.equals(endVertex)){ // Vertices are distinct
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;
            while (!duplicateEdge && neighbors.hasNext()){
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    duplicateEdge = true;
                }

            } // end while
            if (!duplicateEdge){
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if
        return result;
    } // end connect
    public boolean equals(Object other){
        boolean result;
        if ((other == null) || (getClass() != other.getClass())) {
            result = false;
        }
        else
        {
            // The cast is safe within this else clause
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        }
        return result;
    } // end equals
    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public void visit() {
        visited = true;
    }

    @Override
    public void unvisit() {
        visited = false;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public Iterator<Double> getWeightIterator() {
        return new WeightIterator();
    }

    @Override
    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) )
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while
        return result;
    } // end getUnvisitedNeighbor


    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        previousVertex = predecessor;
    }

    @Override
    public VertexInterface<T> getPredecessor() {
        return previousVertex;
    }

    @Override
    public boolean hasPredecessor() {
        return previousVertex != null;
    }

    @Override
    public void setCost(double newCost) {
        cost = newCost;

    }

    @Override
    public double getCost() {
        return cost;
    }

    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    }

    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new NeighborIterator();
    } //

    private class NeighborIterator implements Iterator<VertexInterface<T>> {
        private Iterator<Edge> edges;
        private NeighborIterator()
        {
            edges = edgeList.iterator();
        } // end default constructor

        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext()){
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else {
                throw new NoSuchElementException();
            }
            return nextNeighbor;

        } // end next
        public void remove(){
            throw new UnsupportedOperationException();
        } // end remove
    }

    private class WeightIterator implements Iterator<Double> {
        private Iterator<Edge> edges;

        private WeightIterator() {
            edges = edgeList.iterator();
        }

        @Override
        public boolean hasNext() {
            return edges.hasNext();
        }

        @Override
        public Double next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return edges.next().getWeight();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Edge
    {
        private VertexInterface<T> vertex; // Vertex at end of edge
        private double weight;
        private Edge(VertexInterface<T> endVertex, double edgeWeight){
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor
        private Edge(VertexInterface<T> endVertex){
            vertex = endVertex;
            weight = 0;
        } // end constructor
        private VertexInterface<T> getEndVertex(){
            return vertex;
        } // end getEndVertex
        private double getWeight(){
            return weight;
        } // end getWeight
    } // end Edge



}