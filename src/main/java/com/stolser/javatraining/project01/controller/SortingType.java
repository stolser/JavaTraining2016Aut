package com.stolser.javatraining.project01.controller;

public enum SortingType {
    BY_PRICE_ASC("By price, from the cheapest"),
    BY_PRICE_DESC("By price, from the most expensive"),
    BY_POWER_ASC("By power, form the least powerful"),
    BY_POWER_DESC("By power, form the most powerful"),
    BY_WEIGHT_ASC("By weight, form the lightest"),
    BY_WEIGHT_DESC("By weight, form the heaviest");

    private String description;

    SortingType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
