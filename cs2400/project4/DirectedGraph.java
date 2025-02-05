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

public class DirectedGraph<T extends Comparable<T>> implements GraphInterface<T> {

    int numberOfVertices;
    int numberOfEdges;
    private DictionaryInterface<T,Vertex<T>> dictionary;
    public DirectedGraph() {
        dictionary = new HashedDictionary<>(137);
        numberOfVertices=0;
        numberOfEdges=0;
    }
    @Override
    public boolean addVertex(T vertexLabel) {
        dictionary.add(vertexLabel, new Vertex<>(vertexLabel));
        numberOfVertices++;
        return true;
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        Vertex<T> beginVertex=dictionary.getValue(begin);
        Vertex<T> endVertex=dictionary.getValue(end);
        beginVertex.connect(endVertex,edgeWeight);
        numberOfEdges++;
        return true;
    }

    @Override
    public boolean addEdge(T begin, T end) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    @Override
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public StackInterface<T> getTopologicalOrder() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public int getShortestPath(T begin, T end, StackInterface<T> path) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public double getCheapestPath(T begin, T end, StackInterface<T> path) {
        boolean done=false;
        PriorityQueueInterface<EntryPQ> priorityQueue=new MinHeapPriorityQueue<>();
        if (dictionary.getValue(begin)==null || dictionary.getValue(end)==null) {
            System.out.println("Airport code unknown");
            return -2;

        }
        VertexInterface<T> originVertex = dictionary.getValue(begin);
        VertexInterface<T> endVertex = dictionary.getValue(end);

        priorityQueue.add(new EntryPQ(originVertex,0,null));
        while (!done&&!priorityQueue.isEmpty()) {
            EntryPQ frontEntry=priorityQueue.remove();
            VertexInterface<T> frontVertex= frontEntry.vertex;
            if (!frontVertex.isVisited()) {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.cost);
                frontVertex.setPredecessor(frontEntry.predecessor);

                if (frontVertex.equals(endVertex)) done=true;
                else {
                    Iterator<VertexInterface<T>> neighborIterator=frontVertex.getNeighborIterator();
                    Iterator<Double> weightIterator=frontVertex.getWeightIterator();
                    while (neighborIterator.hasNext()) {
                        VertexInterface<T> nextNeighbor=neighborIterator.next();
                        if (!nextNeighbor.isVisited()) {
                            double weightOfEdgeToNeighbor= weightIterator.next();
                            double nextCost=weightOfEdgeToNeighbor+ frontVertex.getCost();
                            priorityQueue.add(new EntryPQ(nextNeighbor,nextCost,frontVertex));
                        }
                    }
                }
            }
        }
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.getPredecessor() != null) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }
        // unvisit all
        Iterator<T> it=dictionary.getKeyIterator();
        while (it.hasNext()) {
            dictionary.getValue(it.next()).unvisit();
        }
        if ((pathCost==0) && (begin!=end)) return -1;
        return pathCost;
    }
    private class EntryPQ implements Comparable<EntryPQ> {
        VertexInterface<T> vertex;
        double cost;
        VertexInterface<T> predecessor;
        private EntryPQ(VertexInterface<T> v, double c, VertexInterface<T> p) {
            vertex=v; cost=c; predecessor=p;
        }
        @Override
        public int compareTo(EntryPQ rhs) {
            return Double.compare(this.cost,rhs.cost);
        }
    }
}
