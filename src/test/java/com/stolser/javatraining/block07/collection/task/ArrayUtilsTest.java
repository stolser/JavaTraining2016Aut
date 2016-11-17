package com.stolser.javatraining.block07.collection.task;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static com.stolser.javatraining.block07.collection.task.ArrayUtils.*;
import static org.junit.Assert.assertArrayEquals;

public class ArrayUtilsTest {

    private static Integer[] array1;
    private static Integer[] array2;
    private static Integer[] expected;

    @BeforeClass
    public static void setUp() {
        array1 = new Integer[]{1, 3, 5, 5, 7, 20, 4, 2, 7, 7, 8, 13, 19};
        array2 = new Integer[]{3, 1, 55, 21, 8, 2, 0,};
        expected = new Integer[]{4, 5, 7, 13, 19, 20};

        printlnAsSorted(array1, "First array");
        printlnAsSorted(array2, "Second array");
    }

    @Test
    public void getDifferenceUsingForLoop_Should_ReturnDifferenceWithoutDuplicates() {
        Integer[] actual = getDifferenceUsingForLoop(array1, array2, Integer.class);
        printlnAsSorted(actual, "\nDifference using a for loop (arr1 - arr2)");
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getDifferenceUsingWhileLoop_Should_ReturnDifferenceWithoutDuplicates() {
        Object[] actual = getDifferenceUsingWhileLoop(array1, array2);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getDifferenceUsingCollections_Should_ReturnDifferenceWithoutDuplicates() {
        Object[] actual = getDifferenceUsingCollections(array1, array2);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void sortByFrequency_Should_ReturnElementsSortedByFrequency() {
        Integer[] original = new Integer[]{1, 20, 3, 3, 5, 5, 7, 20, 4, 2, 7, 7, 8, 13, 19, 19, 20, };
        printlnAsSorted(original, "Original array");
        Object[] actual = sortByFrequency(original);
        System.out.println("Array sorted by frequency: " + Arrays.asList(actual));
    }
}