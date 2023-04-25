package map;

import junit.framework.TestCase;
import org.junit.Assert;

public class HashTableClosedHashingDHTest extends TestCase {

    public void testContainsKey() {
        Map map = new HashTableClosedHashingDH(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");
        map.put("four", "cuantro");
        map.put("five", "cinco");
        map.put("six", "seis");
        System.out.println(map.toString());
        boolean b1 = map.containsKey("one");
        boolean b2 = map.containsKey("three");
        boolean b3 = map.containsKey("five");
        boolean b4 = map.containsKey("seven");
        Assert.assertEquals(true, b1);
        Assert.assertEquals(true, b2);
        Assert.assertEquals(true, b3);
        Assert.assertEquals(true, !b4);
    }

    public void testPut() {
        Map map = new HashTableClosedHashingDH(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");
        map.put("four", "cuantro");
        map.put("five", "cinco");
        map.put("six", "seis");
        System.out.println(map.toString());
    }

    public void testGet() {
        Map map = new HashTableClosedHashingDH(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");

        assertEquals("uno", map.get("one"));
        assertEquals("do", map.get("two"));
        assertEquals("tres", map.get("three"));
    }

    public void testRemove() {
        Map map = new HashTableClosedHashingDH(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");

        assertEquals("uno", map.remove("one"));
        assertNull(map.get("one"));
        assertEquals("do", map.remove("two"));
        assertNull(map.get("two"));

    }

    public void testSize() {
        Map map = new HashTableClosedHashingDH(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");
        assertEquals(map.size(), 3);
    }


}