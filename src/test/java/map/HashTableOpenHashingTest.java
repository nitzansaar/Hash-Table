package map;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigInteger;

import static map.HashTableOpenHashing.polyHash;

public class HashTableOpenHashingTest extends TestCase {

    @Test
    public void testPolyHash() {
        String key1 = "hello";
        String key2 = "world";
        String key3 = "hello";
        String key4 = "hEllo";

        BigInteger hash1 = polyHash(key1);
        BigInteger hash2 = polyHash(key2);
        BigInteger hash3 = polyHash(key3);
        BigInteger hash4 = polyHash(key4);

        System.out.println("Hash for key1: " + hash1);
        System.out.println("Hash for key2: " + hash2);
        System.out.println("Hash for key3: " + hash3);
        System.out.println("Hash for key4: " + hash4);

        assertEquals(hash1, hash3);
        assertNotSame(hash1, hash2);
        assertNotSame(hash1, hash4);
        assertEquals(hash1, hash1);
    }

    @Test
    public void testPut() {
        Map map = new HashTableOpenHashing(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");
        map.put("four", "cuantro");
        map.put("five", "cinco");
        map.put("six", "seis");
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
        System.out.println("All tests passed for get :)");
    }
}