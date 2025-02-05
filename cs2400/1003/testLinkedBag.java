
public class testLinkedBag {
    public static void main(String[] args) {
        BagInterface<String> test1 = new LinkedBag<>();
        test1.add("a"); test1.add("a"); test1.add("a");test1.add("a");test1.add("a");
        test1.add("b");test1.add("b");test1.add("b");test1.add("c");test1.add("c");
        System.out.println(test1.getFrequencyOf("a"));
    }
}
