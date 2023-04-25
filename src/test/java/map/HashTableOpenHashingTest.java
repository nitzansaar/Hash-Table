package map;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigInteger;

public class HashTableOpenHashingTest extends TestCase {


// need to make method public to test
//    @Test
//    public void testPolyHash() {
//        String key1 = "hello";
//        String key2 = "world";
//        String key3 = "hello";
//        String key4 = "hEllo";
//
//        BigInteger hash1 = polyHash(key1);
//        BigInteger hash2 = polyHash(key2);
//        BigInteger hash3 = polyHash(key3);
//        BigInteger hash4 = polyHash(key4);
//
//        System.out.println("Hash for key1: " + hash1);
//        System.out.println("Hash for key2: " + hash2);
//        System.out.println("Hash for key3: " + hash3);
//        System.out.println("Hash for key4: " + hash4);
//
//        assertEquals(hash1, hash3);
//        assertNotSame(hash1, hash2);
//        assertNotSame(hash1, hash4);
//        assertEquals(hash1, hash1);
//        System.out.println("All tests passed for polyHash :)");
//
//    }

    @Test
    public void testPut() {
        Map map = new HashTableOpenHashing(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");
        map.put("four", "cuantro");
        map.put("five", "cinco");
        map.put("six", "seis");
        map.put("rehash", "timeToRehash");
        System.out.println(map.toString()); // tests toString()
    }

    @Test
    public void testGet() {
        Map map = new HashTableOpenHashing(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");
        map.put("four", "cuatro");
        map.put("five", "cinco");
        map.put("six", "seis");

        assertEquals("uno", map.get("one"));
        assertEquals("do", map.get("two"));
        assertEquals("tres", map.get("three"));
        assertEquals("cuatro", map.get("four"));
        assertEquals("cinco", map.get("five"));
        assertEquals("seis", map.get("six"));
        assertNull(map.get("seven"));
        assertNotSame("seis", map.get("one"));
        System.out.println("\nAll tests passed for get :)");
    }

    @Test
    public void testSize() {
        HashTableOpenHashing table = new HashTableOpenHashing(10);

        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);

        int expectedSize = 3;
        assertEquals(expectedSize, table.size());

        table.put("four", 4);
        expectedSize = 4;
        assertEquals(expectedSize, table.size());

        table.remove("four");
        assertNotSame(table.size(), expectedSize);

        System.out.println("\nAll tests passed for size :)");

    }

    @Test
    public void testRemove() {
        HashTableOpenHashing table = new HashTableOpenHashing(10);

        table.put("one", 1);
        table.put("two", 2);
        table.put("three", 3);

        Object removedValue = table.remove("two");
        assertEquals(2, removedValue);
        assertFalse(table.containsKey("two"));

        removedValue = table.remove("one");
        assertEquals(1, removedValue);
        assertFalse(table.containsKey("one"));

        removedValue = table.remove("nonexistent_key");
        assertNull(removedValue);

        int expectedSize = 1;
        assertEquals(expectedSize, table.size());
        System.out.println("\nAll tests passed for remove :)");

    }
}