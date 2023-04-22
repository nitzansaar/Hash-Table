package map;

/** An entry in the hash table. Stores a key, a corresponding value and a boolean flag */
public class HashEntry {
    public HashEntry next;
    private String key;
    private Object value;
    private boolean deleted; // if there was previously a value here that was later deleted.
    public String toString() {
        return key + ", " + value + ", " + deleted;
    }
    /**
     * Constructor for class HashEntry
     * @param key key
     * @param value corresponding value
     */
    public HashEntry(String key, Object value) {
        this.key = key;
        this.value  = value;
        this.deleted = false;
    }

    /**
     * Returns the key stored in the entry
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the value stored in the entry
     * @return value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Returns true if this entry was deleted
     * @return true if the entry was deleted and false otherwise
     */
    public boolean isDeleted() {
        return deleted;
    }

    /** Sets the value of the "deleted" attribute
     *
     * @param flag new boolean value of the "deleted" variable
     */
    public void setDeleted(boolean flag) {
        deleted = flag;
    }

}
