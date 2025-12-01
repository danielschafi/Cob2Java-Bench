public class CompareLists {
    private static final int MAX_ITEMS = 3;
    private static int i;
    private static String[] listItems = new String[MAX_ITEMS];
    private static boolean equalStrings;
    private static boolean orderedStrings;

    public static void main(String[] args) {
        moveToList("AA BB CC");
        checkList();
        moveToList("AA AA AA");
        checkList();
        moveToList("AA CC BB");
        checkList();
        moveToList("AA ACBBB CC");
        checkList();
        moveToList("AA");
        checkList();
    }

    private static void moveToList(String input) {
        String[] parts = input.split(" ");
        for (int j = 0; j < MAX_ITEMS; j++) {
            if (j < parts.length) {
                listItems[j] = parts[j];
            } else {
                listItems[j] = "";
            }
        }
    }

    private static void checkList() {
        System.out.print("list:");
        equalStrings = true;
        orderedStrings = true;

        for (i = 0; i < MAX_ITEMS; i++) {
            if (!listItems[i].isEmpty() && !listItems[i].equals("   ")) {
                System.out.print(listItems[i].trim() + " ");

                if (i < MAX_ITEMS - 1 && !listItems[i + 1].isEmpty() && !listItems[i + 1].equals("   ")) {
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