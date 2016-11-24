package com.stolser.javatraining.project01.model.appliance.kitchen;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

/**
 * Represents a coffee maker machine.
 */
public class CoffeeMaker extends AbstractElectricalAppliance {
    /**
     * Affects the current input power of this electrical appliance.<br />
     * Is used during calculating the current power as a multiplication parameter along with the max power.
     */
    private static final double EFFICIENCY_RATION = 0.8;

    public CoffeeMaker(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        isSwitchedOn = true;
        currentPower = getMaxPower() * EFFICIENCY_RATION;
    }

    @Override
    public void switchOff() {
        isSwitchedOn = false;
        currentPower = 0;
    }

    @Override
    public double getCurrentPower() {
        return getMaxPower() * EFFICIENCY_RATION;
    }
}
