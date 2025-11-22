import java.util.Scanner;

public class SearchExample {
    private static final int TABLE_SIZE = 3;

    private static final int[][] wsItemTable = new int[TABLE_SIZE][3];
    private static final String[] wsItemName = new String[TABLE_SIZE];
    private static final String[] wsItemDate = new String[TABLE_SIZE];

    private static final int[][] wsNoKeyItemTable = new int[TABLE_SIZE][1];
    private static final String[] wsNoKeyValue = new String[TABLE_SIZE];

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupTestData();

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.print("Enter id-1 to search for: ");
        int wsAcceptId1 = scanner.nextInt();

        int idx = binarySearch(wsItemTable, wsAcceptId1, 0);
        if (idx != -1) {
            displayFoundItem(idx);
        } else {
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

        idx = binarySearch(wsItemTable, wsAcceptId1, wsAcceptId2, wsAcceptId3);
        if (idx != -1) {
            displayFoundItem(idx);
        } else {
            System.out.println("Item not found.");
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching not keyed table using sequential search.");
        System.out.print("Enter id: ");
        wsAcceptId1 = scanner.nextInt();

        idx = sequentialSearch(wsNoKeyItemTable, wsAcceptId1);
        if (idx != -1) {
            System.out.println(" Record found:");
            System.out.println("----------------");
            System.out.println("   ws-no-key-id: " + wsNoKeyItemTable[idx][0]);
            System.out.println("ws-no-key-value: " + wsNoKeyValue[idx]);
            System.out.println();
        } else {
            System.out.println("Item not found.");
        }

        System.out.println();
    }

    private static void displayFoundItem(int idx) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + wsItemTable[idx][0]);
        System.out.println("Item id-2: " + wsItemTable[idx][1]);
        System.out.println("Item id-3: " + wsItemTable[idx][2]);
        System.out.println("Item Name: " + wsItemName[idx]);
        System.out.println("Item Date: " + wsItemDate[idx]);
        System.out.println();
    }

    private static void setupTestData() {
        wsItemTable[0][0] = 1;
        wsItemTable[0][1] = 101;
        wsItemTable[0][2] = 500;
        wsItemName[0] = "test item 1";
        wsItemDate[0] = "2021/01/01";

        wsItemTable[1][0] = 2;
        wsItemTable[1][1] = 102;
        wsItemTable[1][2] = 499;
        wsItemName[1] = "test item 2";
        wsItemDate[1] = "2021/02/02";

        wsItemTable[2][0] = 3;
        wsItemTable[2][1] = 103;
        wsItemTable[2][2] = 498;
        wsItemName[2] = "test item 3";
        wsItemDate[2] = "2021/03/03";

        wsNoKeyItemTable[0][0] = 2;
        wsNoKeyValue[0] = "Value of id 2.";

        wsNoKeyItemTable[1][0] = 3;
        wsNoKeyValue[1] = "Value of id 3.";

        wsNoKeyItemTable[2][0] = 1;
        wsNoKeyValue[2] = "Value of id 1.";
    }

    private static int binarySearch(int[][] table, int id1) {
        int low = 0;
        int high = TABLE_SIZE - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (table[mid][0] == id1) {
                return mid;
            } else if (table[mid][0] < id1) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private static int binarySearch(int[][] table, int id1, int id2, int id3) {
        int low = 0;
        int high = TABLE_SIZE - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (table[mid][0] == id1 && table[mid][1] == id2 && table[mid][2] == id3) {
                return mid;
            } else if (table[mid][0] < id1 || (table[mid][0] == id1 && table[mid][1] < id2) || (table[mid][0] == id1 && table[mid][1] == id2 && table[mid][2] < id3)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    private static int sequentialSearch(int[][] table, int id) {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i][0] == id) {
                return i;
            }
        }
        return -1;
    }
}