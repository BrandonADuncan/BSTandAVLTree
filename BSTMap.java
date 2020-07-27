import java.util.ArrayList;
import java.util.List;

/**
 * This class is a non-balancing BST implementation of a Map to solve the unique
 * problem presented by the DEA in Proj. 2 the psuedo-code,general outline, and
 * much of the syntax is from the IC312 course website presented in the notes.
 * 
 * @param <K> the generic K is the key value
 * @param <V> the generic V is the value of our key
 * @author IC312 Instructors
 * @author Brandon Duncan
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

  /**
   * This is our Node class for a node on our BST
   */
  private class Node {
    public Node left;
    public Node right;
    public Pair<K, V> data;

    /**
     * This is our constructor for the pair class which holds a key, and a value
     * 
     * @param p
     */
    public Node(Pair<K, V> p) {
      data = p;
      left = null;
      right = null;
    }
  }

  // initialize the root as null
  private Node root = null;
  private int size;

  /**
   * this method returns true if the key is found
   * 
   * @param key the key you are looking for
   * @return true if found
   */
  public boolean find(K key) {
    if (key == null) {
      return false;
    }
    return findFrom(root, key);
  }

  /**
   * This is the pivate method for our find method
   * 
   * @param n   the root of our BST
   * @param key the key we are searching for
   * @return true if the key is found in the BST
   */
  private boolean findFrom(Node n, K key) {
    if (n == null)
      return false;
    else if (key.compareTo(n.data.getKey()) == 0)
      return true;
    else if (key.compareTo(n.data.getKey()) == -1)
      return findFrom(n.left, key);
    else
      return findFrom(n.right, key);
  }

  /**
   * this method inserts a new pair into our BST
   * 
   * @param p the pair desired to be inserted
   */
  public void insert(Pair<K, V> p) {
    root = insert(root, p);
    size++;
  }

  /**
   * The private recursive method for insert
   * 
   * @param n root of BST
   * @param p pair being inserted
   * @return new root of BST
   */
  private Node insert(Node n, Pair<K, V> p) {
    if (n == null)
      return new Node(p);
    if (p.getKey().compareTo(n.data.getKey()) == -1 || p.getKey().compareTo(n.data.getKey()) == 0)
      n.left = insert(n.left, p);
    else
      n.right = insert(n.right, p);
    return n;
  }

  /**
   * this method returns the value of the desired key or null if empty
   * 
   * @param key the desired key to read the value of
   * @return value of the key
   */
  public V get(K key) {
    if (find(key))
      return get(root, key);

    else
      return null;
  }

  /**
   * this is the private recursive implementation of the get method
   * 
   * @param n   the root of the BST
   * @param key the key we desire to get the value of
   * @return value of desired key or null.
   */
  private V get(Node n, K key) {
    if (key.compareTo(n.data.getKey()) == 0)
      return n.data.getValue();
    else if (key.compareTo(n.data.getKey()) == -1)
      return get(n.left, key);
    else
      return get(n.right, key);
  }

  /**
   * set an existing key to a new value, or insert a new key with the new value
   * 
   * @param key   key to be changed or inserted
   * @param value the new value associated with the key
   */
  public void set(K key, V value) {
    Pair<K, V> p = new Pair<K, V>(key, value);
    if (find(key) == false)
      insert(p);
    else
      set(root, p);
  }

  /**
   * private recursive method of set
   * 
   * @param n root of the BST
   * @param p pair to be updated or inserted
   */
  private void set(Node n, Pair<K, V> p) {
    if (p.getKey().compareTo(n.data.getKey()) == 0)
      n.data.setValue(p.getValue());
    else if (p.getKey().compareTo(n.data.getKey()) < 0)
      set(n.left, p);
    else
      set(n.right, p);
  }

  /**
   * returns the size of the current BST
   * 
   * @return size of BST
   */
  public int size() {
    return size;
  }

  /**
   * This method returns a list of the current keys in the BST This implementation
   * was taken directly from IC312 lecture where we reviewed our BST of integers
   * with LT. Martin. As was the private method.
   * 
   * @return list of keys in the BST
   */
  public List<K> keys() {
    List<K> list = new ArrayList<K>(size);
    keys(root, list);
    return list;
  }

  /**
   * this is the private recursive method for keys
   * 
   * @param n    the root of the BST to traverse
   * @param list the list we are going to return
   */
  private void keys(Node n, List<K> list) {
    if (n == null)
      return;
    keys(n.left, list);
    list.add(n.data.getKey());
    keys(n.right, list);
  }

  /**
   * this method returns a list of the current values in the BST and should be in
   * the same order as keys() This implementation was taken directly from the
   * IC312 lecture where we reviewed BST of integers with LT. Martin as was the
   * private method of values.
   * 
   * @return list of current values in BST
   */
  public List<V> values() {
    List<V> list = new ArrayList<V>(size);
    values(root, list);
    return list;
  }

  /**
   * this is the private recursive method for values
   * 
   * @param n    the root of the BST to traverse
   * @param list the list we are going to add to and return
   */
  private void values(Node n, List<V> list) {
    if (n == null)
      return;
    values(n.left, list);
    list.add(n.data.getValue());
    values(n.right, list);
  }

}