public class CompareLists {
    private static final int MAX_ITEMS = 3;
    private static String[] listItems = new String[MAX_ITEMS];
    private static boolean equalStrings = false;
    private static boolean orderedStrings = false;

    public static void main(String[] args) {
        move("AA BB CC");
        checkList();
        move("AA AA AA");
        checkList();
        move("AA CC BB");
        checkList();
        move("AA ACBBB CC");
        checkList();
        move("AA");
        checkList();
    }

    private static void move(String value) {
        String[] parts = value.split(" ");
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (i < parts.length) {
                listItems[i] = parts[i];
            } else {
                listItems[i] = "";
            }
        }
    }

    private static void checkList() {
        System.out.println("list:");
        equalStrings = true;
        orderedStrings = true;

        for (int i = 0; i < MAX_ITEMS; i++) {
            if (!listItems[i].equals("")) {
                System.out.print(trim(listItems[i]) + " ");
                if (i < MAX_ITEMS - 1 && !listItems[i + 1].equals("")) {
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

    private static String trim(String s) {
        return s.trim();
    }
}