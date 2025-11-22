import java.util.Scanner;

public class SearchExample {
    
    static class ItemRecord {
        int itemId1;
        int itemId2;
        int itemId3;
        String itemName;
        String itemDate;
    }
    
    static class NoKeyRecord {
        int noKeyId;
        String noKeyValue;
    }
    
    static ItemRecord[] wsItemTable = new ItemRecord[3];
    static NoKeyRecord[] wsNoKeyItemTable = new NoKeyRecord[3];
    
    static int wsAcceptId1;
    static int wsAcceptId2;
    static int wsAcceptId3;
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        setupTestData();
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.print("Enter id-1 to search for: ");
        wsAcceptId1 = scanner.nextInt();
        
        int idx = binarySearchByItemId1(wsAcceptId1);
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
        wsAcceptId2 = scanner.nextInt();
        
        System.out.print("Enter id-3 to search for: ");
        wsAcceptId3 = scanner.nextInt();
        
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
        
        int idx2 = sequentialSearchNoKey(wsAcceptId1);
        if (idx2 == -1) {
            System.out.println("Item not found.");
        } else {
            System.out.println(" Record found:");
            System.out.println("---------------");
            System.out.println("   ws-no-key-id: " + wsNoKeyItemTable[idx2].noKeyId);
            System.out.println("ws-no-key-value: " + wsNoKeyItemTable[idx2].noKeyValue);
            System.out.println();
        }
        
        System.out.println();
        scanner.close();
    }
    
    static int binarySearchByItemId1(int searchId) {
        int left = 0;
        int right = wsItemTable.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (wsItemTable[mid].itemId1 == searchId) {
                return mid;
            } else if (wsItemTable[mid].itemId1 < searchId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    static int binarySearchByAllIds(int searchId1, int searchId2, int searchId3) {
        int left = 0;
        int right = wsItemTable.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = Integer.compare(wsItemTable[mid].itemId1, searchId1);
            
            if (cmp == 0) {
                cmp = Integer.compare(wsItemTable[mid].itemId2, searchId2);
            }
            
            if (cmp == 0) {
                cmp = Integer.compare(wsItemTable[mid].itemId3, searchId3);
            }
            
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    
    static int sequentialSearchNoKey(int searchId) {
        for (int i = 0; i < wsNoKeyItemTable.length; i++) {
            if (wsNoKeyItemTable[i].noKeyId == searchId) {
                return i;
            }
        }
        return -1;
    }
    
    static void displayFoundItem(int idx) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + wsItemTable[idx].itemId1);
        System.out.println("Item id-2: " + wsItemTable[idx].itemId2);
        System.out.println("Item id-3: " + wsItemTable[idx].itemId3);
        System.out.println("Item Name: " + wsItemTable[idx].itemName);
        System.out.println("Item Date: " + wsItemTable[idx].itemDate);
        System.out.println();
    }
    
    static void setupTestData() {
        for (int i = 0; i < 3; i++) {
            wsItemTable[i] = new ItemRecord();
            wsNoKeyItemTable[i] = new NoKeyRecord();
        }
        
        wsItemTable[0].itemId1 = 1;
        wsItemTable[0].itemId2 = 101;
        wsItemTable[0].itemId3 = 500;
        wsItemTable[0].itemName = "test item 1";
        wsItemTable[0].itemDate = "2021/01/01";
        
        wsItemTable[1].itemId1 = 2;
        wsItemTable[1].itemId2 = 102;
        wsItemTable[1].itemId3 = 499;
        wsItemTable[1].itemName = "test item 2";
        wsItemTable[1].itemDate = "2021/02/02";
        
        wsItemTable[2].itemId1 = 3;
        wsItemTable[2].itemId2 = 103;
        wsItemTable[2].itemId3 = 498;
        wsItemTable[2].itemName = "test item 3";
        wsItemTable[2].itemDate = "2021/03/03";
        
        wsNoKeyItemTable[0].noKeyId = 2;
        wsNoKeyItemTable[0].noKeyValue = "Value of id 2.";
        
        wsNoKeyItemTable[1].noKeyId = 3;
        wsNoKeyItemTable[1].noKeyValue = "Value of id 3.";
        
        wsNoKeyItemTable[2].noKeyId = 1;
        wsNoKeyItemTable[2].noKeyValue = "Value of id 1.";
    }
}