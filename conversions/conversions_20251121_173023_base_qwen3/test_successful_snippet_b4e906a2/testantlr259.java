public class testantlr259 {
    private static int COUNTER = 0;
    
    public static void main(String[] args) {
        OPEN_FILES();
    }
    
    public static void OPEN_FILES() {
        COUNTER = COUNTER + 1;
        COUNTER = COUNTER + 100000;
        System.out.println(COUNTER);
    }
}