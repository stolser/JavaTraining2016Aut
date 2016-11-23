package com.stolser.javatraining.collections.linkedlist;

import com.google.common.base.Preconditions;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.ListIterator;

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
     * @param  original the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public CustomLinkedList(Collection<? extends E> original) {
        this();
        Preconditions.checkNotNull(original);

        addAll(original);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E get(int index) {
        return super.get(index);
    }

    @Override
    public E set(int index, E element) {
        return super.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
    }

    @Override
    public E remove(int index) {
        return super.remove(index);
    }

    @Override
    public boolean add(E e) {
        return super.add(e);
    }

    @Override
    public void clear() {
        super.clear();
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
