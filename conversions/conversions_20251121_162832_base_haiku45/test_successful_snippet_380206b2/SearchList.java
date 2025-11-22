public class SearchList {
    private static final int HAYSTACK_SIZE = 10;
    private static final String[] haystack = {
        "Zig    ",
        "Zag    ",
        "Wally  ",
        "Ronald ",
        "Bush   ",
        "Krusty ",
        "Charlie",
        "Bush   ",
        "Boz    ",
        "Zag    "
    };
    
    private static String needle;
    private static int haystackIndex;
    
    public static void main(String[] args) {
        needle = "Bush   ";
        findNeedle();
        
        needle = "Goofy  ";
        findNeedle();
        
        needle = "Bush   ";
        findLastOfNeedle();
    }
    
    private static void findNeedle() {
        boolean found = false;
        for (int i = 0; i < HAYSTACK_SIZE; i++) {
            if (haystack[i].equals(needle)) {
                haystackIndex = i + 1;
                System.out.println("Found " + needle.trim() + " at " + haystackIndex + ".");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(needle.trim() + " not found.");
        }
    }
    
    private static void findLastOfNeedle() {
        haystackIndex = 0;
        for (int i = HAYSTACK_SIZE; i >= 1; i--) {
            if (haystack[i - 1].equals(needle)) {
                haystackIndex = i;
                break;
            }
        }
        
        if (haystackIndex == 0) {
            System.out.println(needle.trim() + " not found.");
        } else {
            System.out.println("Found last of " + needle.trim() + " at " + haystackIndex + ".");
        }
    }
}