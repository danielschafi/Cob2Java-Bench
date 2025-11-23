public class Testantlr359 {
    private static int counter = 0;

    public static void main(String[] args) {
        addOneToCounter();
        addTenThousandToCounter();
        displayCounter();
        System.exit(0);
    }

    private static void addOneToCounter() {
        counter += 1;
    }

    private static void addTenThousandToCounter() {
        counter += 10000;
    }

    private static void displayCounter() {
        System.out.println(counter);
    }
}