/**
 * CustomLinkedList.java
 * Key Operations:
 * - add(): adds assignment to end
 * - remove(): removes assignment by title
 * - find(): finds assignment by title
 * - toArray(): converts list to array for sorting
 * This class demonstrates manual LinkedList implementation
 * without using Java built-in collections.
 * Author: Landon Whitlow
 */
public class CustomLinkedList {

    private Node head;
    private int size;


    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }
    public void add(Assignment assignment) {
        Node newNode = new Node(assignment);


        if (head == null) {
            head = newNode;
        } else {

            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    public boolean remove(String title) {
        if (head == null) {
            return false;  // Nothing to remove
        }
        if (head.data.getTitle().equalsIgnoreCase(title)) {
            head = head.next;
            size--;
            return true;
        }


        Node current = head;
        while (current.next != null) {
            if (current.next.data.getTitle().equalsIgnoreCase(title)) {
                // Skip over the matching node
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }
    public void displayAll() {
        if (head == null) {
            System.out.println("(no assignments)");
            return;
        }

        Node current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.data.toString());
            current = current.next;
            count++;
        }
    }
    public Assignment find(String title) {
        Node current = head;
        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    public Assignment[] toArray() {
        Assignment[] arr = new Assignment[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            arr[i] = current.data;
            current = current.next;
            i++;
        }
        return arr;
    }

    public int getSize() {
        return size;
    }
}
