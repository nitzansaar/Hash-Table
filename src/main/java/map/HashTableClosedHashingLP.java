package map;

import java.math.BigInteger;

/** The class that implements the Map interface using closed hashing;
 *  uses linear probing to resolve collisions */
public class HashTableClosedHashingLP implements Map {
    // Add instance variables - TODO
    private int numEntries;
    private HashEntry[] hashTable;

    public HashTableClosedHashingLP(int n) {
        // FILL IN CODE
        this.hashTable = new HashEntry[n];
        this.numEntries = 0;

    }

    /** Return true if the map contains a (key, value) pair associated with this key,
     *  otherwise return false.
     *
     * @param key  key
     * @return true if the key (and the corresponding value) is the in map
     */
    public boolean containsKey(String key) {
        // FILL IN CODE
        return false;
    }

    /** Add (key, value) to the map.
     * Will replace previous value that this key was mapped to.
     * If key is null, throw IllegalArgumentException.
     *
     * @param key
     * @param value associated value
     */
    public void put(String key, Object value) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        if ((double) numEntries / hashTable.length > 0.6) {
            rehash();
        }
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        int index = hashVal.mod(len).intValue();

        while (hashTable[index] != null && !hashTable[index].getKey().equals(key)) {
            index = (index + 1) % hashTable.length;
        }
        if (hashTable[index] == null) {
            numEntries++;
        }
        hashTable[index] = new HashEntry(key, value);
    }

    /** Return the value associated with the given key or null, if the map does not contain the key.
     * If the key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @return value associated value
     */
    public Object get(String key) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        int index = hashVal.mod(len).intValue();

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[index] != null && !hashTable[index].isDeleted() && hashTable[index].getKey().equals(key)) {
                return hashTable[index].getValue();
            }
            index = (index + 1) % hashTable.length;
        }
        return null;

    }

    /** Remove a (key, value) entry if it exists.
     * Return the previous value associated with the given key, otherwise return null
     * @param key key
     * @return previous value
     */
    public Object remove(String key) {
        // FILL IN CODE

        return null;
    }

    /** Return the actual number of elements in the map.
     *
     * @return number of elements currently in the map.
     */
    public int size() {
        // FILL IN CODE

        return 0;
    }

    public String toString() {
        // FILL IN CODE

        return "";
    }

    // Add may implement other helper methods as needed
    /* Polynomial hash helper method */
    private BigInteger polyHash(String key) {
        int degree = key.length() - 1;
        int a = 33; // other good values are 37, 39
        BigInteger res = BigInteger.valueOf(0);
        for (int i = 0; i < key.length(); i++) {
            BigInteger temp = BigInteger.valueOf(key.charAt(i));
            BigInteger power = BigInteger.valueOf(a).pow(degree);
            res = res.add(temp.multiply(power));
            degree--;
        }
        return res;
    }
    private void rehash() {
        int newSize = nextPrime(2 * hashTable.length);
        HashEntry[] oldHashTable = hashTable;
        hashTable = new HashEntry[newSize];
        numEntries = 0;

        for (HashEntry entry : oldHashTable) {
            if (entry != null && !entry.isDeleted()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private int nextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


}
