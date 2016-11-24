package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.stolser.javatraining.project01.controller.SortingOrder.*;

/**
 * Contains static methods for working with appliances.
 */
public class ApplianceUtils {
    /**
     * Sorts a set passed in as an argument according to the provided sorting order.
     *
     * @param unsorted     a set to be sorted
     * @param sortingOrder a sorting order
     * @return a sorted set
     */
    public static void sortAppliances(List<ElectricalAppliance> unsorted,
                                      SortingOrder sortingOrder) {

        Collections.sort(unsorted, sortingOrder.getOrder());
    }

    /**
     * Selects appliances with price in the specified range.
     *
     * @param original unfiltered appliances to be filtered by the price value
     * @param priceMin the lower price limit for appliances to be included in the result. <br />
     *                 If priceMin >= priceMax the result will be same as the original appliances
     * @param priceMax the upper price limit for appliances to be included in the result
     * @return filtered appliances
     */
    public static List<ElectricalAppliance> getFilteredByPrice(List<ElectricalAppliance> original,
                                                               double priceMin, double priceMax) {

        List<ElectricalAppliance> result;

        if (priceMin <= priceMax) {

            sortAppliances(original, BY_PRICE_ASC);
            result = getInsideRange(original, item ->
                    (item.getPrice() >= priceMin) && (item.getPrice() <= priceMax));

        } else {
            result = new ArrayList<>(original);
        }

        return result;
    }

    private static List<ElectricalAppliance> getInsideRange(List<ElectricalAppliance> original,
                                                            Predicate<ElectricalAppliance> insideRange) {
        return original.stream()
                .filter(insideRange)
                .collect(Collectors.toList());
    }

    /**
     * Selects appliances with power in the specified range.
     *
     * @param original unfiltered appliances to be filtered by the power value
     * @param powerMin the lower power limit for appliances to be included in the result. <br />
     *                 If priceMin >= priceMax the result will be same as the original appliances
     * @param powerMax the upper power limit for appliances to be included in the result
     * @return filtered appliances
     */
    public static List<ElectricalAppliance> getFilteredByPower(List<ElectricalAppliance> original,
                                                               double powerMin, double powerMax) {

        List<ElectricalAppliance> result;

        if (powerMin <= powerMax) {

            sortAppliances(original, BY_POWER_ASC);
            result = getInsideRange(original, item ->
                    (item.getMaxPower() >= powerMin) && (item.getMaxPower() <= powerMax));

        } else {
            result = new ArrayList<>(original);
        }

        return result;
    }

    /**
     * Selects appliances with weight in the specified range.
     *
     * @param original  unfiltered appliances to be filtered by the weight value
     * @param weightMin the lower weight limit for appliances to be included in the result. <br />
     *                  If priceMin >= priceMax the result will be same as the original appliances
     * @param weightMax the upper weight limit for appliances to be included in the result
     * @return filtered appliances
     */
    public static List<ElectricalAppliance> getFilteredByWeight(List<ElectricalAppliance> original,
                                                                double weightMin, double weightMax) {

        List<ElectricalAppliance> result;

        if (weightMin <= weightMax) {

            sortAppliances(original, BY_WEIGHT_ASC);
            result = getInsideRange(original, item ->
                    (item.getWeight() >= weightMin) && (item.getWeight() <= weightMax));
        } else {
            result = new ArrayList<>(original);
        }

        return result;
    }
}
