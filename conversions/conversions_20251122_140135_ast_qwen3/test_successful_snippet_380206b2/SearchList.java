public class SearchList {
    private static final String[] haystack = {
        "Zig", "Zag", "Wally", "Ronald", "Bush",
        "Krusty", "Charlie", "Bush", "Boz", "Zag"
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
    
    public static void findNeedle() {
        for (int haystackIndex = 0; haystackIndex < haystack.length; haystackIndex++) {
            if (haystack[haystackIndex].equals(needle)) {
                System.out.println("Found " + needle + " at " + (haystackIndex + 1) + ".");
                return;
            }
        }
        System.out.println(needle + " not found.");
    }
    
    public static void findLastOfNeedle() {
        int haystackIndex = haystack.length;
        while (haystackIndex > 0) {
            haystackIndex--;
            if (haystack[haystackIndex].equals(needle)) {
                System.out.println("Found last of " + needle + " at " + (haystackIndex + 1) + ".");
                return;
            }
        }
        System.out.println(needle + " not found.");
    }
}