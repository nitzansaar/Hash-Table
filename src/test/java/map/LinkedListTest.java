package map;

import junit.framework.TestCase;
import org.junit.Test;

public class LinkedListTest extends TestCase {

    @Test
    public void testInsert() {
        LinkedList<H> list = new LinkedList<H>();

        LinkedList.Node node1 = list.new Node();
        LinkedList.Node node2 = list.new Node();
        LinkedList.Node node3 = list.new Node();

        list.insert(node1);
        list.insert(node2);
        list.insert(node3);

        assertEquals(node3, list.head);
        assertEquals(node2, list.head.next);
        assertEquals(node1, list.head.next.next);
    }

}