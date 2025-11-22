public class SearchList {
    private static final int HAYSTACK_SIZE = 10;
    private static final String[] HAYSTACK_DATA = {
        "Zig", "Zag", "Wally", "Ronald", "Bush",
        "Krusty", "Charlie", "Bush", "Boz", "Zag"
    };
    
    public static void main(String[] args) {
        String needle = "Bush";
        findNeedle(needle);
        
        needle = "Goofy";
        findNeedle(needle);
        
        needle = "Bush";
        findLastOfNeedle(needle);
    }
    
    public static void findNeedle(String needle) {
        for (int haystackIndex = 0; haystackIndex < HAYSTACK_SIZE; haystackIndex++) {
            if (HAYSTACK_DATA[haystackIndex].equals(needle)) {
                System.out.println("Found " + needle + " at " + (haystackIndex + 1) + ".");
                return;
            }
        }
        System.out.println(needle + " not found.");
    }
    
    public static void findLastOfNeedle(String needle) {
        for (int haystackIndex = HAYSTACK_SIZE - 1; haystackIndex >= 0; haystackIndex--) {
            if (HAYSTACK_DATA[haystackIndex].equals(needle)) {
                System.out.println("Found last of " + needle + " at " + (haystackIndex + 1) + ".");
                return;
            }
        }
        System.out.println(needle + " not found.");
    }
}