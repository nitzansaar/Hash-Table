package map;

import java.math.BigInteger;

public class HashTableOpenHashing implements Map {
    // Add instance variables -
    private LinkedList<HashEntry>[] hashTable; // array of linked-lists
    private int numEntries; // number of entries in hashTable

    public HashTableOpenHashing(int n) {
        // FILL IN CODE
        this.hashTable = new LinkedList[n];
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
    }

    /** Return the value associated with the given key or null, if the map does not contain the key.
     * If the key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @return value associated value
     */
    public Object get(String key) {
        // FILL IN CODE

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
    static BigInteger polyHash(String key) {
        int degree = key.length() - 1;
        int a = 33;
        BigInteger res = BigInteger.valueOf(0);
        for (int i = 0; i < key.length(); i++) {
            BigInteger temp = BigInteger.valueOf(key.charAt(i));
            BigInteger power = BigInteger.valueOf(a).pow(degree);
            res = res.add(temp.multiply(power));
            degree--;
        }
        return res;
    }
}
