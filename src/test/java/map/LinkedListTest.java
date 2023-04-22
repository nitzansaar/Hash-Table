package map;

import junit.framework.TestCase;
import org.junit.Test;

public class LinkedListTest extends TestCase {

    @Test
    public void testInsert() {
        LinkedList list = new LinkedList();

        String key = "Hello";
        String key1 = "World";
        String key2 = "Howdy";

        // Create some nodes to insert
        HashEntry node1 = new HashEntry(key, null);
        HashEntry node2 = new HashEntry(key1, null);
        HashEntry node3 = new HashEntry(key2, null);


        // Insert the nodes
        list.insert(node1);
        list.insert(node2);
        list.insert(node3);

        // Verify that the nodes are in the correct order
        assertEquals(node3, list.head);
        assertEquals(node2, list.head.next);
        assertEquals(node1, list.head.next.next);
    }

}