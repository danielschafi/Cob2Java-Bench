import java.util.Objects;

public class lldemo1 {
    
    private static class Node {
        Node prevNodePtr;
        Node nextNodePtr;
        String nodeData;
        
        public Node() {
            this.prevNodePtr = null;
            this.nextNodePtr = null;
            this.nodeData = "        ";
        }
    }
    
    private static class AList {
        Node listHeadPtr;
        Node listTailPtr;
        
        public AList() {
            this.listHeadPtr = null;
            this.listTailPtr = null;
        }
    }
    
    private static final String MYNAME = "lldemo1 ";
    
    private static Node currNodePtr = null;
    private static Node workNodePtr = null;
    private static Node tempNodePtr = null;
    private static AList aList = new AList();
    
    public static void main(String[] args) {
        allocateNode();
        currNodePtr.nodeData = "NODE3   ";
        aList.listHeadPtr = currNodePtr;
        aList.listTailPtr = currNodePtr;
        tempNodePtr = currNodePtr;
        
        addNodeToTailOfList();
        currNodePtr.nodeData = "NODE4   ";
        
        addNewNodeAfterCurr();
        currNodePtr.nodeData = "NODE5   ";
        
        addNodeToHeadOfList();
        currNodePtr.nodeData = "NODE2   ";
        
        addNewNodeBeforeCurr();
        currNodePtr.nodeData = "NODE1   ";
        
        System.out.println("five nodes allocated");
        printList();
        
        System.out.println("removing list tail");
        currNodePtr = aList.listTailPtr;
        removeCurrNode();
        
        System.out.println("four nodes allocated");
        printList();
        
        System.out.println("removing list head");
        currNodePtr = aList.listHeadPtr;
        removeCurrNode();
        
        System.out.println("three nodes allocated");
        printList();
        
        System.out.println("removing middle entry from list");
        currNodePtr = tempNodePtr;
        removeCurrNode();
        
        System.out.println("free the rest of the list");
        
        currNodePtr = aList.listHeadPtr;
        while (currNodePtr != null) {
            removeCurrNode();
            currNodePtr = aList.listHeadPtr;
        }
    }
    
    private static void printList() {
        currNodePtr = aList.listHeadPtr;
        
        while (currNodePtr != null) {
            System.out.println(
                System.identityHashCode(currNodePtr) +
                " " + currNodePtr.nodeData +
                " " + (currNodePtr.prevNodePtr != null ? System.identityHashCode(currNodePtr.prevNodePtr) : "null") +
                " " + (currNodePtr.nextNodePtr != null ? System.identityHashCode(currNodePtr.nextNodePtr) : "null")
            );
            currNodePtr = currNodePtr.nextNodePtr;
        }
    }
    
    private static void allocateNode() {
        currNodePtr = new Node();
        System.out.println("CURR-NODE-PTR = " + System.identityHashCode(currNodePtr));
    }
    
    private static void addNodeToHeadOfList() {
        allocateNode();
        
        workNodePtr = aList.listHeadPtr;
        workNodePtr.prevNodePtr = currNodePtr;
        currNodePtr.nextNodePtr = aList.listHeadPtr;
        aList.listHeadPtr = currNodePtr;
    }
    
    private static void addNodeToTailOfList() {
        allocateNode();
        
        workNodePtr = aList.listTailPtr;
        workNodePtr.nextNodePtr = currNodePtr;
        currNodePtr.prevNodePtr = aList.listTailPtr;
        aList.listTailPtr = currNodePtr;
    }
    
    private static void addNewNodeAfterCurr() {
        workNodePtr = currNodePtr;
        
        allocateNode();
        currNodePtr.prevNodePtr = workNodePtr;
        currNodePtr.nextNodePtr = workNodePtr.nextNodePtr;
        workNodePtr.nextNodePtr = currNodePtr;
        
        if (aList.listTailPtr == workNodePtr) {
            aList.listTailPtr = currNodePtr;
        }
    }
    
    private static void addNewNodeBeforeCurr() {
        workNodePtr = currNodePtr;
        
        allocateNode();
        currNodePtr.nextNodePtr = workNodePtr;
        currNodePtr.prevNodePtr = workNodePtr.prevNodePtr;
        workNodePtr.prevNodePtr = currNodePtr;
        
        if (aList.listHeadPtr == workNodePtr) {
            aList.listHeadPtr = currNodePtr;
        }
    }
    
    private static void removeCurrNode() {
        if (currNodePtr.nextNodePtr != null) {
            workNodePtr = currNodePtr.nextNodePtr;
            workNodePtr.prevNodePtr = currNodePtr.prevNodePtr;
        }
        
        if (currNodePtr.prevNodePtr != null) {
            workNodePtr = currNodePtr.prevNodePtr;
            workNodePtr.nextNodePtr = currNodePtr.nextNodePtr;
        }
        
        if (aList.listHeadPtr == currNodePtr) {
            aList.listHeadPtr = currNodePtr.nextNodePtr;
        }
        
        if (aList.listTailPtr == currNodePtr) {
            aList.listTailPtr = currNodePtr.prevNodePtr;
        }
        
        System.out.println("freeing " + System.identityHashCode(currNodePtr));
        currNodePtr = null;
    }
}