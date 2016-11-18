package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class ApplianceUtils {

    public static NavigableSet<ElectricalAppliance> getSorted(Set<ElectricalAppliance> unsorted,
                                                                    SortingType sortingType) {
        NavigableSet<ElectricalAppliance> sortedElems = new TreeSet<>(sortingType.getAlgorithm());
        sortedElems.addAll(unsorted);

        return sortedElems;
    }
}
