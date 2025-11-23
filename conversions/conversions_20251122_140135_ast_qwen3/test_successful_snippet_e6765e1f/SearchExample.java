import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        setupTestData();

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.print("Enter id-1 to search for: ");
        int wsAcceptId1 = Integer.parseInt(reader.readLine());

        // Binary search - table must be indexed by an asc or desc id
        // and sorted for search to work. MUCH faster than sequential
        // search which does not require any sorting or indexing.
        // Binary search is indicated by the "SEARCH ALL" syntax.
        int idx = 0;
        boolean found = false;
        while (idx < 3) {
            if (wsItemTable[idx].wsItemId1 == wsAcceptId1) {
                displayFoundItem(idx);
                found = true;
                break;
            }
            idx++;
        }
        if (!found) {
            System.out.println("Item not found.");
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching again with all required ids matching.");

        System.out.print("Enter id-1 to search for: ");
        wsAcceptId1 = Integer.parseInt(reader.readLine());

        System.out.print("Enter id-2 to search for: ");
        int wsAcceptId2 = Integer.parseInt(reader.readLine());

        System.out.print("Enter id-3 to search for: ");
        int wsAcceptId3 = Integer.parseInt(reader.readLine());

        idx = 0;
        found = false;
        while (idx < 3) {
            if (wsItemTable[idx].wsItemId1 == wsAcceptId1 &&
                wsItemTable[idx].wsItemId2 == wsAcceptId2 &&
                wsItemTable[idx].wsItemId3 == wsAcceptId3) {
                displayFoundItem(idx);
                found = true;
                break;
            }
            idx++;
        }
        if (!found) {
            System.out.println("Item not found.");
        }

        // Sequential searches are slower but also don't require the data
        // to be sorted or require a key.
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching not keyed table using sequential search.");
        System.out.print("Enter id: ");
        wsAcceptId1 = Integer.parseInt(reader.readLine());

        int idx2 = 0;
        found = false;
        while (idx2 < 3) {
            if (wsNoKeyItemTable[idx2].wsNoKeyId == wsAcceptId1) {
                System.out.println(" Record found:");
                System.out.println("---------------");
                System.out.println("   ws-no-key-id: " + wsNoKeyItemTable[idx2].wsNoKeyId);
                System.out.println("ws-no-key-value: " + wsNoKeyItemTable[idx2].wsNoKeyValue);
                System.out.println();
                found = true;
                break;
            }
            idx2++;
        }
        if (!found) {
            System.out.println("Item not found.");
        }

        System.out.println();
    }

    private static void displayFoundItem(int index) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + wsItemTable[index].wsItemId1);
        System.out.println("Item id-2: " + wsItemTable[index].wsItemId2);
        System.out.println("Item id-3: " + wsItemTable[index].wsItemId3);
        System.out.println("Item Name: " + wsItemTable[index].wsItemName);
        System.out.println("Item Date: " + wsItemTable[index].wsItemDate);
        System.out.println();
    }

    private static void setupTestData() {
        // Initialize wsItemTable
        for (int i = 0; i < 3; i++) {
            wsItemTable[i] = new ItemTable();
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

        // Initialize wsNoKeyItemTable
        for (int i = 0; i < 3; i++) {
            wsNoKeyItemTable[i] = new NoKeyItemTable();
        }

        wsNoKeyItemTable[0].wsNoKeyId = 2;
        wsNoKeyItemTable[0].wsNoKeyValue = "Value of id 2.";

        wsNoKeyItemTable[1].wsNoKeyId = 3;
        wsNoKeyItemTable[1].wsNoKeyValue = "Value of id 3.";

        wsNoKeyItemTable[2].wsNoKeyId = 1;
        wsNoKeyItemTable[2].wsNoKeyValue = "Value of id 1.";
    }
}