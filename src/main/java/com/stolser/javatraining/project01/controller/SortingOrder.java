package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.Comparator;

/**
 * Represents different sorting orders.<br />
 */
public enum SortingOrder {
    BY_PRICE_ASC("By price, from the cheapest",
            Comparator.comparing(ElectricalAppliance::getPrice)),
    BY_PRICE_DESC("By price, from the most expensive",
            Comparator.comparing(ElectricalAppliance::getPrice).reversed()),
    BY_POWER_ASC("By power, form the least powerful",
            Comparator.comparing(ElectricalAppliance::getMaxPower)),
    BY_POWER_DESC("By power, form the most powerful",
            Comparator.comparing(ElectricalAppliance::getMaxPower).reversed()),
    BY_WEIGHT_ASC("By weight, form the lightest",
            Comparator.comparing(ElectricalAppliance::getWeight)),
    BY_WEIGHT_DESC("By weight, form the heaviest",
            Comparator.comparing(ElectricalAppliance::getWeight).reversed());

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
