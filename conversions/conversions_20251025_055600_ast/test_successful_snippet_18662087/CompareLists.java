import java.util.Arrays;

public class CompareLists {
    private static final int MAX_ITEMS = 3;
    private int i;
    private String[] theList = new String[MAX_ITEMS];
    private boolean equalStrings;
    private boolean orderedStrings;

    public static void main(String[] args) {
        CompareLists compareLists = new CompareLists();
        compareLists.theList = Arrays.copyOf("AA BB CC".split(" "), MAX_ITEMS);
        compareLists.checkList();
        compareLists.theList = Arrays.copyOf("AA AA AA".split(" "), MAX_ITEMS);
        compareLists.checkList();
        compareLists.theList = Arrays.copyOf("AA CC BB".split(" "), MAX_ITEMS);
        compareLists.checkList();
        compareLists.theList = Arrays.copyOf("AA ACBBB CC".split(" "), MAX_ITEMS);
        compareLists.checkList();
        compareLists.theList = Arrays.copyOf("AA".split(" "), MAX_ITEMS);
        compareLists.checkList();
    }

    private void checkList() {
        System.out.print("list:");
        equalStrings = true;
        orderedStrings = true;
        for (i = 0; i < MAX_ITEMS; i++) {
            if (!theList[i].trim().isEmpty()) {
                System.out.print(theList[i].trim() + " ");
                if (i < MAX_ITEMS - 1 && !theList[i + 1].trim().isEmpty()) {
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