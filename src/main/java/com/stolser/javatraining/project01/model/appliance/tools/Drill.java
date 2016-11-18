package com.stolser.javatraining.project01.model.appliance.tools;

import com.stolser.javatraining.project01.model.appliance.ApplianceType;

public class Drill extends ElectricalTool {
    private static final double EFFICIENCY_RATION = 0.8;
    private static final String DRILL_WORKS_USING_ACCUMULATOR_TEXT = "Drill works using accumulator.";
    private static final String DRILL_WORKS_USING_ELECTRICITY_GRID_TEXT = "Drill (%s) works using engine and electricity grid.\n";
    private static final String DRILL_IS_SWITCH_OFF_TEXT = "Drill (%s) is switch off\n";

    public Drill(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        if (accumulator.isOn() && (accumulator.getCurrentCharge() > 0)) {
            System.out.println(DRILL_WORKS_USING_ACCUMULATOR_TEXT);
            isSwitchedOn = true;
            currentPower = getMaxPower() * EFFICIENCY_RATION;
        } else {
            System.out.printf(DRILL_WORKS_USING_ELECTRICITY_GRID_TEXT, this);
            isSwitchedOn = true;
            currentPower = getMaxPower() * EFFICIENCY_RATION;
        }
    }

    @Override
    public void switchOff() {
        System.out.printf(DRILL_IS_SWITCH_OFF_TEXT, this);
        isSwitchedOn = false;
        currentPower = 0;
    }
}
