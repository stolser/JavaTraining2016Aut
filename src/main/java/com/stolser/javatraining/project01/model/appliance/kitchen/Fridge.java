package com.stolser.javatraining.project01.model.appliance.kitchen;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

public class Fridge extends AbstractElectricalAppliance {
    private static final double EFFICIENCY_RATION = 0.8;

    public Fridge(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        System.out.printf("Fridge (%s) is switched on.\n", this);
        isSwitchedOn = true;
        currentPower = getMaxPower() * EFFICIENCY_RATION;
    }

    @Override
    public void switchOff() {
        System.out.printf("Fridge (%s) is switched off.\n", this);
        isSwitchedOn = false;
        currentPower = 0;
    }
}
