package map;

import java.math.BigInteger;

public class HashTableOpenHashing implements Map {
    private LinkedList<HashEntry>[] hashTable; // array of linked-lists
    private int numEntries; // number of entries in hashTable

    public HashTableOpenHashing(int n) {
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
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        BigInteger index = hashVal.mod(len);

        LinkedList<HashEntry> list = hashTable[index.intValue()];
        if (list == null) {
            return false;
        }

        LinkedList<HashEntry>.Node currentNode = list.head;
        while (currentNode != null) {
            if (currentNode.value.getKey().equals(key)) {
                return true;
            }
            currentNode = currentNode.next;
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
    /*
    Before inserting a new entry, check the load factor which is equal to the number of entries
divided by the maximum size of the table. If the load factor is greater than 0.6, allocate a
new array whose size is the smallest prime number that is larger than 2*max_size.
     */
    public void put(String key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }

        double loadFactor = (double) numEntries / hashTable.length;

        if (loadFactor > 0.6) {
            // get the smallest prime number larger than 2 * max_size
            int newSize = nextPrime(2 * hashTable.length);

            // allocate a new array
            LinkedList<HashEntry>[] newHashTable = new LinkedList[newSize];

            // rehash the entries from the old table to the new table
            for (int i = 0; i < hashTable.length; i++) {
                if (hashTable[i] != null) {
                    LinkedList<HashEntry>.Node currentNode = hashTable[i].head;
                    while (currentNode != null) {
                        HashEntry entry = currentNode.value;
                        BigInteger hashVal = polyHash(entry.getKey());
                        BigInteger len = new BigInteger(String.valueOf(newHashTable.length));
                        BigInteger index = hashVal.mod(len);

                        if (newHashTable[index.intValue()] == null) {
                            newHashTable[index.intValue()] = new LinkedList<>();
                        }
                        newHashTable[index.intValue()].insert(entry);
                        currentNode = currentNode.next;
                    }
                }
            }
            hashTable = newHashTable;
        }

        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        BigInteger index = hashVal.mod(len);
        HashEntry entry = new HashEntry(key, value);

        if (hashTable[index.intValue()] == null) {
            hashTable[index.intValue()] = new LinkedList<>();
        }
        hashTable[index.intValue()].insert(entry);
        numEntries++;
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
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        BigInteger index = hashVal.mod(len);

        LinkedList<HashEntry> list = hashTable[index.intValue()];
        if (list == null) {
            return null; // map does not contain key
        }

        LinkedList<HashEntry>.Node currentNode = list.head;
        while (currentNode != null) {
            if (currentNode.value.getKey().equals(key)) {
                return currentNode.value.getValue();
            }
            currentNode = currentNode.next;
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
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        BigInteger index = hashVal.mod(len);

        LinkedList<HashEntry> list = hashTable[index.intValue()];
        if (list == null) {
            return null; // map does not contain key
        }

        LinkedList<HashEntry>.Node currNode = list.head;
        LinkedList<HashEntry>.Node prevNode = null;
        while (currNode != null) {
            if (currNode.value.getKey().equals(key)) {
                // key found, remove the entry
                if (prevNode == null) {
                    list.head = currNode.next;
                } else {
                    prevNode.next = currNode.next;
                }
                numEntries--;
                return currNode.value.getValue();
            }
            prevNode = currNode;
            currNode = currNode.next;
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
        // go through the hashTable and append each hashEntry to sb
        for (int i = 0; i < hashTable.length; i++) {
            sb.append(i + ": ");
            if (hashTable[i] != null) {
                LinkedList<HashEntry>.Node curr = hashTable[i].head;
                while (curr != null) {
                    sb.append("(" + curr.value.toString() + ")");
                    curr = curr.next;
                    if (curr != null) {
                        sb.append(", ");
                    }
                }
            } else {
                sb.append("null");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

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
