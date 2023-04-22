package map;

import junit.framework.TestCase;
import org.junit.Assert;
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

}