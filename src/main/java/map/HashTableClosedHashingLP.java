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
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }

        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        int index = hashVal.mod(len).intValue();

        while (hashTable[index] != null) {
            if (!hashTable[index].isDeleted() && hashTable[index].getKey().equals(key)) {
                return true;
            }
            index = (index + 1) % hashTable.length;
        }
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

        int firstDeletedIndex = -1;
        while (hashTable[index] != null && !hashTable[index].getKey().equals(key)) {
            if (hashTable[index].isDeleted() && firstDeletedIndex == -1) {
                firstDeletedIndex = index;
            }
            index = (index + 1) % hashTable.length;
        }

        if (hashTable[index] == null || hashTable[index].isDeleted()) {
            if (firstDeletedIndex != -1) {
                index = firstDeletedIndex;
            }
            numEntries++;
        }

        hashTable[index] = new HashEntry(key, value);
        hashTable[index].setDeleted(false);
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
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        int index = hashVal.mod(len).intValue();

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[index] != null && !hashTable[index].isDeleted() && hashTable[index].getKey().equals(key)) {
                Object prevValue = hashTable[index].getValue();
                hashTable[index].setDeleted(true);
                numEntries--;
                return prevValue;
            }
            index = (index + 1) % hashTable.length;
        }
        return null;
    }

    /** Return the actual number of elements in the map.
     *
     * @return number of elements currently in the map.
     */
    public int size() {
        // FILL IN CODE
        return numEntries;
    }

    public String toString() {
        // FILL IN CODE
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashTable.length; i++) {
            sb.append(i).append(": ");
            if (hashTable[i] == null) {
                sb.append("null");
            } else {
                sb.append("(").append(hashTable[i].getKey()).append(", ")
                        .append(hashTable[i].getValue()).append(", ")
                        .append(hashTable[i].isDeleted()).append(")");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
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
