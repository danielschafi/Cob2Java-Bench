public class testantlr359 {
    private static int COUNTER = 0;

    public static void main(String[] args) {
        openFiles();
    }

    private static void openFiles() {
        COUNTER += 1;
        COUNTER += 1000;
        System.out.println(COUNTER);
    }
}