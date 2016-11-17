package com.stolser.javatraining.block07.collection.task;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayUtils {

    /**
     * Compare elements from the first array with elements from the second one and
     * returns only those that are contained in the first but not in the second array.
     *
     * @param groupA elements to be included in the result
     * @param groupB elements to be excluded from the result
     */

    public static <T extends Comparable<T>> T[] getDifferenceUsingForLoop(T[] groupA, T[] groupB,
                                                                          Class<T> clazz) {
        List<T> result = new ArrayList<>();

        groupAFor:
        for (T elementA : groupA) {
            if (result.contains(elementA)) {
                continue;
            }

            for (T elementB : groupB) {
                if (0 == elementA.compareTo(elementB)) {
                    continue groupAFor;
                }
            }

            result.add(elementA);
        }

        @SuppressWarnings("unchecked")
        T[] resultArr = (T[]) Array.newInstance(clazz, result.size());
        resultArr = result.toArray(resultArr);

        return resultArr;
    }

    public static <T extends Comparable<T>> Object[] getDifferenceUsingWhileLoop(T[] groupA, T[] groupB) {
        Set<T> result = new TreeSet<>();
        Collections.sort(Arrays.asList(groupA));
        Collections.sort(Arrays.asList(groupB));

        int i = 0;
        int j = 0;

        while ((i < groupA.length) && (j < groupB.length)) {
            if (groupA[i].compareTo(groupB[j]) < 0) {
                result.add(groupA[i]);
                i++;
                continue;
            }

            if (groupA[i].compareTo(groupB[j]) == 0) {
                i++;
                j++;
                continue;
            }

            j++;
        }

        while (i < groupA.length) {
            result.add(groupA[i]);
        }

        return result.toArray();
    }

    public static <T extends Comparable<T>> Object[] getDifferenceUsingCollections(T[] groupA, T[] groupB) {
        Set<T> setA = new TreeSet<>(Arrays.asList(groupA));
        Set<T> setB = new TreeSet<>(Arrays.asList(groupB));

        setA.removeAll(setB);

        return setA.toArray();
    }

    /**
     * Sorts an array by the number of repetitions of its elements.
     * This method relays on the {@code equals()} method, so it should be properly overridden.
     * @return a sorted array
     */
    public static <T> Object[] sortByFrequency(T[] arrayToSort) {
        Map<T, Integer> frequency = new HashMap<>();
        Arrays.stream(arrayToSort)
                .forEach(elem -> {
                    if (frequency.containsKey(elem)) {
                        frequency.put(elem, frequency.get(elem) + 1);
                    } else {
                        frequency.put(elem, 1);
                    }
                });

        Map<Integer, Set<T>> reverseFrequency = new TreeMap<>(
                (Integer first, Integer second) -> (second - first));
        frequency.entrySet()
                .forEach(entry -> {
                    Integer count = entry.getValue();
                    T element = entry.getKey();
                    if (reverseFrequency.containsKey(count)) {
                        reverseFrequency.get(count).add(element);
                    } else {
                        Set<T> elements = new TreeSet<>();
                        elements.add(element);
                        reverseFrequency.put(count, elements);
                    }
                });

        List<T>  sortedElements = new ArrayList<>();
        reverseFrequency.entrySet().forEach(entry -> {
            for (T elem: entry.getValue()) {
                for (int i = 1; i <= entry.getKey(); i++) {
                    sortedElements.add(elem);
                }
            }
        });


        return sortedElements.toArray();
    }


    /**
     * Prints all the elements from the passed array sorted in a natural order.
     * The order of the elements in the original array is not changed.
     */
    public static <T extends Comparable<T>> void printlnAsSorted(T[] array, String label) {
        List<T> newList = new ArrayList<>(Arrays.asList(array));
        Collections.sort(newList);
        System.out.printf("%s: %s\n", label, newList);
    }

}
