package map;

/** A custom LinkedList class to be used in the open hashing / separate chaining implementation
 * of a hash table. You need class Node too.
 */
public class LinkedList<H> {

    // FILL IN CODE
    public class Node {
        H value;
        Node next = null;

        public Node(H value) {
            this.value = value;
        }
    }

    Node head = null;

    // insert node in front of LL
    public void insert(H value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }


}
