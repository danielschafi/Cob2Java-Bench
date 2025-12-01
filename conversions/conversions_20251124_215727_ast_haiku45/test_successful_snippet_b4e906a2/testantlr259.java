public class testantlr259 {
    private long COUNTER = 0;

    public static void main(String[] args) {
        testantlr259 program = new testantlr259();
        program.run();
    }

    public void run() {
        openFiles();
    }

    private void openFiles() {
        COUNTER += 1;
        COUNTER += 100000;
        System.out.println(COUNTER);
    }
}