import java.util.List;

/**
 * Set for holding a discrete group of objects; can check for membership.
 * Nothing may appear more than once.
 */
public interface Set<K> {
  /** Add the key to the set */
  public void add(K key);

  /** Returns whether the key appears in the set */
  public boolean contains(K key);

  /** Returns the number of elements in the set */
  public int size();

  /** Returns the elements of the Set as a List; no order is guaranteed */
  public List<K> toList();
}

