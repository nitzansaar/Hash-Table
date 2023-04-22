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
    public void put(String key, Object value) {
        // FILL IN CODE
        if (key == null) {
            throw new IllegalArgumentException("Invalid key");
        }
        numEntries++;
        BigInteger hashVal = polyHash(key);
        BigInteger len = new BigInteger(String.valueOf(hashTable.length));
        // now we determine where to store
        BigInteger index = hashVal.mod(len);
        // now we store the object in the correct index in the array of linked lists
        HashEntry entry = new HashEntry(key, value);

        if (hashTable[index.intValue()] == null) { // if the index is null, we need to create a new linked-list for that index
            hashTable[index.intValue()] = new LinkedList<>();
        }
        hashTable[index.intValue()].insert(entry);
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
        // FILL IN CODE
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
                // Key found, remove the entry
                if (prevNode == null) {
                    // The entry to be removed is the first one in the list
                    list.head = currNode.next;
                } else {
                    prevNode.next = currNode.next;
                }
                numEntries--;
                return currNode.value.getValue(); // Return the removed value
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
        // FILL IN CODE
        return numEntries;

    }

    public String toString() {
        // FILL IN CODE
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

    // Add may implement other helper methods as needed

    /* Polynomial hash helper method */
    static BigInteger polyHash(String key) {
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
}
