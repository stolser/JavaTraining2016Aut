package com.stolser.javatraining.collections.arraylist;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class CustomArrayList<E> extends AbstractList<E> implements Serializable {
    private static final long serialVersionUID = 777L;
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    /**
     * The size of the CustomArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of this list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public CustomArrayList(int initialCapacity) {
        checkArgument(initialCapacity >= 0, "initial capacity cannot be negative");

        elements = new Object[initialCapacity];
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's iterator.
     *
     * @param original the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public CustomArrayList(Collection<? extends E> original) {
        checkNotNull(original);
        elements = Arrays.copyOf(original.toArray(), original.size(), Object[].class);
        size = elements.length;
    }


    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);

        return (E) elements[index];
    }

    /**
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index      index of the element to replace
     * @param newElement element to be stored at the specified position
     * @return the element previously at the specified position
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E newElement) {
        rangeCheck(index);

        E oldElement = (E) elements[index];
        elements[index] = newElement;

        return oldElement;
    }

    @Override
    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     */
    public boolean add(E newElement) {
        ensureCapacity(size + 1);
        elements[size] = newElement;
        size++;
        modCount++;

        return true;
    }

    @Override
    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void add(int index, E newElement) {
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = newElement;
        size++;
        modCount++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = (E) elements[index];

        int movedElementsNumber = size - index - 1;
        if (movedElementsNumber > 0) {
            System.arraycopy(elements, index + 1, elements, index, movedElementsNumber);
        }

        elements[--size] = null;

        return oldValue;
    }

    @Override
    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        modCount++;

        for (int i = 0; i < size; i++)
            elements[i] = null;

        size = 0;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length)
            increaseCapacity(minCapacity);
    }

    private void increaseCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }

        elements = Arrays.copyOf(elements, newCapacity);
    }

}
