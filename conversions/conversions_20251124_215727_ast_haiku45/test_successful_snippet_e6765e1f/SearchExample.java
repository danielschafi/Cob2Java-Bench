import java.util.Scanner;

public class SearchExample {
    static class ItemRecord {
        int id1;
        int id2;
        int id3;
        String name;
        String date;
    }

    static class NoKeyRecord {
        int id;
        String value;
    }

    static ItemRecord[] itemTable = new ItemRecord[3];
    static NoKeyRecord[] noKeyTable = new NoKeyRecord[3];
    static int idx;
    static int idx2;
    static int acceptId1;
    static int acceptId2;
    static int acceptId3;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupTestData();

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.print("Enter id-1 to search for: ");
        acceptId1 = scanner.nextInt();

        idx = 0;
        boolean found = false;
        for (int i = 0; i < 3; i++) {
            if (itemTable[i].id1 == acceptId1) {
                idx = i;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found.");
        } else {
            displayFoundItem();
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching again with all required ids matching.");

        System.out.print("Enter id-1 to search for: ");
        acceptId1 = scanner.nextInt();

        System.out.print("Enter id-2 to search for: ");
        acceptId2 = scanner.nextInt();

        System.out.print("Enter id-3 to search for: ");
        acceptId3 = scanner.nextInt();

        idx = 0;
        found = false;
        for (int i = 0; i < 3; i++) {
            if (itemTable[i].id1 == acceptId1 && itemTable[i].id2 == acceptId2 && itemTable[i].id3 == acceptId3) {
                idx = i;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found.");
        } else {
            displayFoundItem();
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching not keyed table using sequential search.");
        System.out.print("Enter id: ");
        acceptId1 = scanner.nextInt();

        idx2 = 0;
        found = false;
        for (int i = 0; i < 3; i++) {
            if (noKeyTable[i].id == acceptId1) {
                idx2 = i;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found.");
        } else {
            System.out.println(" Record found:");
            System.out.println("---------------");
            System.out.println("   ws-no-key-id: " + noKeyTable[idx2].id);
            System.out.println("ws-no-key-value: " + noKeyTable[idx2].value);
            System.out.println();
        }

        System.out.println();
    }

    static void displayFoundItem() {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + itemTable[idx].id1);
        System.out.println("Item id-2: " + itemTable[idx].id2);
        System.out.println("Item id-3: " + itemTable[idx].id3);
        System.out.println("Item Name: " + itemTable[idx].name);
        System.out.println("Item Date: " + itemTable[idx].date);
        System.out.println();
    }

    static void setupTestData() {
        for (int i = 0; i < 3; i++) {
            itemTable[i] = new ItemRecord();
            noKeyTable[i] = new NoKeyRecord();
        }

        itemTable[0].id1 = 1;
        itemTable[0].id2 = 101;
        itemTable[0].id3 = 500;
        itemTable[0].name = "test item 1";
        itemTable[0].date = "2021/01/01";

        itemTable[1].id1 = 2;
        itemTable[1].id2 = 102;
        itemTable[1].id3 = 499;
        itemTable[1].name = "test item 2";
        itemTable[1].date = "2021/02/02";

        itemTable[2].id1 = 3;
        itemTable[2].id2 = 103;
        itemTable[2].id3 = 498;
        itemTable[2].name = "test item 3";
        itemTable[2].date = "2021/03/03";

        noKeyTable[0].id = 2;
        noKeyTable[0].value = "Value of id 2.";

        noKeyTable[1].id = 3;
        noKeyTable[1].value = "Value of id 3.";

        noKeyTable[2].id = 1;
        noKeyTable[2].value = "Value of id 1.";
    }
}