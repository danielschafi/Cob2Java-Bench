import java.util.Scanner;

public class SearchExample {
    private static class ItemTable {
        int wsItemId1;
        int wsItemId2;
        int wsItemId3;
        String wsItemName;
        String wsItemDate;
    }

    private static class NoKeyItemTable {
        int wsNoKeyId;
        String wsNoKeyValue;
    }

    private static final ItemTable[] wsItemTable = new ItemTable[3];
    private static final NoKeyItemTable[] wsNoKeyItemTable = new NoKeyItemTable[3];
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupTestData();

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.print("Enter id-1 to search for: ");
        int wsAcceptId1 = scanner.nextInt();

        boolean found = false;
        for (int idx = 0; idx < wsItemTable.length; idx++) {
            if (wsItemTable[idx].wsItemId1 == wsAcceptId1) {
                displayFoundItem(idx);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found.");
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

        found = false;
        for (int idx = 0; idx < wsItemTable.length; idx++) {
            if (wsItemTable[idx].wsItemId1 == wsAcceptId1 &&
                wsItemTable[idx].wsItemId2 == wsAcceptId2 &&
                wsItemTable[idx].wsItemId3 == wsAcceptId3) {
                displayFoundItem(idx);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found.");
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching not keyed table using sequential search.");
        System.out.print("Enter id: ");
        wsAcceptId1 = scanner.nextInt();

        found = false;
        for (int idx2 = 0; idx2 < wsNoKeyItemTable.length; idx2++) {
            if (wsNoKeyItemTable[idx2].wsNoKeyId == wsAcceptId1) {
                System.out.println(" Record found:");
                System.out.println("----------------");
                System.out.println("   ws-no-key-id: " + wsNoKeyItemTable[idx2].wsNoKeyId);
                System.out.println("ws-no-key-value: " + wsNoKeyItemTable[idx2].wsNoKeyValue);
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

    private static void displayFoundItem(int idx) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + wsItemTable[idx].wsItemId1);
        System.out.println("Item id-2: " + wsItemTable[idx].wsItemId2);
        System.out.println("Item id-3: " + wsItemTable[idx].wsItemId3);
        System.out.println("Item Name: " + wsItemTable[idx].wsItemName);
        System.out.println("Item Date: " + wsItemTable[idx].wsItemDate);
        System.out.println();
    }

    private static void setupTestData() {
        for (int i = 0; i < wsItemTable.length; i++) {
            wsItemTable[i] = new ItemTable();
        }
        for (int i = 0; i < wsNoKeyItemTable.length; i++) {
            wsNoKeyItemTable[i] = new NoKeyItemTable();
        }

        wsItemTable[0].wsItemId1 = 1;
        wsItemTable[0].wsItemId2 = 101;
        wsItemTable[0].wsItemId3 = 500;
        wsItemTable[0].wsItemName = "test item 1";
        wsItemTable[0].wsItemDate = "2021/01/01";

        wsItemTable[1].wsItemId1 = 2;
        wsItemTable[1].wsItemId2 = 102;
        wsItemTable[1].wsItemId3 = 499;
        wsItemTable[1].wsItemName = "test item 2";
        wsItemTable[1].wsItemDate = "2021/02/02";

        wsItemTable[2].wsItemId1 = 3;
        wsItemTable[2].wsItemId2 = 103;
        wsItemTable[2].wsItemId3 = 498;
        wsItemTable[2].wsItemName = "test item 3";
        wsItemTable[2].wsItemDate = "2021/03/03";

        wsNoKeyItemTable[0].wsNoKeyId = 2;
        wsNoKeyItemTable[0].wsNoKeyValue = "Value of id 2.";

        wsNoKeyItemTable[1].wsNoKeyId = 3;
        wsNoKeyItemTable[1].wsNoKeyValue = "Value of id 3.";

        wsNoKeyItemTable[2].wsNoKeyId = 1;
        wsNoKeyItemTable[2].wsNoKeyValue = "Value of id 1.";
    }
}