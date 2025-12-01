public class testantlr059 {
    private static int COUNTER = 0;

    public static void main(String[] args) {
        openFiles();
    }

    private static void openFiles() {
        COUNTER = COUNTER + 1;
        COUNTER = COUNTER + 10;
        System.out.println(COUNTER);
    }
}