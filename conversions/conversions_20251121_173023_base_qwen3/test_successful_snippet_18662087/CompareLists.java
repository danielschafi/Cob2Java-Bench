import java.util.*;

public class CompareLists {
    private static final int MAX_ITEMS = 3;
    
    public static void main(String[] args) {
        String[] theList = new String[MAX_ITEMS];
        
        // Test case 1
        theList[0] = "AA"; theList[1] = "BB"; theList[2] = "CC";
        checkList(theList);
        
        // Test case 2
        theList[0] = "AA"; theList[1] = "AA"; theList[2] = "AA";
        checkList(theList);
        
        // Test case 3
        theList[0] = "AA"; theList[1] = "CC"; theList[2] = "BB";
        checkList(theList);
        
        // Test case 4
        theList[0] = "AA"; theList[1] = "ACBBB"; theList[2] = "CC";
        checkList(theList);
        
        // Test case 5
        theList[0] = "AA"; theList[1] = ""; theList[2] = "";
        checkList(theList);
    }
    
    public static void checkList(String[] theList) {
        System.out.println("list:");
        
        boolean equalStrings = true;
        boolean orderedStrings = true;
        
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (theList[i] != null && !theList[i].equals("")) {
                System.out.print(theList[i] + " ");
                
                if (i < MAX_ITEMS - 1 && theList[i + 1] != null && !theList[i + 1].equals("")) {
                    if (!theList[i + 1].equals(theList[i])) {
                        equalStrings = false;
                    }
                    
                    if (theList[i + 1].compareTo(theList[i]) <= 0) {
                        orderedStrings = false;
                    }
                }
            }
        }
        
        System.out.println();
        
        if (equalStrings) {
            System.out.println("... is lexically equal");
        } else {
            System.out.println("... is not lexically equal");
        }
        
        if (orderedStrings) {
            System.out.println("... is in strict ascending order");
        } else {
            System.out.println("... is not in strict ascending order");
        }
        
        System.out.println();
    }
}