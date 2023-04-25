package map;

import java.math.BigInteger;
/** The class that implements the Map interface using closed hashing;
 *  uses double hashing to resolve collisions */
public class HashTableClosedHashingDH implements Map {
    private HashEntry[] hashTable;
    private int numEntries;
    private int maxSize;
    private int q;

    public HashTableClosedHashingDH(int n) {
        maxSize = n;
        hashTable = new HashEntry[maxSize];
        numEntries = 0;
        q = nextPrime((int) Math.ceil(maxSize / 2.0));
    }
    /** Return true if the map contains a (key, value) pair associated with this key,
     *  otherwise return false.
     *
     * @param key  key
     * @return true if the key (and the corresponding value) is the in map
     */
    public boolean containsKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(maxSize));
        int hK = hashVal.mod(len).intValue();
        int dK = q - (hashVal.mod(BigInteger.valueOf(q)).intValue());

        for (int i = 1; i < maxSize; i++) {
            if (hashTable[hK] == null) {
                return false;
            } else if (!hashTable[hK].isDeleted() && hashTable[hK].getKey().equals(key)) {
                return true;
            }
            hK = (hK + (i * dK)) % hashTable.length;
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
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        if ((double) numEntries / hashTable.length > 0.6) {
            rehash();
        }
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(maxSize));
        int hK = hashVal.mod(len).intValue();
        int dK = q - (hashVal.mod(BigInteger.valueOf(q)).intValue());
        for (int i = 1; hashTable[hK] != null; i++) {
            hK = (hK + (i * dK)) % maxSize;
        }
        numEntries++;
        hashTable[hK] = new HashEntry(key, value);
        hashTable[hK].setDeleted(false);
    }

    /** Return the value associated with the given key or null, if the map does not contain the key.
     * If the key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @return value associated value
     */
    public Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(maxSize));
        int hK = hashVal.mod(len).intValue();
        int dK = q - (hashVal.mod(BigInteger.valueOf(q)).intValue());

        for (int i = 1; i < maxSize; i++) {
            if (hashTable[hK] == null) {
                return null;
            } else if (!hashTable[hK].isDeleted() && hashTable[hK].getKey().equals(key)) {
                return hashTable[hK].getValue();
            }
            hK = (hK + (i * dK)) % hashTable.length;
        }
        return null;
    }

    /** Remove a (key, value) entry if it exists.
     * Return the previous value associated with the given key, otherwise return null
     * @param key key
     * @return previous value
     */
    public Object remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(maxSize));
        int hK = hashVal.mod(len).intValue();
        int dK = q - (hashVal.mod(BigInteger.valueOf(q)).intValue());

        for (int i = 1; i < maxSize; i++) {
            if (hashTable[hK] == null) {
                return null;
            } else if (!hashTable[hK].isDeleted() && hashTable[hK].getKey().equals(key)) {
                Object oldValue = hashTable[hK].getValue();
                hashTable[hK].setDeleted(true);
                numEntries--;
                return oldValue;
            }
            hK = (hK + (i * dK)) % maxSize;
        }
        return null;
    }

    /** Return the actual number of elements in the map.
     *
     * @return number of elements currently in the map.
     */
    public int size() {
        return numEntries;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxSize; i++) {
            sb.append(i).append(": ");
            if (hashTable[i] == null) {
                sb.append("null");
            } else {
                sb.append("(").append(hashTable[i].getKey()).append(", ").append(hashTable[i].getValue()).append(", ").
                        append(hashTable[i].isDeleted()).append(")");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }



    private BigInteger polyHash(String key) {
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

}