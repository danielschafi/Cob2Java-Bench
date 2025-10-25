import java.util.Scanner;

public class SearchExample {
    private static final int TABLE_SIZE = 3;
    private static final Item[] wsItemTable = new Item[TABLE_SIZE];
    private static final NoKeyItem[] wsNoKeyItemTable = new NoKeyItem[TABLE_SIZE];
    private static final Scanner scanner = new Scanner(System.in);

    static {
        setupTestData();
    }

    public static void main(String[] args) {
        int wsAcceptId1, wsAcceptId2, wsAcceptId3;

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.print("Enter id-1 to search for: ");
        wsAcceptId1 = scanner.nextInt();

        int idx = binarySearch(wsItemTable, wsAcceptId1, 0, TABLE_SIZE - 1, 1);
        if (idx != -1) {
            displayFoundItem(wsItemTable[idx]);
        } else {
            System.out.println("Item not found.");
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching again with all required ids matching.");

        System.out.print("Enter id-1 to search for: ");
        wsAcceptId1 = scanner.nextInt();

        System.out.print("Enter id-2 to search for: ");
        wsAcceptId2 = scanner.nextInt();

        System.out.print("Enter id-3 to search for: ");
        wsAcceptId3 = scanner.nextInt();

        idx = binarySearch(wsItemTable, wsAcceptId1, wsAcceptId2, wsAcceptId3);
        if (idx != -1) {
            displayFoundItem(wsItemTable[idx]);
        } else {
            System.out.println("Item not found.");
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching not keyed table using sequential search.");
        System.out.print("Enter id: ");
        wsAcceptId1 = scanner.nextInt();

        boolean found = false;
        for (NoKeyItem item : wsNoKeyItemTable) {
            if (item.wsNoKeyId == wsAcceptId1) {
                System.out.println(" Record found:");
                System.out.println("---------------");
                System.out.println("   ws-no-key-id: " + item.wsNoKeyId);
                System.out.println("ws-no-key-value: " + item.wsNoKeyValue);
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found.");
        }

        System.out.println();
    }

    private static void displayFoundItem(Item item) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + item.wsItemId1);
        System.out.println("Item id-2: " + item.wsItemId2);
        System.out.println("Item id-3: " + item.wsItemId3);
        System.out.println("Item Name: " + item.wsItemName);
        System.out.println("Item Date: " + item.wsItemDate);
        System.out.println();
    }

    private static void setupTestData() {
        wsItemTable[0] = new Item(1, 101, 500, "test item 1", "2021/01/01");
        wsItemTable[1] = new Item(2, 102, 499, "test item 2", "2021/02/02");
        wsItemTable[2] = new Item(3, 103, 498, "test item 3", "2021/03/03");

        wsNoKeyItemTable[0] = new NoKeyItem(2, "Value of id 2.");
        wsNoKeyItemTable[1] = new NoKeyItem(3, "Value of id 3.");
        wsNoKeyItemTable[2] = new NoKeyItem(1, "Value of id 1.");
    }

    private static int binarySearch(Item[] table, int id1, int low, int high, int keyType) {
        if (high < low) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (keyType == 1) {
            if (table[mid].wsItemId1 == id1) {
                return mid;
            } else if (table[mid].wsItemId1 < id1) {
                return binarySearch(table, id1, mid + 1, high, keyType);
            } else {
                return binarySearch(table, id1, low, mid - 1, keyType);
            }
        } else {
            if (table[mid].wsItemId1 == id1 && table[mid].wsItemId2 == id1 && table[mid].wsItemId3 == id1) {
                return mid;
            } else if (table[mid].wsItemId1 < id1 || (table[mid].wsItemId1 == id1 && table[mid].wsItemId2 < id1) || (table[mid].wsItemId1 == id1 && table[mid].wsItemId2 == id1 && table[mid].wsItemId3 < id1)) {
                return binarySearch(table, id1, mid + 1, high, keyType);
            } else {
                return binarySearch(table, id1, low, mid - 1, keyType);
            }
        }
    }

    private static int binarySearch(Item[] table, int id1, int id2, int id3) {
        int low = 0;
        int high = TABLE_SIZE - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (table[mid].wsItemId1 == id1 && table[mid].wsItemId2 == id2 && table[mid].wsItemId3 == id3) {
                return mid;
            } else if (table[mid].wsItemId1 < id1 || (table[mid].wsItemId1 == id1 && table[mid].wsItemId2 < id2) || (table[mid].wsItemId1 == id1 && table[mid].wsItemId2 == id2 && table[mid].wsItemId3 < id3)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    static class Item {
        int wsItemId1;
        int wsItemId2;
        int wsItemId3;
        String wsItemName;
        String wsItemDate;

        Item(int id1, int id2, int id3, String name, String date) {
            wsItemId1 = id1;
            wsItemId2 = id2;
            wsItemId3 = id3;
            wsItemName = name;
            wsItemDate = date;
        }
    }

    static class NoKeyItem {
        int wsNoKeyId;
        String wsNoKeyValue;

        NoKeyItem(int id, String value) {
            wsNoKeyId = id;
            wsNoKeyValue = value;
        }
    }
}