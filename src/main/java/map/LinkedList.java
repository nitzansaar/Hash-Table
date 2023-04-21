package map;

/** A custom LinkedList class to be used in the open hashing / separate chaining implementation
 * of a hash table. You need class Node too.
 */
public class LinkedList<H> {
    // FILL IN CODE
    public class Node {
        Node next = null;
    }

    Node head = null;

    // insert node in front of LL
    public void insert(Node node) {
        if (head == null) {
            head = node;
        }
        node.next = head;
        head = node;


    }


}
