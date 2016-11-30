package com.stolser.javatraining.collections.treemap;

import java.util.Comparator;

public class CustomTreeMap<K, V> {
    /**
     * The comparator used to maintain order in this tree map, or
     * null if it uses the natural ordering of its keys.
     */
    private final Comparator<? super K> comparator;

    private transient Entry<K, V> root;

    /**
     * The number of entries in the tree
     */
    private transient int size = 0;

    /**
     * The number of structural modifications to the tree.
     */
    private transient int modCount = 0;


    /**
     * Constructs a new, empty tree map, using the natural ordering of its
     * keys.  All keys inserted into the map must implement the {@link
     * Comparable} interface.
     */
    public CustomTreeMap() {
        comparator = null;
    }

    /**
     * Constructs a new, empty tree map, ordered according to the given
     * comparator.  All keys inserted into the map must be <em>mutually
     * comparable</em> by the given comparator.
     *
     * @param comparator the comparator that will be used to order this map.
     */
    public CustomTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }


    /**
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     * {@code null} if there was no mapping for {@code key}.
     */
    public V put(K key, V value) {
        Entry<K, V> current = root;

        if (current == null) {
            return insertFirstEntry(key, value);
        }

        int compareResult;
        Entry<K, V> currentParent;

        // split comparator and comparable paths
        if (comparator != null) {
            do {
                currentParent = current;
                compareResult = comparator.compare(key, current.key);
                if (compareResult < 0) {
                    current = current.left;
                } else if (compareResult > 0) {
                    current = current.right;
                } else {
                    return current.setValue(value);
                }
            } while (current != null);

        } else {

            if (key == null) {
                throw new NullPointerException();
            }

            @SuppressWarnings("unchecked")
            Comparable<? super K> comparableKey = (Comparable<? super K>) key;

            do {
                currentParent = current;
                compareResult = comparableKey.compareTo(current.key);
                if (compareResult < 0) {
                    current = current.left;
                } else if (compareResult > 0) {
                    current = current.right;
                } else {
                    return current.setValue(value);
                }
            } while (current != null);
        }

        Entry<K, V> newEntry = new Entry<>(key, value, currentParent);
        if (compareResult < 0) {
            currentParent.left = newEntry;
        } else {
            currentParent.right = newEntry;
        }

        balanceThisTree(newEntry);

        size++;
        modCount++;

        return null;
    }

    private V insertFirstEntry(K key, V value) {
        compare(key, key);

        root = new Entry<>(key, value, null);
        size = 1;
        modCount++;

        return null;
    }

    @SuppressWarnings("unchecked")
    final int compare(Object k1, Object k2) {
        return comparator == null ? ((Comparable<? super K>) k1).compareTo((K) k2)
                : comparator.compare((K) k1, (K) k2);
    }

    private void balanceThisTree(Entry<K, V> newEntry) {
        // re-balancing the tree after each add or delete operation;
    }


    static final class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;

        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        /**
         * Replaces the value currently associated with the key with the given
         * value.
         */
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;

            return (key == null) ? (entry.getKey() == null) : key.equals(entry.getKey()) &&
                    (value == null) ? (entry.getValue() == null) : value.equals(entry.getValue());
        }

        public int hashCode() {
            int keyHash = (key == null ? 0 : key.hashCode());
            int valueHash = (value == null ? 0 : value.hashCode());

            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }


}
