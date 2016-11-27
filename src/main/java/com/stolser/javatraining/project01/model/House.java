package com.stolser.javatraining.project01.model;

import com.google.common.base.Preconditions;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * A container for appliances.
 */
public class House {
    private static final Logger LOGGER = LoggerFactory.getLogger(House.class);
    private static final String ALL_APPLIANCES_ON_TEXT = "All appliances have been switched on.";
    private static final String ALL_APPLIANCES_OFF_TEXT = "All appliances have been switched off.";
    private static final String SWITCHING_ON_APPLIANCES_RANDOMLY_TEXT = "Switching on appliances randomly...";
    /**
     * All the appliances in this house.
     */
    private List<ElectricalAppliance> appliances = new ArrayList<>();

    private House() {}

    /**
     * @param appliances a group of appliances to be added to this house
     */
    public void addAppliances(List<ElectricalAppliance> appliances) {
        Preconditions.checkNotNull("appliances cannot be null.", appliances);
        this.appliances.addAll(appliances);
    }

    /**
     * Switches on all the appliances in this house.
     */
    public void switchAllAppliancesOn() {
        appliances.forEach(ElectricalAppliance::switchOn);
        LOGGER.debug(ALL_APPLIANCES_ON_TEXT);
    }

    /**
     * Switches off all the appliances in this house.
     */
    public void switchAllAppliancesOff() {
        appliances.forEach(ElectricalAppliance::switchOff);
        LOGGER.debug(ALL_APPLIANCES_OFF_TEXT);
    }

    /**
     * Randomly switches on some of the appliances in this house.
     */
    public void switchRandomlyAppliancesOn() {
        Random random = new Random();

        LOGGER.debug(SWITCHING_ON_APPLIANCES_RANDOMLY_TEXT);
        appliances.forEach(item -> {
            if (random.nextBoolean()) {
                item.switchOn();
            } else {
                item.switchOff();
            }
        });
    }

    /**
     * @return the total power being consumed by all the appliances in this house
     */
    public double getPowerConsumption() {
        return appliances.stream()
                .mapToDouble(ElectricalAppliance::getCurrentPower)
                .sum();
    }

    /**
     * A static factory method for getting a house without any appliances in it.
     * @return an instance of this class
     */
    public static House newEmptyHouse() {
        return new House();
    }
}
