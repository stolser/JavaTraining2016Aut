package com.stolser.javatraining.project01.model.appliance.tools;

import com.stolser.javatraining.project01.model.appliance.ApplianceType;

/**
 * A tool called 'drill'.
 */
public class Drill extends ElectricalTool {
    /**
     * Affects the current input power of this electrical appliance.<br />
     * Is used during calculating the current power as a multiplication parameter along with the max power.
     */
    private static final double EFFICIENCY_RATION = 0.8;

    public Drill(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        if (accumulatorCanBeUsed()) {
            accumulator.switchOn();
            accumulator.usePower();
        }

        isSwitchedOn = true;

    }

    private boolean accumulatorCanBeUsed() {
        return accumulator.isOn() && (accumulator.getCurrentCharge() > 0);
    }

    @Override
    public void switchOff() {
        if (accumulator.isOn()) {
            accumulator.switchOff();
        }

        isSwitchedOn = false;
    }

    @Override
    public double getCurrentPower() {
        double currentPower = 0;
        if (! accumulator.isOn()) {
            currentPower = getMaxPower() * EFFICIENCY_RATION;
        }

        return isSwitchedOn ? currentPower : 0;
    }
}
