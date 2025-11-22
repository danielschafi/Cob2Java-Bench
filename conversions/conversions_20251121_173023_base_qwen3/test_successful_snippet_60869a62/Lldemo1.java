import java.util.*;

public class Lldemo1 {
    static class Node {
        Node prev;
        Node next;
        String data;
        
        Node(String data) {
            this.data = data;
        }
    }
    
    static Node listHeadPtr = null;
    static Node listTailPtr = null;
    static Node currNodePtr = null;
    static Node workNodePtr = null;
    static Node tempNodePtr = null;
    
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
    
    static void printList() {
        currNodePtr = listHeadPtr;
        
        while (currNodePtr != null) {
            System.out.println(currNodePtr + " " + currNodePtr.data + " " + currNodePtr.prev + " " + currNodePtr.next);
            currNodePtr = currNodePtr.next;
        }
    }
    
    static void allocateNode() {
        currNodePtr = new Node("");
        System.out.println("CURR-NODE-PTR = " + currNodePtr);
    }
    
    static void addNodeToHeadOfList() {
        allocateNode();
        
        workNodePtr = listHeadPtr;
        currNodePtr.prev = workNodePtr;
        currNodePtr.next = listHeadPtr;
        listHeadPtr = currNodePtr;
    }
    
    static void addNodeToTailOfList() {
        allocateNode();
        
        workNodePtr = listTailPtr;
        workNodePtr.next = currNodePtr;
        currNodePtr.prev = listTailPtr;
        listTailPtr = currNodePtr;
    }
    
    static void addNewNodeAfterCurr() {
        workNodePtr = currNodePtr;
        
        allocateNode();
        currNodePtr.prev = workNodePtr;
        currNodePtr.next = workNodePtr.next;
        workNodePtr.next = currNodePtr;
        
        if (listTailPtr == workNodePtr) {
            listTailPtr = currNodePtr;
        }
    }
    
    static void addNewNodeBeforeCurr() {
        workNodePtr = currNodePtr;
        
        allocateNode();
        currNodePtr.next = workNodePtr;
        currNodePtr.prev = workNodePtr.prev;
        workNodePtr.prev = currNodePtr;
        
        if (listHeadPtr == workNodePtr) {
            listHeadPtr = currNodePtr;
        }
    }
    
    static void removeCurrNode() {
        if (currNodePtr.next != null) {
            workNodePtr = currNodePtr.next;
            workNodePtr.prev = currNodePtr.prev;
        }
        
        if (currNodePtr.prev != null) {
            workNodePtr = currNodePtr.prev;
            workNodePtr.next = currNodePtr.next;
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