import java.util.Scanner;

public class SearchList {
    
    private static final int HAYSTACK_SIZE = 10;
    private static String[] haystack = new String[HAYSTACK_SIZE];
    private static int haystackIndex;
    private static String needle;
    
    public static void main(String[] args) {
        initializeHaystack();
        
        needle = "Bush";
        findNeedle();
        
        needle = "Goofy";
        findNeedle();
        
        needle = "Bush";
        findLastOfNeedle();
    }
    
    private static void initializeHaystack() {
        haystack[0] = "Zig";
        haystack[1] = "Zag";
        haystack[2] = "Wally";
        haystack[3] = "Ronald";
        haystack[4] = "Bush";
        haystack[5] = "Krusty";
        haystack[6] = "Charlie";
        haystack[7] = "Bush";
        haystack[8] = "Boz";
        haystack[9] = "Zag";
    }
    
    private static void findNeedle() {
        boolean found = false;
        for (haystackIndex = 0; haystackIndex < HAYSTACK_SIZE; haystackIndex++) {
            if (haystack[haystackIndex].equals(needle)) {
                System.out.println("Found " + needle + " at " + (haystackIndex + 1) + ".");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(needle + " not found.");
        }
    }
    
    private static void findLastOfNeedle() {
        for (haystackIndex = HAYSTACK_SIZE; haystackIndex > 0; haystackIndex--) {
            if (haystack[haystackIndex - 1].equals(needle)) {
                break;
            }
        }
        
        if (haystackIndex == 0) {
            System.out.println(needle + " not found.");
        } else {
            System.out.println("Found last of " + needle + " at " + haystackIndex + ".");
        }
    }
}