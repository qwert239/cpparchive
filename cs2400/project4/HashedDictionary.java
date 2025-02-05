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

public class HashedDictionary<K,V> implements DictionaryInterface<K,V> {
    // VARIABLES
    private int size;
    private int collisionCount;
    private int numberOfEntries;
    private Entry<K,V>[] dictionary;

    // CONSTRUCTOR
    public HashedDictionary(int a) {
        size=a; collisionCount=0; numberOfEntries=0;
        dictionary=(Entry<K, V>[]) new Entry[size];
    }

    // METHODS
    @Override public V add(K key, V value){
        int index=key.hashCode()%size;
        while (index<0) index+=size;
        if (dictionary[index]==null) {
            dictionary[index]=new Entry<>(key,value);
        } else {
            while (dictionary[index]!=null) {
                index=(index+1) % size;
            }
            dictionary[index]=new Entry<>(key,value);
            collisionCount++;
        }
        numberOfEntries++;
        return null;
    }
    @Override public V remove(K key){
        throw new UnsupportedOperationException("Unsupported Operation");
    }
    @Override public V getValue(K key){
        int index = key.hashCode() % size;
        if (index < 0) index += size;

        for (int i = 0; i < size; i++) {
            if (dictionary[index] == null) return null; // Key not found
            if (key.equals(dictionary[index].getKey())) return dictionary[index].getValue();
            index = (index + 1) % size;
        }
        return null;
    }
    @Override public boolean contains(K key){
        throw new UnsupportedOperationException("Unsupported Operation");
    }
    @Override public Iterator<K> getKeyIterator(){
        return new KeyIterator();
    }
    @Override public Iterator<V> getValueIterator(){
        throw new UnsupportedOperationException("Unsupported Operation");
    }
    @Override public boolean isEmpty(){
        throw new UnsupportedOperationException("Unsupported Operation");
    }
    @Override public int getSize(){
        throw new UnsupportedOperationException("Unsupported Operation");
    }
    @Override public void clear(){
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    public int getCollisionCount(){
        return collisionCount;
    }
    private class Entry<K,V> {
        private K key;
        private V value;
        private Entry(K k, V v){
            key=k; value=v;
        }
        private K getKey() {
            return key;
        }
        private V getValue() {
            return value;
        }
        private void setValue(V v) {
            value=v;
        }
    }
    private class KeyIterator<K> implements Iterator<K> {
        private int currentIndex;
        private int numberLeft;
        private KeyIterator() {
            currentIndex=0; numberLeft=numberOfEntries;
        }
        @Override public boolean hasNext(){
            return numberLeft>0;
        }
        @Override public K next(){

            if (hasNext()) {
                do {
                    currentIndex = (currentIndex + 1) % size;
                } while (dictionary[currentIndex]==null);
            }
            numberLeft--;
            return (K)dictionary[currentIndex].getKey();
            //return key
        }
        @Override public void remove(){
            throw new UnsupportedOperationException("Unsupported Operation");
        }

    }
}
