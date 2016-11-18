package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.*;

import static com.stolser.javatraining.project01.controller.SortingType.*;

public class ApplianceUtils {
    private static Map<SortingType, Comparator<ElectricalAppliance>> algorithms;

    static {
        algorithms = new HashMap<>();
        algorithms.put(BY_PRICE_ASC, (first, second) -> Double.compare(first.getPrice(), second.getPrice()));
        algorithms.put(BY_PRICE_DESC, (first, second) -> Double.compare(second.getPrice(), first.getPrice()));
        algorithms.put(BY_POWER_ASC, (first, second) -> Double.compare(first.getMaxPower(), second.getMaxPower()));
        algorithms.put(BY_POWER_DESC, (first, second) -> Double.compare(second.getMaxPower(), first.getMaxPower()));
        algorithms.put(BY_WEIGHT_ASC, (first, second) -> Double.compare(first.getWeight(), second.getWeight()));
        algorithms.put(BY_WEIGHT_DESC, (first, second) -> Double.compare(second.getWeight(), first.getWeight()));
    }

    public static Set<ElectricalAppliance> getSorted(Set<ElectricalAppliance> unsortedElems,
                                                                    SortingType algorithm) {
        Comparator<ElectricalAppliance> comparator = algorithms.get(algorithm);
        Set<ElectricalAppliance> sortedElems = new TreeSet<>(comparator);

        sortedElems.addAll(unsortedElems);

        return sortedElems;
    }
}
