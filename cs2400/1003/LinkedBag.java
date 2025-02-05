public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag() {
        firstNode=null;
        numberOfEntries=0;
    }

    private class Node {
        private T data;
        private Node next;

        private Node (T dataPortion) {
            this(dataPortion, null);
        }
        private Node (T dataPortion, Node nextNode) {
            data=dataPortion;
            next=nextNode;
        }
    }
    public boolean add(T newEntry) {
        //add to beginning of chain:
         //created Node object where data==newEntry
                                //makes newNode reference firstNode, which will become
                                //second Node in next statement

        firstNode=new Node(newEntry,firstNode);      //newNode becomes first Node
        numberOfEntries++;
        return true;
    }

    public T remove() {
        throw new UnsupportedOperationException("");
    }

    public boolean remove(T anEntry) {
        boolean found=false;
        Node currentNode=firstNode;

        while (!found&&currentNode!=null){
            if(currentNode.data.equals(anEntry)) {
                found=true;
                currentNode=currentNode.next;
                numberOfEntries--;
            }
        }
        return found;
    }
    public int getFrequencyOf(T anEntry) {
        Node currentNode = firstNode;
        int count=0;
        while (currentNode!=null) {
               if (anEntry.equals(currentNode.data)) count++;
               currentNode=currentNode.next;
        }
        return count;
    }
    public int getCurrentSize(){throw new UnsupportedOperationException("");}
    public boolean isEmpty(){throw new UnsupportedOperationException("");}
    public void clear(){throw new UnsupportedOperationException("");}
    public boolean contains(T anEntry){throw new UnsupportedOperationException("");}
    public T[] toArray(){throw new UnsupportedOperationException("");}

}