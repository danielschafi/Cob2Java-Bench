import java.util.Arrays;

public class CompareLists {
    private static final int MAX_ITEMS = 3;
    private static String[] theList = new String[MAX_ITEMS];
    private static boolean equalStrings;
    private static boolean orderedStrings;

    public static void main(String[] args) {
        theList = new String[]{"AA", "BB", "CC"};
        checkList();
        theList = new String[]{"AA", "AA", "AA"};
        checkList();
        theList = new String[]{"AA", "CC", "BB"};
        checkList();
        theList = new String[]{"AA", "ACBBB", "CC"};
        checkList();
        theList = new String[]{"AA", null, null};
        checkList();
    }

    private static void checkList() {
        System.out.print("list:");
        equalStrings = true;
        orderedStrings = true;
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (theList[i] != null && !theList[i].trim().isEmpty()) {
                System.out.print(theList[i].trim() + " ");
                if (i < MAX_ITEMS - 1 && theList[i + 1] != null && !theList[i + 1].trim().isEmpty()) {
                    if (!theList[i + 1].trim().equals(theList[i].trim())) {
                        equalStrings = false;
                    }
                    if (theList[i + 1].trim().compareTo(theList[i].trim()) <= 0) {
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