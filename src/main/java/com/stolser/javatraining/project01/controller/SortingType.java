package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.Comparator;

public enum SortingType {
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

    private String description;
    private Comparator<ElectricalAppliance> algorithm;

    SortingType(String description, Comparator<ElectricalAppliance> algorithm) {
        this.description = description;
        this.algorithm = algorithm;
    }

    public Comparator<ElectricalAppliance> getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString() {
        return description;
    }
}
