package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.Comparator;

/**
 * Represents different sorting orders.<br />
 * Filtering methods use the {@link java.util.TreeSet#subSet(Object, boolean, Object, boolean)} method.
 * Since sets delete duplicates, all sorting comparators in this class implemented in such a way that they
 * can return only two values: '-1' and '1'. That makes it possible to filter appliances with
 * equal parameters without deleting duplicates.
 */
public enum SortingOrder {
    BY_PRICE_ASC("By price, from the cheapest",
            (first, second) -> (first.getPrice() < second.getPrice()) ? -1 : 1),
    BY_PRICE_DESC("By price, from the most expensive",
            (first, second) -> (second.getPrice() < first.getPrice()) ? -1 : 1),
    BY_POWER_ASC("By power, form the least powerful",
            (first, second) -> (first.getMaxPower() < second.getMaxPower()) ? -1 : 1),
    BY_POWER_DESC("By power, form the most powerful",
            (first, second) -> (second.getMaxPower() < first.getMaxPower()) ? -1 : 1),
    BY_WEIGHT_ASC("By weight, form the lightest",
            (first, second) -> (first.getWeight() < second.getWeight()) ? -1 : 1),
    BY_WEIGHT_DESC("By weight, form the heaviest",
            (first, second) -> (second.getWeight() < first.getWeight()) ? -1 : 1);

    /**
     * Displayed to a user in the menu.
     */
    private String description;
    /**
     * An order in which appliances will be sorted.
     */
    private Comparator<ElectricalAppliance> order;

    SortingOrder(String description, Comparator<ElectricalAppliance> order) {
        this.description = description;
        this.order = order;
    }

    public Comparator<ElectricalAppliance> getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return description;
    }
}
