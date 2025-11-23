public class Testantlr259 {
    private static int counter = 0;

    public static void main(String[] args) {
        add(1);
        add(100000060);
        counter = counter + 100000060;
        System.out.println("COUNTER = " + counter);
    }

    public static void add(int value) {
        counter += value;
    }
}