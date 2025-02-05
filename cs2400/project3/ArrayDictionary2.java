import java.util.Arrays;
import java.util.Iterator;
public class ArrayDictionary2<K, V> implements DictionaryInterface<K, V>{
    private Entry<K, V>[] dict;
    private int numOfEntries;
    private final static int DEFAULT_MAX_LENGTH = 10;
    private final int MAX_LENGTH_MULTIPLE = 2;

    public ArrayDictionary2(){
        this(DEFAULT_MAX_LENGTH);
    }
    
    public ArrayDictionary2(int length){
        @SuppressWarnings("unchecked")
        Entry<K, V>[] tempDictionary = (Entry<K, V>[])new Entry[length];
        dict = tempDictionary;
        numOfEntries = 0;
    }

    /** 
    * Adds a new entry to this dictionary. If the given search key already
    * exists in the dictionary, replaces the corresponding value.
    * @param key An object search key of the new entry.
    * @param value An object associated with the search key.
    * @return Either null if the new entry was added to the dictionary
    * or the value that was associated with key if that value
    * was replaced. 
    */
    public V add(K key, V value){
        if(numOfEntries >= dict.length){
            resize();
        }

        Entry<K, V> newEntry = new Entry<K,V>(key, value);
        if(isEmpty()){
            dict[numOfEntries] = newEntry;
            numOfEntries++;
        }
        else{
            int i = 0;
            while(dict[i] != null){
                if(key == dict[i].getKey()){
                    dict[i] = newEntry;
                    return value;
                }
                i++;
            }
            dict[numOfEntries] = newEntry;
            numOfEntries++;
        }
        return null;
    }

    /** 
     * Removes a specific entry from this dictionary.
     * @param key An object search key of the entry to be removed.
     * @return Either the value that was associated with the search key
     * or null if no such object exists. 
     */
    public V remove(K key){
        if(isEmpty()){
            System.out.println("No Entries in the Dictionary");
            return null;
        }
        else{
            V result;
            for(int i = 0; i < numOfEntries; i++){
                if(dict[i].getKey().equals(key)){
                    result = dict[i].getValue();
                    dict[i] = dict[numOfEntries-1];
                    dict[numOfEntries-1] = null;
                    numOfEntries--;
                    return result;
                }
            }
            return null;
        }
    }

    /**
     * Retrieves from this dictionary the value associated with a given
     * search key.
     * @param key An object search key of the entry to be retrieved. 
     * @return Either the value that is associated with the search key
     * or null if no such object exists. 
     */
    public V getValue(K key){
        int i = 0;
            while(dict[i] != null){
                if(key == dict[i].getKey()){
                    return dict[i].getValue();
                }
                i++;
            }
        return null;
    }

    /** 
     * Sees whether a specific entry is in this dictionary.
     * @param key An object search key of the desired entry.
     * @return True if key is associated with an entry in the dictionary. 
     */
    public boolean contains(K key){
        int i = 0;
            while(dict[i] != null){
                if(key == dict[i].getKey()){
                    return true;
                }
                i++;
            }
        return false;
    }

    /** 
     * Creates an iterator that traverses all search keys in this dictionary.
     * @return An iterator that provides sequential access to the search
     * keys in the dictionary. 
     */
    public ArrayKeyIterator getKeyIterator(){
        return new ArrayKeyIterator(dict);
    }

    /** 
     * Creates an iterator that traverses all values in this dictionary.
     * @return An iterator that provides sequential access to the values
     * in this dictionary. 
     */
    public ArrayValueIterator getValueIterator(){
        return new ArrayValueIterator(dict);
    }

    /** 
     * Sees whether this dictionary is empty.
     * @return True if the dictionary is empty.
     */
    public boolean isEmpty(){
        return numOfEntries == 0;
    }

    /** 
     * Gets the size of this dictionary.
     * @return The number of entries (key-value pairs) currently
     * in the dictionary. 
     */
    public int getSize(){
        return numOfEntries;
    }

    /** 
     * Removes all entries from this dictionary. 
     */
    public void clear(){
        @SuppressWarnings("unchecked")
        Entry<K, V>[] tempDictionary = (Entry<K, V>[])new Entry[dict.length];
        dict = tempDictionary;
    }

    private void resize(){
        dict = Arrays.copyOf(dict, dict.length * MAX_LENGTH_MULTIPLE);
    }
    
    @SuppressWarnings("hiding")
    private class Entry<K, V>{
        private K key;
        private V value;

        private Entry(K newKey, V newValue){
            key = newKey;
            value = newValue;
            
        }
        private K getKey(){
            return key;
        }

        private V getValue(){
            return value;
        } 

        @SuppressWarnings("unused")
        private void setValue(V dataValue){
            value = dataValue;
        } 
    }
    

    public class ArrayKeyIterator implements Iterator<K>{
        private Entry<K, V>[] iterDict;
        private int currentIndex;

        public ArrayKeyIterator(Entry<K, V>[] dictIter){
            iterDict = dictIter;
            currentIndex = -1;
        }

        @Override
        public boolean hasNext() {
            try{
                return iterDict[currentIndex+1] != null;
            }
            catch(ArrayIndexOutOfBoundsException e){
                return false;
            }
        }

        @Override
        public K next() {
            currentIndex++;
            if(currentIndex >= iterDict.length){
                return null;
            }
            return iterDict[currentIndex].getKey();
        }  
    }

    public class ArrayValueIterator implements Iterator<V>{
        private Entry<K, V>[] iterDict;
        private int currentIndex;

        public ArrayValueIterator(Entry<K, V>[] dictIter){
            iterDict = dictIter;
            currentIndex = -1;
        }

        @Override
        public boolean hasNext() {
            try{
                return iterDict[currentIndex+1] != null;
            }
            catch(ArrayIndexOutOfBoundsException e){
                return false;
            }
        }

        @Override
        public V next() {
            currentIndex++;
            if(currentIndex >= iterDict.length){
                return null;
            }
            return iterDict[currentIndex].getValue();
        }  
    }
}
