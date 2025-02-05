import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class JavaKeywords {
    public static void main(String[] args) {
        BagInterface<String> bag = new ArrayBag<>();
        try {
            //Using Scanner to read file
            Scanner keywordsFile = new Scanner(new File("javakeywords.txt"));
            while (keywordsFile.hasNext()) {
                bag.add(keywordsFile.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Java Keywords by J. Choi\n");
        System.out.printf("%d Java keywords loaded.\n\n", bag.getCurrentSize());

        for (int i=0;i<args.length;i++) {
            if (bag.contains(args[i])) System.out.println(args[i]+" is a keyword");
            else System.out.println(args[i]+" is not a keyword");
        }
        System.out.println();

        //Tests (isEmpty, remove, clear, getFrequencyOf, toArray)
        BagInterface<String> testBag = new ArrayBag<>();
        System.out.println("TEST RESULTS BELOW");
        //isEmpty
        System.out.println("isEmpty test (should print true): " + testBag.isEmpty());

            //adding elements for test
        testBag.add("one"); testBag.add("two"); testBag.add("three"); testBag.add("four");
        testBag.add("one"); testBag.add("two");

        //getFrequencyOf
        System.out.println("getFrequencyOf test (should print 2 and 1): "+testBag.getFrequencyOf("two")+ " "+testBag.getFrequencyOf("four"));

        //toArray
        Object[] objArray = testBag.toArray();
        String[] toArrayTest = Arrays.copyOf(objArray, objArray.length, String[].class);
            //print results
        System.out.print("toArray test: ");
        for (int i=0;i<toArrayTest.length;i++){
            if (toArrayTest[i]==null) break;
            System.out.print(toArrayTest[i]+" ");
        }
        System.out.println();

        //remove
        testBag.remove(); testBag.remove("one");
        Object[] objArray2 = testBag.toArray();
        String[] removeTest = Arrays.copyOf(objArray2, objArray2.length, String[].class);
            //print results
        System.out.print("remove test (should remove one and two): ");
        for (int i=0;i<removeTest.length;i++){
            if (removeTest[i]==null) break;
            System.out.print(removeTest[i]+" ");
        }
        System.out.println();

        //clear
        testBag.clear();
        Object[] objArray3 = testBag.toArray();
        String[] clearTest = Arrays.copyOf(objArray3, objArray3.length, String[].class);
        int i=0;
            //print results
        System.out.print("clear test (should print nothing): ");
        while (clearTest[i]!=null) {
            System.out.print(clearTest[i++]);
        }
        System.out.println();


    }
}
