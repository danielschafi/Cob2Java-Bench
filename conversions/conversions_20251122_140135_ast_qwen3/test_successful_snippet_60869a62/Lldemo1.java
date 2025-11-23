public class Lldemo1 {
    private static class Node {
        Node prev;
        Node next;
        String data;
        
        Node() {
            this.data = "        ";
        }
    }
    
    private static Node listHeadPtr = null;
    private static Node listTailPtr = null;
    private static Node currNodePtr = null;
    private static Node workNodePtr = null;
    private static Node tempNodePtr = null;
    
    public static void main(String[] args) {
        allocateNode();
        currNodePtr.data = "NODE3";
        listHeadPtr = currNodePtr;
        listTailPtr = currNodePtr;
        tempNodePtr = currNodePtr;
        
        addNodeToTailOfList();
        currNodePtr.data = "NODE4";
        
        addNewNodeAfterCurr();
        currNodePtr.data = "NODE5";
        
        addNodeToHeadOfList();
        currNodePtr.data = "NODE2";
        
        addNewNodeBeforeCurr();
        currNodePtr.data = "NODE1";
        
        System.out.println("five nodes allocated");
        printList();
        
        System.out.println("removing list tail");
        currNodePtr = listTailPtr;
        removeCurrNode();
        
        System.out.println("four nodes allocated");
        printList();
        
        System.out.println("removing list head");
        currNodePtr = listHeadPtr;
        removeCurrNode();
        
        System.out.println("three nodes allocated");
        printList();
        
        System.out.println("removing middle entry from list");
        currNodePtr = tempNodePtr;
        removeCurrNode();
        
        System.out.println("free the rest of the list");
        
        currNodePtr = listHeadPtr;
        while (currNodePtr != null) {
            removeCurrNode();
            currNodePtr = listHeadPtr;
        }
    }
    
    private static void allocateNode() {
        currNodePtr = new Node();
        System.out.println("CURR-NODE-PTR = " + currNodePtr);
    }
    
    private static void addNodeToHeadOfList() {
        allocateNode();
        
        workNodePtr = listHeadPtr;
        if (workNodePtr != null) {
            workNodePtr.prev = currNodePtr;
        }
        currNodePtr.next = listHeadPtr;
        listHeadPtr = currNodePtr;
    }
    
    private static void addNodeToTailOfList() {
        allocateNode();
        
        workNodePtr = listTailPtr;
        if (workNodePtr != null) {
            workNodePtr.next = currNodePtr;
        }
        currNodePtr.prev = listTailPtr;
        listTailPtr = currNodePtr;
    }
    
    private static void addNewNodeAfterCurr() {
        workNodePtr = currNodePtr;
        
        allocateNode();
        currNodePtr.prev = workNodePtr;
        currNodePtr.next = workNodePtr.next;
        workNodePtr.next = currNodePtr;
        
        if (listTailPtr == workNodePtr) {
            listTailPtr = currNodePtr;
        }
    }
    
    private static void addNewNodeBeforeCurr() {
        workNodePtr = currNodePtr;
        
        allocateNode();
        currNodePtr.next = workNodePtr;
        currNodePtr.prev = workNodePtr.prev;
        workNodePtr.prev = currNodePtr;
        
        if (listHeadPtr == workNodePtr) {
            listHeadPtr = currNodePtr;
        }
    }
    
    private static void printList() {
        currNodePtr = listHeadPtr;
        
        while (currNodePtr != null) {
            System.out.println(currNodePtr + " " + currNodePtr.data + " " + currNodePtr.prev + " " + currNodePtr.next);
            currNodePtr = currNodePtr.next;
        }
    }
    
    private static void removeCurrNode() {
        if (currNodePtr.next != null) {
            currNodePtr.next.prev = currNodePtr.prev;
        }
        
        if (currNodePtr.prev != null) {
            currNodePtr.prev.next = currNodePtr.next;
        }
        
        if (listHeadPtr == currNodePtr) {
            listHeadPtr = currNodePtr.next;
        }
        
        if (listTailPtr == currNodePtr) {
            listTailPtr = currNodePtr.prev;
        }
        
        System.out.println("freeing " + currNodePtr);
        currNodePtr = null;
    }
}