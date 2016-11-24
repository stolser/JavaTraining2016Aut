package com.stolser.javatraining.project01.model.appliance;

import com.stolser.javatraining.project01.controller.FilteringController;

/**
 * Used by {@link FilteringController} for filtering appliances
 * as parameters for the {@link java.util.TreeSet#subSet(Object, boolean, Object, boolean)} method.
 */
public class EmptyElectricalAppliance extends AbstractElectricalAppliance {
    private static final double EFFICIENCY_RATION = 0.8;
    private double power;
    private double price;
    private double weight;

    public EmptyElectricalAppliance(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    public EmptyElectricalAppliance() {}

    @Override
    public void switchOn() {
        isSwitchedOn = true;
    }

    @Override
    public void switchOff() {
        isSwitchedOn = false;
    }

    @Override
    public double getMaxPower() {
        return power;
    }

    public void setMaxPower(double power) {
        this.power = power;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getCurrentPower() {
        return getMaxPower() * EFFICIENCY_RATION;
    }
}
