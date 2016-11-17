package com.stolser.javatraining.project01.model.appliance.tools;

import com.stolser.javatraining.project01.model.appliance.ApplianceType;

public class Drill extends ElectricalTool {
    private static final double EFFICIENCY_RATION = 0.8;

    public Drill(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        if (accumulator.isOn() && (accumulator.getCurrentCharge() > 0)) {
            System.out.println("Drill works using accumulator.");
            isSwitchedOn = true;
            currentPower = getMaxPower() * EFFICIENCY_RATION;
        } else {
            System.out.printf("Drill (%s) works using engine and electricity grid.\n", this);
            isSwitchedOn = true;
            currentPower = getMaxPower() * EFFICIENCY_RATION;
        }
    }

    @Override
    public void switchOff() {
        System.out.printf("Drill (%s) is switch off\n", this);
        isSwitchedOn = false;
        currentPower = 0;
    }
}
