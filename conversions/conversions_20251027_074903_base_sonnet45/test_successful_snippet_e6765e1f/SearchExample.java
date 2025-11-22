import java.util.Scanner;

public class SearchExample {

    static class ItemRecord {
        int itemId1;
        int itemId2;
        int itemId3;
        String itemName;
        String itemDate;

        ItemRecord(int id1, int id2, int id3, String name, String date) {
            this.itemId1 = id1;
            this.itemId2 = id2;
            this.itemId3 = id3;
            this.itemName = name;
            this.itemDate = date;
        }
    }

    static class NoKeyItemRecord {
        int noKeyId;
        String noKeyValue;

        NoKeyItemRecord(int id, String value) {
            this.noKeyId = id;
            this.noKeyValue = value;
        }
    }

    private static ItemRecord[] wsItemTable = new ItemRecord[3];
    private static NoKeyItemRecord[] wsNoKeyItemTable = new NoKeyItemRecord[3];

    public static void main(String[] args) {
        setupTestData();

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.print("Enter id-1 to search for: ");
        int wsAcceptId1 = scanner.nextInt();

        int idx = binarySearchById1(wsAcceptId1);
        if (idx == -1) {
            System.out.println("Item not found.");
        } else {
            displayFoundItem(idx);
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching again with all required ids matching.");

        System.out.print("Enter id-1 to search for: ");
        wsAcceptId1 = scanner.nextInt();

        System.out.print("Enter id-2 to search for: ");
        int wsAcceptId2 = scanner.nextInt();

        System.out.print("Enter id-3 to search for: ");
        int wsAcceptId3 = scanner.nextInt();

        idx = binarySearchByAllIds(wsAcceptId1, wsAcceptId2, wsAcceptId3);
        if (idx == -1) {
            System.out.println("Item not found.");
        } else {
            displayFoundItem(idx);
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching not keyed table using sequential search.");
        System.out.print("Enter id: ");
        wsAcceptId1 = scanner.nextInt();

        int idx2 = sequentialSearch(wsAcceptId1);
        if (idx2 == -1) {
            System.out.println("Item not found.");
        } else {
            System.out.println(" Record found:");
            System.out.println("---------------");
            System.out.println("   ws-no-key-id: " + String.format("%04d", wsNoKeyItemTable[idx2].noKeyId));
            System.out.println("ws-no-key-value: " + wsNoKeyItemTable[idx2].noKeyValue);
            System.out.println();
        }

        System.out.println();

        scanner.close();
    }

    private static int binarySearchById1(int searchId) {
        int low = 0;
        int high = wsItemTable.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (wsItemTable[mid].itemId1 == searchId) {
                return mid;
            } else if (wsItemTable[mid].itemId1 < searchId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static int binarySearchByAllIds(int id1, int id2, int id3) {
        for (int i = 0; i < wsItemTable.length; i++) {
            if (wsItemTable[i].itemId1 == id1 &&
                wsItemTable[i].itemId2 == id2 &&
                wsItemTable[i].itemId3 == id3) {
                return i;
            }
        }
        return -1;
    }

    private static int sequentialSearch(int searchId) {
        for (int i = 0; i < wsNoKeyItemTable.length; i++) {
            if (wsNoKeyItemTable[i].noKeyId == searchId) {
                return i;
            }
        }
        return -1;
    }

    private static void displayFoundItem(int idx) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + String.format("%04d", wsItemTable[idx].itemId1));
        System.out.println("Item id-2: " + String.format("%04d", wsItemTable[idx].itemId2));
        System.out.println("Item id-3: " + String.format("%04d", wsItemTable[idx].itemId3));
        System.out.println("Item Name: " + wsItemTable[idx].itemName);
        System.out.println("Item Date: " + wsItemTable[idx].itemDate);
        System.out.println();
    }

    private static void setupTestData() {
        wsItemTable[0] = new ItemRecord(1, 101, 500, "test item 1", "2021/01/01");
        wsItemTable[1] = new ItemRecord(2, 102, 499, "test item 2", "2021/02/02");
        wsItemTable[2] = new ItemRecord(3, 103, 498, "test item 3", "2021/03/03");

        wsNoKeyItemTable[0] = new NoKeyItemRecord(2, "Value of id 2.");
        wsNoKeyItemTable[1] = new NoKeyItemRecord(3, "Value of id 3.");
        wsNoKeyItemTable[2] = new NoKeyItemRecord(1, "Value of id 1.");
    }
}