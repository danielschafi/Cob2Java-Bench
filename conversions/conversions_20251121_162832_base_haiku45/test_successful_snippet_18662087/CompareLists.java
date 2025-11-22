public class CompareLists {
    private static final int MAX_ITEMS = 3;
    private static String[] listItems = new String[MAX_ITEMS];
    private static boolean equalStrings;
    private static boolean orderedStrings;

    public static void main(String[] args) {
        initializeList();
        setList("AA", "BB", "CC");
        checkList();
        
        initializeList();
        setList("AA", "AA", "AA");
        checkList();
        
        initializeList();
        setList("AA", "CC", "BB");
        checkList();
        
        initializeList();
        setList("AA", "ACBBB", "CC");
        checkList();
        
        initializeList();
        setList("AA", "", "");
        checkList();
    }

    private static void initializeList() {
        for (int i = 0; i < MAX_ITEMS; i++) {
            listItems[i] = "";
        }
    }

    private static void setList(String... items) {
        for (int i = 0; i < items.length && i < MAX_ITEMS; i++) {
            listItems[i] = items[i];
        }
    }

    private static void checkList() {
        System.out.print("list:");
        equalStrings = true;
        orderedStrings = true;
        
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (!listItems[i].isEmpty()) {
                System.out.print(listItems[i] + " ");
                
                if (i < MAX_ITEMS - 1 && !listItems[i + 1].isEmpty()) {
                    if (!listItems[i + 1].equals(listItems[i])) {
                        equalStrings = false;
                    }
                    if (listItems[i + 1].compareTo(listItems[i]) <= 0) {
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