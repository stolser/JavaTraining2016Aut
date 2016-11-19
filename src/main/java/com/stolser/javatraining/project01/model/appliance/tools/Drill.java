package com.stolser.javatraining.project01.model.appliance.tools;

import com.stolser.javatraining.project01.model.appliance.ApplianceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Drill extends ElectricalTool {
    private static final Logger LOGGER = LoggerFactory.getLogger(Drill.class);
    private static final double EFFICIENCY_RATION = 0.8;
    private static final String DRILL_WORKS_USING_ACCUMULATOR_TEXT = "Drill works using accumulator.";
    private static final String DRILL_WORKS_USING_ELECTRICITY_GRID_TEXT = "Drill (%s) works using " +
            "engine and electricity grid.";
    private static final String DRILL_IS_SWITCH_OFF_TEXT = "Drill (%s) is switch off";

    public Drill(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        if (accumulatorCanBeUsed()) {
            LOGGER.debug(DRILL_WORKS_USING_ACCUMULATOR_TEXT);
            isSwitchedOn = true;
            currentPower = getMaxPower() * EFFICIENCY_RATION;
        } else {
            LOGGER.debug(String.format(DRILL_WORKS_USING_ELECTRICITY_GRID_TEXT, this));
            isSwitchedOn = true;
            currentPower = getMaxPower() * EFFICIENCY_RATION;
        }
    }

    private boolean accumulatorCanBeUsed() {
        return accumulator.isOn() && (accumulator.getCurrentCharge() > 0);
    }

    @Override
    public void switchOff() {
        LOGGER.debug(String.format(DRILL_IS_SWITCH_OFF_TEXT, this));
        isSwitchedOn = false;
        currentPower = 0;
    }
}
