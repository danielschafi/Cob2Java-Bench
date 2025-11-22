import java.util.Arrays;

public class SearchList {
    private static final int HAYSTACK_SIZE = 10;
    private static final String[] haystack = {
        "Zig", "Zag", "Wally", "Ronald", "Bush", "Krusty", "Charlie", "Bush", "Boz", "Zag"
    };
    private static String needle;

    public static void main(String[] args) {
        needle = "Bush";
        findNeedle();

        needle = "Goofy";
        findNeedle();

        needle = "Bush";
        findLastOfNeedle();
    }

    private static void findNeedle() {
        for (int haystackIndex = 0; haystackIndex < HAYSTACK_SIZE; haystackIndex++) {
            if (haystack[haystackIndex].equals(needle)) {
                System.out.println("Found " + needle + " at " + haystackIndex + ".");
                return;
            }
        }
        System.out.println(needle + " not found.");
    }

    private static void findLastOfNeedle() {
        for (int haystackIndex = HAYSTACK_SIZE - 1; haystackIndex >= 0; haystackIndex--) {
            if (haystack[haystackIndex].equals(needle)) {
                System.out.println("Found last of " + needle + " at " + haystackIndex + ".");
                return;
            }
        }
        System.out.println(needle + " not found.");
    }
}