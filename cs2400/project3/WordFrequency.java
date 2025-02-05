//
//  Name: Choi, James
//  Project: 3
//  Due: 11/15
//  Course: cs-2400-01-f24
//
//  Description: A program that counts frequency of all words that appear in a text file using hashed dictionaries.

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class WordFrequency {
    public static void main(String[] args) {
        // DECLARATIONS
        final int SIZE1=1361; final int SIZE2=2003; final int SIZE3=3001;
        DictionaryInterface<String, WordCount> dict1=new HashedDictionary<String,WordCount>(SIZE1);
        DictionaryInterface<String, WordCount> dict2=new HashedDictionary<String,WordCount>(SIZE2);
        DictionaryInterface<String, WordCount> dict3=new HashedDictionary<String,WordCount>(SIZE3);
        int u=addWords(args[0],dict1); addWords(args[0],dict2); addWords(args[0],dict3);
        Iterator<String> keyIt1=dict1.getKeyIterator();

        // print results
        System.out.println("Word Frequency by J. Choi\n");
        System.out.println("Count Word");
        System.out.println("----- ------------------");
        while (keyIt1.hasNext()) {
            String key=keyIt1.next();
            WordCount c=dict1.getValue(key);
            int wordCount=c.count;
            System.out.printf("%5d %s\n", wordCount, key);
        }

        System.out.println("\nUnique word count = " + u);
        System.out.println("\nTable\nLength Collisions");
        System.out.printf("%6d %d\n", SIZE1, ((HashedDictionary<String, WordCount>) dict1).getCollisionCount());
        System.out.printf("%6d %d\n", SIZE2, ((HashedDictionary<String, WordCount>) dict2).getCollisionCount());
        System.out.printf("%6d %d\n", SIZE3, ((HashedDictionary<String, WordCount>) dict3).getCollisionCount());

    }

    private static boolean isInteger(String s) {
        try {Integer.parseInt(s); return true;} catch (NumberFormatException e) {return false;}
    }

    // adds words to dictionary and returns unique word count
    private static int addWords(String f, DictionaryInterface<String, WordCount> dict) {
        // f is file name
        int uniqueWordCount=0;
        try {
            File file=new File(f);
            Scanner scanner = new Scanner(file);
            String str;
            while (scanner.hasNext()) {
                str=scanner.next();
                if (!isInteger(str)) {
                    str=str.toLowerCase();
                    WordCount count=dict.getValue(str);
                    if (count==null) {
                        dict.add(str, new WordCount()); uniqueWordCount++;
                    }
                    else {
                        count.increment();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No such file");
        }
        return uniqueWordCount;
    }

}

class WordCount {
    int count;
    public WordCount() {
        count=1;
    }
    public void increment() {
        count++;

    }
}
