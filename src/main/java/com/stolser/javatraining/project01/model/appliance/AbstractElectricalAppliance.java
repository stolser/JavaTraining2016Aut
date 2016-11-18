package com.stolser.javatraining.project01.model.appliance;

import com.stolser.javatraining.project01.model.appliance.engine.Engine;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractElectricalAppliance implements ElectricalAppliance {
    private Engine engine;
    private ApplianceType type;
    private String brand;
    private double price;
    private double weight;
    protected boolean isSwitchedOn;
    protected double currentPower;

    public AbstractElectricalAppliance(ApplianceType type, String brand, double weight) {
        checkNotNull(type);
        checkNotNull(brand);
        checkArgument(weight > 0);
        this.type = type;
        this.brand = brand;
        this.weight = weight;
    }

    public AbstractElectricalAppliance() {}

    @Override
    public ApplianceType getType() {
        return type;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        checkArgument(price >= 0);
        this.price = price;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isOn() {
        return isSwitchedOn;
    }

    @Override
    public double getMaxPower() {
        return engine.getInputPower();
    }

    @Override
    public double getCurrentPower() {
        return currentPower;
    }

    @Override
    public void setEngine(Engine engine) {
        checkNotNull(engine);
        this.engine = engine;
    }

    @Override
    public String toString() {
        return String.format("{type = %s, brand = '%s', price = %.2f, weight = %s, power = %.1f}",
                type, brand, price, weight, engine.getInputPower());
    }
}
