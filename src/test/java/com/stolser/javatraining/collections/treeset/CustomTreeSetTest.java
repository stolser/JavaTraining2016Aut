package com.stolser.javatraining.collections.treeset;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomTreeSetTest {

    private CustomTreeSet<Integer> intSet;

    @Before
    public void setup() {
        intSet = new CustomTreeSet<>();
        intSet.add(5);
        intSet.add(10);
        intSet.add(2);
        intSet.add(25);
    }

    @Test
    public void size_Should_ReturnCorrectNumberOfElements() throws Exception {
        int expected = 4;
        int actual = intSet.size();

        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void add_Should_ThrowException_IfNullPassed() throws Exception {
        intSet.add(null);
    }

    @Test
    public void add_Should_IncreaseSizeByOne() throws Exception {
        assertEquals(4, intSet.size());

        intSet.add(77);

        assertEquals(5, intSet.size());
    }

    @Test
    public void remove_Should_DecreaseSizeByOne_And_DeleteTheElement() throws Exception {
        int elementToDelete = 10;

        assertEquals(4, intSet.size());

        intSet.remove(elementToDelete);

        assertEquals(3, intSet.size());
        assertFalse(intSet.contains(elementToDelete));
    }

    @Test
    public void contains_Should_ReturnTrueOnlyIfElementWasAdded() throws Exception {
        assertTrue(intSet.contains(5));
        assertTrue(intSet.contains(10));
        assertTrue(intSet.contains(2));
        assertTrue(intSet.contains(25));

        assertFalse(intSet.contains(20));
    }
}