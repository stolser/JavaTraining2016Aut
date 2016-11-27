package com.stolser.javatraining.collections.linkedlist;

import com.google.common.base.Preconditions;

import java.util.*;

public class CustomLinkedList<E> extends AbstractSequentialList<E> {
    private int size = 0;
    /**
     * Pointer to first node.
     */
    private Node<E> first;

    /**
     * Pointer to last node.
     */
    private Node<E> last;

    /**
     * Constructs an empty list.
     */
    public CustomLinkedList() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param original the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public CustomLinkedList(Collection<? extends E> original) {
        this();
        Preconditions.checkNotNull(original);

        addAll(original);
    }

    /**
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list.
     * This method is equivalent to {@code addLast(...)}.
     *
     * @param newElement element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(E newElement) {
        Node<E> oldLast = last; // is 'null' if this list is empty;
        Node<E> newNode = new Node<>(oldLast, newElement, null);
        last = newNode;     // is added as the last node;

        if (oldLast == null) {
            // if this list is empty it will have only one node = first = last;
            first = last;
        } else {
            oldLast.next = newNode;
        }

        size++;
        modCount++;

        return true;
    }

    public boolean addLast(E newElement) {
        return add(newElement);
    }

    public boolean addFirst(E newElement) {
        add(0, newElement);

        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E get(int index) {
        checkIndexForGetSet(index);

        return getNodeByIndex(index).item;
    }

    private Node<E> getNodeByIndex(int index) {
        Node<E> current;

        if (index < (size >> 1)) {
            /* 'index' is in the left part of this list.
            So we traverse the list from left to right;*/
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }

        } else {
            /* 'index' is in the right part of this list.
            So we traverse the list from right to left;*/
            current = last;
            for (int i = size - 1; i > index; i--)
                current = current.prev;

        }

        return current;
    }

    private void checkIndexForGetSet(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException(
                    String.format("Index (%d) is out of the correct range: [0, %d]", index, size));
        }
    }

    private void checkIndexForAdd(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException(
                    String.format("Index (%d) is out of the correct range: [0, %d]", index, size));
        }
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E set(int index, E element) {
        checkIndexForGetSet(index);

        Node<E> thisNode = getNodeByIndex(index);
        E oldItem = thisNode.item;
        thisNode.item = element;
        return oldItem;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) to the right.
     *
     * @param index      index at which the specified element is to be inserted
     * @param newElement element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void add(int index, E newElement) {
        checkIndexForAdd(index);

        if (index == size) {
            addLast(newElement);
        } else {
            Node<E> shiftedToRight = getNodeByIndex(index); // cannot be null
            // 'cause index was checked and it is in the range {0, size - 1};

            insertBefore(newElement, shiftedToRight);
        }
    }

    private void insertBefore(E newElement, Node<E> shiftedToRight) {
        final Node<E> prev = shiftedToRight.prev;
        final Node<E> newNode = new Node<>(prev, newElement, shiftedToRight);
        shiftedToRight.prev = newNode;

        if (prev == null) {
            // the element shifted to the right was the first node;
            first = newNode;
        } else {
            prev.next = newNode;
        }

        size++;
        modCount++;
    }

    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E remove(int index) {
        Node<E> nodeToRemove = getNodeByIndex(index);

        return unLinkNode(nodeToRemove);
    }

    private E unLinkNode(Node<E> nodeToRemove) {
        final E element = nodeToRemove.item;
        final Node<E> next = nodeToRemove.next;
        final Node<E> prev = nodeToRemove.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            nodeToRemove.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            nodeToRemove.next = null;
        }

        nodeToRemove.item = null;
        size--;
        modCount++;

        return element;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        for (Node<E> current = first; current != null; ) {
            Node<E> next = current.next;
            current.item = null;
            current.next = null;
            current.prev = null;
            current = next;
        }

        first = last = null;
        size = 0;
        modCount++;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkIndexForAdd(index);

        return new ThisListIterator(index);
    }

    private class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class ThisListIterator implements ListIterator<E> {
        private Node<E> lastReturnedNode;
        private Node<E> nextNode;
        private int nextIndex;
        private int expectedModCount = modCount;

        public ThisListIterator(int index) {
            if (index == size) {
                nextNode = null;
            } else {
                nextNode = getNodeByIndex(index);
            }

            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            checkForModification();

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturnedNode = nextNode;
            nextNode = nextNode.next;
            nextIndex++;

            return lastReturnedNode.item;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            checkForModification();

            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if (nextNode == null) {
                nextNode = last;
            } else {
                nextNode = nextNode.prev;
            }

            lastReturnedNode = nextNode;
            nextIndex--;

            return lastReturnedNode.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            checkForModification();

            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }

            Node<E> lastNext = lastReturnedNode.next;

            unLinkNode(lastReturnedNode);

            if (nextNode == lastReturnedNode) {
                nextNode = lastNext;
            } else {
                nextIndex--;
            }

            lastReturnedNode = null;
            expectedModCount++;
        }

        @Override
        public void set(E newElement) {
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }

            checkForModification();

            lastReturnedNode.item = newElement;
        }

        @Override
        public void add(E newElement) {
            checkForModification();

            lastReturnedNode = null;
            if (nextNode == null) {
                addLast(newElement);
            } else {
                insertBefore(newElement, nextNode);
            }

            nextIndex++;
            expectedModCount++;
        }

        void checkForModification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
