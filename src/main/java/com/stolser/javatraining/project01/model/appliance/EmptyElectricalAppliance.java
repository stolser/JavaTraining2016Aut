package com.stolser.javatraining.project01.model.appliance;

public class EmptyElectricalAppliance extends AbstractElectricalAppliance {
    private double power;
    private double price;
    private double weight;

    public EmptyElectricalAppliance(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    public EmptyElectricalAppliance() {}

    @Override
    public void switchOn() {}

    @Override
    public void switchOff() {}

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
}
