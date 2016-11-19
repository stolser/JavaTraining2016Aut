package com.stolser.javatraining.project01.model.appliance.kitchen;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a kitchen refrigerator.
 */
public class Fridge extends AbstractElectricalAppliance {
    private static final Logger LOGGER = LoggerFactory.getLogger(Fridge.class);
    /**
     * Affects the current input power of this electrical appliance.<br />
     * Is used during calculating the current power as a multiplication parameter along with the max power.
     */
    private static final double EFFICIENCY_RATION = 0.8;
    private static final String FRIDGE_IS_SWITCHED_ON_TEXT = "Fridge (%s) is switched on.";
    private static final String FRIDGE_IS_SWITCHED_OFF_TEXT = "Fridge (%s) is switched off.";

    public Fridge(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        LOGGER.debug(String.format(FRIDGE_IS_SWITCHED_ON_TEXT, this));
        isSwitchedOn = true;
        currentPower = getMaxPower() * EFFICIENCY_RATION;
    }

    @Override
    public void switchOff() {
        LOGGER.debug(String.format(FRIDGE_IS_SWITCHED_OFF_TEXT, this));
        isSwitchedOn = false;
        currentPower = 0;
    }
}
