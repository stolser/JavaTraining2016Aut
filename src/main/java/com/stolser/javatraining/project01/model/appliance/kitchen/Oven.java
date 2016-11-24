package com.stolser.javatraining.project01.model.appliance.kitchen;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a kitchen electrical oven.
 */
public class Oven extends AbstractElectricalAppliance {
    private static final Logger LOGGER = LoggerFactory.getLogger(Oven.class);
    /**
     * Affects the current input power of this electrical appliance.<br />
     * Is used during calculating the current power as a multiplication parameter along with the max power.
     */
    private static final double EFFICIENCY_RATION = 0.8;
    private static final String OVEN_IS_SWITCHED_ON_TEXT = "Oven (%s) is switched on.";
    private static final String OVEN_IS_SWITCHED_OFF_TEXT = "Oven (%s) is switched off.";

    public Oven(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        LOGGER.debug(String.format(OVEN_IS_SWITCHED_ON_TEXT, this));
        isSwitchedOn = true;
        currentPower = getMaxPower() * EFFICIENCY_RATION;
    }

    @Override
    public void switchOff() {
        LOGGER.debug(String.format(OVEN_IS_SWITCHED_OFF_TEXT, this));
        isSwitchedOn = false;
        currentPower = 0;
    }

    @Override
    public double getCurrentPower() {
        return getMaxPower() * EFFICIENCY_RATION;
    }
}
