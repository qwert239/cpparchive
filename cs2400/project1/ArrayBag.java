//
// Name: Choi, James
// Homework: #1
// Due: 9/20/24
// Course: cs-2400—01-f24
//
// Description:
// client application that reads strings and outputs
//

import javax.annotation.processing.SupportedAnnotationTypes;
import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T> {
    private int size = 25;
    

    @SuppressWarnings("unchecked")
    private T[] bag = (T[])new Object[size];
    
    private int numberOfEntries = 0;
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }
    @Override
    public boolean isEmpty(){
return numberOfEntries == 0;
}
    @Override
    public boolean add(T newEntry) {
        if (numberOfEntries < size) {
            bag[numberOfEntries++] = newEntry;
            return true;
        }
        else {
            doubleCapacity();
            bag[numberOfEntries++] = newEntry;
            return true;
        }
}
    @Override public T remove() {
        if (numberOfEntries > 0) {
            T result = bag[numberOfEntries - 1];
        bag[numberOfEntries-- - 1] = null;
        return result;
    } 
    else return null;
}
    @Override public boolean remove(T anEntry) {
        if (numberOfEntries>0) {
            for (int i=0; i<numberOfEntries; i++) {
                if (bag[i] == anEntry) {
                    bag[i] = null;

                    for (int j=i;j<numberOfEntries;j++){
                        bag[j]=bag[j+1];
                    }
                    bag[numberOfEntries-- - 1]=null;


                    return true;
                }
            }
        }
        return false;
    }
    @Override public void clear() {
        while (numberOfEntries != 0) {
            bag[--numberOfEntries] = null;
        }

}
    @Override public int getFrequencyOf(T anEntry){
        int c=0;
        for (int i=0;i<numberOfEntries;i++){
            if (bag[i]==anEntry) {
                c++;
            }

        }
        return c;

}
    @Override public boolean contains(T anEntry){
        for (int i=0;i<numberOfEntries;i++) {
            if (bag[i].equals(anEntry)) return true;
        }
        return false;
    }
    @Override public T[] toArray(){
        return Arrays.copyOf(bag,bag.length);
    }

    private void doubleCapacity() {
        size = 2*bag.length;
        bag = Arrays.copyOf(bag,size);
    }
}
