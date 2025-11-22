public class testantlr359 {
    private static int COUNTER = 0;
    
    public static void main(String[] args) {
        openFiles();
    }
    
    public static void openFiles() {
        COUNTER += 1;
        COUNTER += 100000;
        System.out.println("DISPLAY " + COUNTER);
    }
}