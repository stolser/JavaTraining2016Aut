package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Contains static methods for working with appliances.
 */
public class ApplianceUtils {

    /**
     * Sorts a set passed in as an argument according to the provided sorting order.
     * @param unsorted a set to be sorted
     * @param sortingOrder a sorting order
     * @return a sorted set
     */
    public static NavigableSet<ElectricalAppliance> getSorted(Set<ElectricalAppliance> unsorted,
                                                                    SortingOrder sortingOrder) {
        NavigableSet<ElectricalAppliance> sorted = new TreeSet<>(sortingOrder.getOrder());
        sorted.addAll(unsorted);

        return sorted;
    }
}
