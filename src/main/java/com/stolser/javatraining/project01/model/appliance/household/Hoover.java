package com.stolser.javatraining.project01.model.appliance.household;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

public class Hoover extends AbstractElectricalAppliance {
    private static final double EFFICIENCY_RATION = 0.8;

    public Hoover(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        System.out.printf("Hoover (%s) is switched on.\n", this);
        isSwitchedOn = true;
        currentPower = getMaxPower() * EFFICIENCY_RATION;
    }

    @Override
    public void switchOff() {
        System.out.printf("Hoover (%s) is switched off.\n", this);
        isSwitchedOn = false;
        currentPower = 0;
    }
}
