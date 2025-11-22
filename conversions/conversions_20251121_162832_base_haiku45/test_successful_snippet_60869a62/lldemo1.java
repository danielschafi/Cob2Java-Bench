import java.util.*;

public class lldemo1 {
    static class Node {
        Node prevNodePtr;
        Node nextNodePtr;
        String nodeData;
        
        Node() {
            this.prevNodePtr = null;
            this.nextNodePtr = null;
            this.nodeData = "        ";
        }
    }
    
    static class LinkedList {
        Node listHeadPtr;
        Node listTailPtr;
        
        LinkedList() {
            this.listHeadPtr = null;
            this.listTailPtr = null;
        }
    }
    
    static Node currNodePtr = null;
    static Node workNodePtr = null;
    static Node tempNodePtr = null;
    static LinkedList aList = new LinkedList();
    
    public static void main(String[] args) {
        allocateNode();
        currNodePtr.nodeData = "NODE3";
        aList.listHeadPtr = currNodePtr;
        aList.listTailPtr = currNodePtr;
        tempNodePtr = currNodePtr;
        
        addNodeToTailOfList();
        currNodePtr.nodeData = "NODE4";
        
        addNewNodeAfterCurr();
        currNodePtr.nodeData = "NODE5";
        
        addNodeToHeadOfList();
        currNodePtr.nodeData = "NODE2";
        
        addNewNodeBeforeCurr();
        currNodePtr.nodeData = "NODE1";
        
        System.out.println("five nodes allocated");
        printList();
        
        System.out.println("removing list tail");
        currNodePtr = aList.listTailPtr;
        removeCurrentNode();
        
        System.out.println("four nodes allocated");
        printList();
        
        System.out.println("removing list head");
        currNodePtr = aList.listHeadPtr;
        removeCurrentNode();
        
        System.out.println("three nodes allocated");
        printList();
        
        System.out.println("removing middle entry from list");
        currNodePtr = tempNodePtr;
        removeCurrentNode();
        
        System.out.println("free the rest of the list");
        
        currNodePtr = aList.listHeadPtr;
        while (currNodePtr != null) {
            removeCurrentNode();
            currNodePtr = aList.listHeadPtr;
        }
    }
    
    static void printList() {
        currNodePtr = aList.listHeadPtr;
        
        while (currNodePtr != null) {
            String prevPtr = (currNodePtr.prevNodePtr == null) ? "null" : currNodePtr.prevNodePtr.toString();
            String nextPtr = (currNodePtr.nextNodePtr == null) ? "null" : currNodePtr.nextNodePtr.toString();
            System.out.println(currNodePtr.toString() + " " + currNodePtr.nodeData + " " + prevPtr + " " + nextPtr);
            currNodePtr = currNodePtr.nextNodePtr;
        }
    }
    
    static void allocateNode() {
        currNodePtr = new Node();
        System.out.println("CURR-NODE-PTR = " + currNodePtr.toString());
    }
    
    static void addNodeToHeadOfList() {
        allocateNode();
        
        workNodePtr = aList.listHeadPtr;
        workNodePtr.prevNodePtr = currNodePtr;
        currNodePtr.nextNodePtr = aList.listHeadPtr;
        aList.listHeadPtr = currNodePtr;
    }
    
    static void addNodeToTailOfList() {
        allocateNode();
        
        workNodePtr = aList.listTailPtr;
        workNodePtr.nextNodePtr = currNodePtr;
        currNodePtr.prevNodePtr = aList.listTailPtr;
        aList.listTailPtr = currNodePtr;
    }
    
    static void addNewNodeAfterCurr() {
        workNodePtr = currNodePtr;
        
        allocateNode();
        currNodePtr.prevNodePtr = workNodePtr;
        currNodePtr.nextNodePtr = workNodePtr.nextNodePtr;
        workNodePtr.nextNodePtr = currNodePtr;
        
        if (aList.listTailPtr == workNodePtr) {
            aList.listTailPtr = currNodePtr;
        }
    }
    
    static void addNewNodeBeforeCurr() {
        workNodePtr = currNodePtr;
        
        allocateNode();
        currNodePtr.nextNodePtr = workNodePtr;
        currNodePtr.prevNodePtr = workNodePtr.prevNodePtr;
        workNodePtr.prevNodePtr = currNodePtr;
        
        if (aList.listHeadPtr == workNodePtr) {
            aList.listHeadPtr = currNodePtr;
        }
    }
    
    static void removeCurrentNode() {
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
        
        System.out.println("freeing " + currNodePtr.toString());
        currNodePtr = null;
    }
}