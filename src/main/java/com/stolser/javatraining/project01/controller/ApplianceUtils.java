package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.*;

import static com.stolser.javatraining.project01.controller.SortingType.*;

public class ApplianceUtils {
    private static Map<SortingType, Comparator<ElectricalAppliance>> algorithms;

    static {
        algorithms = new HashMap<>();
        algorithms.put(BY_PRICE_ASC, (first, second) -> (first.getPrice() < second.getPrice()) ? -1 : 1);
        algorithms.put(BY_PRICE_DESC, (first, second) -> (second.getPrice() < first.getPrice()) ? -1 : 1);
        algorithms.put(BY_POWER_ASC, (first, second) -> (first.getMaxPower() < second.getMaxPower()) ? -1 : 1);
        algorithms.put(BY_POWER_DESC, (first, second) -> (second.getMaxPower() < first.getMaxPower()) ? -1 : 1);
        algorithms.put(BY_WEIGHT_ASC, (first, second) -> (first.getWeight() < second.getWeight()) ? -1 : 1);
        algorithms.put(BY_WEIGHT_DESC, (first, second) -> (second.getWeight() < first.getWeight()) ? -1 : 1);
    }

    public static NavigableSet<ElectricalAppliance> getSorted(Set<ElectricalAppliance> unsortedElems,
                                                                    SortingType algorithm) {
        Comparator<ElectricalAppliance> comparator = algorithms.get(algorithm);
        NavigableSet<ElectricalAppliance> sortedElems = new TreeSet<>(comparator);
        System.out.println("-----------UnsortedSet----------");
        unsortedElems.forEach(System.out::println);
        System.out.println("---------------------------------");
        sortedElems.addAll(unsortedElems);

        return sortedElems;
    }
}
