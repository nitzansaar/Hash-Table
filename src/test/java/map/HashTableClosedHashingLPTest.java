package map;

import junit.framework.TestCase;
import org.junit.Test;

public class HashTableClosedHashingLPTest extends TestCase {

    @Test
    public void testPut() {
        Map map = new HashTableClosedHashingLP(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");

        assertEquals("uno", map.get("one"));
        assertEquals("do", map.get("two"));
        assertEquals("tres", map.get("three"));
    }

    @Test
    public void testGet() {
        Map map = new HashTableClosedHashingLP(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");

        assertEquals("uno", map.get("one"));
        assertEquals("do", map.get("two"));
        assertEquals("tres", map.get("three"));
    }

    @Test
    public void testRemove() {
        Map map = new HashTableClosedHashingLP(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");

        assertEquals("uno", map.remove("one"));
        assertNull(map.get("one"));
        assertEquals("do", map.remove("two"));
        assertNull(map.get("two"));
    }

    @Test
    public void testContainsKey() {
        Map map = new HashTableClosedHashingLP(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");

        assertTrue(map.containsKey("one"));
        assertTrue(map.containsKey("two"));
        assertTrue(map.containsKey("three"));
        assertFalse(map.containsKey("four"));
    }
}