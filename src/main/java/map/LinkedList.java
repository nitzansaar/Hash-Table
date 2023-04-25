


package map;

/** A custom LinkedList class to be used in the open hashing / separate chaining implementation
 * of a hash table. You need class Node too.
 */
public class LinkedList<Type> {

    public class Node {
        Type value;
        Node next = null;

        public Node(Type value) {
            this.value = value;
        }
    }

    Node head = null;

    // insert node in front of LL
    public void insert(Type value) {
        Node newNode = new Node(value);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
    }



}