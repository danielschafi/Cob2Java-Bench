public class Testantlr159 {
    private static int counter = 0;
    
    public static void main(String[] args) {
        add(1);
        add(10);
        displayCounter();
        System.exit(0);
    }
    
    private static void add(int value) {
        counter += value;
    }
    
    private static void displayCounter() {
        System.out.println(counter);
    }
}