import java.util.Arrays;

public class CompareLists {
    private static final int MAX_ITEMS = 3;
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
        Arrays.fill(listItems, "   ");
        int index = 0;
        int pos = 0;
        while (pos < input.length() && index < MAX_ITEMS) {
            if (pos + 3 <= input.length()) {
                listItems[index] = input.substring(pos, pos + 3);
                index++;
                pos += 3;
            } else {
                break;
            }
        }
    }

    private static void checkList() {
        System.out.println("list:");
        equalStrings = true;
        orderedStrings = true;

        for (int i = 0; i < MAX_ITEMS; i++) {
            if (!listItems[i].trim().isEmpty()) {
                System.out.print(listItems[i].trim() + " ");
                if (i < MAX_ITEMS - 1 && !listItems[i + 1].trim().isEmpty()) {
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