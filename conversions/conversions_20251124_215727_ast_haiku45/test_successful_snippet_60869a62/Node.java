public class Node {
    public Node prevNode;
    public Node nextNode;
    public String nodeData;

    public Node() {
        this.prevNode = null;
        this.nextNode = null;
        this.nodeData = "        ";
    }
}

public class lldemo1 {
    private static Node currNode;
    private static Node workNode;
    private static Node tempNode;
    private static Node listHeadPtr;
    private static Node listTailPtr;

    public static void main(String[] args) {
        allocateNode();
        currNode.nodeData = "NODE3   ";
        listHeadPtr = currNode;
        listTailPtr = currNode;
        tempNode = currNode;

        addNodeToTailOfList();
        currNode.nodeData = "NODE4   ";

        addNewNodeAfterCurr();
        currNode.nodeData = "NODE5   ";

        addNodeToHeadOfList();
        currNode.nodeData = "NODE2   ";

        addNewNodeBeforeCurr();
        currNode.nodeData = "NODE1   ";

        System.out.println("five nodes allocated");
        printList();

        System.out.println("removing list tail");
        currNode = listTailPtr;
        removeCurrentNode();

        System.out.println("four nodes allocated");
        printList();

        System.out.println("removing list head");
        currNode = listHeadPtr;
        removeCurrentNode();

        System.out.println("three nodes allocated");
        printList();

        System.out.println("removing middle entry from list");
        currNode = tempNode;
        removeCurrentNode();

        System.out.println("free the rest of the list");

        currNode = listHeadPtr;
        while (currNode != null) {
            removeCurrentNode();
            currNode = listHeadPtr;
        }
    }

    private static void printList() {
        currNode = listHeadPtr;

        while (currNode != null) {
            System.out.println(System.identityHashCode(currNode) + " " + 
                             currNode.nodeData + " " + 
                             (currNode.prevNode != null ? System.identityHashCode(currNode.prevNode) : "null") + " " + 
                             (currNode.nextNode != null ? System.identityHashCode(currNode.nextNode) : "null"));
            currNode = currNode.nextNode;
        }
    }

    private static void allocateNode() {
        currNode = new Node();
        System.out.println("CURR-NODE-PTR = " + System.identityHashCode(currNode));
    }

    private static void addNodeToHeadOfList() {
        allocateNode();

        workNode = listHeadPtr;
        workNode.prevNode = currNode;
        currNode.nextNode = listHeadPtr;
        listHeadPtr = currNode;
    }

    private static void addNodeToTailOfList() {
        allocateNode();

        workNode = listTailPtr;
        workNode.nextNode = currNode;
        currNode.prevNode = listTailPtr;
        listTailPtr = currNode;
    }

    private static void addNewNodeAfterCurr() {
        workNode = currNode;

        allocateNode();
        currNode.prevNode = workNode;
        currNode.nextNode = workNode.nextNode;
        workNode.nextNode = currNode;

        if (listTailPtr == workNode) {
            listTailPtr = currNode;
        }
    }

    private static void addNewNodeBeforeCurr() {
        workNode = currNode;

        allocateNode();
        currNode.nextNode = workNode;
        currNode.prevNode = workNode.prevNode;
        workNode.prevNode = currNode;

        if (listHeadPtr == workNode) {
            listHeadPtr = currNode;
        }
    }

    private static void removeCurrentNode() {
        if (currNode.nextNode != null) {
            workNode = currNode.nextNode;
            workNode.prevNode = currNode.prevNode;
        }

        if (currNode.prevNode != null) {
            workNode = currNode.prevNode;
            workNode.nextNode = currNode.nextNode;
        }

        if (listHeadPtr == currNode) {
            listHeadPtr = currNode.nextNode;
        }

        if (listTailPtr == currNode) {
            listTailPtr = currNode.prevNode;
        }

        System.out.println("freeing " + System.identityHashCode(currNode));
    }
}