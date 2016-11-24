package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.Comparator;

/**
 * Represents different sorting orders.<br />
 */
public enum SortingOrder {
    BY_PRICE_ASC("By price, from the cheapest",
            (first, second) -> (Double.compare(first.getPrice(), second.getPrice()))),
    BY_PRICE_DESC("By price, from the most expensive",
            (first, second) -> (Double.compare(second.getPrice(), first.getPrice()))),
    BY_POWER_ASC("By power, form the least powerful",
            (first, second) -> (Double.compare(first.getMaxPower(), second.getMaxPower()))),
    BY_POWER_DESC("By power, form the most powerful",
            (first, second) -> (Double.compare(second.getMaxPower(), first.getMaxPower()))),
    BY_WEIGHT_ASC("By weight, form the lightest",
            (first, second) -> (Double.compare(first.getWeight(), second.getWeight()))),
    BY_WEIGHT_DESC("By weight, form the heaviest",
            (first, second) -> (Double.compare(second.getWeight(), first.getWeight())));

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
