package com.stolser.javatraining.project01.model.appliance.household;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

/**
 * Represents a vacuum cleaner.
 */
public class Hoover extends AbstractElectricalAppliance {
    /**
     * Affects the current input power of this electrical appliance.<br />
     * Is used during calculating the current power as a multiplication parameter along with the max power.
     */
    private static final double EFFICIENCY_RATION = 0.8;

    public Hoover(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        isSwitchedOn = true;
    }

    @Override
    public void switchOff() {
        isSwitchedOn = false;
    }

    @Override
    public double getCurrentPower() {
        return isSwitchedOn ? (getMaxPower() * EFFICIENCY_RATION) : 0;
    }
}
