package com.stolser.javatraining.collections.treeset;

import java.util.Comparator;
import java.util.Objects;

public class CustomTreeSet<E> {
    /**
     * The comparator used to maintain order in this tree set, or
     * null if it uses the natural ordering of its keys.
     */
    private final Comparator<? super E> comparator;

    private Node<E> root;

    /**
     * The number of entries in the tree
     */
    private int size = 0;

    /**
     * The number of structural modifications to the tree.
     */
    private int modCount = 0;

    /**
     * Constructs a new, empty tree set, using the natural ordering of its
     * elements.  All elements inserted into this set must implement the {@link
     * Comparable} interface.
     */
    public CustomTreeSet() {
        comparator = null;
    }

    /**
     * Constructs a new, empty tree set, ordered according to the given
     * comparator.  All elements inserted into this set must be <em>mutually
     * comparable</em> by the given comparator.
     */
    public CustomTreeSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Returns the number of elements in this set.
     */
    public int size() {
        return size;
    }

    /**
     * Adds the specified value to this set if it is not already present.
     * If this set already contains the value, the call leaves the set
     * unchanged and returns {@code false}.
     *
     * @param value value to be added to this set
     * @return {@code true} if this set did not already contain the specified value
     */
    public boolean add(E value) {
        Node<E> current = root;    // traversing the tree always starts from the root;

        if (current == null) {      // adding this element into an empty tree;
            return insertFirstValue(value);
        }

        int compareResult;
        Node<E> currentParent;

        // split comparator and comparable paths
        if (comparator != null) {
            // use comparator for comparison of the values;
            do {
                currentParent = current;

                compareResult = comparator.compare(value, current.value);

                if (compareResult < 0) {
                    current = current.left;     // move to the left sub-branch;
                } else if (compareResult > 0) {
                    current = current.right;    // move to the right sub-branch;
                } else {
                    return false;   // the tree already contains this value;
                }

            } while (current != null);  // traverse the tree until reach one of its ends;

        } else {
            // comparator == null, so natural comparison is used;
            checkNotNull(value);

            do {
                currentParent = current;

                @SuppressWarnings("unchecked")
                Comparable<? super E> comparableValue = (Comparable<? super E>) value;

                compareResult = comparableValue.compareTo(current.value);

                if (compareResult < 0) {
                    current = current.left;         // move to the left sub-branch;
                } else if (compareResult > 0) {
                    current = current.right;        // move to the right sub-branch;
                } else {
                    return false;   // the tree already contains this value;
                }

            } while (current != null);  // traverse the tree until reach one of its ends;
        }

        // this tree does not contain this value, so we create and insert a new Node;
        Node<E> newNode = insertNewEntryIntoTree(value, compareResult, currentParent);

        balanceThisTree(newNode);

        size++;
        modCount++;

        return true;
    }

    private boolean insertFirstValue(E value) {
        compare(value, value); // type (and possibly null) check

        root = new Node<>(value, null);
        size = 1;
        modCount++;
        return false;
    }

    private void checkNotNull(E value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    private Node<E> insertNewEntryIntoTree(E value, int compareResult, Node<E> currentParent) {
        Node<E> newNode = new Node<>(value, currentParent);
        if (compareResult < 0) {
            currentParent.left = newNode;
        } else {
            currentParent.right = newNode;
        }
        return newNode;
    }

    private void balanceThisTree(Node<E> node) {
        // re-balancing the tree after each add or delete operation;
    }

    /**
     * Compares two values using the correct comparison method for this tree set.
     */
    @SuppressWarnings("unchecked")
    private int compare(Object value1, Object value2) {
        return (comparator == null)
                ? ((Comparable<? super E>) value1).compareTo((E) value2)
                : comparator.compare((E) value1, (E) value2);
    }

    /**
     * Removes the specified element from this tree if present.
     *
     * @param value element to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     */
    public boolean remove(Object value) {
        Node<E> thisNode = getEntry(value);

        if (thisNode == null)
            return false;

        deleteNode(thisNode);

        return true;
    }

    private Node<E> getEntry(Object value) {
        // Offload comparator-based version for sake of performance
        if (comparator != null)
            return getEntryUsingComparator(value);

        if (value == null) {
            throw new NullPointerException();
        }

        @SuppressWarnings("unchecked")
        Comparable<? super E> comparableValue = (Comparable<? super E>) value;
        Node<E> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = comparableValue.compareTo(currentNode.value);

            if (comparisonResult < 0) {
                currentNode = currentNode.left;
            } else if (comparisonResult > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode;
            }
        }

        return null;
    }

    private Node<E> getEntryUsingComparator(Object value) {
        @SuppressWarnings("unchecked")
        E comparableValue = (E) value;

        if (comparator != null) {
            Node<E> currentNode = root;
            while (currentNode != null) {
                int comparisonResult = comparator.compare(comparableValue, currentNode.value);

                if (comparisonResult < 0) {
                    currentNode = currentNode.left;
                } else if (comparisonResult > 0) {
                    currentNode = currentNode.right;
                } else {
                    return currentNode;
                }
            }
        }

        return null;
    }

    private void deleteNode(Node<E> nodeToDelete) {
        modCount++;
        size--;

        // If strictly internal, copy successor's element to nodeToDelete
        // and then make nodeToDelete point to successor.
        if ((nodeToDelete.left != null)
                && (nodeToDelete.right != null)) {  // nodeToDelete has 2 children;

            Node<E> successor = successor(nodeToDelete);
            nodeToDelete.value = successor.value;
            nodeToDelete = successor;
        }

        // Start fix-up at replacement node, if it exists;
        Node<E> replacement = (nodeToDelete.left != null)
                ? nodeToDelete.left
                : nodeToDelete.right;

        if (replacement != null) {
            // Link replacement to parent
            replacement.parent = nodeToDelete.parent;

            if (nodeToDelete.parent == null) {
                root = replacement;
            } else if (nodeToDelete == nodeToDelete.parent.left) {
                nodeToDelete.parent.left = replacement;
            } else {
                nodeToDelete.parent.right = replacement;
            }

            // Null out links so they are OK to use by balanceTreeAfterDelete();
            nodeToDelete.left = nodeToDelete.right = nodeToDelete.parent = null;

            // Fix replacement
            balanceTreeAfterDelete(nodeToDelete, replacement);

        } else if (nodeToDelete.parent == null) { // return if this is the only node;
            root = null;

        } else { //  No children. Use self as phantom replacement and unlink.
            balanceTreeAfterDelete(nodeToDelete, nodeToDelete);

            if (nodeToDelete.parent != null) {
                if (nodeToDelete == nodeToDelete.parent.left) {
                    nodeToDelete.parent.left = null;
                } else if (nodeToDelete == nodeToDelete.parent.right) {
                    nodeToDelete.parent.right = null;
                }

                nodeToDelete.parent = null;
            }
        }
    }

    private void balanceTreeAfterDelete(Node<E> nodeToDelete, Node<E> replacement) {
        // balance the tree after deleting the 'nodeToDelete' node;
    }

    /**
     * Returns the successor of the specified Node, or null if no such.
     */
    static <E> Node<E> successor(Node<E> thisNode) {
        if (thisNode == null) {     // there is no successor for a 'null' element;
            return null;

        } else if (thisNode.right != null) {        // thisNode has the right branch;
            Node<E> successor = thisNode.right;     // select the right branch;

            while (successor.left != null) {        // look for the smallest element in the right branch;
                successor = successor.left;
            }

            return successor;

        } else {    // the right branch is absent, so we go up the tree ...
            Node<E> currentNode = thisNode;
            Node<E> currentParent = thisNode.parent;

            while ((currentParent != null)      // ... until we reach the top or ...
                    && (currentNode == currentParent.right)) {  // ... currentNode is the left node for its parent;

                currentNode = currentParent;
                currentParent = currentParent.parent;
            }

            return currentParent;
        }
    }

    public boolean contains(Object value) {
        return getEntry(value) != null;
    }

    public static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }

        public E getValue() {
            return value;
        }

        public E setValue(E value) {
            E oldValue = this.value;
            this.value = value;

            return oldValue;
        }

        public boolean equals(Object object) {
            if (!(object instanceof Node))
                return false;

            Node<?> node = (Node<?>) object;

            return Objects.equals(value, node.getValue());
        }

        @Override
        public int hashCode() {
            return (value == null) ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return String.format("Node{value = %s}", value);
        }
    }
}
