package map;

import junit.framework.TestCase;

public class HashTableClosedHashingLPTest extends TestCase {

    public void testPut() {
        HashTableClosedHashingLP map = new HashTableClosedHashingLP(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");
        map.put("four", "cuatro");
        map.put("five", "cinco");
        map.put("six", "seis");

        String val1 = (String) map.get("one");
        String val2 = (String) map.get("two");
        String val3 = (String) map.get("three");
        String val4 = (String) map.get("four");
        String val5 = (String) map.get("five");
        String val6 = (String) map.get("six");

        assertEquals("The put method does not store the correct value for key 'one'", "uno", val1);
        assertEquals("The put method does not store the correct value for key 'two'", "do", val2);
        assertEquals("The put method does not store the correct value for key 'three'", "tres", val3);
        assertEquals("The put method does not store the correct value for key 'four'", "cuatro", val4);
        assertEquals("The put method does not store the correct value for key 'five'", "cinco", val5);
        assertEquals("The put method does not store the correct value for key 'six'", "seis", val6);
    }
}