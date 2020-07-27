import java.util.List;

public interface Map<K, V> {

  /**
   * Retrieve a value from the map via its key. Should be O(height).
   *
   * @param key : the key to be looked up
   * @return : the value associated with that key or null if the key is not in the
   *         map
   **/
  public V get(K key);

  /**
   * Set a key to an associated value. If the key is present, the value is
   * updated. If the key is not present, a new key,value is inserted into the map.
   * Should be O(height).
   *
   * @param key   : the key to be be set
   * @param value : the value associated with that key
   **/
  public void set(K key, V value);

  /**
   * Retrieve the number of distinct keys stored in the map. Should be O(1).
   *
   * @return : the number of distinct keys stored in the map
   **/
  public int size();

  /**
   * Returns a List of unique keys stored in the map. The order of the keys should
   * match the order of the list return by values(). Should be O(n). You should
   * import and use java.util.ArrayList or java.util.LinkedList for this.
   *
   * @return : a list of distinct keys stored in the map
   **/
  public List<K> keys();

  /**
   * Returns a List of unique values stored in the map. The order of the values
   * should match the order of the list return by keys(). Should be O(n). You
   * should import and use java.util.ArrayList or java.util.LinkedList for this.
   *
   * @return : a list of values stored in the map
   **/
  public List<V> values();

}

