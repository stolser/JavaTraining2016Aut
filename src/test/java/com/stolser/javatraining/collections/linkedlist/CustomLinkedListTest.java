package com.stolser.javatraining.collections.linkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CustomLinkedListTest {
    private List<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_Should_ThrowException_IfIndexIsNegative() throws Exception {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_Should_ThrowException_IfIndexEqualsToSize() throws Exception {
        list.get(list.size());
    }

    @Test
    public void size_Should_ReturnCorrectNumber() throws Exception {
        int expected = 3;
        int actual = list.size();

        assertEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_Should_ThrowException_IfIndexIsNegative() throws Exception {
        list.set(-1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_Should_ThrowException_IfIndexEqualsToSize() throws Exception {
        list.set(list.size(), 5);
    }

    @Test
    public void set_Should_ReplaceElement_IfIndexIsCorrect() throws Exception {
        int index = 1;
        Integer newElement = 100;
        Integer oldElement = list.get(index);

        assertEquals(oldElement, list.set(index, newElement));
        assertEquals(newElement, list.get(index));
    }

    @Test
    public void add_Should_IncreaseSizeByOne() throws Exception {
        Integer newElement = 88;
        int oldSize = list.size();
        list.add(newElement);
        int newSize = list.size();

        assertEquals(newSize, oldSize + 1);
        assertEquals(newElement, list.get(list.size() - 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add_Should_ThrowException_IfIndexIsNegative() throws Exception {
        list.add(-1, 25);
    }

    @Test
    public void add_Should_AddElementCorrectly_InSpecifiedPosition() {
        Integer element1 = list.get(1);
        Integer element2 = list.get(2);
        int index = 1;
        Integer newElement = 102;

        list.add(index, newElement);

        assertEquals(4, list.size());
        assertEquals(newElement, list.get(index));
        assertEquals(element1, list.get(index + 1));
        assertEquals(element2, list.get(index + 2));
    }

    @Test
    public void remove_Should_DecreaseSizeByOne() throws Exception {
        int oldSize = list.size();
        list.remove(1);
        int newSize = list.size();

        assertEquals(newSize, oldSize - 1);
    }

    @Test
    public void remove_Should_ReturnRemovedElement() throws Exception {
        int index = 1;
        Integer expected = list.get(index);
        Integer actual = list.remove(index);

        assertEquals(expected, actual);
    }

    @Test
    public void clear_Should_DeleteAllElementsFromList() throws Exception {
        list.clear();

        assertEquals(0, list.size());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void listIterator_Should_FailFastAfter_Add() {
        Iterator<Integer> listIterator = list.listIterator();

        list.add(77);

        listIterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void listIterator_Should_FailFastAfter_Remove() {
        Iterator<Integer> listIterator = list.listIterator();

        list.remove(2);

        listIterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void listIterator_Should_FailFastAfter_Clear() {
        Iterator<Integer> listIterator = list.listIterator();

        list.clear();

        listIterator.next();
    }

    @Test
    public void listIterator_Should_IterateThroughAllElements() {
        Iterator<Integer> listIterator = list.listIterator();

        for (int i = 0; i < list.size(); i++) {
            Integer expected = list.get(i);
            Integer actual = listIterator.next();

            assertEquals(expected, actual);
        }

        assertFalse(listIterator.hasNext());
    }

    @Test
    public void listIterator_Should_RemoveElement_WhenRemoveIsCalled() {
        Iterator<Integer> listIterator = list.listIterator();

        listIterator.next();
        listIterator.remove();

        assertEquals(2, list.size());

        listIterator.next();
        listIterator.remove();
        listIterator.next();
        listIterator.remove();

        assertEquals(0, list.size());

    }

    @Test(expected = IllegalStateException.class)
    public void listIteratorRemove_Should_ThrowException_WhenCalledTwice() {
        Iterator<Integer> listIterator = list.listIterator();

        listIterator.next();
        listIterator.remove();
        listIterator.remove();
    }
}