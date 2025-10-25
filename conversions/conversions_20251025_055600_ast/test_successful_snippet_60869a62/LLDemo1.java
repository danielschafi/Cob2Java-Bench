import java.util.*;

class Node {
    Node prevNodePtr;
    Node nextNodePtr;
    String nodeData;

    Node() {
        this.nodeData = "        ";
    }
}

public class LLDemo1 {
    private Node currNodePtr;
    private Node workNodePtr;
    private Node tempNodePtr;
    private Node listHeadPtr;
    private Node listTailPtr;

    public static void main(String[] args) {
        LLDemo1 demo = new LLDemo1();
        demo.run();
    }

    public void run() {
        allocateNode();
        currNodePtr.nodeData = "NODE3";
        listHeadPtr = currNodePtr;
        listTailPtr = currNodePtr;
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

    public void printList() {
        currNodePtr = listHeadPtr;
        while (currNodePtr != null) {
            System.out.println(currNodePtr + " " + currNodePtr.nodeData + " " + currNodePtr.prevNodePtr + " " + currNodePtr.nextNodePtr);
            currNodePtr = currNodePtr.nextNodePtr;
        }
    }

    public void allocateNode() {
        currNodePtr = new Node();
        System.out.println("CURR-NODE-PTR = " + currNodePtr);
    }

    public void addNodeToHeadOfList() {
        allocateNode();
        workNodePtr = listHeadPtr;
        currNodePtr.nextNodePtr = listHeadPtr;
        if (workNodePtr != null) {
            workNodePtr.prevNodePtr = currNodePtr;
        }
        listHeadPtr = currNodePtr;
    }

    public void addNodeToTailOfList() {
        allocateNode();
        workNodePtr = listTailPtr;
        currNodePtr.prevNodePtr = listTailPtr;
        if (workNodePtr != null) {
            workNodePtr.nextNodePtr = currNodePtr;
        }
        listTailPtr = currNodePtr;
    }

    public void addNewNodeAfterCurr() {
        workNodePtr = currNodePtr;
        allocateNode();
        currNodePtr.prevNodePtr = workNodePtr;
        currNodePtr.nextNodePtr = workNodePtr.nextNodePtr;
        if (workNodePtr.nextNodePtr != null) {
            workNodePtr.nextNodePtr.prevNodePtr = currNodePtr;
        }
        workNodePtr.nextNodePtr = currNodePtr;
        if (listTailPtr == workNodePtr) {
            listTailPtr = currNodePtr;
        }
    }

    public void addNewNodeBeforeCurr() {
        workNodePtr = currNodePtr;
        allocateNode();
        currNodePtr.nextNodePtr = workNodePtr;
        currNodePtr.prevNodePtr = workNodePtr.prevNodePtr;
        if (workNodePtr.prevNodePtr != null) {
            workNodePtr.prevNodePtr.nextNodePtr = currNodePtr;
        }
        workNodePtr.prevNodePtr = currNodePtr;
        if (listHeadPtr == workNodePtr) {
            listHeadPtr = currNodePtr;
        }
    }

    public void removeCurrNode() {
        if (currNodePtr.nextNodePtr != null) {
            currNodePtr.nextNodePtr.prevNodePtr = currNodePtr.prevNodePtr;
        }
        if (currNodePtr.prevNodePtr != null) {
            currNodePtr.prevNodePtr.nextNodePtr = currNodePtr.nextNodePtr;
        }
        if (listHeadPtr == currNodePtr) {
            listHeadPtr = currNodePtr.nextNodePtr;
        }
        if (listTailPtr == currNodePtr) {
            listTailPtr = currNodePtr.prevNodePtr;
        }
        System.out.println("freeing " + currNodePtr);
        currNodePtr = null;
    }
}