package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.EmptyElectricalAppliance;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import static com.stolser.javatraining.project01.controller.SortingOrder.BY_POWER_ASC;
import static com.stolser.javatraining.project01.controller.SortingOrder.BY_PRICE_ASC;
import static com.stolser.javatraining.project01.controller.SortingOrder.BY_WEIGHT_ASC;

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

    /**
     * Exploits {@link java.util.TreeSet#subSet(Object, boolean, Object, boolean)}
     * to get appliances with price in the specified range.
     * @param original unfiltered appliances to be filtered by the price value
     * @param priceMin the lower price limit for appliances to be included in the result
     * @param priceMax the upper price limit for appliances to be included in the result
     * @return filtered appliances
     */
    public static Set<ElectricalAppliance> getFilteredByPrice(Set<ElectricalAppliance> original,
                                                              double priceMin, double priceMax) {

        Set<ElectricalAppliance> result;
        EmptyElectricalAppliance lowerFilteringLimits;
        EmptyElectricalAppliance upperFilteringLimits;

        if (priceMin < priceMax) {
            lowerFilteringLimits = new EmptyElectricalAppliance();
            upperFilteringLimits = new EmptyElectricalAppliance();
            lowerFilteringLimits.setPrice(priceMin);
            upperFilteringLimits.setPrice(priceMax);

            result = getSorted(original, BY_PRICE_ASC)
                    .subSet(lowerFilteringLimits, true, upperFilteringLimits, true);
        } else {
            result = original;
        }

        return result;
    }

    /**
     * Exploits {@link java.util.TreeSet#subSet(Object, boolean, Object, boolean)}
     * to get appliances with power in the specified range.
     * @param original unfiltered appliances to be filtered by the power value
     * @param powerMin the lower power limit for appliances to be included in the result
     * @param powerMax the upper power limit for appliances to be included in the result
     * @return filtered appliances
     */
    public static Set<ElectricalAppliance> getFilteredByPower(Set<ElectricalAppliance> original,
                                                               double powerMin, double powerMax) {

        Set<ElectricalAppliance> result;

        if (powerMin < powerMax) {
            EmptyElectricalAppliance lowerFilteringLimits = new EmptyElectricalAppliance();
            EmptyElectricalAppliance upperFilteringLimits = new EmptyElectricalAppliance();
            lowerFilteringLimits.setMaxPower(powerMin);
            upperFilteringLimits.setMaxPower(powerMax);

            result = getSorted(original, BY_POWER_ASC)
                    .subSet(lowerFilteringLimits, true, upperFilteringLimits, true);
        } else {
            result = original;
        }

        return result;
    }

    /**
     * Exploits {@link java.util.TreeSet#subSet(Object, boolean, Object, boolean)}
     * to get appliances with weight in the specified range.
     * @param original unfiltered appliances to be filtered by the weight value
     * @param weightMin the lower weight limit for appliances to be included in the result
     * @param weightMax the upper weight limit for appliances to be included in the result
     * @return filtered appliances
     */
    public static Set<ElectricalAppliance> getFilteredByWeight(Set<ElectricalAppliance> original,
                                                              double weightMin, double weightMax) {

        Set<ElectricalAppliance> result;

        if (weightMin < weightMax) {
            EmptyElectricalAppliance lowerFilteringLimits = new EmptyElectricalAppliance();
            EmptyElectricalAppliance upperFilteringLimits = new EmptyElectricalAppliance();
            lowerFilteringLimits.setWeight(weightMin);
            upperFilteringLimits.setWeight(weightMax);

            result = getSorted(original, BY_WEIGHT_ASC)
                    .subSet(lowerFilteringLimits, true, upperFilteringLimits, true);
        } else {
            result = original;
        }

        return result;
    }
}
