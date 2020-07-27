import java.util.List;

/**
 * this class uses a AVLTree implementation of a set. Using the proper inherited
 * methods
 * 
 * @param <K> generic key type
 * @author Brandon Duncan
 */
public class AVLSet<K extends Comparable<K>> implements Set<K> {
    private AVLMap<K, Boolean> map;
    private int size;

    /**
     * this is the constructor which initialize a new AVLMap of the type K and
     * Boolean
     */
    public AVLSet() {
        map = new AVLMap<K, Boolean>();
    }

    /**
     * this method adds a key to our set and sets the value of that key to TRUE
     * 
     * @param key the desired key type.
     */
    public void add(K key) {
        map.set(key, Boolean.TRUE);
        size++;
    }

    /**
     * this method returns true if the key is already in the set.
     * 
     * @param key the key to be searched for
     * @return true if in map
     */
    public boolean contains(K key) {
        return map.get(key) != null;
    }

    /**
     * this method returns the size of the set.
     * 
     * @return size of set.
     */
    public int size() {
        return size;
    }

    /**
     * this method returns the keys of the set in a List.
     * 
     * @return List<K> of keys in set
     */
    public List<K> toList() {
        return map.keys();
    }
}