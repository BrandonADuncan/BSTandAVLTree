/**
 * this is a pair class which holds a key and value pair
 * 
 * @param <K> key to be stored in pair
 * @param <V> value to be stored in pair
 * @author Brandon Duncan
 */
public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
    private K key;
    private V value;

    /**
     * this constructor creates a new instance of a Pair using the Key/Value.
     * 
     * @param key   desired key of pair
     * @param value desired value of pair
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * this method sets the key of the pair.
     * 
     * @param key desired key of pair
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * this method sets the value of the pair
     * 
     * @param value desired value of pair
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * this method returns the pairs key
     * 
     * @return pair's key
     */
    public K getKey() {
        return key;
    }

    /**
     * this method returns the pair's value
     * 
     * @return value of pair
     */
    public V getValue() {
        return value;
    }

    /**
     * this method compares the key of this pair to the key of the desired pair
     * 
     * @return positive number, 0, or negative number to indicate >,<,==
     */
    public int compareTo(Pair<K, V> p) {
        return key.compareTo(p.getKey());
    }
}