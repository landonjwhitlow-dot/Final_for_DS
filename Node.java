/**
 * Node.java
 * Represents a single node in the custom LinkedList.
 * Each node stores one Assignment and a reference to the next node.
 * Author: Landon Whitlow
 */
public class Node {

    Assignment data;
    Node next;

    // Constructor
    public Node(Assignment data) {
        this.data = data;
        this.next = null;
    }
}
